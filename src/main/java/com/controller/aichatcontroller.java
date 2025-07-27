package com.controller;

import com.pojo.ChatContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.aichatservice;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping("/huanghe")
public class aichatcontroller {

    @Autowired
    public aichatservice aichatservice;
    @Autowired
    private com.controller.logincontroller logincontroller;

    @PostMapping("/aichat")
    public ResponseEntity<String> ai(@RequestBody ChatContent content) throws Exception {
        log.info("AI聊天请求: {}", content.getContent());
        // 1. 验证用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not authenticated");
        }
        String username = authentication.getName();
        // 处理新会话
        String sessionId = content.getSessionId();
        boolean isNewSession = false;
        log.info("sessionId: {}", sessionId);

        // 处理新会话
        if (sessionId == null || sessionId.isEmpty()) {
            log.info("创建新会话");
            sessionId = aichatservice.createNewSession(username, content.getContent());
            isNewSession = true;
        } else if (!aichatservice.validateSession(sessionId, username)) {
            return ResponseEntity.status(403).body("Invalid session");
        }

        String url = "https://api.siliconflow.cn/v1/chat/completions";

        // 使用Jackson构建JSON请求体
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestJson = objectMapper.createObjectNode();

        // 设置基本参数
        requestJson.put("model", "deepseek-ai/DeepSeek-V3"); // 使用官方文档中的模型
        requestJson.put("stream", false);

        requestJson.putNull("stop"); // 正确设置为null
        requestJson.put("temperature", 0.7);
        requestJson.put("top_p", 0.7);
        requestJson.put("top_k", 50);
        requestJson.put("frequency_penalty", 0.5);
        requestJson.put("n", 1);

        // 添加消息
        ArrayNode messagesArray = requestJson.putArray("messages");
        ObjectNode messageObject = messagesArray.addObject();
        messageObject.put("role", "user");
        messageObject.put("content", content.getContent()); // 内容会被自动正确转义

        // 设置响应格式
        ObjectNode responseFormat = requestJson.putObject("response_format");
        responseFormat.put("type", "text");

        // 如果需要tools功能，可以取消注释以下代码
        /*
        ArrayNode toolsArray = requestJson.putArray("tools");
        ObjectNode toolObject = toolsArray.addObject();
        toolObject.put("type", "function");
        ObjectNode functionObject = toolObject.putObject("function");
        functionObject.put("description", "实际描述");
        functionObject.put("name", "实际函数名");
        functionObject.putObject("parameters");
        functionObject.put("strict", false);
        */

        // 转换为字符串
        String json = objectMapper.writeValueAsString(requestJson);
        log.info("完整请求体: {}", json);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer sk-mxixsjtkntvqmdmnmxaxowoocfbcjopiurctdlrwcyabytci")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            // 同步发送请求
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.error("API请求失败，状态码: {}, 响应内容: {}", response.statusCode(), response.body());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to get response from AI service: " + response.body());
            }

            // 解析响应
            JSONObject responseBody = new JSONObject(response.body());
            String modelResponse = responseBody.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
            log.info("AI响应内容: {}", modelResponse);

            // 创建响应JSON
            JSONObject responseJson = new JSONObject();

            // 保存到数据库
            int res = aichatservice.AddChatHistory(username, modelResponse, content.getContent(), sessionId);
            if (res == 2) {
                log.info("成功保存用户 {} 的聊天记录", username);
            } else {
                log.warn("保存用户 {} 的聊天记录失败", username);
            }

            // 返回响应
            responseJson.put("sessionId", sessionId);
            responseJson.put("isNewSession", isNewSession);
            responseJson.put("response", modelResponse);

            return ResponseEntity.ok(responseJson.toString());

        } catch (Exception e) {
            log.error("处理AI聊天请求时出错", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("处理请求时出错: " + e.getMessage());
        }
    }
}
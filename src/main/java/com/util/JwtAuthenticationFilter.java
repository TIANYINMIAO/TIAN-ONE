package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.config.Codes.SECRET_KEY;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("doFilterInternal 被调用"); // 添加此行调试
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorizationHeader); // 添加此行调试
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)  // 设置签名密钥，密钥需要和生成 JWT 时的密钥一致
                        .build()
                        .parseClaimsJws(token)  // 解析 JWT
                        .getBody();  // 获取 JWT 的 Body 部分
                String username = claims.getSubject(); // 从 JWT 的 subject 中获取用户名
                System.out.printf("用户名: %s\n", username);
                // 创建认证对象并将其注入上下文
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, null); // 使用用户名作为 Principal
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 也可以将用户信息存入请求属性中，以便控制器中直接访问
                request.setAttribute("username", username);
                System.out.println("请求路径: " + request.getRequestURI());
                System.out.println("Authorization Header: " + authorizationHeader);
                System.out.println("当前用户认证信息: " + SecurityContextHolder.getContext().getAuthentication());

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } //else {
//            System.out.println("没有 Authorization 头部或格式不正确");
//        }

        filterChain.doFilter(request, response);
    }

}

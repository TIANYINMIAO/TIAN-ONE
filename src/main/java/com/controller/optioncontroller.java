package com.controller;

import com.config.Codes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pojo.option;
import com.service.optionservice;
import com.result.Result;
@RestController
@Slf4j
@RequestMapping("/huanghe")
public class optioncontroller {
    /**
     * 问题互动
     * @return
     */
    @Autowired
    private optionservice optionservice;
    @PostMapping("/option")
    public Result<String> quession(@RequestBody  option option){
        log.info("用户选择了"+option.getOptionId()+"选项");
        log.info("用户选择了"+option.getQuestionId()+"题目");
        //option = optionservice.getanswer(option);
        log.info("返回==={}",option);
        if( optionservice.getanswer(option)!=0){
            log.info("用户回答正确");
            return Result.success(Codes.SUCCESS_2);
        }
        else{
            log.info("用户回答错误");
            return Result.error(Codes.ERROR_12);
        }
    }
}

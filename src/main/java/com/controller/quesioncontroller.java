package com.controller;

import com.pojo.chat;
import com.pojo.question;
import com.service.getidservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.result.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.service.questionservice;
@RestController
@Slf4j
@RequestMapping("/huanghe")
public class quesioncontroller {

    @Autowired
    private questionservice questionservice;
    @Autowired
    private com.service.getidservice getidservice;
    @GetMapping("/question")
    public Result<List<question>> question(){
        /**
         * 查询题目
         */
        log.info("查询题目");
//        //生成随机五个数字，用来查询题目（不包含0）
//
//        List<Integer> numbers = new ArrayList<>();
//        for (int i = 1; i < 10; i++) {
//            numbers.add(i);
//        }
//// 2. 使用 Collections.shuffle() 随机打乱列表
//        Collections.shuffle(numbers);
//// 3. 取出前 5 个元素

        //1.查询数据库，返回主键id
        List<Long> allphotosIds = getidservice.findAllquestionIds();
        //2.随机获取5个主键id
        // 随机排序
        Collections.shuffle(allphotosIds);
        // 获取前5个随机id
        List<Long> randomIds = allphotosIds.subList(0, Math.min(5, allphotosIds.size()));
        List<Integer> result = new ArrayList<>();
        for (Long id : randomIds) {
            result.add(id.intValue());
        }



        // List<Integer> result = numbers.subList(0, 5);



        log.info("查询到的题目是"+result);
        return Result.success(questionservice.getanswer(result));
    }
}

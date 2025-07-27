package com.controller;

import com.pojo.photo;
import com.result.Result;
import com.service.getidservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.doenloadservice;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/huanghe")
public class downloadcontroller {
    /**
     * 自动下载照片
     * @return
     */
    @Autowired
    private doenloadservice downloadservice;
    @Autowired
    private getidservice getidservice;
    @GetMapping("/download")
    public Result download() {
        // TODO: 实现自动下载照片的逻辑
        //随机下载一张图片
        //1.查询数据库，返回主键id
        List<Long> allphotosIds = getidservice.findAllphotosIds();
        //2.随机获取5个主键id
        // 随机排序
        Collections.shuffle(allphotosIds);
        // 获取前5个随机id
        List<Long> randomIds = allphotosIds.subList(0, Math.min(5, allphotosIds.size()));

        // 选择一个随机id进行下载
        // 选择一个随机id进行下载
        int index = (int) (Math.random() * allphotosIds.size());
        int randomNumber = allphotosIds.get(index).intValue();

        photo photo = downloadservice.download(randomNumber);


        log.info("第{}张图片下载成功", randomNumber);
        log.info("下载成功:{}" + photo.getOssUrl());
        return Result.success(photo.getOssUrl());
    }
}

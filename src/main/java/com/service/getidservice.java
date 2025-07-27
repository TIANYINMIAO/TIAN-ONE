package com.service;

import java.util.List;

public interface getidservice {
    /**
     * 获取主键id
     * @param
     * @return
     */
    //获取照片的主键id
    List<Long> findAllphotosIds();

    /**
     * 获取问题主键id
     * @return
     */
    List<Long> findAllquestionIds();
}

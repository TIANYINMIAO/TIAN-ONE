package com.service.impl;

import com.service.getidservice;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.getidmapper;
import java.util.List;
@Service
public class getidserviceimpl implements getidservice {
    @Autowired
    private getidmapper getidmapper;
    @Override
    public List<Long> findAllphotosIds() {

        return getidmapper.findAllphotosIds();
    }

    @Override
    public List<Long> findAllquestionIds() {

        return getidmapper.findAllquestionIds();
    }
}

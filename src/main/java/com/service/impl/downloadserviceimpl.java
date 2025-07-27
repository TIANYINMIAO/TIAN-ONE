package com.service.impl;

import com.pojo.photo;
import com.service.doenloadservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.downloadmapper;

@Service
public class downloadserviceimpl implements doenloadservice {

    @Autowired
    private downloadmapper downloadmapper;
    @Override
    public photo download(int randomNumber) {

        return downloadmapper.download(randomNumber);
    }
}

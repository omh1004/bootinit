package com.bs.basicboot.model.service;

import com.bs.basicboot.model.dto.Demo;

import java.util.List;

public interface DemoService {

    List<Demo> findAll();
    int saveDemo(Demo d);

}

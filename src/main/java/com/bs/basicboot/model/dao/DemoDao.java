package com.bs.basicboot.model.dao;

import com.bs.basicboot.model.dto.Demo;
import com.bs.basicboot.model.service.DemoService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface DemoDao {
    public List<Demo> findAll(SqlSession session);
    public int saveDemo(Demo d);
}

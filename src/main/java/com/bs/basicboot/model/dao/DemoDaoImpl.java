package com.bs.basicboot.model.dao;

import com.bs.basicboot.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoDaoImpl implements DemoDao {
    @Override
    public List<Demo> findAll(SqlSession session) {
        return session.selectList("demo.findAll");
    }

    @Override
    public int saveDemo(Demo d) {
        return 0;
    }
}

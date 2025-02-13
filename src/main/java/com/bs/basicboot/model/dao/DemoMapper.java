package com.bs.basicboot.model.dao;

import com.bs.basicboot.model.dto.Demo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoMapper {
//    @Results(id = "",value =
//            {
//                    @Results(property=,column=)
//            })
    @Select("select * from demo")
    List<Demo> findAll();

    @Insert("insert into demo values (#{devName},#{devAge},#{devAge},#{devLang})")
    public int insertDemo(Demo m);
}

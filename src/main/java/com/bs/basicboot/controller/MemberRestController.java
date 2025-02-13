package com.bs.basicboot.controller;

import com.bs.basicboot.model.dto.Member;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController//@Controller + @ResponseBody
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final Validator validator;

    @PostMapping
    //ResponseEntity는 스프링부트에서 응답객체를 컨트롤 할 수 있도록 제공하는 객체이다.
    public ResponseEntity saveMember(@RequestBody Member m){
       List<Map<String,String>>  result =  validator.validate(m).stream().map(cons->{
           return Map.of("msg",cons.getMessage()
                   ,"property",cons.getPropertyPath().toString());
        }).toList();

       if(result.size()>0){
           //유효성 검사 실패
           return ResponseEntity.badRequest().body(result);
       }else{
           //DB저장하기
            return ResponseEntity.ok(m);
       }
    }
}

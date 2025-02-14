package com.bs.basicboot.controller;

import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.service.JpaMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthentiCateController {

    private final JpaMemberService service;
    @PostMapping("login.do")
    public ResponseEntity<Map> login(
//            String userId,String password){
            @RequestBody JpaMember m){
        try {
            String token = service.loginService(m.getUserId(), m.getPassword());
            return ResponseEntity.ok().body(Map.of("token",token));
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().build();
        }



    }



}

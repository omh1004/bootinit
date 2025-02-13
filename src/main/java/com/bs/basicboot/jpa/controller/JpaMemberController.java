package com.bs.basicboot.jpa.controller;


import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.service.JpaMemberService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/jpa/member")
@RequiredArgsConstructor
public class JpaMemberController {

    private final JpaMemberService service;
    private final Validator validator;

    @PostMapping
    public ResponseEntity saveMember(@RequestBody JpaMember member){
            boolean result = service.addMember(member);
            if(result){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
    }

}

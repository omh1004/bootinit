package com.bs.basicboot.jpa.controller;

import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.service.JpaMemberService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa/member")
public class JpaMemberController {
    private final JpaMemberService service;
    private final Validator validator;

    @PostMapping
    public ResponseEntity saveMember(@RequestBody JpaMember member){

        boolean result=service.addMember(member);
        if(result){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity member(){
        List<JpaMember> members=service.getMembers();
        if(members.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(members);
        }
    }


    @GetMapping("/{no}")
    public ResponseEntity memberByNo(@PathVariable Long no){
        try {
            JpaMember findMember = service.getMemberByNo(no);
            return ResponseEntity.ok().body(findMember);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity updateMember(@RequestBody JpaMember m){
        boolean result=service.updateMember(m);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{no}")
    public ResponseEntity deleteMember(@PathVariable Long no){
        try {
//            JpaMember findM = service.getMemberByNo(no);
//            boolean result=service.deleteMember(findM);
            boolean result=service.deleteMember(no);
            if(result){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity<JpaMember> searchUserId(String userId){
        return ResponseEntity.ok().body(service.getMemberById(userId));
    }

    @GetMapping("/age")
    public ResponseEntity searchAge(Integer age){
        return ResponseEntity.ok().body(service.getMemberByAge(age));
    }


}

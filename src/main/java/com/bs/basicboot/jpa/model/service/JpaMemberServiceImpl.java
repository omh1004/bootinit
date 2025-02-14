package com.bs.basicboot.jpa.model.service;

import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.entity.JpaMemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JpaMemberServiceImpl implements JpaMemberService{

    private final JpaMemberRepository repository;

    @Override
    public boolean addMember(JpaMember m) {
        JpaMemberEntity entity=JpaMemberEntity.fromJpaMember(m);
        JpaMemberEntity result=repository.save(entity);
        return result.getMemberNo()!=null;
    }

    @Override
    public boolean updateMember(JpaMember m) {
        try{
            repository.save(JpaMemberEntity.fromJpaMember(m));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean deleteMember(JpaMember m) {
        try {
            repository.delete(JpaMemberEntity.fromJpaMember(m));
            return true;
        }catch(RuntimeException e){
            return false;
        }
    }

    @Override
    public List<JpaMember> getMembers() {

        return repository.findAll().stream()
                .map(entity->entity.toJpaMember()).toList();
    }

    @Override
    public JpaMember getMemberById(String userId) {
        return repository.findByUserId(userId).orElse(new JpaMemberEntity()).toJpaMember();
    }

    @Override
    public List<JpaMember> searchMember(Map param) {
        return List.of();
    }

    @Override
    public JpaMember getMemberByNo(Long memberNo) {
        return repository.findById(memberNo).orElseThrow().toJpaMember();
//        return repository.findById(memberNo).orElse(new JpaMemberEntity()).toJpaMember();
    }

    @Override
    public boolean deleteMember(Long m) {
        try {
            repository.deleteById(m);
            return true;
        }catch(RuntimeException e){
            return false;
        }
    }

    @Override
    public List<JpaMember> getMemberByAge(Integer age) {
        return repository.findByAgeGreaterThan(age)
                .stream()
                .map(entity-> entity.toJpaMember())
                .toList();
    }
}

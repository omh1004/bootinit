package com.bs.basicboot.common.token;

import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.entity.JpaMemberEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenUtils {
    private String issuer = "bsyoo";//생성자를 설정하는 값
    //토큰을 생성하는데 사용하는 key
    private SecretKey key = Jwts.SIG.HS512.key().build();

    //토큰생성시에 인증관련 기능구현하는 값
    private final JpaMemberRepository repository;

    //토큰을 만드는 메소드제공
    public String generateToken(JpaMember m){
        return createToken(m,Duration.ofDays(5));
    }

    //토큰을 생성하여 반환하는 메소드
    //저장할 사용자 정보와 토큰 유효기간을 외부에서 설정할 수 있게 매개변수로 처리
    private String createToken(JpaMember m, Duration exp){
        // 토큰생성시의 기간을 정해주기 위함
        Date limit = new Date(new Date().getTime()+exp.toMillis());
        return Jwts.builder()
                .header().add(Map.of("type","jwt")).and()
                .issuer(this.issuer)//생성자 설정
                .signWith(this.key)//토큰생성키
                .expiration(limit)// 유효기간
                .claims(Map.of("id",m.getUserId(),"memberNo",m.getMemberNo()))
                .compact();
    }




    //토큰으로 인증을 처리하는 메소드
    public Authentication getAuthentication(String token){
        String userId = getUserId(token);
        JpaMember member = repository.findByUserId(userId).orElseThrow( ()->{
            throw new BadCredentialsException("인증실패");
                }).toJpaMember();
        return new UsernamePasswordAuthenticationToken(member,member.getPassword(),
                    member.getAuthorities());
    }
    //토큰의 유효성을 확인하는 메소드
    public boolean validToToken(String token){

        try {
            Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    //토큰에 clims에 저장된 id값을 가져오는 메소드
    public String getUserId(String token){
        Claims climes = getClaims(token);
        return climes.get("id",String.class);
    }
    //토큰의 caims를 가져오는 메소드
    public Claims getClaims(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

}

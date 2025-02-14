package com.bs.basicboot.jpa.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.stream.XMLInputFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaMember implements UserDetails {
    private Long memberNo;
    @NotEmpty
    @Size(min=4, message="4글자이상 입력하세요")
    private String userId;
    @Pattern(regexp="(?=.*?[0-9])(?=.*?[a-zA-Z]).{5,}"
            ,message = "비밀번호 규칙에 위배됩니다. 숫자 영문자 5글자로 설정 ")
    private String password;
    @Size(min=2, message="이름은 한글자 이상이여야함니다.")
    private String name;
    @PastOrPresent(message = "생일은 과거여야해요")
    private LocalDate birthday;
    @Future(message = "미래여야함")
    private LocalDate reservationDay;
    @Positive//양수
    @Min(value=19,message = "성인만 가능")
    private Integer age;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(userId.equals("admin"))
            authorities.add(new SimpleGrantedAuthority(("admin")));
        authorities.add(new SimpleGrantedAuthority("user"));
        return authorities;

    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

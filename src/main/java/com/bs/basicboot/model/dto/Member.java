package com.bs.basicboot.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private Long memberNo;
    @NotEmpty
    @Size(min=4, message="4글자 이상 입력하세요")
    private String userId;
    @Pattern(regexp="(?=.*?[0-9])(?=.*?[a-zA-Z]).{5,}"
    ,message = "비밀번호 규칙에 위배됩니다. 숫자 영문자 5글자로 설정 ")
    private String password;

    @PastOrPresent(message = "생년월인은 과거로 설정해야합니다.")
    private LocalDate birthday;

    @Min(value=19,message="성인만 가입가능합니다.")
    private Integer age;
}

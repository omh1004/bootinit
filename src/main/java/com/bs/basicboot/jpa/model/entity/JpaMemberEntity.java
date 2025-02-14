package com.bs.basicboot.jpa.model.entity;


import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.model.dto.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seqjpamemberno",sequenceName = "seq_jpa_member_no",allocationSize = 1)
public class JpaMemberEntity {
    @Id
    @GeneratedValue(generator = "seqjpamemberno",strategy = GenerationType.SEQUENCE)

    private Long memberNo;
    @Column(unique = true , nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    private String name;
    private LocalDate birthday;
    private LocalDate reservationDay;
    private Integer age;



    public JpaMember toJpaMember(){
        return JpaMember.builder()
                .memberNo(memberNo)
                .userId(userId)
                .password(password)
                .name(name)
                .birthday(birthday)
                .reservationDay(reservationDay)
                .age(age)
                .build();
    }
    public static JpaMemberEntity fromJpaMember(JpaMember m){
        return JpaMemberEntity.builder()
                .memberNo(m.getMemberNo())
                .userId(m.getUserId())
                .password(m.getPassword())
                .name(m.getName())
                .birthday(m.getBirthday())
                .reservationDay(m.getReservationDay())
                .age(m.getAge())
                .build();
    }




}

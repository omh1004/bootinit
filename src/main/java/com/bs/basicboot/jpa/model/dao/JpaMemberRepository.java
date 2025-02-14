package com.bs.basicboot.jpa.model.dao;

import com.bs.basicboot.jpa.model.entity.JpaMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface JpaMemberRepository
        extends JpaRepository<JpaMemberEntity,Long> {
    //쿼리메소드 선언하기
    //메소드명 : findBy필드명[연산자][OrderBy필드명ASC||DESC](...)
    Optional<JpaMemberEntity> findByUserId(String userId);
//    select m from JpaMemberEntity m where m.userId=:userId

    List<JpaMemberEntity> findByAgeGreaterThan(Integer age);

    //아이디와 나이를 조회하는 메소드
    //아이디는 포함, 나이는 이상인 조건
    List<JpaMemberEntity> findByUserIdContainingAndAgeGreaterThan(String id, Integer age);

    //생년월일이 이전인 회원을 조회
    List<JpaMemberEntity> findByBirthdayLessThan (LocalDate birthDay);

    //반환값을 Stream설정할 수 있음
    //like 문은 패턴과 같이 들어와야한다.
    Stream<JpaMemberEntity> findByUserNameLike(String userName);

    List<JpaMemberEntity> deleteByReservationDayBefore(LocalDate res);

    //직접 JPQL구문 작성하기

    @Query("select m from JpaMemberEntity m")
    List<JpaMemberEntity> selectMemberAll();

    @Query("select m from JpaMemberEntity m where m.age>=:age and m.userId like :userId")
    List<JpaMemberEntity> selectMemberByAgeAndUserId(Integer age, String userId);

    @Query(value = "select * from JPA_MEMBER_ENTITY",nativeQuery = true)
    List<JpaMemberEntity> selectNativeQuery();



}

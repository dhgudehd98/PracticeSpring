package com.sh.loginbyrestcontroller.requestTest;


import com.sh.loginbyrestcontroller.dao.MemberMapper;
import com.sh.loginbyrestcontroller.dto.Member;
import groovy.util.logging.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberTest {

    @Autowired
    private MemberMapper memberMapper;


    @Test
    @DisplayName("중복된 아이디 검사 ")
    void test1() {
       //given
        String email = "ohd7150@nate.com";
        //when
        Member member = memberMapper.duplicateId(email);
        //then
        System.out.println(member);
    }

    @Test
    @DisplayName("아이디가 데이터베이스에 등록되어있는지 확인")
    void test2() {
        //given
        int memberid = 0;
        String id = "아시발좀 되라1";
        String name = "오형동";
        String password = "1234";
        //when

        Member member = new Member(memberid, id, password, name, LocalDateTime.now());

        int result = memberMapper.registMember(member);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("")
    void test3() {
        //given
        int id=3;
        Member member = memberMapper.findMemberById(id);
        System.out.println(member);
        //when
        //then

    }
}
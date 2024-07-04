package com.sh.demo;

import com.sh.demo.login.dao.LoginMapper;
import com.sh.demo.login.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {
    private LoginMapper loginMapper;

    @Autowired
    public DemoApplicationTests(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Test
    void test1() {
        String id = "ohd7150";
        String password = "tjffl123";

        MemberDto memberDto = loginMapper.findByInformation(id, password);

        System.out.println(memberDto);

        Assertions.assertThat(memberDto).isNotNull();
    }

    @Test
    @DisplayName("아이디 있는지 확인 ")
    void test2() {
        //given
        String id = "ohd7150";
        Optional<MemberDto> optionalLoginDto = loginMapper.duplicateId(id);
        System.out.println(optionalLoginDto);
        Boolean bool = optionalLoginDto.isPresent();
        String str = String.valueOf(bool);
        System.out.println(str);
        //when
        //then
        Assertions.assertThat(optionalLoginDto.isPresent()).isTrue();

    }

    @Test
    @DisplayName("아이디가 데이터베이스에 등록되어있는지 확인")
    void test3() {
        //given
        String id = "dhgudehd";
        String password = "1234";
        //when
        int result = loginMapper.registerMember(id, password);
        //then
        Assertions.assertThat(result).isEqualTo(1);
    }

}

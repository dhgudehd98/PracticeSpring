package com.sh.demo;

import com.sh.demo.login.dao.LoginMapper;
import com.sh.demo.login.dto.LoginDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        LoginDto loginDto = loginMapper.findByInformation(id, password);

        System.out.println(loginDto);

        Assertions.assertThat(loginDto).isNotNull();
    }

}

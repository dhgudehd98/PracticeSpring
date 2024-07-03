package com.sh.demo.login;

import com.sh.demo.login.dao.LoginMapper;
import com.sh.demo.login.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;

    public LoginDto findByInformation(String id, String password) {
        return loginMapper.findByInformation(id, password);
    }
}
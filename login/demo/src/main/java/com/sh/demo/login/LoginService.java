package com.sh.demo.login;

import com.sh.demo.login.dao.LoginMapper;
import com.sh.demo.login.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = Exception.class
)
public class LoginService {
    private final LoginMapper loginMapper;

    public MemberDto findByInformation(String id, String password) {
        return loginMapper.findByInformation(id, password);
    }

    public Boolean duplicateId(String id) {
        Optional<MemberDto> optionalLoginDto = loginMapper.duplicateId(id);
        Boolean b = optionalLoginDto.isPresent();
        log.debug(id);
        log.debug("{}",b);
        return b;
    }

    @Transactional
    public int registerMember(String id, String password) {
       return loginMapper.registerMember(id, password);
    }
}
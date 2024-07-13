package com.sh.loginbyrestcontroller.service;

import com.sh.loginbyrestcontroller.dao.MemberMapper;
import com.sh.loginbyrestcontroller.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {


    private final MemberMapper memberMapper;
    public Optional<Member> duplicateId(String email) {
        Member member = memberMapper.duplicateId(email);
        return Optional.ofNullable(member);
    }

    public int registMember(String id, String password,String name) {
        int memberid = 0;
        Member member = new Member(memberid, id, password, name, LocalDateTime.now());
       return memberMapper.registMember(member);
    }
}
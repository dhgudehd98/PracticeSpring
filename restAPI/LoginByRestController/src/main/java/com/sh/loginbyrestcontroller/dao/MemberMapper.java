package com.sh.loginbyrestcontroller.dao;

import com.sh.loginbyrestcontroller.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    Member duplicateId(String email);

    int registMember(Member member);

    Member findMemberById(int id);
}
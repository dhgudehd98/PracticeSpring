package com.sh.demo.login.dao;

import com.sh.demo.login.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginMapper {
    MemberDto findByInformation(String id, String password);

    Optional<MemberDto> duplicateId(String id);

    int registerMember(String id, String password);
}

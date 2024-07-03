package com.sh.demo.login.dao;

import com.sh.demo.login.dto.LoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    LoginDto findByInformation(String id, String password);
}

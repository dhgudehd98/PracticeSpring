package com.sh.loginbyrestcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class RestResponse<T>
{
    private Integer code; //http 상태 코드
    private HttpStatus httpStatus;
    private String message;
    private String redirectUrl;
    private T data; // 데이터값
}
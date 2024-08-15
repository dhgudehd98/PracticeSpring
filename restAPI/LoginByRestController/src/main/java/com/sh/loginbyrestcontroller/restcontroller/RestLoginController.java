package com.sh.loginbyrestcontroller.restcontroller;

import com.sh.loginbyrestcontroller.dto.Member;
import com.sh.loginbyrestcontroller.dto.Message;
import com.sh.loginbyrestcontroller.dto.RestResponse;
import com.sh.loginbyrestcontroller.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestLoginController {

    private final MemberService memberService;

    // id 조회
    @GetMapping("/member")
    public ResponseEntity<RestResponse<Object>> duplicateId(@RequestParam String email) {
        Optional<Member> member = memberService.duplicateId(email);
        Member member2 = member.orElse(null);
        log.debug("membere : {}", member2);
//        log.debug("{}",member.get());
        RestResponse<Object> restResponse;
        //member가 존재한다 -> 이미 등록된 아이디 : 즉 중복된 아이디
        //member가 존재하지 않는다 -> 등록되지 않은 아이디 : 중복되지 않는 아이디
        if (member.isPresent()) { // 아이디가 이미 존재하면 -> 중복된 아이디
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.FOUND.getStatus())
                    .httpStatus(HttpStatus.OK)
                    .data(member.get()) // 중복됨을 나타내는 데이터 반환
                    .build();
        } else {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(Message.NOT_FOUND.getStatus())
                    .httpStatus(HttpStatus.OK)
                    .redirectUrl(null)
                    .data(null) // 중복되지 않음을 나타내는 null 반환
                    .build();

        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    // member 등록
    @PostMapping("/member")
    public ResponseEntity<RestResponse<Object>> registMember(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name) {
        RestResponse<Object> restResponse ;
       int result =  memberService.registMember(email, password,name);
        log.debug("Member가 등록되어있나 ? result : {}", result);
        Optional<Member> member = memberService.duplicateId(email);
        if (result == 1) { // resgister
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.SUCCESS.getStatus())
                    .httpStatus(HttpStatus.OK)
                    .data(member)
                    .build();
        }
        else{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(Message.FAIL.getStatus())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .data(null)
                    .build();
        }
        restResponse.setRedirectUrl("/");
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }
}

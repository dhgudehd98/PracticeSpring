package com.sh.loginbyrestcontroller.dto;

import lombok.Data;


public enum Message {
    NOT_FOUND("찾으시는 정보가 존재하지 않습니다"), FOUND("찾으시는 정보를 찾았습니다."),
    SUCCESS("회원 정보가 성공적으로 저장되었습니다."), FAIL("회원 정보 저장을 실패했습니다.");

    private String status;

    Message(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

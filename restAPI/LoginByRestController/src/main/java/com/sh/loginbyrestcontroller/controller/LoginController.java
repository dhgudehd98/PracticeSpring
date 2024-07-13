package com.sh.loginbyrestcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/regist")
    public String regist() {
        return "regist";
    }
}
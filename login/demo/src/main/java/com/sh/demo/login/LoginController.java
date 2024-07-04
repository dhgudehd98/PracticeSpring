package com.sh.demo.login;

import com.sh.demo.login.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor


public class LoginController {
    private final LoginService loginService;


    @GetMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, Model model, HttpServletRequest request) {
        MemberDto memberDto = loginService.findByInformation(id, password);
        if (memberDto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", memberDto);
            model.addAttribute("user", memberDto);
        } else {
            log.debug("로그인 정보가 잘못되었습니다.");
            return "redirect:/";
        }
        return "result";
    }

    @GetMapping("/registerButton")
    public String register() {
        return "register";
    }


    @GetMapping(path = "/duplicateCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String checkDuplicate(@RequestParam String id) {
        Boolean bool = loginService.duplicateId(id);
        String status = String.valueOf(bool);
        return status;
    }

    @PostMapping("/registerMember")
    public String registerMember(@RequestParam String id, @RequestParam String password) {
        loginService.registerMember(id, password);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String redirect() {
        return "success";
    }

    @GetMapping("/register")
    public String filter() {
        return "register";
    }
}
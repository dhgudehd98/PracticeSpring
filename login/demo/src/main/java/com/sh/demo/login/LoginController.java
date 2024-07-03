package com.sh.demo.login;

import com.sh.demo.login.dto.LoginDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;


    @PostMapping("/login")
    public String login(@RequestParam String id , @RequestParam String password, Model model, HttpServletRequest request) {
        LoginDto loginDto = loginService.findByInformation(id, password);
        if (loginDto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginDto);
            model.addAttribute("user",loginDto);
        } else {
            log.debug("로그인 정보가 잘못되었습니다.");
            return "redirect: /";
        }
        return "result";
    }

}
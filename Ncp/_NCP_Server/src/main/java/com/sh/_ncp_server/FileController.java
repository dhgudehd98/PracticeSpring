package com.sh._ncp_server;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Controller
 // 기본 경로를 설정합니다.
public class FileController {


    private final FileName fileName ;

    @GetMapping("/")
    public String index(Model model) {
        List<String> image = fileName.name();
        model.addAttribute("imgUrl", image);
        return "index";
    }
}

package com.yeonjjj.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // mustache starter에 의해 컨트롤러에서 문자열 반환 시 앞에 경로, 뒤에 파일 확장자가 붙어서 처리됨
        // src/main/resources/templates/index.mustache
        return "index";
    }

}

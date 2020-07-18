package com.yeonjjj.springboot.web;

import com.yeonjjj.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {      // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        model.addAttribute("posts", postsService.findAllDesc());
        // mustache starter에 의해 컨트롤러에서 문자열 반환 시 앞에 경로, 뒤에 파일 확장자가 붙어서 처리됨
        // src/main/resources/templates/index.mustache
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}

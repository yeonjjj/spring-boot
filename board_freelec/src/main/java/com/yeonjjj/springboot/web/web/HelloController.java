package com.yeonjjj.springboot.web.web;

import com.yeonjjj.springboot.web.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController                 // JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {

    @GetMapping("/hello")       // HTTP Method Get 요청을 받을 수 있는 API
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }

}

package com.yeonjjj.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)                        // JUnit에 내장된 실행자 외에 다른 실행자(SpringRunner) 실행
@WebMvcTest(controllers = HelloController.class)    // 여러 스프링 어노테이션 중, 스프링 MVC에 집중
                                                    // @Controller, @ControllerAdvice 등을 사용할 수 있음
public class HelloControllerTest {

    @Autowired                  // Bean 주입
    private MockMvc mvc;        // 웹 API 테스트, 스프링 MVC 테스트의 시작점

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))            // /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk())             // HTTP Header의 Status 검증(200, 404, 500)
                .andExpect(content().string(hello));    // 응답 본문의 내용 검증
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name)   // param: API 테스트에 사용될 요청 파라미터 설정
                .param("amount", String.valueOf(amount)))             //        String 값만 허용됨
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))          // JSON 응답값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}

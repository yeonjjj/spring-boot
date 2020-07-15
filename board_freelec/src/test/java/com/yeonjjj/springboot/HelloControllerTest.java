package com.yeonjjj.springboot;

import com.yeonjjj.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

}

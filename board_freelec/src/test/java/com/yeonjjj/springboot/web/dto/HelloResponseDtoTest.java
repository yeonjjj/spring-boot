package com.yeonjjj.springboot.web.dto;

import com.yeonjjj.springboot.web.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;   // assertJ가 JUnit의 assertThat보다 편리

public class HelloResponseDtoTest {

    @Test
    public void testLombok() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);      // assertThat: 검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getAmount()).isEqualTo(amount);  // isEqualTo: 동등 비교 메소드
    }

}

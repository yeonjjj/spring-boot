package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Jsr310JpaConverters 적용하기
@EnableJpaAuditing
@EntityScan(    //애플리케이션이 실행될 때 basePackages로 지정된 패키지 하위에서 JPA 엔티티 검색
                //JPA 엔티티: @Entity 어노테이션이 설정된 도메인 클래스
        basePackageClasses = {Jsr310Converters.class},
        basePackages = {"board"}
)

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}


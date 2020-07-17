package com.yeonjjj.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Entity 클래스에는 절대 Setter 메소드를 만들지 않는다.
@Getter             // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity             // 테이블과 링크될 클래스임을 나타냄, 클래스(카멜케이스) → 테이블(언더스코어 네이밍)
public class Posts {

    @Id                                                     // 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK의 생성규칙, IDENTITY: auto_increment
    private Long id;

    // @Column: 테이블의 칼럼, 굳이 선언하지 않아도 됨, 기본값 외에 추가 변경 옵션이 있으면 사용
    @Column(length = 500, nullable = false)                 // 문자열의 경우 VARCHAR(255)가 기본값 → 사이즈를 500으로 변경
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)    // 문자열의 경우 VARCHAR(255)가 기본값 → 타입을 TEXT로 변경
    private String content;

    private String author;

    @Builder    // 빌더 패턴 클래스 생성, 어느 필드에 어떤 값을 채울지 명확해 짐, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}

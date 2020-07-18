package com.yeonjjj.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest         // H2 데이터베이스 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After      // JUnit에서 단위 테스트가 끝날 떄마다 수행되는 메소드 지정, 테스트 간 데이터 침범을 막기 위해 주로 사용
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndFindPosts() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // postsRepository.save: 테이블 posts에 (id 없으면)insert/(id값 있으면)update 쿼리 실행
        postsRepository.save(Posts.builder().title(title).content(content).author("yeonjjj").build());

        //when
        List<Posts> postsList = postsRepository.findAll();      // 테이블 posts에 있는 모든 데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void addBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2020, 7, 17, 0, 0, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}

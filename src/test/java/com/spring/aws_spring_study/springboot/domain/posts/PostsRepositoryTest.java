package com.spring.aws_spring_study.springboot.domain.posts;


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
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("qkrrldus93g@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2023, 5, 27, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> CreatedDate = "+posts.getCreateDate()+" , modifiedDate = "+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

    // 앞으로 추가될 엔티티들은 더이상 등록일/수정일로 고민할 필요가 없다. BaseTimeEntity만 상속받으면 자동으로 해결되기 때문

}

/*
    1. @After
     - Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
     - 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다
     - 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 싱핼 시 실패할 수 있음(그래서 deleteAll() 사용으로 초기화)

    2. postsRepository.save
     - 테이블 posts에 insert/update 쿼리를 실행한다
     - id값이 있다면 update가, 없다면 insert 쿼리를 실행

    3. postsRepository.findAll
     - 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

     별다른 설정 없이 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행해준다

     만약 실제로 실행된 쿼리는 어던 형태인지 확인 하기 위해서는 application.yml에서 설정을 바꿔주면 가능

*/
package com.spring.aws_spring_study.springboot.web.dto;

import com.spring.aws_spring_study.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    /*
        여기서 Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성하였다.
        하지만, 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.

        Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이다.
        Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경이 되는데 화면 변경은 사소한 기능 변경

        사소한 화면 변경을 위해서 DB 스키마(Entity 클래스를 변경 하는 것은 너무 큰 변경이다)

        수많은 서비스 클래스, 비즈니스 로직들이 Entity 클래스를 기준으로 동작
        Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만, Request와 Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요하다

        View Layer와 DB Layer의 역할 분리를 철저하게 하는게 좋다.(즉 DTO와 Entity는 별도로 구분해서 사용하자)
        추가로 DTO에는 toEntity라는 메소드를 만들어서 Entity로 변환해주는 메서드를 추가해주자(이건 내 생각 => 틀렸음)

        DTO > Entity로 가는 경우에는 toEntity
        Entity > DTO로 가는 경우에는 Dto 생성자 만들때 Entity를 파라미터로 받아서 데이터 넣어서 생성하는 방식으로 진행
     */
}

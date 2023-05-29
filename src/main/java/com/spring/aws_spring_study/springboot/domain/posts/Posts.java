package com.spring.aws_spring_study.springboot.domain.posts;


import com.spring.aws_spring_study.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}

/*
1. @Entity
 - 테이블과 링크될 클래스임을 나타낸다
 - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다
 ex) SalesManager.java  ->  sales_manager [table]

2. @Id
 - 해당 테이블의 PK 필드를 나타낸다

3. @GeneratedValue
 - PK 생성 규칙을 나타낸다
 - 스프링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다

4. @Column
 - 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다
 - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다
 - 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶은 경우 사용한다

5. @Builder
 - 해당 클래스의 빌더 패턴 클래스를 생성
 - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함

!KEY POINT
1. 웬만하면 Entity의 PK는 Long타입의 Auto_increment를 추천한다
2. Entity 클래스에는 Setter 메소드 사용 X
 - 필요시 명확한 의도를 나타낼 수 있는 메소드를 추가해야한다
 */

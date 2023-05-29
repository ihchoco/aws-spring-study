package com.spring.aws_spring_study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
/*
Posts(Entity)클래스 생성 이후 Posts클래스로 DB접근이 가능하게 해주는 JpaRepository를 생성해주어야 한다

보통 Mybais에서는 DAO라고 불리는 DB Layer 접근자이다

JPA에서는 Repository라고 부르며 인터페이스로 생성한다

사용법 : 인터페이스 생성명 extends JpaRepository<Entity 클래스명, ID타입>

@Repository를 추가할 필요도 없습니다. 하지만 주의할 점은 Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다는 점
(나중에 프로젝트가 커지면 도메인 패키지에 Entity, Repository는 함께 움직여야 한다)

 */


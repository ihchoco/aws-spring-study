package com.spring.aws_spring_study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication //어노테이션 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
public class Application { //앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}

//@SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트의 최상단에 위치

//1. SpringApplication.run()으로 내장 WAS 실행
//2. Jar파일로 패키징되어 있는 파일을 실행하기만 하면 어디서든 프로젝트 기동 가능
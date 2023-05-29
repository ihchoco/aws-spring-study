package com.spring.aws_spring_study.springboot.web;

import com.spring.aws_spring_study.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
/*
KEY POINT
1. @RequestParam
 - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션 (쿼리 파라미터)

2. @RequestBody
 - 외부에서 API로 넘길때 HTTP Body에서 넘어오는 데이터(POST 방식)

3. @PathVariable
 - URL(localhost:8088/users/{id}) 에서 id 부분
 */
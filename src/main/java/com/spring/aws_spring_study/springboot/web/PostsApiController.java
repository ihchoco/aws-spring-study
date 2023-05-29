package com.spring.aws_spring_study.springboot.web;

import com.spring.aws_spring_study.springboot.domain.posts.PostsRepository;
import com.spring.aws_spring_study.springboot.service.posts.PostsService;
import com.spring.aws_spring_study.springboot.web.dto.PostsResponseDto;
import com.spring.aws_spring_study.springboot.web.dto.PostsSaveRequestDto;
import com.spring.aws_spring_study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
/*
    스프링을 써봤던 분들이라면 Controller와 Service에서 @Autowired가 없는 것이 어색하게 느껴질텐데. 스프링에서는 Bean을 주입하는 방식이 아래와 같음
    1. @Autowired
    2. setter
    3. 생성자

    이 중 가장 권장하는 방식은 생성자로 주입받는 방식
    그럼 여기서 생성자는 어디 있을까?(ANSWER : @RequiredArgsConstructor)
*/
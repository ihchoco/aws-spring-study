package com.spring.aws_spring_study.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//테스트 진행할 때 JUnt에 내장된 실행자 외에 다른 실행자를 실행시킨다(JUnit과 스프링부트 테스트 중간 역할)
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
    //웹 API를 테스트할 때 사용한다.(스프링MVC 테스트의 시작점이자 API 테스트 도구)

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // < (1)
                .andExpect(status().isOk()) // < (2)
                .andExpect(content().string(hello)); // < (3)
        //mvc.perform의 결과를 검증(응답 본문의 내용을 검증)
    }

    /* ************************************************************ */
    /* 여기 부분 새로 추가 */
    @Test
    public void helloResponseDTO가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name) // < (4)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // < (5)
                .andExpect(jsonPath("$.amount", is(amount)));
    }
    /* ************************************************************ */
/*
  여기서 사용하는 코드는 Junit의 assertThat이 아닌 assertj의 assertThat
  assertj 역시 Junit에서 자동으로 라이브러리 등록을 해주기 때문에 Junit이 등록되어 있으면 사용 가능

  1. mvc.perform(get("/hello"))
   - MockMvc를 통해 /hello 주소로 HTTP GET 요청 수행

  2. .andExpect(status().isOk())
   - 우리가 흔히 알고있는 200, 404, 500 등의 상태를 검증(mvc.perform의 결과를 검증)

  3. .andExpect(content().string(hello));
   - mvc.perform의 결과를 검증(응답 본문의 내용을 검증)

  4. param
   - API 테스트할 때 사용될 요청 파라미터를 설정
   - 단, 값은 String만 허용된다
   - 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능하다

  5. jsonPath
   - JSON 응답값을 필드별로 검증할 수 있는 메소드이다
   - $를 기준으로 필드명을 명시한다
   - 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증한다
*/
}
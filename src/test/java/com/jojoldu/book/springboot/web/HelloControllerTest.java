package com.jojoldu.book.springboot.web;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class) //테스트실행시 JUnit에 내장된 SpringRunner라는 스프링실행자를 실행함. 즉 스프링부트 테스트와 JUnit 사이에 연결자 역할.
@WebMvcTest(controllers = HelloController.class) // 여러 스프링테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션으로 @Controller, @ControllerAdvice는 사용할 수 있으나, @service, @Component, @Repository등은 사용할수 없음
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입받음.
    private MockMvc mvc; //웹API 테스트할 때 사용. 스프링 MVC테스트의 시작점. 이클래스로 HTTP GET, POST 등에 대한 API테스트를 할 수 있음.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET요청을 함. 체이닝이 지원되어 아래와 같이 검증기능을 이어서 선언가능.
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증. HTTP Header의 Status(200,404,500)를 검증.
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증. 응답본문의 내용을 검증. "HelloController의 리턴값 hello1"이 맞는지 검증함.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "name";
        int amount = 1000;

        mvc.perform(
          get("/hello/dto")
          .param("name", name) // api 테스트 할 때 사용될 요청파라미터를 설정함. 단 String만 허용.
          .param("amount",String.valueOf(amount))
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name))) //jsonPath: JSON응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시. name검증하기 위해 $.name으로 검증.
        .andExpect(jsonPath("$.amount",is(amount)));
    }
}

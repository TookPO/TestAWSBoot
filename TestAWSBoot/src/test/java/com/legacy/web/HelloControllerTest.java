package com.legacy.web;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@RunWith(SpringRunner.class) // JUnit 이외의 실행자를 실행
							 // 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@WebMvcTest(controllers = HelloController.class) // Web(MVC)에 집중할 수 있는 어노테이션
public class HelloControllerTest {
	
	@ Autowired
	private MockMvc mvc; // 이 클래스를 통해 get, post 등에 대한 API 테스트 가능
	
	@Test
	public void hello가_리턴된다() throws Exception {
		String hello = "hello";
		
		mvc.perform(get("/hello")) // /hello get 방식 요청
				.andExpect(status().isOk()) // mvc.perform의 결과를 검증 200인지 아닌지 검증
				.andExpect((ResultMatcher) content().string(hello)); // controller가 hello를 리턴하는지 검증
	}
	
}

package com.legacy.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.legacy.posts.dao.PostsRepository;
import com.legacy.posts.domain.Posts;
import com.legacy.web.domain.PostsUpdateRequestDto;
import com.legacy.web.dto.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostsRepository postsRepository;
	
	// 작업이 끝나면 초기화
	@After
	public void tearDown() throws Exception {
		postsRepository.deleteAll();
	}
	
	@Test
	public void Posts_등록된다() throws Exception {
		// givien
		String title = "title";
		String content = "content";
		PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
				.title(title)
				.content(content)
				.author("tomcat@io.coi")
				.build();
		
		String url = "http://localhost:"+port+"/api/v1/posts";
		
		// when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postsSaveRequestDto, Long.class);
		
		// than
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		assertThat(responseEntity.getBody()).isGreaterThan(0L);
		
		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);
		
	}
	
	@Test
	public void Posts_수정된다() throws Exception {
		// given
		Posts savedPosts = postsRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());
		
		Long updateId = savedPosts.getId();
		String experctedTitle = "title2";
		String expectedContent = "content2";
		
		PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
				.title(experctedTitle)
				.content(expectedContent)
				.build();
		
		String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;
		
		HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto)();
		
		// when
		
	}
}

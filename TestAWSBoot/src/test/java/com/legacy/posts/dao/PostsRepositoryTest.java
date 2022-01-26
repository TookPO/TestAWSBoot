package com.legacy.posts.dao;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.legacy.posts.domain.Posts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	
	@Autowired
	PostsRepository postsRepository;
	
	@After // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
		   // 혹시라도 데이터가 남을수도 있으시 삭제
	public void cleaup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void 게시글저장_불러오기() {
		// given
		String title = "테스트 게시글";
		String content = "테스트 본문";
		
		// insert 또는 update
		postsRepository.save(Posts.builder()
											.title(title)
											.content(content)
											.author("JOJO@naver.coi")
											.build());
		
		// when 
		List<Posts> postsList = postsRepository.findAll(); // 전체 조회
		
		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}
}

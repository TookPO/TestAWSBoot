package com.legacy.web.domain;

import com.legacy.posts.domain.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {
	
	private Long id;
	private String title;
	private String content;
	private String author;
	
	// 생성자
	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
	}
}

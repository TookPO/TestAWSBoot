package com.legacy.posts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	private String author;
	
	@Builder
	public Posts(Long id, String title, String content, String author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

}

package com.legacy.web.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.legacy.posts.dao.PostsRepository;
import com.legacy.posts.domain.Posts;
import com.legacy.web.domain.PostsResponseDto;
import com.legacy.web.domain.PostsUpdateRequestDto;
import com.legacy.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	
	
	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		// TODO Auto-generated method stub
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		posts.update(requestDto.getTitle(), requestDto.getContent());
		
		return id;
	}

	public PostsResponseDto findById(Long id) {
		// TODO Auto-generated method stub
		Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
		
		
		return new PostsResponseDto(entity);
	}
	
	
}

package com.legacy.posts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacy.posts.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}

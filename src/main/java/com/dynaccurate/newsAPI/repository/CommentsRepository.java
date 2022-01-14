package com.dynaccurate.newsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dynaccurate.newsAPI.model.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long>  {

}

package com.dynaccurate.newsAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dynaccurate.newsAPI.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

	public List<News> findAllByTitleContainingIgnoreCase(String title);
	
	public List<News> findAllByTagsContainingIgnoreCase(String tags);
}

package com.dynaccurate.newsAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dynaccurate.newsAPI.model.News;
import com.dynaccurate.newsAPI.repository.NewsRepository;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {

	@Autowired
	private NewsRepository repository;

	@GetMapping
	public ResponseEntity<List<News>> getAll() {
		List<News> listObject = repository.findAll();

		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<News> getById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title_news}")
	public ResponseEntity<List<News>> searchTitle(@PathVariable(value = "title_news") String title) {
		List<News> listObject = repository.findAllByTitleContainingIgnoreCase(title);
		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}	

	@PostMapping("/register")
	public ResponseEntity<News> registerNews(@RequestBody News news) {
		return ResponseEntity.status(201).body(repository.save(news));

	}

	@PutMapping("/update")
	public ResponseEntity<News> updateNews(@Valid @RequestBody News news) {
		return repository.findById(news.getId()).map(resp -> {
			resp.setContent(news.getContent());
			resp.setAuthor(news.getAuthor());
			resp.setTitle(news.getTitle());
			resp.setDate(news.getDate());
			resp.setTags(news.getTags());
			repository.save(resp);
			return ResponseEntity.status(201).body(resp);
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "News not found");
		});

	}

	@DeleteMapping("/delete/{id_news}")
	public ResponseEntity<Object> deleteNews(@PathVariable(value = "id_news") Long idNews) {
		return repository.findById(idNews).map(resp -> {
			repository.deleteById(idNews);
			return ResponseEntity.status(204).build();
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
		});
	}

}

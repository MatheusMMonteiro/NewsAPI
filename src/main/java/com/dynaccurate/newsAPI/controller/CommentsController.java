package com.dynaccurate.newsAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynaccurate.newsAPI.model.Comments;
import com.dynaccurate.newsAPI.repository.CommentsRepository;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentsController {
	
	@Autowired
	private CommentsRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Comments>> getAll(){
		List<Comments> listObject = repository.findAll();

		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Comments> registerComment(@RequestBody Comments comment){
		return ResponseEntity.status(201).body(repository.save(comment));
		}

}

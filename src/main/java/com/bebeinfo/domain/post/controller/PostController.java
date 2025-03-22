package com.bebeinfo.domain.post.controller;

import com.bebeinfo.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import com.bebeinfo.domain.post.service.PostService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public String getAllPosts(Model model) {
		postService.findAllPosts();
		return "posts/list";
	}

	@GetMapping("/{id}")
	public String getPostDetail(@PathVariable Long id, Model model) {
		return "posts/detail";
	}

	@PostMapping
	public String createPost(Post post) {
		return "posts/create";
	}

	@PutMapping("/{id}")
	public String updatePost(@PathVariable Long id, Post post) {
		return "posts/update";
	}

	@DeleteMapping("/{id}")
	public String deletePost(@PathVariable Long id) {
		return "posts/delete";
	}

	@GetMapping
	public String searchPosts() {
		return "posts/search";
	}

}

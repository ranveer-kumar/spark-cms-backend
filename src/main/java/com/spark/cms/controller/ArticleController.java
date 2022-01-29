package com.spark.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spark.cms.model.Article;
import com.spark.cms.service.ArticleService;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@PostMapping("/api/v1/spark/cms/article")
	public Article saveArticle(@RequestBody Article article) {
		return articleService.saveArticle(article);
	}
	
	@GetMapping("/api/v1/spark/cms/article/{id}")
	public Article getArticle(@PathVariable String id) {
		return articleService.getArticleByID(id).get();
	}

}

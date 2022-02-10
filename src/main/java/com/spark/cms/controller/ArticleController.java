package com.spark.cms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spark.cms.exception.NotFoundException;
import com.spark.cms.model.Article;
import com.spark.cms.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Deprecated(since = "pre-beta-0.1", forRemoval = true)
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@PostMapping("/api/v1/spark/cms/articles")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Article saveArticle(@RequestBody Article article) {
		log.info("start saving the article");
		return articleService.saveArticle(article);
	}
	
	@GetMapping("/api/v1/spark/cms/articles/{id}")
	public Article getArticle(@PathVariable Long articleId) throws Exception{
		
		Optional<Article> articleOptional = articleService.getArticleByID(articleId);
		if(articleOptional.isPresent()) {
			return articleOptional.get();
		}
		throw new NotFoundException("Article not found for id: "+articleId);
	}

}

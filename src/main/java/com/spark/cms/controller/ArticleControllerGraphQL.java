package com.spark.cms.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.spark.cms.exception.NotFoundException;
import com.spark.cms.model.Article;
import com.spark.cms.model.ArticleInput;
import com.spark.cms.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleControllerGraphQL {

	@Autowired
	ArticleService articleService;

//	@SchemaMapping(typeName = "Query", field = "getArticleById")
	@QueryMapping
	public Article getArticleById(@Argument Long articleId) throws NotFoundException{
		Optional<Article> articleOptional = articleService.getArticleByID(articleId);
		if(articleOptional.isPresent()) {
			return articleOptional.get();
		}
		throw new NotFoundException("Article not found for id: "+articleId);
	}
	
	@SchemaMapping(typeName = "Query", field = "allArticles")
	public Page<Article> getAllArticles(@Argument Integer page, @Argument Integer size){
		log.info("Before getAllArticles with page {}  and size {}", page, size);
		return articleService.getAllArticles(PageRequest.of(page != null ? page : 0, size != null ? size : 10));
	}
	
	@SchemaMapping(typeName = "Mutation",field = "deleteArticle")
	public void deleteArticle(@Argument Long articleId) {
		log.info("Deleting article with id {}",articleId);
		articleService.deleteArticle(articleId);
		log.info("Deleted article with id {}",articleId);
	}
	
//	@SchemaMapping (typeName = "Mutation", field = "saveArticle")
//	@ResponseStatus(code = HttpStatus.CREATED)
	@MutationMapping
	public Article saveArticle(@Argument ArticleInput articleInput) {
		Article article = new Article();
		log.info(articleInput.getMetaData().toString());
		BeanUtils.copyProperties(articleInput, article);
		log.info("start saving the article");
//		return ResponseEntity.status(HttpStatus.CREATED).body(articleService.saveArticle(article));
		return articleService.saveArticle(article);
	}
//	
//	
//	@PostMapping("/v1/libraryevent")
//	public ResponseEntity<LibraryEvent> postLibraryEvent (@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException{
//		libraryEventProducer.sendLibraryEvent(libraryEvent);
//		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
//	}
	
	
	@MutationMapping
	public Article updateArticle(@Argument ArticleInput articleInput) {
		Article article = new Article();
		log.info(articleInput.getMetaData().toString());
		BeanUtils.copyProperties(articleInput, article);
		log.info("start saving the article");
		return articleService.saveArticle(article);
	}
}

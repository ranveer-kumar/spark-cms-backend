package com.spark.cms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spark.cms.exception.NotFoundException;
import com.spark.cms.model.Article;
import com.spark.cms.model.ArticleInput;
import com.spark.cms.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleControllerGraphQL {

//	@Autowired
//	CustomerRepository customerRepository;
	

	@Autowired
	ArticleService articleService;

	@SchemaMapping(typeName = "Query", field = "getArticleById")
	public Article getArticle(@Argument Long articleId) throws NotFoundException{
		Optional<Article> articleOptional = articleService.getArticleByID(articleId);
		if(articleOptional.isPresent()) {
			return articleOptional.get();
		}
		throw new NotFoundException("Article not found for id: "+articleId);
	}
	
	@SchemaMapping(typeName = "Query", field = "allArticles")
	public List<Article> articles(){
		 return articleService.getAllArticles();
	}
	
	@SchemaMapping(typeName = "Mutation",field = "deleteArticle")
	public void deleteArticle(@Argument Long articleId) {
		log.info("Deleting article with id {}",articleId);
		articleService.deleteArticle(articleId);
		log.info("Deleted article with id {}",articleId);
	}
	
//	@SchemaMapping (typeName = "Mutation", field = "saveArticle")
	@ResponseStatus(code = HttpStatus.CREATED)
	@MutationMapping
	public Article saveArticle(@Argument ArticleInput articleInput) {
		Article article = new Article();
		log.info(articleInput.getMetaData().toString());
		BeanUtils.copyProperties(articleInput, article);
		log.info("start saving the article");
		return articleService.saveArticle(article);
	}
	
	@MutationMapping
	public Article updateArticle(@Argument ArticleInput articleInput) {
		Article article = new Article();
		log.info(articleInput.getMetaData().toString());
		BeanUtils.copyProperties(articleInput, article);
		log.info("start saving the article");
		return articleService.saveArticle(article);
	}
}

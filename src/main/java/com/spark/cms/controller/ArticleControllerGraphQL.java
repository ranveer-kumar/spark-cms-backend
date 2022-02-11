package com.spark.cms.controller;

import java.security.SecureRandom;
import java.util.Optional;

import javax.validation.Valid;

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
import com.spark.cms.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleControllerGraphQL {

	@Autowired
	ArticleService articleService;
	
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
	
	@MutationMapping
	public Article saveArticle(@Argument @Valid ArticleInput articleInput) {
		Article article = new Article();
		log.info(articleInput.getMetaData().toString());
		BeanUtils.copyProperties(articleInput, article);
		article.setId((long)new SecureRandom().nextInt(100000));
		article.getMetaData().setUrl(CommonUtils.urlGenerator(article.getTitle(), article.getId()));
		
		log.info("start saving the article");
		return articleService.saveArticle(article);
	}
	
	@MutationMapping
	public Article updateArticle(@Argument @Valid Article articleInput) throws NotFoundException {
		
		Optional<Article> existingArticle = articleService.getArticleByID(articleInput.getId());
		if(existingArticle.isEmpty()) {
			log.error("Article id {} not found", articleInput.getId());
			throw new NotFoundException("Article not found");
		}
		Article article = new Article();
		log.info(articleInput.getMetaData().toString());
		BeanUtils.copyProperties(articleInput, article);
		
		article.setCreatedBy(existingArticle.get().getCreatedBy());

		
		article.setCreatedDate(existingArticle.get().getCreatedDate());
		article.setFirstPublishedDate(existingArticle.get().getFirstPublishedDate());
		article.setExpireAt(existingArticle.get().getExpireAt());
		article.setExpiryDate(existingArticle.get().getExpiryDate());
		article.setScheduledAt(existingArticle.get().getScheduledAt());
		article.setScheduledDate(existingArticle.get().getScheduledDate());
		article.setChangesPublished(Boolean.TRUE);
		
		article.getMetaData().setUrl(existingArticle.get().getMetaData().getUrl());
		article.getMetaData().setCanonicalUrl(existingArticle.get().getMetaData().getCanonicalUrl());

		log.info("Start updating the article id {}", article.getId());
		return articleService.saveArticle(article);
	}
}

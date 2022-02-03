package com.spark.cms.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spark.cms.model.Article;

public interface ArticleService {
	
	public Article saveArticle(Article article);
	public Optional<Article> getArticleByID(Long articleId);
	public Page<Article> getAllArticles(Pageable pageable);
	public void deleteArticle(Long articleId);
}

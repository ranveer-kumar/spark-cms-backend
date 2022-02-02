package com.spark.cms.service;

import java.util.List;
import java.util.Optional;

import com.spark.cms.model.Article;

public interface ArticleService {
	
	public Article saveArticle(Article article);
	public Optional<Article> getArticleByID(Long articleId);
	public List<Article> getAllArticles();
	public void deleteArticle(Long articleId);
}

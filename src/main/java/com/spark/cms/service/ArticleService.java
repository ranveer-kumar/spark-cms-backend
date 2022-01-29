package com.spark.cms.service;

import java.util.Optional;

import com.spark.cms.model.Article;

public interface ArticleService {
	
	public Article saveArticle(Article article);
	public Optional<Article> getArticleByID(String id);

}

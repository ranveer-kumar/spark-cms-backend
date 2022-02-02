package com.spark.cms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.cms.model.Article;
import com.spark.cms.repository.ArticleRepository;
import com.spark.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Optional<Article> getArticleByID(Long id) {
		// TODO Auto-generated method stub
		return articleRepository.findById(id);
	}

	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}
	
	@Override
	public void deleteArticle(Long articleId) {
		articleRepository.deleteById(articleId);
		
	}
}

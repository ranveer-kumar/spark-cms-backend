package com.spark.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spark.cms.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {

}

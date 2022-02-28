package com.spark.cms.resolver;


import java.security.SecureRandom;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spark.cms.exception.NotFoundException;
import com.spark.cms.model.Article;
import com.spark.cms.model.ArticleInput;
import com.spark.cms.service.ArticleService;
import com.spark.cms.util.CommonUtils;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ArticleMutationResolver implements GraphQLMutationResolver {
    @Autowired
    ArticleService articleService;

    public Article saveArticle(@Valid ArticleInput articleInput) {
        Article article = new Article();
        BeanUtils.copyProperties(articleInput, article);
        article.setId((long) new SecureRandom().nextInt(100000));
        article.getMetaData().setUrl(CommonUtils.urlGenerator(article.getTitle(), article.getId()));
        log.info("start saving the article");
        return articleService.saveArticle(article);
    }

    public Article updateArticle(@Valid ArticleInput articleInput) throws NotFoundException {

        Optional<Article> existingArticle = articleService.getArticleByID(articleInput.getId());
        if (existingArticle.isEmpty()) {
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
    public Boolean deleteArticle(Long articleId) throws NotFoundException {
        log.info("Deleting article with id {}", articleId);
        Optional<Article> articleOptional = articleService.getArticleByID(articleId);
        if (articleOptional.isPresent()) {
            articleService.deleteArticle(articleId);
            return true;
        }
        throw new NotFoundException("Article not found for id: " + articleId);

    }
}

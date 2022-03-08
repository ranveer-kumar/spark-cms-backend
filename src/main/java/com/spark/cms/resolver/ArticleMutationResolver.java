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

    private final ArticlePublisher articlePublisher;

    public ArticleMutationResolver(ArticlePublisher articlePublisher) {
        this.articlePublisher = articlePublisher;
    }
    public Article saveArticle(@Valid ArticleInput articleInput) {
        Article article = new Article();
        BeanUtils.copyProperties(articleInput, article);
        article.setId((long) new SecureRandom().nextInt(100000));
        article.getMetaData().setUrl(CommonUtils.urlGenerator(article.getTitle(), article.getId()));
        log.info("start saving the article");
        Article article1 = articleService.saveArticle(article);
        articlePublisher.publish(article1);
        return article1;
    }

    public Article updateArticle(@Valid ArticleInput articleInput) throws NotFoundException {

        Optional<Article> optionalArticle = articleService.getArticleByID(articleInput.getId());
        Article existingArticle = optionalArticle.orElseThrow(
                () -> new NotFoundException("Article cann't update as article with id: "+articleInput.getId()+" not found"));

        Article article = new Article();
        log.info(existingArticle.getMetaData().toString());
        BeanUtils.copyProperties(articleInput, article);

        article.setCreatedBy(existingArticle.getCreatedBy());

        article.setCreatedDate(existingArticle.getCreatedDate());
        article.setFirstPublishedDate(existingArticle.getFirstPublishedDate());
        article.setExpireAt(existingArticle.getExpireAt());
        article.setExpiryDate(existingArticle.getExpiryDate());
        article.setScheduledAt(existingArticle.getScheduledAt());
        article.setScheduledDate(existingArticle.getScheduledDate());
        article.setChangesPublished(Boolean.TRUE);

        article.getMetaData().setUrl(existingArticle.getMetaData().getUrl());
        article.getMetaData().setCanonicalUrl(existingArticle.getMetaData().getCanonicalUrl());

        log.info("Start updating the article id {}", article.getId());
        Article savedArticle = articleService.saveArticle(article);
        articlePublisher.publish(savedArticle);
        return savedArticle;
    }
    public Boolean deleteArticle(Long articleId) throws NotFoundException {
        log.info("Deleting article with id {}", articleId);
        Optional<Article> articleOptional = articleService.getArticleByID(articleId);
        Article article = articleOptional.orElseThrow(
                () -> new NotFoundException("Article cann't delete as article with id: "+articleId+" not found"));
        articleService.deleteArticle(articleId);
        article.setIsDeleted(true);
        articlePublisher.publish(article);

        return true;

    }
}

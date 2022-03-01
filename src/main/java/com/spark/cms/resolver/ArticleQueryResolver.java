package com.spark.cms.resolver;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.spark.cms.exception.NotFoundException;
import com.spark.cms.model.Article;
import com.spark.cms.service.ArticleService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ArticleQueryResolver implements GraphQLQueryResolver {

    @Autowired
    ArticleService articleService;

    public Article getArticleById(Long articleId) throws NotFoundException {

        Optional<Article> articleOptional = articleService.getArticleByID(articleId);
        Article article = articleOptional.orElseThrow(
                () -> new NotFoundException("Article not found for id: "+articleId));
        return articleOptional.get();

//        if (articleOptional.isPresent()) {
//            return articleOptional.get();
//        }
//        throw new NotFoundException("Article not found for id: " + articleId);
    }


    public Page<Article> getAllArticles(Integer page, Integer size, String sortBy,
                                        String sortDirection) {
        log.info("Before getAllArticles with page {}  and size {}", page, size);
        PageRequest pageRequest = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10,
                Sort.by(Sort.Direction.fromString(sortDirection),
                        sortBy));
        return articleService.getAllArticles(pageRequest);
    }



}

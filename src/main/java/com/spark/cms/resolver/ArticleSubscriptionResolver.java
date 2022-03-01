package com.spark.cms.resolver;


import com.spark.cms.model.Article;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;



@Component
public class ArticleSubscriptionResolver implements GraphQLSubscriptionResolver {


    private final ArticlePublisher articlePublisher;

    public ArticleSubscriptionResolver(ArticlePublisher articlePublisher) {
        this.articlePublisher = articlePublisher;
    }

    public Publisher<Article> recentArticle(){
        return articlePublisher.getRecentArticle();
    }

//    public Publisher<List<Article>> recentArticles(){
//        return articlePublisher.getRecentArticles();
//    }
}

package com.spark.cms.resolver;


import com.spark.cms.model.Article;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class ArticlePublisher {

    private final Sinks.Many<Article> processor;
//    private final Sinks.Many<List<Article>> processors;

    public ArticlePublisher() {
//        this.processors = Sinks.many().replay().all();
        this.processor = Sinks.many().replay().all();
//                this.processor = DirectProcessor
    }

    public Publisher<Article> getRecentArticle() {

        return  processor.asFlux();
    }

    //    public Publisher<List<Article>> getRecentArticles() {
//
//        return  List<processor.asFlux()>;
//    }
    public void publish(Article article) {

        processor.emitNext(article,Sinks.EmitFailureHandler.FAIL_FAST);
    }

//    public Publisher<List<Article>> getRecentArticles(List<Article> articles) {
//        processors.emitNext(articles, Sinks.EmitFailureHandler.FAIL_FAST);
//    }
}

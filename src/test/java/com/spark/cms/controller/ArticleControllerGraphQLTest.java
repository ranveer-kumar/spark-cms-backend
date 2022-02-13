package com.spark.cms.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.spark.cms.model.Article;
import com.spark.cms.service.ArticleService;

@GraphQlTest(ArticleControllerGraphQL.class)
class ArticleControllerGraphQLTest {

	@Autowired
	GraphQlTester graphQlTester;

	@MockBean
	ArticleService articleService;

	@Test
	void testGetArticleById_shouldReturnArticleId() {
		Optional<Article> optionalArticle = Optional.of(Article.builder().id(82851L).build());
		org.mockito.BDDMockito.given(this.articleService.getArticleByID(82851L)).willReturn(optionalArticle);
		String queryArticleById = "{" + "	getArticleById(articleId:82851)	{ " + "	id	" + "	}	" + "}";
		this.graphQlTester.query(queryArticleById).execute().path("getArticleById.id").entity(Long.class)
				.isEqualTo(82851L);
	}

	@Test
	void testGetArticleById_shouldThrowNotFoundError() {
		org.mockito.BDDMockito.given(this.articleService.getArticleByID(82851L)).willReturn(Optional.empty());
		String queryArticleById = "{" + "	getArticleById(articleId:82851)	{ " + "	id	" + "	}	" + "}";
		this.graphQlTester.query(queryArticleById).execute().errors();
	}

	@Test
	void testGetAllArticles_shouldReturnArticleList() {
		Page<Article> listArticle = new PageImpl<>(Stream.of(Article.builder().id(82851L).articleType("news").build(),
				Article.builder().id(82852L).articleType("news").build()).collect(Collectors.toList()));
		Page<Article> givenArticles = this.articleService.getAllArticles(PageRequest.of(0, 10));
		org.mockito.BDDMockito.given(givenArticles).willReturn(listArticle);
		String queryAllArticle = "{" + "	allArticles( page:0, size:10)	{ " + "	id	" + "	}	" + "}";
		this.graphQlTester.query(queryAllArticle).execute().path("allArticles[*].id").entityList(Long.class)
				.containsExactly(82851L, 82852L);
	}

	@Test
	void testGetAllArticles_withNoPageAndSize_shouldReturnArticles() {
		Page<Article> listArticle = new PageImpl<>(Stream.of(Article.builder().id(82851L).articleType("news").build(),
				Article.builder().id(82852L).articleType("news").build()).collect(Collectors.toList()));
		Page<Article> givenArticles = this.articleService.getAllArticles(PageRequest.of(0, 10));
		org.mockito.BDDMockito.given(givenArticles).willReturn(listArticle);
		String queryAllArticle = "{" + "	allArticles	{ " + "	id	" + "	}	" + "}";
		this.graphQlTester.query(queryAllArticle).execute().path("allArticles[*].id").entityList(Long.class)
				.containsExactly(82851L, 82852L);
	}

	@Test
	void testDeleteArticle() {
		Optional<Article> optionalArticle = Optional.of(Article.builder().id(82851L).build());
		org.mockito.BDDMockito.doNothing().when(this.articleService).deleteArticle(1L);
		this.articleService.deleteArticle(1L);
		String queryArticleById = "{" + "	deleteArticle(articleId:82851)" + "}";
		this.graphQlTester.query(queryArticleById).execute().equals(null);
	}

	@Test
	void testSaveArticle() {
	}

	@Test
	void testUpdateArticle() {
	}

}

package com.spark.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.spark.cms.model.Article;
import com.spark.cms.repository.ArticleRepository;


class ArticleServiceImplTest {

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@InjectMocks
	ArticleServiceImpl articleServiceImpl;

//	@Mock
//	Article article;
	
	@Mock
	ArticleRepository articleRepository;


	
	@Test
	void testSaveArticle() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetArticleByID() {
		Optional<Article> articleOptional = Optional.of(Article.builder().id(1L).articleType("news").title("Ttitle").domainId(1).build());
		when(articleServiceImpl.getArticleByID(1L)).thenReturn(articleOptional);
		
		Optional<Article> articleObj = articleServiceImpl.getArticleByID(1L);
		
		assertEquals("news", articleObj.get().getArticleType());
		assertEquals("Ttitle", articleObj.get().getTitle());
		assertEquals(1, articleObj.get().getDomainId());
		
	}

//	
//	@Override
//	public Page<Article> getAllArticles(Pageable pageable) {
//		log.debug("Before getAllArticles with pageable parameter");
//		return articleRepository.findAll(pageable);
//	}
//	
	
//	@Test
//	void testGetAllArticles() {
//		
//		Page<Article> listArticle;
//		Article article = new Article();
//		article.setId(1L);
//		article.setArticleType("news");
////		listArticle.toList().add(article);
//		
////		List<Article> articleList = Article.builder().id(1L).articleType("news").title("Ttitle").domainId(1).build());
//		when(articleServiceImpl.getAllArticles(PageRequest.of( 0, 10))).thenReturn(listArticle);
//		
//		Optional<Article> articleObj = articleServiceImpl.getArticleByID(1L);
//		
//		assertEquals("news", articleObj.get().getArticleType());
//		assertEquals("Ttitle", articleObj.get().getTitle());
//		assertEquals(1, articleObj.get().getDomainId());
//		
//	}

	@Test
	void testDeleteArticle() {
//		when(articleServiceImpl.deleteArticle(1L)).thenReturn(null);
		Mockito.doNothing().when(articleRepository).deleteById(1L);
		articleServiceImpl.deleteArticle(1L);
		Mockito.verify(articleRepository, Mockito.times(1)).deleteById(1L);
//		assertEquals(null, null);
	}
	


}

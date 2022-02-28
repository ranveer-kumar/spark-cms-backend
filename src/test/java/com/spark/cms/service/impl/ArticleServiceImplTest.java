//package com.spark.cms.service.impl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import com.spark.cms.model.Article;
//import com.spark.cms.model.ArticleType;
//import com.spark.cms.repository.ArticleRepository;
//
//class ArticleServiceImplTest {
//
//	@BeforeEach
//	public void init() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@InjectMocks
//	ArticleServiceImpl articleServiceImpl;
//
//	@Mock
//	ArticleRepository articleRepository;
//
//	@Test
//	void testSaveArticle() {
//		// set
//		Article article = new Article();
//		article.setId(1L);
//		article.setArticleType(ArticleType.ARTICLE);
//		when(articleRepository.save(article)).thenReturn(article);
//
//		// act
//		Article returnObj = articleServiceImpl.saveArticle(article);
//
//		// assert
//		assertNotNull(returnObj);
//		assertEquals("news", returnObj.getArticleType());
//	}
//
//	@Test
//	void testGetArticleByID() {
//		Optional<Article> articleOptional = Optional
//				.of(Article.builder().id(1L).articleType(ArticleType.ARTICLE).title("Ttitle").domainId(1).build());
//		when(articleRepository.findById(1L)).thenReturn(articleOptional);
//
//		Optional<Article> articleObj = articleServiceImpl.getArticleByID(1L);
//
//		assertTrue(articleObj.isPresent());
//		assertEquals("ARTICLE", articleObj.get().getArticleType());
//		assertEquals("Ttitle", articleObj.get().getTitle());
//		assertEquals(1, articleObj.get().getDomainId());
//
//	}
//
//	@Test
//	void testGetAllArticles() {
//
//		// set
//		Article article = Article.builder().id(1L).articleType(ArticleType.ARTICLE).build();
//		Page<Article> listArticle = new PageImpl<>(Stream.of(article).collect(Collectors.toList()));
//
//		when(articleRepository.findAll(PageRequest.of(0, 10))).thenReturn(listArticle);
//
//		// act
//		Page<Article> paginatedList = articleServiceImpl.getAllArticles(PageRequest.of(0, 10));
//
//		// assert
//		assertNotNull(paginatedList);
//		assertEquals(1, paginatedList.getTotalElements());
//		assertEquals(ArticleType.ARTICLE, paginatedList.get().findFirst().get().getArticleType());
//
//	}
//
//	@Test
//	void testDeleteArticle() {
//		// set
//		Mockito.doNothing().when(articleRepository).deleteById(1L);
//
//		// act
//		articleServiceImpl.deleteArticle(1L);
//
//		// assert
//		Mockito.verify(articleRepository, Mockito.times(1)).deleteById(1L);
//	}
//
//}

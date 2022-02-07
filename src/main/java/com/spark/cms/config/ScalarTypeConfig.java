//package com.spark.cms.config;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.util.Date;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import graphql.schema.Coercing;
//import graphql.schema.CoercingParseLiteralException;
//import graphql.schema.CoercingParseValueException;
//import graphql.schema.CoercingSerializeException;
//import graphql.schema.GraphQLScalarType;
//
//@Configuration
//public class ScalarTypeConfig {
//	
////	@Bean
////	public GraphQLScalarType typeDate() {
////		return GraphQLScalarType.newScalar().build();
////	}
////
////	@Bean
////	public GraphQLScalarType dateScalar() {
////		return GraphQLScalarType
////				.newScalar()
////				.build();
////	}
////				
//	
//	@Bean
//	public GraphQLScalarType dateScalar() {
//		return GraphQLScalarType.newScalar()
//				.name("Date")
//				.description("Custom Date Scalar Type")
//				.coercing(new Coercing<Date, String>() {
//
//					@Override
//					public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
//						return dataFetcherResult.toString();
//					}
//
//					// used for mutation publishedDate: $pubDate;  variables: { "pubDate" : "10-JAN-2022"}
//					@Override
//					public Date parseValue(Object input) throws CoercingParseValueException {
//						try {
//							return DateFormat.getInstance().parse((String)input);
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//						return null;
//					}
//
//					// used for mutation publishedDate: "22-JAN-2022" ==> convert to Date and assign to Book.publishedDate
//					@Override
//					public Date parseLiteral(Object input) throws CoercingParseLiteralException {
//						try {
//							return DateFormat.getInstance().parse((String)input);
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//						return null;
//					}
//					
//				}).build();
//	}
//}

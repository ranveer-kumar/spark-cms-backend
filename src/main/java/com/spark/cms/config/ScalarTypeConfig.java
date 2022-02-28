package com.spark.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class ScalarTypeConfig {
    @Bean
    public GraphQLScalarType date(){
        return ExtendedScalars.Date;
    }
    

	@Bean
	public GraphQLScalarType uploadScalar() {
	    return ApolloScalars.Upload;
	}
}

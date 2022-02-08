package com.spark.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;


//@Configuration
public class ValidationConfig {
	
	@Bean
	public ValidationSchemaWiring  validationSchemaWiring() {
		ValidationRules validationRules = ValidationRules.newValidationRules()
				.onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
				.build();
		return new ValidationSchemaWiring(validationRules);
	}
}
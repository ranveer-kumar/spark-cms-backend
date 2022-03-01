package com.spark.cms.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException implements GraphQLError {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;
	
	public NotFoundException(String message){
		super(message);
		this.message = message;
	}


	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		return null;
	}

	@Override
	public List<Object> getPath() {
		return GraphQLError.super.getPath();
	}

	@Override
	public Map<String, Object> toSpecification() {
		return GraphQLError.super.toSpecification();
	}

	@Override
	public Map<String, Object> getExtensions() {
		return GraphQLError.super.getExtensions();
	}
}

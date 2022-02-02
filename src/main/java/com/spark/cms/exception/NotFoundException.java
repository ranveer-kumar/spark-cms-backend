package com.spark.cms.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;
	
	public NotFoundException(String message){
		this.message = message;
	}
	
	
}

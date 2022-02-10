package com.spark.cms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image{
	 @JsonProperty("images") 
	 @Builder.Default
	 private List<Image> images = new ArrayList<>();
	 private String caption;
	 private String name;
	 private String imageCredit;
	 private String anchorTag;
	}
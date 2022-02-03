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
	 public List<Image> images = new ArrayList<>();
	 public String caption;
	 public String name;
	 public String imageCredit;
	 public String anchorTag;
	}
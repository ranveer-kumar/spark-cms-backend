package com.spark.cms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image{
	 @JsonProperty("images") 
	 public List<Image> images = new ArrayList<>();
	 public String caption;
	 public String name;
	 public String imageCredit;
	 public String anchorTag;
	}
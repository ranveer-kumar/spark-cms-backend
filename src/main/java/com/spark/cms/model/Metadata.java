package com.spark.cms.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metadata{
	 public String metaTitle;
	 public String metaDescription;
	 @JsonProperty("url") 
	 public String url;
	 public ArrayList<Object> authors;
	 public ArrayList<Object> keywords;
	 public String section;
	 public String subSection;
	 public int sectionId;
	 public long subSectionId;
	 public ArrayList<Object> secondarySections;
	 public ArrayList<Object> topic;
	 public String canonicalUrl;
	}
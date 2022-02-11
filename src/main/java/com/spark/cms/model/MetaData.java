package com.spark.cms.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaData {
	private String metaTitle;
	private String metaDescription;
	@JsonProperty("url")
	private String url;
	private ArrayList<String> authors;
	private ArrayList<String> keywords;
	private String section;
	private String subSection;
	private Integer sectionId;
	private Long subSectionId;
	private ArrayList<String> secondarySections;
	private ArrayList<String> topic;
	private String canonicalUrl;
}
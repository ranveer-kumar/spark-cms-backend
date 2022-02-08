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
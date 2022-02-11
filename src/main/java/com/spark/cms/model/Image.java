package com.spark.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image{
	 private String caption;
	 private String name;
	 private String imageCredit;
	 private String anchorTag;
	 private Boolean isDefault;
	}
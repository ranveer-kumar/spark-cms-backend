package com.spark.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video{
	 public String url;
	 public String body;
	 public String caption;
	 public String embedUrl;
	}

package com.spark.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeadMedia {
	private String mediaType;
	@Builder.Default
	private Image image = new Image();
	@Builder.Default
	private Video video = new Video();
}
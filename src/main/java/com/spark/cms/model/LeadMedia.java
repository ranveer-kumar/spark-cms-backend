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
	public String mediaType;
	@Builder.Default
	public Image image = new Image();
	@Builder.Default
	public Video video = new Video();
}
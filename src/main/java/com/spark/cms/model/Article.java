package com.spark.cms.model;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Article {
	@Id
	private Long id;
	@NotEmpty
	private String domainId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String headline;
	private String summary;
	@NotEmpty
	private String articleType;
	private String createdBy;
	private String createdDate;
	private String lastModifiedBy;
	private String lastModifiedByUserName;
	private String lastModifiedDate;
	private String lastPublishedBy;
	private String lastPublishedByUserName;
	private String lastPublishedDate;
	private String firstPublishedDate;
	@Builder.Default
	private MetaData metaData = new MetaData();
	@Builder.Default
	private Date scheduledDate = new Date();
	@Builder.Default
	private Date scheduledAt = new Date();
	@Builder.Default
	private Date scheduledBy = new Date();
	private int isDeleted;
	@Builder.Default
	private ArrayList<String> readByUsers = new ArrayList<>();
	@Builder.Default
	private Date expiryDate = new Date();
	@Builder.Default
	private Date expireAt = new Date();
	private boolean changesPublished;
	@Builder.Default
	private LeadMedia leadMedia = new LeadMedia();
}

package com.spark.cms.model;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Min;
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
	@Min(value=1, message = "Id must be positive number")
	private Long id;
	@Min(value = 1, message = "Value should be greater then 0")
	private Integer domainId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String headline;
	@NotEmpty
	private String summary;
	@NotEmpty
	private String articleType;
	private String createdBy;
	@Builder.Default
	private Date createdDate = new Date();
	private String lastModifiedBy;
	private String lastModifiedByUserName;
	@Builder.Default
	private Date lastModifiedDate = new Date();
	private String lastPublishedBy;
	private String lastPublishedByUserName;
	@Builder.Default
	private Date lastPublishedDate = new Date();
	@Builder.Default
	private Date firstPublishedDate = new Date();
	@Builder.Default
	private MetaData metaData = new MetaData();
	@Builder.Default
	private Date scheduledDate = new Date();
	private Date scheduledAt;
	private String scheduledBy;
	private Boolean isDeleted;
	@Builder.Default
	private ArrayList<String> readByUsers = new ArrayList<>();
	@Builder.Default
	private Date expiryDate = new Date();
	@Builder.Default
	private Date expireAt = new Date();
	
	@Builder.Default
	private Boolean changesPublished = false;
	@Builder.Default
	private LeadMedia leadMedia = new LeadMedia();
}

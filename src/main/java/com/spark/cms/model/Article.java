package com.spark.cms.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
	@Id
	private String id;
	private String domainId;
	private String title;
	private String headline;
	private String summary;
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
	private Metadata metadata = new Metadata();
	private Date scheduledDate = new Date();
	private Date scheduledAt = new Date();;
	private Date scheduledBy = new Date();;
	private int isDeleted;
	private ArrayList<String> readByUsers = new ArrayList<>();
	private Date expiryDate = new Date();;
	private Date expireAt = new Date();;
	private boolean changesPublished;
	private LeadMedia leadMedia = new LeadMedia();
}

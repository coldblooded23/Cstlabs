package com.cst.CstLabs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "videocount")
public class VideoCountEntity {
	
	@Id
	@Column (name = "videoid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long videoId;
	
	@Column( name = "emailid")
	private String emailId;
	
	@Column(name = "count")
	private Integer count; 
	
	@Column(name = "watched")
	private Boolean watched;
	
	@Column(name = "courseid")
	private Long  courseId;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	 
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Boolean getWatched() {
		return watched;
	}
	public void setWatched(Boolean watched) {
		this.watched = watched;
	}
	
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
	
	
	
	
	
	
	
	
	

}

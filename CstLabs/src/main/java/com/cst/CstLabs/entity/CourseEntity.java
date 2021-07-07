package com.cst.CstLabs.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Course")
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "courseid")
	private Long courseId;

	@Column(name = "coursename")
	private String courseName;

	@Column(name = "coursevideopath")
	private String courseVideoPath;

	@Column(name = "courseanswercount")
	private String courseAnswerCount;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "courseEntity",targetEntity = QuestionsEntity.class )
	List<QuestionsEntity> questionsEntity;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseVideoPath() {
		return courseVideoPath;
	}

	public void setCourseVideoPath(String courseVideoPath) {
		this.courseVideoPath = courseVideoPath;
	}

	public String getCourseAnswerCount() {
		return courseAnswerCount;
	}

	public void setCourseAnswerCount(String courseAnswerCount) {
		this.courseAnswerCount = courseAnswerCount;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public List<QuestionsEntity> getQuestionsEntity() {
		return questionsEntity;
	}

	public void setQuestionsEntity(List<QuestionsEntity> questionsEntity) {
		this.questionsEntity = questionsEntity;
	}
	
	
	
	

}

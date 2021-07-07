package com.cst.CstLabs.model;

import java.util.List;



public class CourseInfo {
	
	
	private Long courseId;

	private String courseName;


	private String courseVideoPath;


	private String courseAnswerCount;
	
	private Integer totalNoOFQuestions;
	
	
	List<QuestionsInfo> questionsInfo;



	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


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


	public List<QuestionsInfo> getQuestionsInfo() {
		return questionsInfo;
	}


	public void setQuestionsInfo(List<QuestionsInfo> questionsInfo) {
		this.questionsInfo = questionsInfo;
	}


	public Integer getTotalNoOFQuestions() {
		return totalNoOFQuestions;
	}


	public void setTotalNoOFQuestions(Integer totalNoOFQuestions) {
		this.totalNoOFQuestions = totalNoOFQuestions;
	}





	
	
	
	
	
	

}

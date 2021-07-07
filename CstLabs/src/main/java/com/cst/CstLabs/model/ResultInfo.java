package com.cst.CstLabs.model;


public class ResultInfo {

	private Integer resultId;

	private Integer userId;

	private Integer correct;

	private Integer incorrect;

	private String result;

	private String firstName;

	private String lastName;
	
	private Long  courseId;
	
	private Integer unAnswered;
	
	
	

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Integer getIncorrect() {
		return incorrect;
	}

	public void setIncorrect(Integer incorrect) {
		this.incorrect = incorrect;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getUnAnswered() {
		return unAnswered;
	}

	public void setUnAnswered(Integer unAnswered) {
		this.unAnswered = unAnswered;
	}
	
	
	
}

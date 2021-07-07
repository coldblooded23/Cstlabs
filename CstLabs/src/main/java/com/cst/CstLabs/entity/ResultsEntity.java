package com.cst.CstLabs.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "results")
public class ResultsEntity {
@Id
@Column(name = "resultid")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long	resultId;

@Column(name = "userid")
private Long userId;

@Column(name = "correct")
private Integer correct;

@Column(name = "incorrect")
private Integer incorrect;

@Column(name = "result")
private String result;

@Column(name = "dateon")
@CreationTimestamp
private Date dateOn;

@Column(name = "courseid")
private Long  courseId;






public Long getResultId() {
	return resultId;
}

public void setResultId(Long resultId) {
	this.resultId = resultId;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
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

public Long getCourseId() {
	return courseId;
}

public void setCourseId(Long courseId) {
	this.courseId = courseId;
}





}

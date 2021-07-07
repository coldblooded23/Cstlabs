package com.cst.CstLabs.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst.CstLabs.CstLabsApplication;
import com.cst.CstLabs.entity.CourseEntity;
import com.cst.CstLabs.entity.QuestionsEntity;
import com.cst.CstLabs.model.CourseInfo;
import com.cst.CstLabs.model.QuestionsInfo;
import com.cst.CstLabs.repository.CourseRepository;
import com.cst.CstLabs.repository.QuestionsRepository;
import com.cst.CstLabs.utils.CstLabsExpection;

@Service
@Transactional
public class QuestionsService {
	
	@Autowired
	QuestionsRepository questionsRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	public CourseInfo  getQuestions(Long id){
		
         CourseInfo courseInfo  = new CourseInfo();
		
		try {
			CourseEntity courseEntity = courseRepository.getCoursesDetails(id);
			courseInfo.setCourseId(courseEntity.getCourseId());
			courseInfo.setCourseName(courseEntity.getCourseName());
			courseInfo.setCourseAnswerCount(courseEntity.getCourseAnswerCount());
		
			
			List<QuestionsInfo> noOFQuestions =new ArrayList<QuestionsInfo>();
			Iterable<QuestionsEntity> QEntities = courseEntity.getQuestionsEntity();
			int i=0;
			for (QuestionsEntity questionsEntity : QEntities) {
			QuestionsInfo questions = new QuestionsInfo();
			questions.setQuestion((questionsEntity.getQuestion()));
			questions.setOption1(questionsEntity.getOption1());
			//System.out.println(i +"---------------------------"+questionsEntity.getOption1());
			questions.setOption2(questionsEntity.getOption2());
			questions.setOption3(questionsEntity.getOption3());
			questions.setOption4(questionsEntity.getOption4());
			questions.setAnswer(questionsEntity.getAnswer());;
			noOFQuestions.add(questions);		
			i++;
			}
			courseInfo.setTotalNoOFQuestions(i);
			System.out.println("======================="+i);
			courseInfo.setQuestionsInfo(noOFQuestions);
			return courseInfo;
		}catch (Exception e) {
			// TODO: handle exception
			throw new CstLabsExpection("exeption Occered While accessing the Data");
		}
	
	}


}

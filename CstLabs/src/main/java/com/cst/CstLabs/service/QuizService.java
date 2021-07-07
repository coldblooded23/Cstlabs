package com.cst.CstLabs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst.CstLabs.entity.CourseEntity;
import com.cst.CstLabs.entity.ResultsEntity;
import com.cst.CstLabs.entity.SignUpEntity;
import com.cst.CstLabs.entity.VideoCountEntity;
import com.cst.CstLabs.model.CourseInfo;
import com.cst.CstLabs.model.QuizInfo;
import com.cst.CstLabs.model.ResultInfo;
import com.cst.CstLabs.repository.CourseRepository;
import com.cst.CstLabs.repository.ResultsRepository;
import com.cst.CstLabs.repository.SignUpRepository;
import com.cst.CstLabs.repository.VideoCountRepository;
import com.cst.CstLabs.utils.CstLabsExpection;

@Service
public class QuizService {

	@Autowired
	SignUpRepository signUpRepository;

	@Autowired
	ResultsRepository resultsRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	VideoCountRepository videoCountRepository;
	private static Logger LOGGER = LogManager.getLogger(QuizService.class);

	public ResultInfo result(QuizInfo quizInfo, String Email) {
		ResultsEntity resultsEntity = new ResultsEntity();
		ResultInfo resultInfo = new ResultInfo();

		try {
			String result = null;
			Long userId = null;
			int value;

			SignUpEntity signUpEntity = signUpRepository.getUserByEmail(Email);
			VideoCountEntity videoCountEntity = videoCountRepository.getVideoWatchedByEmail(Email,
					quizInfo.getCourseId());
			int videoCount = videoCountEntity.getCount() + 1;
			LOGGER.info("yyyyyyyyyyyyy==========" + quizInfo.getOpt1() + quizInfo.getOpt2() + quizInfo.getOpt3()
					+ quizInfo.getOpt4() + quizInfo.getOpt5() + quizInfo.getOpt6() + quizInfo.getOpt7()
					+ quizInfo.getOpt8() + quizInfo.getOpt9() + quizInfo.getOpt10() + quizInfo.getOpt11()
					+ quizInfo.getOpt12() + quizInfo.getOpt13() + quizInfo.getOpt14() + quizInfo.getOpt15()
					+ quizInfo.getOpt16() + quizInfo.getOpt17() + quizInfo.getOpt18() + quizInfo.getOpt19()
					+ quizInfo.getOpt20() + quizInfo.getOpt21() + quizInfo.getOpt22() + quizInfo.getOpt23()
					+ quizInfo.getOpt24() + quizInfo.getOpt25() + quizInfo.getOpt26() + quizInfo.getOpt27()
					+ quizInfo.getOpt28() + quizInfo.getOpt29() + quizInfo.getOpt30() + quizInfo.getOpt31()
					+ quizInfo.getOpt32() + quizInfo.getOpt33() + quizInfo.getOpt34() + quizInfo.getOpt35()
					+ quizInfo.getOpt36() + quizInfo.getOpt37() + quizInfo.getOpt38() + quizInfo.getOpt39()
					+ quizInfo.getOpt40());
			LOGGER.info("New VideoCount Entity will be    " + videoCount);
			int correctMarks = quizInfo.getOpt1() + quizInfo.getOpt2() + quizInfo.getOpt3() + quizInfo.getOpt4()
					+ quizInfo.getOpt5() + quizInfo.getOpt6() + quizInfo.getOpt7() + quizInfo.getOpt8()
					+ quizInfo.getOpt9() + quizInfo.getOpt10() + quizInfo.getOpt11() + quizInfo.getOpt12()
					+ quizInfo.getOpt13() + quizInfo.getOpt14() + quizInfo.getOpt15() + quizInfo.getOpt16()
					+ quizInfo.getOpt17() + quizInfo.getOpt18() + quizInfo.getOpt19() + quizInfo.getOpt20()
					+ quizInfo.getOpt21() + quizInfo.getOpt22() + quizInfo.getOpt23() + quizInfo.getOpt24()
					+ quizInfo.getOpt25() + quizInfo.getOpt26() + quizInfo.getOpt27() + quizInfo.getOpt28()
					+ quizInfo.getOpt29() + quizInfo.getOpt30() + quizInfo.getOpt31() + quizInfo.getOpt32()
					+ quizInfo.getOpt33() + quizInfo.getOpt34() + quizInfo.getOpt35() + quizInfo.getOpt36()
					+ quizInfo.getOpt37() + quizInfo.getOpt38() + quizInfo.getOpt39() + quizInfo.getOpt40();

			int wrongMarks = (int) (quizInfo.getTotalCount() - correctMarks);

			if (correctMarks >= quizInfo.getCourseAnswerCount()) {
				result = "pass";
			} else {
				result = "fail";

				if (videoCount > 3) {
					value = videoCountRepository.UpdateVedioCountByEmail(Email, 0, false, quizInfo.getCourseId());
					LOGGER.info("New value Entity will be    " + value);
				}
				value = videoCountRepository.UpdateVedioCountByEmail(Email, videoCount, true, quizInfo.getCourseId());
				LOGGER.info("New value is less Entity will be    " + value);
			}

			if (null != signUpEntity) {
				userId = signUpEntity.getUserId();
				resultsEntity.setCorrect(correctMarks);
				resultsEntity.setIncorrect(wrongMarks);
				resultsEntity.setResult(result);
				resultsEntity.setUserId(userId);
				resultsEntity.setCourseId(quizInfo.getCourseId());
				resultInfo.setCorrect(correctMarks);
				resultInfo.setIncorrect(wrongMarks-quizInfo.getUnanswered());
				resultInfo.setResult(result);
				resultInfo.setCourseId(quizInfo.getCourseId());
				resultInfo.setUnAnswered(quizInfo.getUnanswered());

				ResultsEntity rEntity = resultsRepository.save(resultsEntity);
				LOGGER.info("New value is less Entity will be    " + rEntity);

				return resultInfo;

			}

		} catch (Exception e) {

			LOGGER.info(e);
			throw new CstLabsExpection("error occered while accessing the data");
		}
		return resultInfo;

	}

	public CourseInfo getCourseDetails(Long id) {

		try {
			CourseInfo courseInfo = new CourseInfo();
			CourseEntity entity = courseRepository.getCoursesDetails(id);
			courseInfo.setCourseId(entity.getCourseId());
			courseInfo.setCourseName(entity.getCourseName());
			courseInfo.setCourseVideoPath(entity.getCourseVideoPath());
			courseInfo.setCourseAnswerCount(entity.getCourseAnswerCount());
			return courseInfo;
		} catch (Exception e) {

			LOGGER.info("exeption occered while Accessing the Data" + e.getMessage());
			throw new CstLabsExpection("exeption occered while Accessing the Data");
		}

	}

	public List<CourseInfo> getIncompleatedCourse(String Email) {
		List<CourseInfo> courseInfo = new ArrayList<CourseInfo>();
		try {

			Long userid = signUpRepository.getUserIdByEmail(Email);
			if (userid != null) {
				Iterable<CourseEntity> course = courseRepository.getUncompleatedCourses(userid);
				for (CourseEntity courseEntity : course) {
					CourseInfo coInfo = new CourseInfo();
					coInfo.setCourseId(courseEntity.getCourseId());
					coInfo.setCourseName(courseEntity.getCourseName());
					courseInfo.add(coInfo);
				}
			}
			return courseInfo;

		} catch (Exception e) {
			LOGGER.info("Exception occered while accessing the data " + e.getMessage());
			throw new CstLabsExpection("Exception occered while accessing the data");
		}

	}

	public List<CourseInfo> getCompleatedCourse(String Email) {
		List<CourseInfo> courseInfo = new ArrayList<CourseInfo>();
		try {

			Long userid = signUpRepository.getUserIdByEmail(Email);
			if (userid != null) {
				Iterable<CourseEntity> course = courseRepository.getCompleatedCourse(userid);
				for (CourseEntity courseEntity : course) {
					CourseInfo coInfo = new CourseInfo();
					coInfo.setCourseId(courseEntity.getCourseId());
					coInfo.setCourseName(courseEntity.getCourseName());
					courseInfo.add(coInfo);
				}
			}
			return courseInfo;

		} catch (Exception e) {
			LOGGER.info("Exception occered while accessing the data " + e.getMessage());
			throw new CstLabsExpection("Exception occered while accessing the data");
		}

	}

	public ResultInfo getCertificateDetails(String Email, Long id) {

		ResultInfo resultInfo = new ResultInfo();

		try {
			SignUpEntity signUpEntity = signUpRepository.getUserByEmail(Email);
			if (null != signUpEntity) {
				Long userid = signUpEntity.getUserId();

				ResultsEntity resultsEntity = resultsRepository.getCertificateDetails(userid, "pass", id);
				System.out.println("=============================" + resultsEntity);
				if (resultsEntity == null) {
					return null;
				}
				resultInfo.setFirstName(signUpEntity.getFirstName());
				resultInfo.setLastName(signUpEntity.getLastName());
				resultInfo.setCorrect(resultsEntity.getCorrect());
				resultInfo.setIncorrect(resultsEntity.getIncorrect());

				return resultInfo;
			}
		} catch (Exception e) {
System.out.println(e.getMessage());
			throw new CstLabsExpection("error occered while accing the data");
		}
		return resultInfo;

	}

}

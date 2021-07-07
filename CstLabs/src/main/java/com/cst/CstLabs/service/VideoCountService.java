package com.cst.CstLabs.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst.CstLabs.entity.VideoCountEntity;
import com.cst.CstLabs.repository.SignUpRepository;
import com.cst.CstLabs.repository.VideoCountRepository;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;


@Service
@Transactional
public class VideoCountService {

	@Autowired
	VideoCountRepository videoCountRepository;

	@Autowired
	SignUpRepository signUpRepository;

	private static Logger LOGGER = LogManager.getLogger(VideoCountService.class);

	public String AddVideoWatched(String Email,Long id) {
		VideoCountEntity videoEntity = new VideoCountEntity();

		VideoCountEntity videoCountEntity = videoCountRepository.getVideoWatchedByEmail(Email,id);

		try {
			if (null == videoCountEntity) {
				LOGGER.info("New VideoCount Entity will be Added");
				videoEntity.setEmailId(Email);
				videoEntity.setCount(0);
				videoEntity.setWatched(true);
				videoEntity.setCourseId(id);

				VideoCountEntity video = videoCountRepository.save(videoEntity);
				if (null != video.getEmailId()) {
					LOGGER.info("New VideoCount Entity is Added");

					return Constants.AuthenticationSuccess;
				}
				LOGGER.info("New VideoCount Entity is Failed");
				return Constants.AuthenticationFailed;
			} else {
				int response = videoCountRepository.UpdateVedioWatchByEmail(Email, true,id);

				if (response > 0) {
					return Constants.AuthenticationSuccess;
				}
			}
		} catch (Exception e) {
			throw new CstLabsExpection("Exception occured while accessing the data");
		}
		return Constants.AuthenticationFailed;
	}

	public String ifUserWatchedVideo(String email,long id) {
		LOGGER.info("New user" + email + " taking thr course first time");
		VideoCountEntity videoCountEntity = videoCountRepository.getVideoWatchedByEmail(email,id);
		try {
			if (videoCountEntity == null) {
				LOGGER.info("New user" + email + " taking thr course first time");
				return "Video";
			} else if (videoCountEntity.getWatched() == true) {
				LOGGER.info(" user" + email + " taking the Quiz "+videoCountEntity.getCount());
				
				
				
				if (videoCountEntity.getCount() > 3) {
					LOGGER.info(" user" + email + " has to watch the video ");
					int response = videoCountRepository.UpdateVedioCountByEmail(email, 0, false,id);
					if (response > 0) {
						LOGGER.info(" user" + email + " has count has updated ");
						return "Video";
					}
					return Constants.AuthenticationFailed;
				}
				return "Quiz";

			} else if (videoCountEntity.getWatched() == false) {

				return "Video";
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new CstLabsExpection("Exception occured while accessing the data");
		}
		return Constants.AuthenticationFailed;
	}

}

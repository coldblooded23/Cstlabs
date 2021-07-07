package com.cst.CstLabs.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cst.CstLabs.entity.SignUpEntity;
import com.cst.CstLabs.model.SignUpInfo;
import com.cst.CstLabs.repository.SignUpRepository;
import com.cst.CstLabs.utils.AES;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;



@Service
@Transactional
public class SignUpService {

	@Autowired
	SignUpRepository signUpRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	

	private static Logger LOGGER =  LogManager.getLogger(SignUpService.class);

	public String createUser(SignUpInfo signUpInfo) {
		SignUpEntity signUpEntity = new SignUpEntity();

		try {
			SignUpEntity exestingUser = signUpRepository.getUserByEmail(signUpInfo.getEmailId());
			if (null == exestingUser) {

				signUpEntity.setEmailId(signUpInfo.getEmailId());
				signUpEntity.setFirstName(signUpInfo.getFirstName());
				signUpEntity.setLastName(signUpInfo.getLastName());
				signUpEntity.setPhone(signUpInfo.getPhone());
				signUpEntity.setPassword(passwordEncoder.encode(signUpInfo.getPassword()));
				SignUpEntity entity = signUpRepository.save(signUpEntity);

				if (null != entity.getEmailId()) {
					return Constants.User + " " + entity.getFirstName() + " " + Constants.UserCreatedSuccess;
				} else {
					return Constants.User + " " + entity.getFirstName() + " " + Constants.UserCreatedFailed;
				}
			} else {
				return Constants.User + " " + signUpInfo.getFirstName() + "already exists";
			}
		} catch (Exception e) {
			throw new CstLabsExpection("Exception occured while accessing the data");
		}
	}
	
	
	public String updatePassWord(SignUpInfo signUpInfo, String Email) {

		try {
			
			String newPassword=passwordEncoder.encode(signUpInfo.getNewPassword());
			SignUpEntity exestingUser = signUpRepository.getUserByEmail(Email);
			LOGGER.info(exestingUser.getPassword()+"exesting user "+exestingUser);
			if(null!=exestingUser) {	
				if(passwordEncoder.matches(signUpInfo.getOldPassWord(), exestingUser.getPassword())) {	
					
					if(signUpInfo.getOldPassWord().equals(signUpInfo.getConfirmPassword())) {
						return Constants.Password;
					}
					
					int report =signUpRepository.updatePassword(newPassword, Email);			
					LOGGER.info("password updated gor the user successfull"+Email+"  "+report);
						return Constants.AuthenticationSuccess;	
				}else {
					return Constants.AuthenticationFailed;
				}		
			}else {
				return Constants.UserNotExests;
			}
			
		}catch (Exception e) {
			
			LOGGER.info("Exception occered while accessing the Data" +e.getMessage());
		throw new CstLabsExpection("Exception occered while accessing the Data");
		}
		
	}
	
	public SignUpInfo getUser(String email) {
		SignUpInfo signUpInfo = new SignUpInfo();
		
		try {
			SignUpEntity SignUpEntity = signUpRepository.getUserByEmail(email);	
			LOGGER.info("Get User Details are method ");
			signUpInfo.setFirstName(SignUpEntity.getFirstName());
			signUpInfo.setLastName(SignUpEntity.getLastName());
			signUpInfo.setPhone(SignUpEntity.getPhone());
			signUpInfo.setEmailId(SignUpEntity.getEmailId());
			LOGGER.info("User Details retrun ");
		}catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("Exception occered while accessing the Data" +e.getMessage());
			throw new CstLabsExpection("Exception occered while accessing the Data");
		}
		return signUpInfo;	
	}
	
	
	public String updateUser(SignUpInfo signUpInfo) {
		
		try {
			SignUpEntity user = signUpRepository.getUserByEmail(signUpInfo.getEmailId());
			user.setFirstName(signUpInfo.getFirstName());
			user.setLastName(signUpInfo.getLastName());
			user.setPhone(signUpInfo.getPhone());
			SignUpEntity signUpEntity =signUpRepository.save(user);
			if(null!=signUpEntity) {
				
				return Constants.AuthenticationSuccess;
			}else {
				return Constants.AuthenticationFailed;
			}
		}catch (Exception e) {
			LOGGER.info("Exception occered while accessing the Data" +e.getMessage());
			throw new CstLabsExpection("Exception occered while accessing the Data");
		}	
		
	}
	


}

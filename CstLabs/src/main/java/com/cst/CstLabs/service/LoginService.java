package com.cst.CstLabs.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cst.CstLabs.entity.SignUpEntity;
import com.cst.CstLabs.model.LoginInfo;
import com.cst.CstLabs.repository.SignUpRepository;
import com.cst.CstLabs.utils.AES;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;



@Service
@Transactional
public class LoginService implements UserDetailsService {

	@Autowired
	SignUpRepository signUpRepository;

	private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

	@Value("${aesSecretKey}")
	private String secretKey;

	public String login(LoginInfo loginInfo) {
		String Password = null;
		try {
			SignUpEntity userDetails = signUpRepository.getUserByEmail(loginInfo.getUserName());

			if (null != userDetails) {
				try {
					Password = AES.encrypt(loginInfo.getPassword(), secretKey);
				} catch (Exception e) {
					throw new CstLabsExpection("Exception occured while encrypting the password");
				}
				if (Password.equals(userDetails.getPassword())) {

					return Constants.AuthenticationSuccess;
				}
			}
			return Constants.AuthenticationFailed;
		} catch (Exception e) {
			throw new CstLabsExpection("xception occured while validate Loggedin Userd");

		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SignUpEntity existingUser = signUpRepository.getUserByEmail(username);
		if (null != existingUser) {
			LOGGER.info("About to validate the password for " + username);
			return new User(existingUser.getEmailId(),existingUser.getPassword(),getAuthorities());
		}else {
			LOGGER.info("Invalid username and password");
		throw new UsernameNotFoundException("Invalid username and password");
		
		}
		
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		List<GrantedAuthority> authorities
	      = new ArrayList<>();
     return  authorities;
    }
}



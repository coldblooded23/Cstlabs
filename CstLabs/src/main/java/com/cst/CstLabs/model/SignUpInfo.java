package com.cst.CstLabs.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class SignUpInfo {

	@NotNull
	@Size(max = 50, message = "Mininum 2 and Maximum 50 characters")
	private String firstName;

	@NotNull
	@Size(max = 50, message = "Mininum 2 and Maximum 50 characters")
	private String lastName;

	
	
	private String emailId;
 

	@Size(min = 10,message = "Phone number must contain 10 digits")
	private String phone;


	private String password;

	private String confirmPassword;

	private String username;
	
	
	private String oldPassWord;
	
	private String newPassword;

	public SignUpInfo() {

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public SignUpInfo(String firstName, String lastName, String emailId, String phone, String password,
			String confirmPassword, String username, String oldPassWord, String newPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phone = phone;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.username = username;
		this.oldPassWord = oldPassWord;
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "SignUpInfo [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", phone="
				+ phone + ", password=" + password + ", confirmPassword=" + confirmPassword + ", username=" + username
				+ ", oldPassWord=" + oldPassWord + ", newPassword=" + newPassword + "]";
	}


	
	

}

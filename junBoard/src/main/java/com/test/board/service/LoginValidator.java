package com.test.board.service;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.test.board.model.LoginSessionModel;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginSessionModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginSessionModel loginModel = (LoginSessionModel) target;
		
		// check userId field
		if(loginModel.getMember_id() == null || loginModel.getMember_id().trim().isEmpty()) {
			errors.rejectValue("member_id", "required");
		}
		
		// check userPw field
		if(loginModel.getMember_password() == null || loginModel.getMember_password().trim().isEmpty()){
			errors.rejectValue("member_password", "required");
		}

	}

}

package com.test.board.service;



import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.test.board.model.MemberModel;

public class MemberValidatior implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberModel memberModel = (MemberModel) target;
		
		if(memberModel.getMember_id() == null || memberModel.getMember_id().trim().isEmpty()){
			errors.rejectValue("member_id", "required");
		}
		
		if(memberModel.getMember_password() == null || memberModel.getMember_password().trim().isEmpty()){
			errors.rejectValue("member_password", "required");
		}
		
		if(memberModel.getMember_email() == null || memberModel.getMember_email().trim().isEmpty()){
			errors.rejectValue("member_email", "required");
		}

	}

}

package com.test.board.service;


import com.test.board.model.LoginSessionModel;

public interface LoginService {
	public LoginSessionModel checkUserId(String userId);
}

package com.test.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.test.board.model.LoginSessionModel;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	

	@Override
	public LoginSessionModel checkUserId(String member_id) {
		return (LoginSessionModel) sqlMapClientTemplate.queryForObject("login.loginCheck", member_id);
	}

}

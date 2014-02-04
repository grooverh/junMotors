package com.test.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.test.board.model.MemberModel;



@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	@Override
	public boolean addMember(MemberModel memberModel) {
		sqlMapClientTemplate.insert("member.addMember", memberModel);
		MemberModel checkAddMember = findByUserId(memberModel.getMember_id());
		
		//check addMember Process
		if(checkAddMember == null){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public MemberModel findByUserId(String member_id) {
		return (MemberModel) sqlMapClientTemplate.queryForObject("member.findByUserId", member_id);
	}

}

package com.test.board.service;

import com.test.board.model.MemberModel;

public interface MemberService {
	public boolean addMember(MemberModel memberModel);
	public MemberModel findByUserId(String member_id);
}

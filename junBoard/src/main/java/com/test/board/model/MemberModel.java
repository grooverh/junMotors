package com.test.board.model;

public class MemberModel {
	private int member_no;
	private String member_id;
	private String member_password;
	private String member_email;
	private String member_hp;
	private String password_hint;
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_hp() {
		return member_hp;
	}
	public void setMember_hp(String member_hp) {
		this.member_hp = member_hp;
	}
	public String getPassword_hint() {
		return password_hint;
	}
	public void setPassword_hint(String password_hint) {
		this.password_hint = password_hint;
	}
	
}

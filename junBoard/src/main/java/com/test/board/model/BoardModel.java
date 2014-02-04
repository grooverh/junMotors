package com.test.board.model;

public class BoardModel {
	private int rnum;
	private int board_no;
	private String board_title;
	private String board_content;
	private String board_writer;
	private int board_hit = 0;
	private int board_RECOMMENDCOUNT = 0;
	private int board_comment = 0;
	private String board_regdate;
	private int boardtype_num;
	private String board_password;
	private String board_file1;
	private String board_file2;
	private String boardtype_name;
	
	public String getBoardtype_name() {
		return boardtype_name;
	}
	public void setBoardtype_name(String boardtype_name) {
		this.boardtype_name = boardtype_name;
	}
	public String getBoard_password() {
		return board_password;
	}
	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public int getBoard_RECOMMENDCOUNT() {
		return board_RECOMMENDCOUNT;
	}
	public void setBoard_RECOMMENDCOUNT(int board_RECOMMENDCOUNT) {
		this.board_RECOMMENDCOUNT = board_RECOMMENDCOUNT;
	}
	public int getBoard_comment() {
		return board_comment;
	}
	public void setBoard_comment(int board_comment) {
		this.board_comment = board_comment;
	}
	public String getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(String board_regdate) {
		this.board_regdate = board_regdate;
	}
	public int getBoardtype_num() {
		return boardtype_num;
	}
	public void setBoardtype_num(int boardtype_num) {
		this.boardtype_num = boardtype_num;
	}
	public String getBoard_file1() {
		return board_file1;
	}
	public void setBoard_file1(String board_file1) {
		this.board_file1 = board_file1;
	}
	public String getBoard_file2() {
		return board_file2;
	}
	public void setBoard_file2(String board_file2) {
		this.board_file2 = board_file2;
	}
	
	
}

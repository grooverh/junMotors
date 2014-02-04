package com.test.board.service;


import java.util.List;

import com.test.board.model.BoardCommentModel;
import com.test.board.model.BoardModel;
import com.test.board.model.FileModel;

public interface BoardService {
	public List<BoardModel> getBoardList(int startArticleNum, int endArticleNum);
	public BoardModel getOneArticle(int idx);
	public List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum);
	public List<BoardCommentModel> getCommentList(int idx);
	public boolean writeArticle(BoardModel board);
	public boolean writeComment(BoardCommentModel comment);
	public void updateHitcount(int board_hit, int idx);
	public void updateRecommendCount(int board_RECOMMENDCOUNT, int board_no);
	public int getTotalNum();
	public int getSearchTotalNum(String type, String keyword);
	public void deleteComment(int idx);
	public void deleteArticle(int idx);
	public BoardCommentModel getOneComment(int idx);
	public boolean modifyArticle(BoardModel board);
	public List<BoardModel> getBoardListType(int startArticleNum,
			int endArticleNum, int boardtype_num);
	public int getTotalNumType(int boardtype_num);
}

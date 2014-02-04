package com.test.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.test.board.model.BoardCommentModel;
import com.test.board.model.BoardModel;
import com.test.board.model.FileModel;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		


	@Override
	public List<BoardModel> getBoardList(int startArticleNum, int endArticleNum) {
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlMapClientTemplate.queryForList("board.getBoardList", valueMap);
	}

	@Override
	public BoardModel getOneArticle(int board_no) {
		return (BoardModel) sqlMapClientTemplate.queryForObject("board.getOneArticle", board_no);
	}

	@Override
	public List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum) {
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlMapClientTemplate.queryForList("board.searchArticle", valueMap);
	}

	@Override
	public List<BoardCommentModel> getCommentList(int comment_no) {
		return sqlMapClientTemplate.queryForList("board.getCommentList", comment_no);
	}

	@Override
	public boolean writeArticle(BoardModel board) {
		sqlMapClientTemplate.insert("board.writeArticle", board);		
		return true;
	}

	@Override
	public boolean writeComment(BoardCommentModel comment) {
		sqlMapClientTemplate.insert("board.writeComment", comment);
		return true;
	}

	@Override
	public void updateHitcount(int board_hit, int board_no) {
		valueMap.put("board_hit", board_hit);
		valueMap.put("board_no", board_no);
		sqlMapClientTemplate.update("board.updateHitcount", valueMap);		
	}

	@Override
	public void updateRecommendCount(int board_RECOMMENDCOUNT, int board_no) {
		valueMap.put("board_RECOMMENDCOUNT", board_RECOMMENDCOUNT);
		valueMap.put("board_no", board_no);
		sqlMapClientTemplate.update("board.updateRecommendcount", valueMap);
		
	}
	@Override
	public int getTotalNum() {
		return (Integer) sqlMapClientTemplate.queryForObject("board.getTotalNum");
	}

	@Override
	public int getSearchTotalNum(String type, String keyword) {
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		return (Integer) sqlMapClientTemplate.queryForObject("board.getSearchTotalNum", valueMap);
	}

	@Override
	public void deleteComment(int idx) {
		sqlMapClientTemplate.delete("board.deleteComment", idx);
	}
	
	@Override
	public void deleteArticle(int board_no) {
		sqlMapClientTemplate.delete("board.deleteArticle", board_no);	
	}

	@Override
	public BoardCommentModel getOneComment(int idx) {
		return (BoardCommentModel) sqlMapClientTemplate.queryForObject("board.getOneComment", idx);		
	}

	@Override
	public boolean modifyArticle(BoardModel board) {
		sqlMapClientTemplate.update("board.modifyArticle", board);
		return true;
	}

	@Override
	public List<BoardModel> getBoardListType(int startArticleNum,
			int endArticleNum,int boardtype_num) {
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		valueMap.put("boardtype_num", boardtype_num);
		return sqlMapClientTemplate.queryForList("board.getBoardListType", valueMap);
	}

	@Override
	public int getTotalNumType(int boardtype_num) {
		return (Integer) sqlMapClientTemplate.queryForObject("board.getTotalNumType",boardtype_num);
	}


}

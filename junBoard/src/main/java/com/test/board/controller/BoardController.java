package com.test.board.controller;

import java.io.File;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.board.model.BoardCommentModel;
import com.test.board.model.BoardModel;
import com.test.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	// DI
	@Autowired
	private BoardService boardService;
	//
	// * User variable
	// article, page variables
	private int currentPage = 1;
	private int showArticleLimit = 10; // change value if want to show more articles by one page
	private int showPageLimit = 10; // change value if want to show more page links
	private int startArticleNum = 0;
	private int endArticleNum = 0;
	private int totalNum = 0;
	//
	// file upload path
	private String uploadPath = "C:\\springBoard\\junBoard\\src\\main\\webapp\\files\\";
	//
	//
	
	@RequestMapping("/boardtest.do")
	public ModelAndView boardTtest(@ModelAttribute("BoardModel") BoardModel boardModel){
		return new ModelAndView("hello");
	}	
	
	@RequestMapping("/list.do")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response){
		int boardtype_num=Integer.parseInt( request.getParameter("boardtype_num"));	
		String type = null;
		String keyword = null;		
		
		// set variables from request parameter
		if(request.getParameter("page") == null || request.getParameter("page").trim().isEmpty() || request.getParameter("page").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("type") != null){
			type = request.getParameter("type").trim();
		}
		
		if(request.getParameter("keyword") != null){
			keyword = request.getParameter("keyword").trim();
		}
		//
		
		// expression article variables value
		startArticleNum = (currentPage - 1) * showArticleLimit + 1;
		endArticleNum = startArticleNum + showArticleLimit -1;
		//
		
		// get boardList and get page html code
		List<BoardModel> boardList =null;
		
		
			if(type != null && keyword != null){
				if(boardtype_num == 4){
					boardList = boardService.searchArticle(type, keyword, startArticleNum, endArticleNum);
					totalNum = boardService.getSearchTotalNum(type, keyword);
				}else{
					boardList = boardService.searchArticle(type, keyword, startArticleNum, endArticleNum);
					totalNum = boardService.getSearchTotalNum(type, keyword);	
				}
			} else {
				if(boardtype_num == 4){
					boardList = boardService.getBoardList(startArticleNum, endArticleNum);
					totalNum = boardService.getTotalNum();
				}else{
					boardList = boardService.getBoardListType(startArticleNum, endArticleNum,boardtype_num);
					totalNum = boardService.getTotalNumType(boardtype_num);
				}
			}
		
		StringBuffer pageHtml = getPageHtml(currentPage, totalNum, showArticleLimit, showPageLimit, type, keyword,boardtype_num);
		//
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.addObject("pageHtml", pageHtml);
		mav.addObject("boardtype_num",boardtype_num);
		mav.setViewName("/board/list");
		
		return mav;
	}
	
	// A method for Creating page html code
	private StringBuffer getPageHtml(int currentPage, int totalNum, int showArticleLimit, int showPageLimit, String type, String keyword,int boardtype_num) {
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int lastPage = 0;
		
		// expression page variables
		startPage = ((currentPage-1) / showPageLimit) * showPageLimit + 1;
		lastPage = startPage + showPageLimit - 1;
		if(lastPage > totalNum / showArticleLimit) {
			lastPage = (totalNum / showArticleLimit) + 1;
		}
		//
		
		// create page html code
		// if: when no search	
		if(type == null && keyword == null){			
			if(currentPage == 1){
				pageHtml.append("<span>");
			} else {
				pageHtml.append("<span><a href=\"list.do?page=" + (currentPage-1) + "&boardtype_num="+boardtype_num+"\"<이전></a>&nbsp;&nbsp;");
			}
			
			for(int i = startPage ; i <= lastPage ; i++) {
				if(i == currentPage){
					pageHtml.append(".&nbsp;<strong>");
					pageHtml.append("<a href=\"list.do?page=" +i + "&boardtype_num="+boardtype_num+"\" class=\"page\">" + i + "</a>");
					pageHtml.append("&nbsp;</strong>");
				} else {
					pageHtml.append(".&nbsp;<a href=\"list.do?page=" +i + "&boardtype_num="+boardtype_num+"\" class=\"page\">" + i + "</a>&nbsp;");
				}
				
			}
			if(currentPage == lastPage){
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage+1) + "&boardtype_num="+boardtype_num+"\"><다음></a></span></span>");
			} else {
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage+1) + "&boardtype_num="+boardtype_num+"\"><[>]></a></span>");
			}
		//
		// else: when search
		} else {			
			if(currentPage == 1){
				pageHtml.append("<span>");
			} else {
				pageHtml.append("<span><a href=\"list.do?page=" + (currentPage-1) + "&type=" + type + "&keyword=" + keyword + "&boardtype_num="+boardtype_num+"\"><이전></a>&nbsp;&nbsp;");
			}
			
			for(int i = startPage ; i <= lastPage ; i++) {
				if(i == currentPage){
					pageHtml.append(".&nbsp;<strong>");
					pageHtml.append("<a href=\"list.do?page=" +i + "&type=" + type + "&keyword=" + keyword + "&boardtype_num="+boardtype_num+"\" class=\"page\">" + i + "</a>&nbsp;");
					pageHtml.append("&nbsp;</strong>");
				} else {
					pageHtml.append(".&nbsp;<a href=\"list.do?page=" +i + "&type=" + type + "&keyword=" + keyword + "&boardtype_num="+boardtype_num+"\" class=\"page\">" + i + "</a>&nbsp;");
				}
				
			}
			if(currentPage == lastPage){
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage+1) + "&boardtype_num="+boardtype_num+"\"><다음></a></span></span>");
			} else {
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage+1) + "&type=" + type + "&keyword=" + keyword + "&boardtype_num="+boardtype_num+"\"><다음></a></span>");
			}
		}
		//		
		return pageHtml;
	}
	
	@RequestMapping("/view.do")
	public ModelAndView boardView(HttpServletRequest request){
		int idx;
		if(request.getParameter("board_no") != null){
		idx = Integer.parseInt(request.getParameter("board_no"));	
		}
		else{
		idx = Integer.parseInt(request.getParameter("comment_no"));		
		}
		BoardModel board = boardService.getOneArticle(idx); 
		boardService.updateHitcount(board.getBoard_hit()+1, idx);
		
		List<BoardCommentModel> commentList = boardService.getCommentList(idx); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("commentList", commentList);
		mav.setViewName("/board/view");
		return mav;
	}
	
	@RequestMapping("/write.do")
	public String boardWrite(@ModelAttribute("BoardModel") BoardModel boardModel){		
		return "/board/write";
	}
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String boardWriteProc(@ModelAttribute("BoardModel") BoardModel boardModel ,MultipartHttpServletRequest request){
		 //get upload file
		MultipartFile file = request.getFile("file");
		String file_name = file.getOriginalFilename();
		MultipartFile file2 = request.getFile("file2");
		String file_name2 = file2.getOriginalFilename();
		File uploadFile = new File(uploadPath + file_name);
		File uploadFile2 = new File(uploadPath + file_name2);
		if(!file.isEmpty()){
			if(uploadFile.exists()){
				System.out.println("파일이 존재하면..;;");
				file_name = new Date().getTime() + file_name;
				uploadFile = new File(uploadPath + file_name);
			}
			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				
			}
		}
		if(!file2.isEmpty()){
		if(uploadFile2.exists()){
			file_name2 = new Date().getTime() + file_name2;
			uploadFile2 = new File(uploadPath + file_name2);
		}
		try {
			file2.transferTo(uploadFile2);
		} catch (Exception e) {
			
		}
		}
		String content =  boardModel.getBoard_content().replaceAll("\r\n", "<br />");		
		boardModel.setBoard_content(content);
		boardModel.setBoard_file1(file_name);
		boardModel.setBoard_file2(file_name2);
		//
		boardService.writeArticle(boardModel);
		
		
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/delete.do")
	public ModelAndView boardDelete(HttpServletRequest request, HttpSession session){
		String board_writer = (String) session.getAttribute("member_id");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		BoardModel board = boardService.getOneArticle(board_no);
		
		ModelAndView mav = new ModelAndView();
		
		if(!board_writer.equals(board.getBoard_writer())){
			mav.addObject("errCode", "1");	
			mav.addObject("board_no", board_no);
			mav.setViewName("redirect:view.do");
		} else {
			List<BoardCommentModel> commentList = boardService.getCommentList(board_no); // check comments
			if(commentList.size() > 0){
				mav.addObject("errCode", "2"); // can't delete because a article has comments
				mav.addObject("board_no", board_no);
				mav.setViewName("redirect:view.do");
			} else {
				// if: when the article has upload file - remove that
				if(board.getBoard_file1() != null){
					System.out.println("파일첨부 지우기!!!");
					System.out.println("deltefile:"+board.getBoard_file1());
					File removeFile = new File(uploadPath + board.getBoard_file1());
					removeFile.delete();
				}
				if(board.getBoard_file2() != null){
					System.out.println("파일첨부2 지우기!!!");
					System.out.println("deltefile2:"+board.getBoard_file2());
					File removeFile2 = new File(uploadPath + board.getBoard_file2());
					removeFile2.delete();
				}
								
				boardService.deleteArticle(board_no);
				
				mav.setViewName("redirect:list.do");
			}
		}		
		return mav;
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView boardModify(HttpServletRequest request, HttpSession session){
		String member_id = (String) session.getAttribute("member_id");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		BoardModel board = boardService.getOneArticle(board_no);
		String content = board.getBoard_content().replaceAll("<br />", "\r\n");
		board.setBoard_content(content);
		//
		
		ModelAndView mav = new ModelAndView();
		
		if(!member_id.equals(board.getBoard_writer())){
			mav.addObject("errCode", "1");	
			mav.addObject("board_no", board_no);
			mav.setViewName("redirect:view.do");
		} else {
			mav.addObject("board", board);
			mav.setViewName("/board/modify");
		}		
		
		return mav;
	}
	
	@RequestMapping(value = "/modify.do", method=RequestMethod.POST)
	public ModelAndView boardModifyProc(@ModelAttribute("BoardModel") BoardModel boardModel, MultipartHttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String orgFileName = request.getParameter("orgFile");
		MultipartFile newFile = request.getFile("newFile");
		String newFileName = newFile.getOriginalFilename();
		
		boardModel.setBoard_file1(orgFileName);
		
		// if: when want to change upload file
		if(newFile != null && !newFileName.equals("")){
			if(orgFileName != null || !orgFileName.equals("")){
				// remove uploaded file
				File removeFile = new File(uploadPath  + orgFileName);
				removeFile.delete();
			}
			// create new upload file
			File newUploadFile = new File(uploadPath +newFileName);
			try {
				newFile.transferTo(newUploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			boardModel.setBoard_file1(newFileName);
		}
		// new line code change to <br /> tag
		String board_content =  boardModel.getBoard_content().replaceAll("\r\n", "<br />");		
		boardModel.setBoard_content(board_content);
		BoardModel board = boardService.getOneArticle(boardModel.getBoard_no());
		if(board.getBoard_password().equals(boardModel.getBoard_password())){
		boardService.modifyArticle(boardModel);
		}
		else{
			mav.addObject("board_no",board.getBoard_no());
			mav.addObject("errCode", 3);	// not matched password
			mav.setViewName("redirect:view.do");			
			return mav;  	
		}
		mav.addObject("board_no",board.getBoard_no());
		mav.addObject("errCode", 4);
		mav.setViewName("redirect:view.do");
		return mav;
	}
	
	
	@RequestMapping("/commentWrite.do")
	public ModelAndView commentWriteProc(@ModelAttribute("CommentModel") BoardCommentModel commentModel){
		// new line code change to <br /> tag
		String content = commentModel.getComment_content().replaceAll("\r\n", "<br />");
		commentModel.setComment_content(content);
		//
		boardService.writeComment(commentModel);
		ModelAndView mav = new ModelAndView();
		mav.addObject("comment_no", commentModel.getLinkedArticleNum());
		mav.setViewName("redirect:view.do");
		
		return mav;
	}
	
	@RequestMapping("/commentDelete.do")
	public ModelAndView commendDelete(HttpServletRequest request, HttpSession session){
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		int linkedArticleNum = Integer.parseInt(request.getParameter("linkedArticleNum"));
		
		String userId = (String) session.getAttribute("member_id");
		BoardCommentModel comment = boardService.getOneComment(comment_no);
		
		ModelAndView mav = new ModelAndView();
		
		if(!userId.equals(comment.getComment_writer())){
			mav.addObject("errCode", "1");
		} else {
			boardService.deleteComment(comment_no);
		}		
				
		mav.addObject("comment_no", linkedArticleNum); // move back to the article
		mav.setViewName("redirect:view.do");
		
		return mav;
	}
	
	@RequestMapping("/recommend.do")
	public ModelAndView updateRecommendcount(HttpServletRequest request, HttpSession session){
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String member_id = (String) session.getAttribute("member_id");
		BoardModel board = boardService.getOneArticle(board_no);
		
		ModelAndView mav = new ModelAndView();
		
		if(member_id.equals(board.getBoard_writer())){
			mav.addObject("errCode", "1");
		} else {
			boardService.updateRecommendCount(board.getBoard_RECOMMENDCOUNT()+1, board_no);
		}
		
		mav.addObject("board_no", board_no);
		mav.setViewName("redirect:/board/view.do");
		
		return mav;
	}
}

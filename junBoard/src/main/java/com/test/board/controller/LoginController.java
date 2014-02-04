package com.test.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.board.model.LoginSessionModel;
import com.test.board.service.LoginService;
import com.test.board.service.LoginValidator;


@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	@RequestMapping("/login.do")
	public String login() {		
		return "/board/login";
	}
	
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public ModelAndView loginProc(@ModelAttribute("LoginModel") LoginSessionModel loginModel, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// form validation
		new LoginValidator().validate(loginModel, result);
		if(result.hasErrors()){
			mav.setViewName("/board/login");
			return mav;
		}
		
		String member_id = loginModel.getMember_id();
		String member_password = loginModel.getMember_password();
	
		LoginSessionModel loginCheckResult = loginService.checkUserId(member_id);
		
		//check joined user
		if(loginCheckResult == null){
			mav.addObject("member_id", member_password);
			mav.addObject("errCode", 1);	// not exist userId in database
			mav.setViewName("/board/login");			
			return mav; 
		}
		
		//check password
		if(loginCheckResult.getMember_password().equals(member_password)){			
			session.setAttribute("member_id", member_id);
			mav.setViewName("redirect:/board/list.do?boardtype_num=4");
			return mav;
		} else {
			mav.addObject("member_id", member_id);
			mav.addObject("errCode", 2);	// not matched password
			mav.setViewName("/board/login");			
			return mav;  
		}	
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login.do";
	}
}

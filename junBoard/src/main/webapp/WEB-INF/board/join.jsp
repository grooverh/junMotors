<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join Member!</title>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<c:if test="${errCode == null}">
	<c:set var="errCode" value="\"\""></c:set>
</c:if>
<script type="text/javascript">
	function errCodeCheck(){
		var errCode = ${errCode};
		if(errCode != null || errCode != ""){
			switch (errCode) {
			case 1:
				alert("이미 가입된 id 입니다!");
				break;
			case 2:
				alert("회원가입 처리가 실패하였습니다. 잠시 후 다시 시도해 주십시오.");
				break;
			}
		}
	}
	
	function passwordCheck(){
		if($("#member_password").val() != $("#userPwCheck").val()){
			alert("패스워드 입력이 일치하지 않습니다");
			$("#userPwCheck").focus();
			return false;
		}		
		return true;
	}
</script>
</head>
<body onload="errCodeCheck()">
<div class="wrapper">
	<h3>회원가입</h3>
	<spring:hasBindErrors name="MemberModel" />
	<form:errors path="MemberModel" />
	<form action="join.do" method="post" onsubmit="return passwordCheck()">
		<fieldset>
			<label for="member_id">&nbsp;아이디 : </label>
			<input type="text" id="member_id" name="member_id" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.member_id" /></span><br />
			<label for="member_password">&nbsp;비밀번호 : </label>
			<input type="password" id="member_password" name="member_password" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.member_password" /></span><br />
			<label for="userPwCheck">&nbsp;비밀번호 확인 : </label>
			<input type="password" id="userPwCheck" name="userPwCheck" class="loginInput"/><br />
			<label for="member_email">&nbsp;이메일 : </label>
			<input type="text" id="member_email" name="member_email" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.member_email" /></span><br /><br />
			<label for="member_phone">&nbsp;폰번호 : </label>
			<input type="text" id="member_hp" name="member_hp" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.member_hp" /></span><br /><br />
			<label for="password_hint">&nbsp;패스워드 힌트 : </label>
			<input type="text" id="password_hint" name="password_hint" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.password_hint" /></span><br /><br />
			<center>
			<input type="submit" value="확인" class="submitBt"/>
			<input type="reset" value="재작성" class="submitBt" /><br /><br />
			<a href="<%=request.getContextPath()%>/login.do">로그인 페이지로 돌아가기</a>
			</center>			
		</fieldset>
	</form>
</div>
</body>
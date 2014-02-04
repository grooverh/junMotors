<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 글 쓰기</title>
<link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	function writeFormCheck() {
		if($("#board_title").val() == null || $("#board_title").val() == ""){
			alert("제목을 입력해 주세요!");
			$("#board_title").focus();
			return false;
		}
		
		if($("#board_content").val() == null || $("#board_content").val() == ""){
			alert("내용을 입력해 주세요!");
			$("#board_content").focus();
			return false;
		}		
		return true;
	}
	
</script>
</head>
<body>
<div class="wrapper">	
	<h3>새 글 쓰기</h3>
	<form action="write.do" method="post" onsubmit="return writeFormCheck()" enctype="multipart/form-data">
	<table class="boardWrite">	
		<tr>
			<th><label for="title">제목</label></th>
			<td>
				<input type="text" id="board_title" name="board_title" class="boardSubject"/>
				<input type="hidden" id="board_writer" name="board_writer" value="${member_id}" />
			</td>			
		</tr>
		<tr>
			<th><label for="password">비빌번호</label></th>
			<td>
				<input type="password" id="board_password" name="board_password" class="boardSubject"/>
			</td>			
		</tr>
		<tr>
			<th><label for="content">내용</label></th>
			<td><textarea id="board_content" name="board_content" class="boardContent"></textarea></td>			
		</tr>
		<tr>
			<th><label for="boardType_num">게시판 유형</label></th>
			<td>
			<select id="boardtype_num" name="boardtype_num">
				<option value="1">자유게시판</option>
				<option value="2">공지사항</option>
				<option value="3">사진첩</option>
			</select>
			</td>			
		</tr>
		<tr>
			<th><label for="file">파일</label></th>
			<td><input type="file" id="file" name="file" /><span class="date">&nbsp;&nbsp;*&nbsp;임의로 파일명이 변경될 수 있습니다.</span></td>			
		</tr>
		<tr>
			<th><label for="file">파일2</label></th>
			<td><input type="file" id="file2" name="file2" /><span class="date">&nbsp;&nbsp;*&nbsp;임의로 파일명이 변경될 수 있습니다.</span></td>			
		</tr>
	</table>
	<br />
	<input type="reset" value="재작성" class="writeBt"/>
	<input type="submit" value="확인" class="writeBt"/>	
	</form>
</div>
</body>
</html>
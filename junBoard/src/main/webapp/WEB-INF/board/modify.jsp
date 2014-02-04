<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정: ${board.board_title}</title>
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
		if($("#board_password").val() == null || $("#board_password").val() == ""){
			alert("비빌번호를 입력해 주세요!");
			$("#board_password").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="wrapper">	
	<h3>새 글 쓰기</h3>
	<form action="modify.do" method="post" onsubmit="return writeFormCheck()"  enctype="multipart/form-data">	
	<table class="boardWrite">	
		<tr>
			<th>제목</th>
			<td>
				<input type="text" id="board_title" name="board_title" class="boardSubject" value="${board.board_title}"/>
				<input type="hidden" id="board_writer" name="board_writer" value="${member_id}" />
				<input type="hidden" id="board_no" name="board_no" value="${board.board_no}" />
			</td>			
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" id="board_password" name="board_password" class="boardSubject"/>
			</td>			
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea id="board_content" name="board_content" class="boardContent">${board.board_content}</textarea></td>			
		</tr>
		<tr>
			<th><label for="file">파일</label></th>
			<td>
				<input type="file" id="newFile" name="newFile" /><p>업로드된 파일: ${board.board_file1}</p>
				<input type="hidden" id="orgFile" name="orgFile" value="${board.board_file1}" />
			</td>			
		</tr>				
	</table>
	<br />
	<input type="reset" value="재작성" class="writeBt"/>
	<input type="submit" value="확인" class="writeBt"/>	
	</form>
</div>
</body>
</html>
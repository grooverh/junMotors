<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 보기: ${board.board_title}</title>
<link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function errCodeCheck(){
		var errCode = <%=request.getParameter("errCode")%>;
		if(errCode != null || errCode != ""){
			switch (errCode) {
			case 1:
				alert("잘못된 접근 경로입니다!");
				break;
			case 2:
				alert("댓글이 있어 글을 삭제하실 수 없습니다!");
				break;
			case 3:
				alert("비밀 번호가 일치 하지 않아 수정 불가능합니다.");
				break;
			
			}
		}		
	}
	
	function commentDelete(comment_no, linkedArticleNum){
		if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
			location.replace("commentDelete.do?comment_no=" + comment_no + "&linkedArticleNum=" + linkedArticleNum);
		}		
	}
	
	function moveAction(where){
		switch (where) {
		case 1:
			if(confirm("글을 삭제하시겠습니까?")){
				location.href ="delete.do?board_no=${board.board_no}";
			}
			break;

		case 2:
			if(confirm("글을 수정하시겠습니까?")){
				location.href = "modify.do?board_no=${board.board_no}";
			}
			break;
			
		case 3:
			
			location.href = "list.do?boardtype_num=${board.boardtype_num}";			
			//javascript:history:back(0);return false;			
			break;
		
		case 4:
			if(confirm("글을 추천하시겠습니까?")){
				location.href = "recommend.do?board_no=${board.board_no}";
			}
			break;
		}
	}
</script>
</head>
<body onload="errCodeCheck()">
<div class="wrapper">	
	<table class="boardView">
		<tr>
			<td colspan="4"><h3>${board.board_title}</h3></td>
		</tr>
		<tr>
			<th>작성자</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>작성일</th>
		</tr>
		<tr>
			<td>${board.board_writer}</td>
			<td>${board.board_hit}</td>
			<td>${board.board_RECOMMENDCOUNT}</td>
			<td>${board.board_regdate}</td>
		</tr>
		<tr>
			<th colspan="4">내용</th>
		</tr>
		<c:if test="${board.board_file1 != null }">
		<tr>
			<td colspan="4" align="left"><span class="date">첨부파일:&nbsp;<a href="<%=request.getContextPath()%>/files/${board.board_file1}" target="_blank">${board.board_file1}</a></span></td>
		</tr>
		</c:if>	
		<c:if test="${board.board_file2 != null }">
		<tr>
			<td colspan="4" align="left"><span class="date">첨부파일:&nbsp;<a href="<%=request.getContextPath()%>/files/${board.board_file2}" target="_blank">${board.board_file2}</a></span></td>
		</tr>
		</c:if>	
		<tr>
			<td colspan="4" align="left"><p>${board.board_content}</p><br /><br /></td>
		</tr>		
	</table>
	<table class="commentView">
		<tr>
			<th colspan="2">댓글</th>
		</tr>		
		<c:forEach var="comment" items="${commentList}">
		<tr>
			<td class="writer">				
				<p>${comment.comment_writer}
				<c:if test="${comment.comment_writer == member_id}">
					<br /><a onclick="commentDelete(${comment.comment_no}, ${board.board_no})"><small>댓글 삭제</small></a>					
				</c:if>
				</p>
			</td>
			<td class="content" align="left">
				<span class="date">${comment.comment_regdate}</span>
				<p>${comment.comment_content}</p>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td class="writer"><strong>댓글 쓰기</strong></td>
			<td class="content">
				<form action="commentWrite.do" method="post">
					<input type="hidden" id="comment_writer" name="comment_writer" value="${member_id}" />
					<input type="hidden" id="linkedArticleNum" name="linkedArticleNum" value="${board.board_no}" />
					<textarea id="comment_content" name="comment_content" class="commentForm"></textarea>
					<input type="submit" value="확인" class="commentBt" />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<c:choose>
		<c:when test="${board.board_writer == member_id}">
			<input type="button" value="삭제" class="writeBt" onclick="moveAction(1)" />
			<input type="button" value="수정" class="writeBt" onclick="moveAction(2)" />
			<input type="button" value="목록" class="writeBt" onclick="moveAction(3)" />
		</c:when>
		<c:otherwise>
			<input type="button" value="추천" class="writeBt" onclick="moveAction(4)" />
			<input type="button" value="목록" class="writeBt" onclick="moveAction(3)" />
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>
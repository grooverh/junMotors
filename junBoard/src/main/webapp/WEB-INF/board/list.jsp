<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>준우스프링 게시판</title>
<link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
<!--
	function selectedOptionCheck(){
		$("#type > option[value=<%=request.getParameter("type")%>]").attr("selected", "true");
	}
	
	function moveAction(where){
		switch (where) {
		case 1:
			location.href = "write.do";
			break;
		
		case 2:
			location.href = "list.do?boardtype_num=${boardtype_num}";
			break;
		}
	}
//-->
</script>
</head>
<body onload="selectedOptionCheck()">
<div class="wrapper">
	<div class="navBar">
		<ul>
			<li><a href="list.do?boardtype_num=4">스프링  게시판</a></li>
			<li><a href="../logout.do">로그아웃</a></li>		
			<li>접속중인 id : ${member_id}</a></li>	asdasd:	${boardtype_num}
		</ul>
		<form action="list.do" method="get">		
			<select id="type" name="type">
				<option value="board_title">제목</option>
				<option value="board_content">내용</option>
				<option value="board_writer">작성자</option>
			</select>
			<input type="text" id="keyword" name="keyword" value="<%if(request.getParameter("keyword") != null){ out.print(request.getParameter("keyword")); } else { out.print(""); }%>" />
			<input type="submit" value="검색" />	
			<input type="hidden" id="boardtype_num" name="boardtype_num" value="${ boardtype_num }"/>		
		</form>
	</div>
	<div class="navBar">
		<ul>
		 	<li><a href="list.do?boardtype_num=4">전체글</a></li>
			<li><a href="list.do?boardtype_num=2">공지사항</a></li>
			<li><a href="list.do?boardtype_num=1">자유게시판</a></li>		
			<li><a href="list.do?boardtype_num=3">사진첩</a></li>		
		</ul>
	</div>	
	
	<table border="0" class="boardTable">
		<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>글 유형</th>
			<th>추천수</th>
			<th>작성일</th>	
		</tr>
		</thead>
		<tbody>
		<c:forEach var="board" items="${boardList}">
		<tr>
			<td class="idx">${board.rnum}</td>
			<td align="left" class="subject">
				<c:if test="${board.board_comment >= 10}"><img src="<%=request.getContextPath()%>/img/hit.jpg" /></c:if>
				<a href="view.do?board_no=${board.board_no}">${board.board_title}</a></td>
			<td class="writer"><c:choose><c:when test="${board.board_writer == member_id}"><strong>${board.board_writer}</strong></c:when><c:otherwise>${board.board_writer}</c:otherwise></c:choose></td>
			<td class="hitcount">${board.board_hit}</td>
			<td class="boardtypename">${board.boardtype_name}</td>
			<td class="recommendcount">${board.board_RECOMMENDCOUNT}</td>
			<td class="writeDate">${board.board_regdate}</td>		
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<br />
	${pageHtml}
	<br /><br />
	<input type="button" value="목록" class="writeBt" onclick="moveAction(2)"/>
	<input type="button" value="쓰기" class="writeBt" onclick="moveAction(1)"/>	
</div>
</body>
</html>
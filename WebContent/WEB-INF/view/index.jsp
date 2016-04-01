<%@ page import="java.util.List"%>
<%@ page import="com.estsoft.DB.MySQLWebDBConnection"%>
<%@ page import="com.estsoft.GuestBook.dao.GuestBookDao" %>
<%@ page import="com.estsoft.GuestBook.vo.GuestBookVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
String newLine = "\r\n";
pageContext.setAttribute(newLine, "\r\n");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/GuestBook2/el" method="post">
	<input type="hidden" name="a" value="add">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br>
	
	<!-- fn을 이용해 java ftn같은거 대신함 -->
	<c:set var="count" value="${fn.length(list) }"/>
	${count }<br>
	<c:forEach items="${list}" var="vo" varStatus="status">
	<table width=510 border=1>
		<tr>
			<td>[<${count}-status.index]</td>
			<td><${vo.name}></td>
			<td><${vo.reg_date}></td>
			<td><a href="/GuestBook2/el?a=deleteform&id=<${vo.no}>">삭제 </a></td>
		</tr>
		<tr>
		<!-- .replace()대신에 fn을 쓴다  // 여기서 newLine은 바로 받을 수 있다는 점 이 중요하다-->
			<td colspan=4>${fn.replace(newLine,"\r\n","<br>")} </td>
		</tr>
	</table>
	</c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("utf-8");
String test = request.getParameter("test");
%>
<center>
<table border="1" cellspacing="0">
<tr>
	<td bgcolor="aqua"><b>전달받은값</b></td>
	<td><%= test%></td>
</tr>
</table>
</center>
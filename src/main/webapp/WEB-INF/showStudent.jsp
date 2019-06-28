
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Studentをimportする -->
<%@ page import= "beans.Student" %>

<html>
<head><title>結果</title></head>

<body bgcolor="skyblue">
<br>

<table border="1" width="100%">
	<tr>
		<td width = "40%"> <center>ID</center> </td>
		<td width = "60%"> <center>${Student.studentID} </center> </td>
	</tr>
	<tr>
		<td width = "40%"> <center>名前</center> </td>
		<td width = "60%"> <center>${Student.studentName} </center> </td>
	</tr>
	<tr>
		<td width = "40%"> <center>出身地</center> </td>
		<td width = "60%"> <center>${Student.studentBirthplace} </center> </td>
	</tr>
</table>
<br>
</body>
</html>
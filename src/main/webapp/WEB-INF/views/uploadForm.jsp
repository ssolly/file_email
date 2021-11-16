<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadFom</title>
</head>
<body>

	<h2> File Upload</h2>
	<form action="${contextPath }/upload" method="post" enctype="multipart/form-data">
	<!-- fileupload는 무조건 post타입과 multipart 설정 필요 -->
		<input type="text" name="id"><br>
		<input type="text" name="name"><br>
		<input type="file" name="file"><br>
		<input type="submit" value="upload"><br>
	</form>

	<hr>
	<a href="${contextPath }/views"> 파일보기</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<meta charset="UTF-8">
<title>Tu Aplicación</title>
</head>
<body>
	<%@ include file="../contenidos/header.jsp"%>


	<%@ include file="../menu/menu.jsp"%>


	<div id="content">
		<jsp:include page="${content}" />
	</div>


	<%@ include file="../contenidos/footer.jsp"%>
</body>
</html>

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
<body class="bg-light">
<%@ include file="contenidos/header.jsp" %>

<div id="content">
    <c:choose>
        <c:when test="${not empty content}">
            <jsp:include page="${content}" />
        </c:when>
        <c:otherwise>
            <%@ include file="view/contenidoPrincipal.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="contenidos/footer.jsp" %>
</body>
</html>


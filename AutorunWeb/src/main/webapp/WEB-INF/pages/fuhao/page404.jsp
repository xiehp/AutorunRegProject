<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h2>对不起， 你访问的页面</h2>
	<p>java:<%=request.getRequestURL()%></p>
	<p>jstl:${pageContext.request.contextPath}</p>
	<p>jstl:${pageContext.request.requestURL}</p>
	<h2>不存在。</h2>



	<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
	Spring URL: ${springUrl} at ${time}
	<br>
	JSTL URL: ${url}
	<br>
	Message: ${message}
</body>
</html>

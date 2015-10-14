
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello world</title>
</head>
<body>
	Hello world!
	<br /> ${AAA}
	<br /> ${param.AAA}
	<br /> ${requestScope.AAA}
	<br />

	<%=request.getAttribute("AAA")%><br />

	<c:out value="${AAA}" />
	BBBBBBBBB
	<br />

	<c:out value="${AAAA}" />
	BBBBBBB
	<br />
<form action="app/view/view2"></form>
</body>
</html>

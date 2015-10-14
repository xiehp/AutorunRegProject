<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
</head>
<body>
	<h2>\webapp\WEB-INF\pages的index.jsp</h2>
	request.getContextPath(): <%=request.getContextPath() %><br />
	request.getRequestURI(): <%=request.getRequestURI() %><br />
	request.getRequestURL(): <%=request.getRequestURL() %><br />
	request.getServletPath(): <%=request.getServletPath() %><br />
	request.getRemoteAddr(): <%=request.getRemoteAddr() %><br />
	request.getServletContext().getRealPath(): <%=request.getServletContext().getRealPath("") %><br />
	request.getServletContext().getServletContextName(): <%=request.getServletContext().getServletContextName() %><br />
	<br /><br />

	request.getContextPath(): <%=request.getAttribute("request.getContextPath") %><br />
	request.getRequestURI(): <%=request.getAttribute("request.getRequestURI") %><br />
	request.getRequestURL(): <%=request.getAttribute("request.getRequestURL") %><br />
	request.getServletPath(): <%=request.getAttribute("request.getServletPath") %><br />
	request.getRemoteAddr(): <%=request.getAttribute("request.getRemoteAddr") %><br />
	request.getServletContext().getRealPath: <%=request.getAttribute("request.getServletContext().getRealPath(\"\")") %><br />
	request.getServletContext().getServletContextName: <%=request.getAttribute("request.getServletContext().getServletContextName()") %><br />



</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<script src="${pageContext.request.contextPath}/resource/js/site/ajax.js"></script>
	<script>
		function submitRegist() {
			var registSerialNumber = document.getElementById("registSerialNumber").value;
			requestAjax("${pageContext.request.contextPath}/register/registerInfo?serialNumber="+registSerialNumber, function(jsonData) {
				document.getElementById("registResultSuccess").value = jsonData.success;
				document.getElementById("registResultMessage").value = jsonData.message;
				document.getElementById("registResultData").value = JSON.stringify(jsonData.result.data);
			})
		}
	</script>
</head>
<body>
<br />
<br />
<br />
<br />
<form id="viewForm" action="${pageContext.request.contextPath}/RegInfo/view">
	请输入要查看的注册码：<input name="serialNumber" value="${serialNumber}" />
	<input type="submit">

</form>
<form id="registForm" action="${pageContext.request.contextPath}/register/registerInfo">
	请输入要注册的注册码：<input id="registSerialNumber" value="${serialNumber}" />
	<input type="button" onclick="submitRegist()" value="注册"><br>

	结果：<input id="registResultSuccess"><br>
	信息：<input id="registResultMessage"><br>
	数据：<input id="registResultData"><br>
</form>

<p>
<c:choose>
	<c:when test="${registerInfo != null}">
	<ul>
		<li>
			注册码：${registerInfo.serialNumber}
		</li>
		<li>
			注册时间：${registerInfo.createDate}
		</li>
	</ul>

	</c:when>
	<c:otherwise>
		输入的注册码[${serialNumber}]不存在
	</c:otherwise>
</c:choose>
</p>


</body>
</html>

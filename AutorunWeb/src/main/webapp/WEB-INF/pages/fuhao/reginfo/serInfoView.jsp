<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<%@ include file="../include/commonHead.jspf"%>

<script>
	function submitRegist() {
		var registSerialNumber = document.getElementById("registSerialNumber").value;
		requestAjax("register/doRegisterInfo?serialNumber=" + registSerialNumber, function(jsonData) {
			document.getElementById("registResultSuccess").value = jsonData.success;
			document.getElementById("registResultMessage").value = jsonData.message;
			document.getElementById("registResultData").innerText = JSON.stringify(jsonData.result.data);
		})
	}
</script>
</head>
<body>
	<br />
	<br />
	<br />
	<br />
	<form id="viewForm" action="RegInfo/view">
		请输入要查看的注册码：
		<input name="serialNumber" value="${serialNumber}" />
		<input type="submit">

	</form>
	<form id="registForm" action="register/doRegisterInfo">
		<nobr>请输入要注册的注册码：</nobr>
		<input id="registSerialNumber" value="${serialNumber}" />
		<input type="button" onclick="submitRegist()" value="注册">
		<br>
		<nobr>结果：</nobr>
		<input id="registResultSuccess">
		<br>
		<nobr>信息：</nobr>
		<input id="registResultMessage">
		<br>
		<nobr>数据：</nobr>
		<label id="registResultData"></label><br>
	</form>

	<p>
		<c:choose>
			<c:when test="${registerInfo != null}">
				<ul>
					<li>注册码：${registerInfo.serialNumber}</li>
					<li>注册时间：${registerInfo.createDate}</li>
					<li>注册信息：${registerInfo}</li>
				</ul>

			</c:when>
			<c:otherwise>
		输入的注册码[${serialNumber}]不存在
	</c:otherwise>
		</c:choose>
	</p>

	<p></p>
	<p>check文件
	<form id="checkNewVersionfile" action="check/getNewVersionName">
		<input id="clientVersion" value="${clientVersion}" />
		<input type="submit" value="check">
	</form>
	</p>
	下载文件
	<form id="downloadNewVersionfile" action="download/downloadNewVersion">
		请输入要注册的注册码：
		<input id="clientVersion" value="${clientVersion}" />
		<input type="submit" value="download">
	</form>
	</p>

</body>
</html>

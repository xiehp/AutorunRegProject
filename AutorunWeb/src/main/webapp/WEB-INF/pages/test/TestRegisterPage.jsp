<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello world</title>

<script type="text/javascript">
	function registetr() {
		post("register/doRegisterInfo");
	}

	function check() {
		post("register/getRegisterInfo");
	}
	
	function post(url) {
		var contextPath = "<%=request.getContextPath()%>";
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState == 0) {
				document.getElementById("labelRegisterResult").innerHTML="0 （未初始化）";
			} else if (httpRequest.readyState == 1) {
				document.getElementById("labelRegisterResult").innerHTML="1 （初始化）";
			} else if (httpRequest.readyState == 2) {
				document.getElementById("labelRegisterResult").innerHTML="2 （发送数据）";
			} else if (httpRequest.readyState == 3) {
				document.getElementById("labelRegisterResult").innerHTML="3 （数据传送中）";
			} else if (httpRequest.readyState == 4 && httpRequest.status == 200) {
				//document.getElementById("textRegisterResult").value=httpRequest.responseText;
				document.getElementById("labelRegisterResult").innerHTML = httpRequest.responseText;
				//document.getElementById("labelRegisterResult2").innerHTML = httpRequest.responseText;
			} else {
				document.getElementById("labelRegisterResult").innerHTML="4 （完成） 返回错误, status:" + httpRequest.status;
			}
		}

		httpRequest.open("POST", contextPath + url, true);
		httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var serialNumberName = document.getElementById("serialNumberName").value;
		var serialNumberValue = document.getElementById("serialNumberValue").value;
		var pcInfoName = document.getElementById("pcInfoName").value;
		var pcInfoValue = document.getElementById("pcInfoValue").value;
		httpRequest.send(serialNumberName + "=" + serialNumberValue + "&" + pcInfoName + "=" + pcInfoValue);
		
		
	}
</script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td><input type="text" id="serialNumberName" value="serialNumber" /></td>
				<td><input type="text" id="serialNumberValue" value="1" /></td>
			</tr>
			<tr>
				<td><input type="text" id="pcInfoName" value="pcInfo" /></td>
				<td><input type="text" id="pcInfoValue" value="pcInfoValue" /></td>
			</tr>
			<tr>
				<td>
					<button type="button" onclick="registetr();">注册</button>
				</td>
			</tr>
		</table>
	</div>
	<div>
		<table>
			<tr>
				<td>
					<button type="button" onclick="check();">检测</button>
				</td>
			</tr>
		</table>
	</div>

	<div id="divRegisterResult">
		<table>
			<tr>
				<td><input type="text" id="textRegisterResult" ></input></td>
			</tr>
			<tr>
				<td><label id="labelRegisterResult" >labelRegisterResult</label></td>
			</tr>
			<tr>
				<td id="labelRegisterResult2"></td>
			</tr>
		</table>
	</div>
	<div id="divCheckResult"></div>
</body>
</html>

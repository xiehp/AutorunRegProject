<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	function loadXMLDoc()
	{
		var xmlhttp;
		var txt,x,i;
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				xmlDoc=xmlhttp.responseXML;
				txt="";
				x=xmlDoc.getElementsByTagName("title");
				for (i=0;i<x.length;i++)
				{
					txt=txt + x[i].childNodes[0].nodeValue + "<br />";
				}
				document.getElementById("myDiv").innerHTML=txt;
			}
		}
		xmlhttp.open("GET","/example/xmle/books.xml",true);
		xmlhttp.send();
	}
</script>
</head>
<body>
<br />
<br />
<br />
<br />
<form action="view">
	请输入要查看的注册码：<input name="serialNumber" value="${serialNumber}" />
	<input type="submit">

</form>
<form action="regist">
	请输入要注册的注册码：<input name="serialNumber" value="${serialNumber}" />
	<input type="submit">

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

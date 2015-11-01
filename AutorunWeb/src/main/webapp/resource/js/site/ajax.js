function requestAjax(url, successCallback, errorCallback) {
	var xmlHttp;
	var txt, x, i;
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlHttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlHttp.onreadystatechange = function () {
		var ccc = xmlHttp.responseType;
		var aaa = xmlHttp.responseXML;
		var bbb = xmlHttp.responseText
		if(xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200 || xmlHttp.status == 0) {
				if (successCallback != null) {
					var jsonData = JSON.parse(xmlHttp.responseText);
					successCallback(jsonData);
				}
			} else {
				if (errorCallback != null) {
					errorCallback(xmlHttp.responseText);
				} else {
					alert("提交发生错误")
				}
			}
		}

	}
	xmlHttp.open("GET", url, true);
	//xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send();
}

function encode(str) {
	//str = str.re
}
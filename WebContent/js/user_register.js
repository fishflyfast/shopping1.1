/*用户注册检查*/

function CheckRegisterForm(){
	if(document.registerForm.username.value.length == 0){
		alert('请输入用户名');
		document.registerForm.username.focus();
		return false;
	}
	
	if(document.registerForm.password.value.length == 0){
		alert('请输入密码');
		document.registerForm.password.focus();
		return false;
	}
	
	if(document.registerForm.repassword.value != document.registerForm.password.value){
		alert('两次密码不一样');
		document.registerForm.repassword.focus();
		return false;
	}
	if(document.registerForm.phone.value.length == 0){
		alert('请输入联系方式');
		return false;
	}
	
	if(document.registerForm.addr.value.length == 0){
		alert('请输入联系地址');
		return false;
	}
	return true;
}

/**Ajax请求验证用户名是否存在*/
function usernameValidate(){
	var username = document.registerForm.username.value;
	var url = "usernameValidate.do?username="+username;
	if(window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET",url, true);
	req.onreadystatechange = callback;
	req.send(null);
}

/**
 * 回调函数
 */
function callback(){
	if(req.readyState == 4){
		if(req.status == 200){
			var msg = req.responseXML.getElementsByTagName("msg")[0];
			
			setMsg(msg.childNodes[0].nodeValue);
		}
	}
}

/**
 * 把提示信息加到字段后面去
 */
function setMsg(msg){
	if(eval(msg))
		document.getElementById("usernameMsg").innerHTML="<img src=./images/no.gif>";
	else
		document.getElementById("usernameMsg").innerHTML="<img src=./images/yes.gif>";
}
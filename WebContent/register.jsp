<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>用户注册</title>
<script type="text/javascript" src="./js/user_register.js"></script>

</head>
<body>
<div align="center" style="margin:-8px 0 -8px 0;">
  <table width="1024" border="0" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr>
      <td width="200" rowspan="2" valign="top" bgcolor="#FFFFFF"><img src="images/logo.jpg" /></td>
      <td width="824" height="26" valign="top"><div align="right">
      
      hi，<c:if test="${username==null}"><c:set var="username" value="游客" /></c:if><font size="6" color="red">${username } </font>欢迎来我佳炒货网上商城！<a href="index.do">首页</a>请<a href="#">登录</a> <a href="#">免费注册</a> <a href="#">我的订单</a> <a href="#" class="btn">
	  <span class="btn_word"><img src="images/cart.jpg" />购物车0件&nbsp;<a href="unlogin.do">注销</a></span>
	  <span class="btn_right"></span>
	</a>

</div></td>
    </tr>
    <tr>
      <td height="54" valign="top">
			&nbsp;</td>
    </tr>
  </table>
</div>

<center><h1>用户注册</h1></center>

<hr>
<!-- 幻灯片end -->
	<table style="margin:-8px 0 -8px 0;" width="100%" height="300" bgcolor="#D6F7F8">
	<tr><td>
	<div><font color="red">${msg }</font></div>
	<div align="center"><font color="red">请先注册以后再进行购物，注意：注册的信息将是我们送货的信息，希望您填写正确详细的信息。谢谢合作！</font></div>
	<form  name="registerForm" action="user_register.do" method="post" onSubmit="return CheckRegisterForm()">
	<table bgcolor="#D6F7F8" width="600" align="center" border="0">
		
		<tr>
			<td>用户名:</td>
			<td><input type="text" size="15" maxlength="18" name="username" id="username" onblur="usernameValidate()"/>(<font color="red">*</font>)</td>
			<td align="left" width="10"><div id="usernameMsg"></div></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" size="15" maxlength="25" name="password">(<font color="red">*</font>)</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>确认密码:</td>
			<td><input type="password" size="15" maxlength="25" name="repassword">(<font color="red">*</font>)</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>电话:</td>
			<td><input type="text" size="25" maxlength="25" name="phone">(<font color="red">*</font>)</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>性别:</td>
			<td><input type="radio" name="sex" checked="checked" value="true" />女
    		    <input type="radio" name="sex" value="false" />男</td>
		</tr>
		<tr>
			<td>QQ号码:</td>
			<td><input type="text" size="15" maxlength="10" name="QQ"></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" size="50" maxlength="50" name="email"></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td><input type="text" size="70" maxlength="89" name="addr" >(<font color="red">*</font>)</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="同意协议并注册"><input type="reset" value="重填"></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="#">《我佳炒货服务协议》</a></td>
		</tr>
	</table>
	</form>
	</td>
	</tr>
	</table>
<hr>
<!--footer-->
<div align="center">
  <table width="1024" border="0" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr>
      <td width="429" height="35" valign="top">
  	    <div align="right"><img src="images/biaoshi.gif" border="0" height="35" width="28">        </div></td>
	  <td width="595" align="left" valign="top"><div align="left">版权所有:贵州省平坝县夏云我佳炒货店<br>
	    服务电话：13985327839<br>
      运作日期：2012-11-26</div></td>
    </tr>
  </table>
</div>	
</body>
</html>
<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head></head>
<body>
<div align="center" style="margin:-8px 0 -8px 0;">
  <table  width="1024" border="0" cellpadding="0" cellspacing="0">
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
<hr>

<center>
<table height="70%">
<tr style="margin:50% auto;">
	<td>
	<center><h1>谢谢您在本站购物!欢迎<a href="./index.do"><font color="green">继续</font></a>。否则<a href="javascript:if(document.all){window.open('', '_parent', '');window.close();}else{window.open('', '_self', '');window.close();};"><font color="red">关闭</font></a></h1></center>
	</td>
</tr>
</table>
</center>

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

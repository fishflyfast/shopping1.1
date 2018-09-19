<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>定单详细信息</title>
<style type="text/css">
<!--
#senfe {
	width: 60%;
	border-top: #000 1px solid;
	border-left: #000 1px solid;
}
#senfe td {
	border-right: #000 1px solid;
	border-bottom: #000 1px solid;
	
}
.STYLE1 {
	font-size: 20px;
	font-weight: bold;
}
--></style>
<script language="javascript">
function senfe(o,a,b,c,d){
	var t=document.getElementById(o).getElementsByTagName("tr");
	for(var i=0;i<t.length;i++){
		t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
		t[i].onclick=function(){
			if(this.x!="1"){
				this.x="1";//本来打算直接用背景色判断，FF获取到的背景是RGB值，不好判断
				this.style.backgroundColor=d;
			}else{
				this.x="0";
				this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
			}
		}
		t[i].onmouseover=function(){
			if(this.x!="1")this.style.backgroundColor=c;
		}
		t[i].onmouseout=function(){
			if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
		}
	}
}
</script>



</head>
<body>
<div align="center" style="margin:-8px 0 -8px 0;">
  <table  width="1024" border="0" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr>
      <td width="200" rowspan="2" valign="top" bgcolor="#FFFFFF"><img src="images/logo.jpg" /></td>
      <td width="824" height="26" valign="top"><div align="right">
      
       hi，<c:if test="${user.name==null}">游客</c:if><font size="6" color="red">${user.name } </font>欢迎来我佳炒货网上商城！<a href="index.do">首页</a>请<a href="#">登录</a> <a href="index.do">首页</a><a href="register.jsp">免费注册</a> <a href="#">我的订单</a> <a href="#" class="btn">
       <span class="btn_word"><img src="images/cart.jpg" />购物车${size }种&nbsp;<a href="unlogin.do">注销</a></span>
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

<!-- 内容区 -->
	<!-- 修改回馈信息显示 -->
<center><font color="red">${msg }</font></center>

<hr>
<table style="margin:-8px 0 -8px 0;" width="100%" height="300" bgcolor="#D6F7F8">
<tr><td>
<!-- 定单 start -->
	<center><h3>${ordermsg }</h3></center>
	<center><h2><font color="red">定&nbsp;单</font></center>
	<center>
	<form action="confirm_order.do" method="post">
		<table width="60%">
			<tr>
				<td>客户名称：<input size="8" type=text name="user" value="${order.user }" readonly></td>
				<td>定单编号：<input size="13" type=text name="orderCode" value="${order.orderCode }" readonly></td>
				<td>日期：<input size="18" type=text name="orderDate" value="${order.odate }" readonly></td>
			</tr>
			<tr>
				<td colspan="1" >电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input type=text name="phone" value="${order.phone }" size="10"></td>
				<td colspan="2" >&nbsp;E&nbsp;m&nbsp;a&nbsp; i&nbsp;&nbsp;l&nbsp;：<input type=text name="email" value="" size="54%"></td>
			</tr>
			<tr>
				<td colspan="1" >QQ&nbsp;&nbsp;号码：<input type=text name="qq" value="${order.QQ }" size="10"></td>
				<td colspan="2" >备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：<input type=text name="remark" value="${order.remark }" size="54%"></td>
			</tr>
			<tr>
				<td colspan="3" >送货地址：<input type=text name="address" value="${order.addr }" size="75"></td>
			</tr>
			</table>
			<table id="senfe" width="60%" border="0" cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle" bgcolor="#6633FF">
				<td><div align="center" class="STYLE1">商品ID</div></td>
				<td><div align="center" class="STYLE1">商品名称</div></td>
				<td><div align="center" class="STYLE1">商品价格</div></td>
				<td><div align="center" class="STYLE1">购买数量</div></td>
				<td><div align="center" class="STYLE1">金额</div></td>
			</tr>
			<%int i=0; %>
			<c:forEach items="${items}" var="c">
			  <tr align="center" valign="middle" bgcolor="#99FF33">
				<td>${c.productId }</td>
				<td>${c.productName }</td>
				<td>${c.price }</td>
				<td>${c.number }</td>
				<td><fmt:formatNumber value="${c.price*c.number }" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></td>
			 </tr>
			 <%i++; %>
			 </c:forEach>
		</table>
			
			共${totalPrice }元
			<input type="submit" value="确定订单"></input>
			
	</form>
	</center>
<!-- 定单 end -->
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
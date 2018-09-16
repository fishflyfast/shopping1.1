<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>你的定单信息</title>
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
<script language="javascript"><!--
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
--></script>



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
<hr>
<table style="margin:-8px 0 -8px 0;" width="100%" height="300" bgcolor="#D6F7F8">
<tr><td>
<!-- 定单 start -->
	<center>您好 <font color="red">${user.name }</font>！ 您的定单列表如下：</center>
	<center>
	
		
			<table id="senfe" width="60%" border="0" cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle" bgcolor="#6633FF">
				<td><div align="center" class="STYLE1">定单编号</div></td>
				<td><div align="center" class="STYLE1">定单日期</div></td>
				<td><div align="center" class="STYLE1">定单金额</div></td>
				<td><div align="center" class="STYLE1">状态</div></td>
				<td><div align="center" class="STYLE1">操作</div></td>
			</tr>
				<c:choose>
				<c:when test="${empty orders}">
					<tr align="center" valign="middle" bgcolor="#99FF33">
						<td colspan="5">没有数据</td>
					 </tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${orders}" var="o">
					  <tr align="center" valign="middle" bgcolor="#99FF33">
					  
						<td>${o.orderCode }</td>
						<td>${o.odate }</td>
						<td>${totalPrice }</td>
						<td>${o.status?"已付款":"未付款" }</td>
						<td><a href="orderDetail.do?orderCode=${o.orderCode  }">详细信息</a></td>
					 </tr>
					 </c:forEach>
			 </c:otherwise>
			 </c:choose>
		</table>
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
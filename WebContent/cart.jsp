<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>你的购物车</title>
<style type="text/css">
<!--
#senfe {
	width: 300px;
	border-top: #000 1px solid;
	border-left: #000 1px solid;
}
#senfe td {
	border-right: #000 1px solid;
	border-bottom: #000 1px solid;
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
      
       hi，<c:if test="${user.name==null}">游客</c:if><font size="6" color="red">${user.name } </font>欢迎来我佳炒货网上商城！<a href="index.do">首页</a>请<a href="#">登录</a> <a href="register.jsp">免费注册</a> <a href="#">我的订单</a> <a href="#" class="btn">
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
<center>您一共购买了这么多的商品:${size}</center>

<hr>
<table style="margin:-8px 0 -8px 0;" width="100%" height="300" bgcolor="#D6F7F8">
<tr><td>
<!-- 购物车 start -->
	<form action="buy_update.do" method="post">
			<table id="senfe" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td>商品ID</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>购买数量</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${cartList}" var="c">
			  <tr>
				<td>${c.productId }</td>
				<td>${c.productName }</td>
				<td>${c.price }</td>
				<td><input type="text" size=4 name="p${c.productId }" value="${c.count }"></td>
				<td><fmt:formatNumber value="${c.price*c.count}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></td>
			 </tr>
			 </c:forEach>
			</table>
			<script language="javascript"><!--
			//senfe("表格名称","奇数行背景","偶数行背景","鼠标经过背景","点击后背景");
			senfe("senfe","#fff","#ccc","#cfc","#f00");
		</script>

			
			<center>
			共${totalPrice }元
			<input type="submit" value="修改数量" />
			<input type="button" value="下订单" onclick="document.location.href='buy_update.do?confirm=xiadingdan'">
			</center>
			<center><a href="index.do"><font size=5>继续购买</font></a></center>
			</form>
<!-- 购物车end -->
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
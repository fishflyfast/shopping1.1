<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>我佳炒货网上商城</title>
<link href="images/css/top.css" rel="stylesheet" type="text/css" />
<link href="images/css/body.css" rel="stylesheet" type="text/css" />
<link href="images/css/menu.css" rel="stylesheet" type="text/css" />
<link href="images/css/leftmenu.css" rel="stylesheet" type="text/css" />
<link rel="images/css/index.css"/>
<script type="text/javascript" src="./js/userindex.js"></script>
<style>
*{margin:0 0 0 0;}
body,td,th {
	font-family: 新宋体;
	font-size: 14px;
}
A {
	TEXT-DECORATION: none
}
A:link {
	COLOR: #505050; color1: #54564c
}
A:visited {
	COLOR: #505050; color1: #54564c
}
A:hover {
	COLOR: #d40005; TEXT-DECORATION: underline
}
A:active {
	COLOR: #f30
}
IMG {
	BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px
}
.box {
	FLOAT: left; WIDTH: 1022px
}
.box .boxpadding {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; MARGIN-BOTTOM: 0px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px
}
#Slide {
	CLEAR: both; BORDER-RIGHT: #fff 0px solid; BORDER-TOP: #fff 0px solid; MARGIN-BOTTOM: 0px; OVERFLOW: hidden; BORDER-LEFT: #fff 0px solid; WIDTH: 1024px; BORDER-BOTTOM: #fff 0px solid; HEIGHT: 100px
}
#Slide A {
	COLOR: #000
}
.img {
	BORDER-RIGHT: #fff 0px solid; BACKGROUND: #fff; MARGIN: 0px auto; BORDER-LEFT: #fff 0px solid; TEXT-ALIGN: center
}
.boxpadding {
	BORDER-RIGHT: #fff 0px solid; BORDER-TOP: #fff 0px solid; BACKGROUND: #f8f8f8; MARGIN-BOTTOM: 1px; BORDER-LEFT: #fff 0px solid; PADDING-TOP: 1px; BORDER-BOTTOM: #fff 0px solid
}

.thumb_title {
	MARGIN-TOP: 5px; BACKGROUND: #fff; FILTER: alpha(opacity=0); WIDTH: 80px; TEXT-INDENT: 10px; LINE-HEIGHT: 25px; POSITION: absolute; HEIGHT: 15px; -moz-opacity: 0.3
}
#Slide_Thumb {
	MARGIN-TOP: 57px; MARGIN-LEFT: 0px; POSITION: absolute
}
.thumb_on {
	DISPLAY: inline; FLOAT: left; MARGIN-LEFT: 1px; CURSOR: pointer
}
.thumb_off {
	DISPLAY: inline; FLOAT: left; MARGIN-LEFT: 1px; CURSOR: pointer
}
.thumb_off {
	FILTER: alpha(opacity=50); -moz-opacity: 0.5
}
.thumb_on {
	FILTER: alpha(opacity=90); -moz-opacity: 1
}
.thumb_off IMG {
	BORDER-RIGHT: #fff 8px solid; BORDER-TOP: #fff 2px solid; MARGIN-TOP: 20px; BORDER-LEFT: #fff 1px solid; WIDTH: 15px; BORDER-BOTTOM: #fff 1px solid; HEIGHT: 16px
}
.thumb_on IMG {
	BORDER-RIGHT: #fff 8px solid; BORDER-TOP: #fff 2px solid; MARGIN-TOP: 20px; BORDER-LEFT: #fff 1px solid; WIDTH: 17px; BORDER-BOTTOM: #fff 0px solid; HEIGHT: 18px
}
body,p {
    margin: 0;
    padding: 0;
    text-align: center;
    font: normal 14px/180% Tahoma,sans-serif;
}
#loginBox {
    margin: 0 auto;
    padding: 0px;
    text-align: left;
    width: 280px;
    height: 150px;
    background: #EAEEFF;
    font-size: 9pt;
    border: 1px solid #829AFF;
    overflow: hidden;
    filter: alpha(opacity=90);
    opacity: 0.9;
}
#loginBox .title {
    text-align: left;
    padding-left: 10px;
    font-size: 11pt;
    border-bottom: 1px solid #829AFF;
    height: 25px;
    line-height: 25px;
    cursor: move;
}
#loginBox .t1 {
    float: left;
    font-weight: bold;
    color: #AA7B7B;
    text-decoration: none;
}
#loginBox .t2 {
    float: right;
    text-align: center;
    line-height: 18px;
    height: 18px;
    width: 18px;
    margin-top: 3px;
    margin-right: 2px;
    overflow: hidden;
    border: 1px solid #FF5889;
    background: #FFE0E9;
    cursor: pointer;
}
#loginBox .login {
    text-align: center;
    width: 100%;
    height: 100%;
}
input.submit {
    float: right;
    border: 1px solid #829AFF;
    FONT-SIZE: 9pt;
    background: #EAEEFF;
    HEIGHT: 20px;
    margin-top: 5px;
    margin-right: 70px;
}
#bgDiv {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    right: 0px;
    background-color: #777;
    filter: progid: DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75) filter: alpha(opacity=70);
    opacity: 0.7;
} 
body,div,ul,li{margin:0;padding:0;}
ul{list-style-type:none;}
body{text-align:center;font:12px/20px Arial;}
#box{position:relative;width:1020px;height:100px;background:#fff;border-radius:0px;border:1px dashed #CCC;margin:0px auto;cursor:pointer;}
#box .list{position:relative;width:1020px;height:100px;overflow:hidden;}
#box .list ul{position:absolute;top:0;left:0;}
#box .list li{width:1020px;height:100px;overflow:hidden;}
#box .count{position:absolute;right:0;bottom:5px;}
#box .count li{color:#fff;float:left;width:20px;height:20px;cursor:pointer;margin-right:5px;overflow:hidden;background:#F90;opacity:0.7;filter:alpha(opacity=70);border-radius:20px;}
#box .count li.current{color:#fff;opacity:1;filter:alpha(opacity=100);font-weight:700;background:#f60;}
#tmp{width:100px;height:100px;background:red;position:absolute;}
</style>
<script type="text/javascript"> 
window.onload = function ()
{
	var oBox = document.getElementById("box");
	var oList = oBox.getElementsByTagName("ul")[0];
	var aImg = oBox.getElementsByTagName("img");
	var timer = playTimer = null;
	var index = i = 0;
	var bOrder = true;
	var aTmp = [];
	var aBtn = null;
	
	//生成数字按钮
	for (i = 0; i < aImg.length; i++) aTmp.push("<li>" + (i + 1) + "</li>");
	
	//插入元素
	var oCount = document.createElement("ul");
	oCount.className = "count";
	oCount.innerHTML = aTmp.join("");
	oBox.appendChild(oCount);	
	aBtn = oBox.getElementsByTagName("ul")[1].getElementsByTagName("li");
	
	//初始化状态
	cutover();
	
	//按钮点击切换
	for (i = 0; i < aBtn.length; i++)
	{
		aBtn[i].index = i;
		aBtn[i].onmouseover = function ()
		{
			index = this.index;
			cutover();
		}
	}
	
	function cutover()
	{
		for (i = 0; i < aBtn.length; i++) aBtn[i].className = "";
		aBtn[index].className = "current";			
		startMove(-(index * aImg[0].offsetHeight))
	}
	
	function next()
	{
		bOrder ? index++ : index--;
		index <= 0 && (index = 0, bOrder = true);
		index >= aBtn.length - 1 && (index = aBtn.length - 1, bOrder = false)
		cutover()
	}
	
	playTimer = setInterval(next, 3000);
	
	//鼠标移入展示区停止自动播放
	oBox.onmouseover = function ()
	{
		clearInterval(playTimer)
	};
	
	//鼠标离开展示区开始自动播放
	oBox.onmouseout = function ()
	{
		playTimer = setInterval(next, 3000)
	};
	function startMove(iTarget)
	{
		clearInterval(timer);
		timer = setInterval(function ()
		{
			doMove(iTarget)
		}, 30)	
	}
	function doMove (iTarget)
	{		
		var iSpeed = (iTarget - oList.offsetTop) / 10;
		iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);		
		oList.offsetTop == iTarget ? clearInterval(timer) : oList.style.top = oList.offsetTop + iSpeed + "px"
	}
};
</script>
</head>

<body background="images/bg1.jpg">
<!-- 登陆弹出窗口start -->
<div id="bgDiv" style="display:none;">
 </div>
 <div style="position:absolute; left:378px; top:150px; z-index:1; display: none;" id="login">
  <div id="loginBox">
    <p class="title" id="Mdown"><span class="t1">登录</span><span class="t2" title="关闭" onClick="login.style.display='none';bgDiv.style.display='none'">X</span></p>
　　　　   <div class="login"><form name="loginForm1"  method="post" action="login.do" onsubmit="return CheckLoginForm1()">用户名:<input type="text" name="username" size="12" maxlength="10"><div class="login">密　码:<input type="text"name="password" size="12" maxlength="10"></div><input class="submit" type="submit" value="登录"></form></div>
    </div>
 </div>
<script type="text/javascript">
      var IsMousedown, LEFT, TOP, S;
      document.getElementById("Mdown").onmousedown=function(e) {
        S = document.getElementById("login");
        IsMousedown = true;
        e = e||event;
        LEFT = e.clientX - parseInt(S.style.left);
        TOP = e.clientY - parseInt(S.style.top);
        document.body.onmousemove = function(e) {
            e = e||event;
            if (IsMousedown) {
                S.style.left = e.clientX - LEFT + "px";
                S.style.top = e.clientY - TOP + "px";
            }
        };
        document.onmouseup=function(){IsMousedown=false;}
   };
</SCRIPT>
<!-- 登陆弹出窗口end -->


<!--流动的网页背景 start-->
<script language="JavaScript">
<!-- Begin
var backgroundOffset = 0;				//背景图片的偏移量
var bgObject = eval('document.body');				//得到文挡本身的对象
function scrollBG(maxSize) {				//这个函数就是滚动背景的核心
backgroundOffset = backgroundOffset + 1;			//将背景偏移加1点
if (backgroundOffset > maxSize) backgroundOffset = 0;		//如果偏移量超过了最大值则回零
bgObject.style.backgroundPosition = "0 " + backgroundOffset;	//设定背景的偏移量，使其生效
}
var ScrollTimer = window.setInterval("scrollBG(307)", 64);	//设定每次移动背景之间的间隔。
// End -->
</script>
<!--流动的网页背景 end-->


<div align="center">
  <table width="1024" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <!--DWLayoutTable-->
    <tr>
      <td width="200" rowspan="2" valign="top" bgcolor="#FFFFFF"><img src="images/logo.jpg" /></td>
      <td width="824" height="26" valign="top"><div align="right">
      
      <font color="red" size="3">${username}</font>hi，<c:if test="${user.name==null}">游客</c:if><font size="6" color="red">${user.name } </font>欢迎来我佳炒货网上商城！<a href="index.do">首页</a>请<a href="javascript:" onClick="bgDiv.style.display='';login.style.display='';">登录</a> <a href="register.jsp">免费注册</a> <c:if test="${user.name!=null}"><a href="searchOrderByUsername.do?username=${user.name }">我的订单</a></c:if> <a href="#" class="btn">
	  <span class="btn_word"><img src="images/cart.jpg" />购物车${size>0?size:0 }种&nbsp;<a href="unlogin.do">注销</a>&nbsp;<a href="index.html">后台管理</a></span>
	  <span class="btn_right"></span>
	</a>

</div></td>
    </tr>
    <tr>
      <td height="54" valign="top"><div align="center"><div id="keyword">
      		<form name="keywordform" action="searchKeyword.do" method="post" onsubmit="return checkKeyword()">
			<font size=4>关键字:</font>
			<input name="keyword" style="height:20px; position: relative;top:-3px; font-size: 12pt; color: rgb(85, 85, 85);" onFocus="if(this.value=='请输入您所要查找的商品名称'){this.value='';}" onBlur="if(this.value==''){this.value='请输入您所要查找的商品名称';}" value="请输入您所要查找的商品名称" size="35" type="text"/>
		  &nbsp;<input id="keywordBut" onclick="searchKeyword()" src="images/go.gif" style="position: relative; top: 5px;" border="0" height="25" type="image" width="50"/>
		  	</form>
	  </div></div></td>
    </tr>
  </table>
</div>



<!--幻灯片图start-->
<div id="box">
    <div class="list">
        <ul>
            <li><img src="images/p1.jpg" width="1022" height="100" /></li>
            <li><img src="images/p2.jpg" width="1022" height="100" /></li>
            <li><img src="images/p3.jpg" width="1022" height="100" /></li>
            <li><img src="images/p4.jpg" width="1022" height="100" /></li>
        </ul>
    </div>
</div>
<!--幻灯片图end-->

<!--menu-->
<div align="center">
  <table width="1024" border="0" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr>
      <td width="1024" height="26">
	  	
			<!-- BEGIN Menu -->
			<div class="menu">
			<ul>
			
			<c:choose>
			<c:when test="${empty categoryList}">
				<li><a href="#" target="_blank">无类别</a></li>
			</c:when>
			<c:otherwise>
				<c:forEach items="${categoryList}" var="category">
					<c:if test="${category.parent==0}">
						<li><a href="searchCategory.do?categoryId=${category.id }" target="_self">${category.text}</a>
							<!-- 子分类 -->
							<c:if test="${category.leaf == false}">
								<table>
									<tr>
										<td>
											<ul>
												<c:set var="pid" value="${category.id}" />
												<c:forEach items="${categoryList}" var="category">
													<c:if test="${category.parent==pid}">
														<li><a href="searchCategory.do?categoryId=${category.id }" target="_self">${category.text }</a></li>
													</c:if>
												</c:forEach>
											</ul>
										</td>
									</tr>
								</table>
							</c:if>
						
						</li>
					</c:if>
				</c:forEach>
			</c:otherwise>
			</c:choose>				
			
			</ul>
			</div>
		<!-- END Menu -->
	  
	  </td>
    </tr>
  </table>
</div>

<!--滚动图片-->

<div align="center">
  <table width="1024" border="0" cellpadding="0" cellspacing="0" bgcolor="#D6F7F8">
    <!--DWLayoutTable-->
    <tr>
      <td valign="top"> 
	  		<style type="text/css">
				<!--
				#demo {
				background: #FFF;
				overflow:hidden;
				border: 1px dashed #CCC;
				width: 1022px;
				}
				#demo img {
				border: 3px solid #F2F2F2;
				}
				#indemo {
				float: left;
				width: 1500%;
				}
				#demo1 {
				float: left;
				}
				#demo2 {
				float: left;
				}
				-->
				</style>
				<div id="demo">
				<div id="indemo">
				<div id="demo1">
				<!-- 展示所有的商品图片 -->
				<c:choose>
				<c:when test="${empty productList}">
					
				</c:when>
				<c:otherwise>
					<c:forEach items="${productList}" var="product">
						<a href="#"><img width="94" height="76" src="images/product/${product.photo}" border="0" /></a>
					</c:forEach>
				</c:otherwise>
				</c:choose>
				
				</div>
				<div id="demo2"></div>
				</div>
				</div>
				<script>
				
				var speed=30;
				var tab=document.getElementById("demo");
				var tab1=document.getElementById("demo1");
				var tab2=document.getElementById("demo2");
				tab2.innerHTML=tab1.innerHTML;
				function Marquee(){
				if(tab2.offsetWidth-tab.scrollLeft<=0)
				tab.scrollLeft-=tab1.offsetWidth
				else{
				tab.scrollLeft++;
				}
				}
				var MyMar=setInterval(Marquee,speed);
				tab.onmouseover=function() {clearInterval(MyMar)};
				tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
				
				</script>
	  </td>
    </tr>
  </table>
</div>



<!--用户登陆产品展示-->
<div align="center">
  <table width="1024" border="0" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr>
      <td width="200" rowspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--menu left-->
		<tr bgcolor="#ffffff">
          	<td width="200" height="0" valign="top">
			<!--左菜单start-->
				
			<!--左菜单end-->
			</td>
		</tr>
		
		<!--login -->
        <tr>
          <td width="200" height="128" valign="top">
            
            <!--left login start-->
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tbody>
                <tr>
                  <td align="center" height="40"><img src="images/login.gif" height="39" width="100%"></td>
				  </tr>
                </tbody>
              </table>
				  <table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
				      <td align="center" bgcolor="#e2e4f0">
				      	<form name="loginForm" action="login.do" method="post" onsubmit="return CheckLoginForm()">
				        <table border="0" cellpadding="0" cellspacing="0" width="92%">
				          
				            <tr>
				              <td align="left" height="25"><div align="right">用户名：</div></td>
				              <td><div align="left">
			                    <input name="username" size="10" style="font-size:11px;" type="text" />
				              </div></td>
						      <td rowspan="2">
						        <input src="images/down.gif" border="0" height="45" type="image" /></td>
						    </tr>
				            <tr>
				              <td align="left" height="25" width="83%"><div align="right">密　码：</div></td>
				              <td><div align="left">
		                        <input name="password" size="10" style="font-size: 11px;" type="password"/>
				              </div></td>
						    </tr>
				            <tr>
				              <td colspan="3" height="30"><p align="center">[<a href="register.jsp">新用户注册</a>] &nbsp;[<a    									href="passwdview.php.htm" onClick="js_callpage(href);return false">忘记密码</a>]</p></td>
						    </tr>
				            
					    </table>
					    </form>	
					    </td>
					  </tr>	
				    <tr>
				      <td bgcolor="#aab3d5" height="1"><img src="images/line.gif" height="1" width="1"></td>
					  </tr>
			      </table>
				  <!--left login end-->		</td>
          </tr>
        <tr>
          <td height="128" valign="top">
		  		<table>
					<!--店主信息-->
					<tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                              <td colspan="3"><img src="images/jingcai.gif" height="32" width="100%"></td>
                            </tr>
                            <tr>
								 <td align="center" bgcolor="#e2e4f0">
										<table bgcolor="#ffffff" border="0" width="90%">
											<tr>
												<td align="center" bgcolor="#e2e4f0">
													<table bgcolor="#ffffff" border="0" width="100%">
													   <tr>
														  <td bgcolor="#ffffff" height="80"><div align="left">														  																<DIV><MARQUEE style="WIDTH: 150; HEIGHT: 260" scrollAmount=1 scrollDelay=77 direction=up width=200 height=160 onmouseout="this.start()" onmouseover="this.stop()">贵阳,贵州省省会，全省政治、经济和文化中心.全市总面积8034平方公里,中国古代以山北为阴，山南为阳。"贵阳"因位于境内贵山之南而得名，延用至今，已有400多年历史。古代贵阳盛产竹子，以制作乐器筑而闻名，故简称"筑".作为全国第一个国家森林城市此外，独一无二的避暑之都·森林之城。</MARQUEE></DIV>
															</div></td>
														</tr>
													</table>												</td>
											</tr>
										</table>								</td>
								<td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#e2e4f0" height="8"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#aab3d5" height="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                        </table></td>
                   </tr>
					<!---店主信息结束-->
				</table>		  </td>
        </tr>
        <tr>
          <td height="164" valign="top">
		  	<table>
					<!--店主信息-->
					<tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                              <td colspan="3"><img src="images/jingcai.gif" height="32" width="100%"></td>
                            </tr>
                            <tr>
								 <td align="center" bgcolor="#e2e4f0">
										<table bgcolor="#ffffff" border="0" width="90%">
											<tr>
												<td align="center" bgcolor="#e2e4f0">
													<table bgcolor="#ffffff" border="0" width="100%">
													   <tr>
														  <td bgcolor="#ffffff" height="80"><div align="left">店&nbsp;&nbsp;&nbsp;名: 我佳炒货连锁<br/>
													        地&nbsp;&nbsp;&nbsp;址: 夏云兴隆花园B栋首艺发廊旁<br/>
													        店&nbsp;&nbsp;&nbsp;主: 陈 燕<br/>
													        电&nbsp;&nbsp;&nbsp;话: 0853－4663430<br/>
													        Q&nbsp;&nbsp;&nbsp;Q: 80256876<br/>
													        手&nbsp;&nbsp;&nbsp;机: 13985327839<br/>
															个人名言: 改变不能接受的,接受不能改变的！<br/>
															加盟商: 贵阳我佳炒货<br/>														  
															</div></td>
														</tr>
													</table>												</td>
											</tr>
										</table>								</td>
								<td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#e2e4f0" height="8"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#aab3d5" height="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                        </table></td>
                   </tr>
					<!---店主信息结束-->
				</table>		  </td>
        </tr>
        <!-- 调格式 -->
        <tr>
          <td height="376" bgcolor="#aab3d5">&nbsp;</td>
        </tr>
      </table></td>
      <td width="824" height="615" valign="top">
      <table height="920" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#99CC00">
        <!--展示最后25条商品信息或者按类别查询展示-->
        <c:choose>
		<c:when test="${empty productList25Last}">
					
		</c:when>
		<c:otherwise>
			<%int i=0; %>
			<tr valign="top">
			<c:forEach items="${productList25Last}" var="product">
				<% i++; %>
				
		        
	            <td width="154.8" height="160">
	              <table>
	                <tr><td width="154.8" height="100"><img width="94" height="76" src="images/product/${product.photo }"/></td></tr>
	                <tr><td width="154.8" height="20">${product.name }</td></tr>
	                <tr><td width="154.8" height="20"><img src="images/qian.gif"><font size="4" color="#FF0000">${product.normaPrice }</font>元/斤<img src="images/hot.gif" /></td></tr>
	                <tr><td width="154.8" height="20"><a href="buy.do?productId=${product.id }&productName=${product.name}&price=${product.normaPrice }&count=1"><img src="images/buy.gif" /></a></td></tr>
	              </table>		  
	            </td>
			    
				    <%if(i>=5){ %>
		</tr>
				    	<tr><td height="10">&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td>&nbsp;</td>
					     </tr>
				    <%i=0;} %>
				    
			</c:forEach>
			
		</c:otherwise>
		</c:choose>
        
		<!--6start--->
			<!--分页start-->
			
			<!--分页end-->
		<!--7start--->
		
		<!--8start--->
        
      </table></td>
    </tr>
  </table>
</div>

<!--footer-->
<div align="center">
  <table width="1024" border="0" cellpadding="0" cellspacing="0" bgcolor="#D6F7F8">
    <!--DWLayoutTable-->
    <tr>
      <td width="429" height="35" valign="top">
  	    <div align="right"><img src="images/biaoshi.gif" border="0" height="35" width="28"/>        </div></td>
	  <td width="595" align="left" valign="top"><div align="left">版权所有:贵州省平坝县夏云我佳炒货店<br/>
	    服务电话：13985327839<br/>
      运作日期：2012-11-26</div></td>
    </tr>
  </table>
</div>
</body>
</html>

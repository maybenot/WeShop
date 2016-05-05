<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WeShop主页</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<!-- Loading Bootstrap -->
<link
	href="${pageContext.request.contextPath}/dist/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="${pageContext.request.contextPath}/dist/css/flat-ui.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/docs/assets/css/demo.css"
	rel="stylesheet">



</head>
<body style="background-color: #E5E5E5">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation">
				<div class="navbar-header">

					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"> <img
						alt="Bootstrap Image Preview"
						src="${pageContext.request.contextPath}/img/login/1.jpg"
						height="40px" width="50px"></a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<c:if test="${USER_IDENTITY_INFO==null}">
							<li class="active"><a
								href="${pageContext.request.contextPath}/login/login.jsp"><span
									class="glyphicon glyphicon-user"></span>&nbsp;登录</a></li>
							<li><a href="${pageContext.request.contextPath}/to_register"><span
									class="glyphicon glyphicon-globe"></span>&nbsp;注册</a></li>
						</c:if>
						<c:if test="${USER_IDENTITY_INFO!=null}">
							<li class="active"><a href=""><span
									class="glyphicon glyphicon-user"></span>&nbsp;欢迎,${sessionScope.USER_IDENTITY_INFO }</a></li>
						</c:if>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span class="glyphicon glyphicon-home"></span>&nbsp;我的WeShop<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="#"><span class="glyphicon glyphicon-cloud"></span>&nbsp;已购买的宝贝</a>
								</li>
								<li class="divider">
								<li><a href="#"><span
										class="glyphicon glyphicon-shopping-cart"></span>&nbsp;购物车</a></li>
								<li class="divider">
								<li><a href="#"><span class="glyphicon glyphicon-inbox"></span>&nbsp;我的收藏</a>
								</li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input class="form-control" type="text" placeholder="搜索秋裤试试" />
						</div>
						<button type="submit" class="btn btn-default">
							<span class="glyphicon glyphicon-search"></span>&nbsp;搜索
						</button>
					</form>
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span class="glyphicon glyphicon-link">&nbsp;卖家中心<strong
									class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath}/seller/sellerRegist.jsp"><span
										class="glyphicon glyphicon-send">&nbsp;免费开店</a></li>
								<li class="divider"></li>
								<li><a
									href="${pageContext.request.contextPath}/seller/s_login.jsp"><span
										class="glyphicon glyphicon-plane">&nbsp;进入我的店铺</a></li>
							</ul></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-envelope"></span>&nbsp;联系我们</a></li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</ul>
				</div>

				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="carousel slide" id="carousel-528023">
							<ol class="carousel-indicators">
								<li class="active" data-slide-to="0"
									data-target="#carousel-528023"></li>
								<li data-slide-to="1" data-target="#carousel-528023"></li>
								<li data-slide-to="2" data-target="#carousel-528023"></li>
							</ol>
							<div class="carousel-inner">
								<div class="item active" style="height: 500px;">
									<img alt="Carousel Bootstrap First" src=" ../img/login/1.jpg">
									<div class="carousel-caption">
										<h4>First Thumbnail label</h4>
										<p>this is anotation</p>
									</div>
								</div>
								<div class="item" style="height: 500px;">
									<img alt="Carousel Bootstrap Second" src=" ../img/login/1.jpg">
									<div class="carousel-caption">
										<h4>Second Thumbnail label</h4>
										<p>this is anotation</p>
									</div>
								</div>
								<div class="item" style="height: 500px;">
									<img alt="Carousel Bootstrap Third" src=" ../img/login/1.jpg">
									<div class="carousel-caption">
										<h4>Third Thumbnail label</h4>
										<p>this is anotation</p>
									</div>
								</div>
							</div>
							<a class="left carousel-control" href="#carousel-528023"
								data-slide="prev"><span
								class="glyphicon glyphicon-chevron-left"></span></a> <a
								class="right carousel-control" href="#carousel-528023"
								data-slide="next"><span
								class="glyphicon glyphicon-chevron-right"></span></a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="panel-group" id="panel-485591">
							<c:forEach items="${bigClasses }" var="temp">
								<!-- 这个是大类号和小类号选择器 -->
								<div class="panel panel-default">

									<!-- 大类 -->
									<div class="panel-heading">
										<a class="panel-title collapsed" data-toggle="collapse"
											data-parent="#panel-485591"
											href="#panel-element-${temp.bigClassName}">${temp.bigClassName}</a>
									</div>
									<!-- 大类 -->
									<!-- 小类 -->
									<div id="panel-element-${temp.bigClassName}"
										class="panel-collapse collapse">
										<c:forEach items="${temp.extendClasses }" var="etc">
											<div class="panel-body">
												<a href="123">${etc.extendClassName }</a>
											</div>
											<br>
										</c:forEach>
									</div>
									<!-- 小类 -->
								</div>
								<!-- 这个是大类号和小类号选择器 -->
							</c:forEach>
						</div>
					</div>
					<div class="col-md-8"  id="listDiv">
						<div class="row">
							<div class="col-md-12" style="height: 20px;"></div>
						</div>

						<!-- 展示商品的面板 -->
						
						<!-- 第一行面板 -->
							<div class="row" >
								<div class="col-md-12">
									<div class="row" id="fistListDiv">
										<c:forEach  items="${listProducts }" begin="0" end="2" step="1" var="temp">
											<!-- 小的商品展示面板 -->
											<div class="col-md-4">
												<div class="thumbnail">
													<img style="width:250px;height: 200px "   alt="图片缺失，请联系管理员18702603258"
														src="${pageContext.request.contextPath}/pdt_image/${temp.imagPath }">
													<div class="caption">
														<h6>${temp.shopProductName }</h6>
												<a class="btn btn-primary" href="#">立即购买</a>
												 <a class="btn" href="#?${temp.shopProductNo }">查看详情</a>
														 
													</div>
											   </div>
											</div>
											<!-- 小的商品展示面板 -->
										</c:forEach>
									</div>
								</div>
							</div>
							 
							
							<!-- 第二行面板 -->
							<div class="row" id="secondListDiv">
								<div class="col-md-12">
									<div class="row">
										<c:forEach  items="${listProducts }" begin="3" step="1" var="temp">
											<!-- 小的商品展示面板 -->
											<div class="col-md-4">
												<div class="thumbnail">
												    <img style="width:250px;height: 200px " alt="图片缺失请联系管理员"
														src="${pageContext.request.contextPath}/pdt_image/${temp.imagPath }">
													
													<div class="caption">
														<h6>${temp.shopProductName }</h6>
														 
												<a class="btn btn-primary" href="#">立即购买</a> <a
													class="btn" href="#?${temp.shopProductNo }">查看详情</a>
														 
													</div>
												</div>
											</div>
											<!-- 小的商品展示面板 -->
										</c:forEach>
									</div>
								</div>
							</div>
						 
						<!-- 展示商品的面板 -->

					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"></div>
						<!--用于分页  -->
						<div id="paginat">
							<ul class="pagination">
							     <c:if test="${currentPage !=1}">
								<li class="previous"><a href="#"   onclick="page(${currentPage -1},${totalPages })">上一页</a></li>
								</c:if>
								<li><span class="label label-primary"><font color="green" >当前第&nbsp;${currentPage }&nbsp;页</font></span></li>
								<li ><span class="label label-primary"><font color="green">共&nbsp;${totalPages }&nbsp;页</font></span></li>
								<c:if test="${currentPage !=totalPages}">
								<li class="next"><a href="#"  onclick="page(${currentPage+1 },${totalPages })">下一页</a></li>
							    </c:if>
							</ul>
						</div>
						<!-- /pagination -->
						<hr />
					</div>
				</div>
			</div>
		</div>
  
		
	</div>
   
<div class="row">
			<div class="col-md-12">
				<hr />
				<h4>友情链接</h4>
				<div class="footer-hd">
					<p>
						<span><a href="http://www.alibabagroup.com/cn/global/home">阿里巴巴集团</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="//www.taobao.com">淘宝网</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="//www.tmall.com">天猫</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="//ju.taobao.com">聚划算</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="http://www.aliexpress.com">全球速卖通</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="http://www.alibaba.com/">阿里巴巴国际交易市场</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="http://www.1688.com">1688</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="http://www.alimama.com">阿里妈妈</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="//www.alitrip.com">阿里旅行 ·
								去啊</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.aliyun.com">阿里云计算</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.yunos.com">YunOS</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.aliqin.cn/">阿里通信</a></span><b> &nbsp;</b><span><a
							href="http://www.etao.com/">一淘</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.net.cn">万网</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.autonavi.com/">高德</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.uc.cn/">UC</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.umeng.com/">友盟</a></span><b> <span><a
							href="http://www.xiami.com">虾米</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.ttpod.com/">天天动听</a></span><b>|</b><span><a
							href="http://www.laiwang.com/">来往</a></span><b> &nbsp;|&nbsp;</b><span><a
							href="http://www.dingtalk.com/?lwfrom=20150130160830727">钉钉</a></span><b>
							&nbsp;|&nbsp;</b><span><a href="https://www.alipay.com">支付宝</a></span>
					</p>
				</div>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-8">
				<p>Copyright © 2015–2016 by Mr.tong. 數據庫開發大隊</p>
			</div>
		</div>

	<script
		src="${pageContext.request.contextPath}/dist/js/vendor/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/dist/js/vendor/video.js"></script>
	<script src="${pageContext.request.contextPath}/dist/js/flat-ui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/docs/assets/js/application.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/page.js"></script>
</body>
</html>
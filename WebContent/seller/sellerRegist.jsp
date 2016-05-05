<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>S_Register</title>
  
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
<body style="background-color: #293A4A;">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-8">
						<div class="row">
							<div class="col-md-6">
								<blockquote>
									<p>
										<font color="white"> 免费开店&nbsp;
										<a href="${pageContext.request.contextPath}/seller/s_login.jsp">登录</a>
									</p>
									<small>我就是要全世界都知道,<cite> &nbsp;这个鱼塘,被你承包了</cite></small> </font>
								</blockquote>
								<hr size="5" color="black" />
								<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/regist" method="post">
									<div class="form-group">
										<div class="col-sm-10">
											<input class="form-control"  type="email"
												placeholder="Email/邮件*" name="email">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-10">
											<input class="form-control" id="inputPassword3"
												type="password" placeholder="Password/密码*" name="password">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-10">
											<input class="form-control"  
												type="text" placeholder="ShopName/店铺名称*"  name="shopName">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-10">
											<input class="form-control" 
												type="text" placeholder="Shopower/掌柜名称*"  name="shopOwer">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-10">
											<input class="form-control" 
												type="text" placeholder="Tel/联系电话*"  name="tel">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-10">
											<button type="submit" class="btn btn-success">注册并登录
											</button>
										</div>
								</form>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/dist/js/vendor/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/dist/js/vendor/video.js"></script>
	<script src="${pageContext.request.contextPath}/dist/js/flat-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/docs/assets/js/application.js"></script>
</body>
</html>
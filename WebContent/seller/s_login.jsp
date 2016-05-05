<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>S_Login</title>

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
<body style="background-color: #293A4A;">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-8">
						<div class="row">
							<div class="col-md-8">
								<blockquote>
									<font color="beige">
										<p>
											店铺登录 &nbsp;<a
												href="${pageContext.request.contextPath}/seller/sellerRegist.jsp">注册</a>
										</p> <small>我想有一所大房子，里面塞满了钞票</small>
									</font>
								</blockquote>
								<hr size="5" />
								<form class="form-horizontal" role="form"
									action="${pageContext.request.contextPath}/s_login" method="post">
									<div class="form-group">
										<div class="col-sm-8">
											<input class="form-control" id="inputEmail3" type="email"
												placeholder="邮箱/Eamil" name="email">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-8">
											<input class="form-control" id="inputPassword3"
												type="password" placeholder="密码/Password"  name="password">
										</div>
									</div>
									<c:if test="${ message.falg }">
									<div class="alert alert-success alert-dismissable">

										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">×</button>
										<h4>温柔的警告！</h4>
										<strong>${message.message }</strong> 
									</div>
									</c:if>
									<div class="form-group">
										<div class="col-sm-12">
											<button type="submit" class="btn btn-success">登录</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/dist/js/vendor/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/dist/js/vendor/video.js"></script>
	<script src="${pageContext.request.contextPath}/dist/js/flat-ui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/docs/assets/js/application.js"></script>
</body>
</html>
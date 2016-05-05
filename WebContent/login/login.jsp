<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<!-- Loading Bootstrap -->
<link href="${pageContext.request.contextPath }/dist/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="${pageContext.request.contextPath }/dist/css/flat-ui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/docs/assets/css/demo.css" rel="stylesheet">

</head>
<body style="background-color: #DBDBDB;">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<div class="page-header">
					<blockquote>
						<p>世界觸手可及</p>
						<small>我說的</small>
					</blockquote>
				</div>
				<img alt="bag" src="${pageContext.request.contextPath }/img/login/1.jpg" width="500px" height="400px"
					style="padding: 40px;">
			</div>
			<div class="col-md-4">
				<div class="row">
					<div class="col-md-12">
						<div style="margin: 100px;"></div>
					</div>

					<form action="${pageContext.request.contextPath }/login"  method="post" class="form-horizontal" role="form">

						<div class="form-group">
							<font size="6" style="align-content: flex-start;">用戶登錄</font>
							<div style="margin: 50px;"></div>
							<div class="col-sm-10">
								<input class="form-control" id="inputEmail3" type="email"
									placeholder="郵箱/Email" name="userName">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="password"
									placeholder="密碼/Password" name="password">
							</div>
						</div>
						<c:if test="${resultMessage.flag }">
							<div class="alert">
								<button type="button" class="close" data-dismiss="alert">×</button>
								<font color="red"><h4>亲</h4>
								<strong>警告!</strong>${resultMessage.message }</font>
							</div>
						</c:if>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div style="margin: 50px;"></div>
								<div class="checkbox">
									<label> <input type="checkbox"> 記住我
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">

								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-user"></span> 登錄
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/to_register">註冊</a>
								<div style="margin: 100px;"></div>
								<div>
									<p>Copyright © 2015–2016 by Mr.tong.</p>
									<p>數據庫開發大隊</p>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>


	</div>

	<script src="${pageContext.request.contextPath }/dist/js/vendor/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/dist/js/vendor/video.js"></script>
	<script src="${pageContext.request.contextPath }/dist/js/flat-ui.min.js"></script>
	<script src="${pageContext.request.contextPath }/docs/assets/js/application.js"></script>
</body>
</html>
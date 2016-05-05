<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 导入两个标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>用户注册</title>

<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<!-- Loading Bootstrap -->
<link href="dist/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="dist/css/flat-ui.css" rel="stylesheet">
<link href="docs/assets/css/demo.css" rel="stylesheet">
</head>


<body style="background-color: #EBEBEB;">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-5">

				<div class="page-header">
					<h3>
						用户注册<small> <font color="#398439">立即收获极致购买体验</font></small>
					</h3>
				</div>

				<form:form action="add" modelAttribute="buyer" method="post">
					<form class="bs-example bs-example-form" role="form">
						<div class="input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-user"></span>邮箱</span> <input type="email"
								class="form-control" placeholder="邮箱/Email" name="userName">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-lock"></span>密码</span> <input
								type="password" class="form-control" placeholder="密码/Password"
								name="password">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-heart-empty"></span> 昵称</span> <input
								type="text" class="form-control" placeholder="昵称/nickname"
								name="nickName">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-earphone"></span> 手机</span> <input
								type="tel" class="form-control" placeholder="手机/phone"
								name="phone">
						</div>
						<div style="height: 30px;"></div>
						<div   class="">
							<legend class="">
								<span class="glyphicon glyphicon-tag"></span> 性别
							</legend>
						</div>
						<%  
						        Map gender=new HashMap();
						        gender.put(1, "Male");
						        gender.put(0,"Female");
						        pageContext.setAttribute("genderMap", gender);
						
						%>
						<form:radiobuttons path="gender" items="${genderMap }"  delimiter="&nbsp&nbsp&nbsp&nbsp&nbsp"/>
						<div id="legend" class="">
							<legend class="">
								<span class="glyphicon glyphicon-headphones"></span> 興趣愛好
							</legend>
						</div>
						<form:checkboxes items="${ints }" path="inters" itemLabel="name"
							itemValue="id" delimiter="&nbsp&nbsp&nbsp&nbsp&nbsp" />
						<br /> <br /> <br />
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-ok-sign"></span> 注册并登陆
						</button>
					</form>
				</form:form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>

	<script src="dist/js/vendor/jquery.min.js"></script>
	<script src="dist/js/vendor/video.js"></script>
	<script src="dist/js/flat-ui.min.js"></script>
	<script src="docs/assets/js/application.js"></script>
</body>
</html>
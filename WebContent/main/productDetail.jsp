<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>商品详情</title>
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
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-5">
						<img alt="诸葛亮天命不来，这些书都有记载，不是我在乱掰"
							src="http://lorempixel.com/140/140/">
					</div>
					<div class="col-md-6"></div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="tabbable" id="tabs-826914">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#panel-650719"
									data-toggle="tab">Section 1</a></li>
								<li><a href="#panel-547798" data-toggle="tab">Section 2</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="panel-650719">
									<p>I'm in Section 1.</p>
								</div>
								<div class="tab-pane" id="panel-547798">
									<p>Howdy, I'm in Section 2.</p>
								</div>
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
	<script src="${pageContext.request.contextPath}/js/addProduct.js"></script>

</body>
</html>
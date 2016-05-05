<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>产品详情添加页</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/recPdtDetail" name="upfile" method="post">
		<input type="hidden" name="shopProductNo" value="${param.shopProductNo }"/>
		<!-- 加载编辑器的容器 -->
		<script id="container" name="content" type="text/plain">
              这里添加商品描述，快来制作你的精美描述吧！
        </script>
        <input type="submit" value="提交"/>
	</form>
	
	
	
	<!-- 配置文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var editor = UE.getEditor('container');
	</script>


</body>

</html>
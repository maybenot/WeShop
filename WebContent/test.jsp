<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script
	src="${pageContext.request.contextPath}/dist/js/vendor/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Map.js"></script>
<script type="text/javascript">
	function cl() {
		/*测试map*/
		var map = new Map();
		map.put("sex", "男");
		map.put("gender", "nv");
		map.put("price", "400");

		$.ajax({
			type : "post",
			url : "test_ajax",
			data : "name:tong age:777",
			dataType : "json",
			success : function(msg) {

				for (key in msg) {
					alert(key + ":" + msg[key]);
				}

			}
		});
	}
</script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#extendsClass").change(function() {
			var ec=$.trim($(this).val())  ;
			//产生一个ajax,把这个变量传入到后台
			 
			$.ajax({
				type : "GET",
				url : "test2",
				data :{'eca':ec},
				dataType : "json",
				//contentType: "application/json",
				success : function(msg) {
					//msg是一个map
					//Map<String,Map<Integer,String>>
					//清除原来有的东西
					$("#forUse select").remove();
					$("#forUse br").remove();
					$("#forUse label").remove();
					for(attrName in msg){
						//temp是属性的名字   msg[temp]就是另外一个map
						//创建一个select，名字就是属性名字
						$("#forUse").append('<label>'+attrName+'</label>'); 
						$("#forUse").append('<br/>'); 
						$("#forUse").append('<select id='+attrName+'  name='+attrName+'>'); 
					 
						for(attrValues in  msg[attrName]){
							//这个就是具体的属性值的名字msg[attrName][attrValues]
							//alert(msg[attrName][attrValues]);
							 $("#"+attrName).append();
					        $("#"+attrName).append('<option value='+attrValues+'>'+msg[attrName][attrValues]+'</option>'); 
						}
						$("#forUse").append('</select> '); 
						$("#forUse").append('<br/>'); 
					}
				}
			});
			 
			
		});
	 
	});
	 
</script>
<body>
	<h1 onclick="cl()">123</h1>
	<form action="">
		<select id="extendsClass">
			<option>----请选择商品小类----</option>
			<c:forEach items="${exts }"  var="temp">
			      <option>${temp.extendClassName }</option>
			</c:forEach>
		</select> 
		<div id="forUse"></div>
	</form>



</body>
</html>
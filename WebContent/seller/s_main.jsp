<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>卖家主页</title>
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
					<div class="col-md-12"></div>
				</div>
				<blockquote>
					<font color="white">
						<p>欢迎，${USER_IDENTITY.shopOwer}</p> <small>我们不生产梦想</small>
					</font>
				</blockquote>
				<ul class="nav nav-tabs">
					<li class="active"><a href="#reles1" data-toggle="tab"> <span
							class="badge pull-right">42</span> 未受理订单
					</a></li>
					<li><a href="#reles2" data-toggle="tab"> <span
							class="badge pull-right">42</span> 已处理订单
					</a></li>
					<li><a href="#reles3" data-toggle="tab"> <span
							class="badge pull-right">42</span> 商品管理
					</a></li>
					<li><a href="#reles4" data-toggle="tab"> <span
							class="badge pull-right">16</span> More
					</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="reles1">
						<!-- 页面内容的制作  数据来源于数据库，可能用到循环-->
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<table class="table table-striped">
											<thead>

												<tr>
													<th><font color="#FFFAFA">订单编号</font></th>
													<th><font color="#FFFAFA">商品编号</font></th>
													<th><font color="#FFFAFA">品名</font></th>
													<th><font color="#FFFAFA">订购数量</font></th>
													<th><font color="#FFFAFA">订单提交日期</font></th>
													<th><font color="#FFFAFA">查看详情</font></th>
													<th><font color="#FFFAFA">取消订单</font></th>
													<th><font color="#FFFAFA">发货</font></th>

												</tr>

											</thead>
											<tbody>
												<tr class="success">
													<td>156156156</td>
													<td>15189</td>
													<td>华硕电脑550c笔记本包邮</td>
													<td align="center"><font color="#CD9B1D">2</font></td>
													<td>2013/1/21</td>
													<td align="center"><a href="#modal-container-1001"
														data-toggle="modal"> 详情 </a></td>
													<td><button type="button" class="btn btn-warning">取消订单</button></td>
													<td><button type="button" class="btn btn-success">发货</button></td>
													<td></td>
												</tr>
											</tbody>
										</table>
									</div>
									<!--使用一个弹幕-->
									<div class="modal fade" id="modal-container-1001" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">

													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">商品标题</h4>
												</div>
												<div class="modal-body">...</div>
												<div class="modal-footer">

													<button type="button" class="btn btn-default"
														data-dismiss="modal">朕知道了</button>
													<button type="button" class="btn btn-primary">
														赏给他吧</button>
												</div>
											</div>

										</div>
									</div>
									<!--使用一个弹幕-->
									
									<div class="col-md-1"></div>
								</div>
							</div>
						</div>
						<!-- 页面内容的制作 -->
					</div>
					<!--使用一个弹幕，用于添加自定义商品-->
									<div class="modal fade" id="reles9" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">

													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">添加自定义同类商品</h4>
												</div>
												<form action="${pageContext.request.contextPath}/addSimilarClass"  method="post" enctype="multipart/form-data">
												<div class="modal-body">
												<!-- 添加自定义种类的面板 -->
												同类商品名称:<input type="text"  placeholder="同类商品名称*" name="simClassName"/>
												<br/><br/>
												同类描述图片:<input type="file"   name="simImage"/>
												<br/><br/>
												同类最低价:<input type="text"  placeholder="同类最低价*" name="lowestPrice"/>
												<br/><br/> 
												 所属商品小类:<select id="SKUeClass" class="input-xlarge">
													<option>----请选择商品小类----</option>
													<c:forEach items="${exts }" var="temp">
														<option>${temp.extendClassName }</option>
													</c:forEach>
												</select>
												<!-- 预留一个面板，作为添加用 -->
												<div id="forSKU" ></div>
												 
                                                
                                                   <!-- 添加自定义种类的面板 -->
                                                 </div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">等下再添加</button>
													<button type="sumbit" class="btn btn-primary">
														确认添加</button>
												</div>
												</form>
											</div>

										</div>

									</div>
									<!--使用一个弹幕-->
					<!--使用一个弹幕，用于添加自定义商品-->
									<div class="modal fade" id="reles10" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">

													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">添加自定义SKU值</h4>
												</div>
											   <form action="${pageContext.request.contextPath}/addDefineSUKValue" method="post">
												<div class="modal-body">
											    所属商品小类:<select id="UserDefineClassSelect" class="input-xlarge">
													<option>----请选择商品小类----</option>
													<c:forEach items="${exts }" var="temp">
														<option>${temp.extendClassName }</option>
													</c:forEach>
												</select>
											    
											   
												<div id="forDefineSKU"></div>
                                              
                                                   <!-- 添加自定义种类的面板 -->
                                                </div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">等下再添加</button>
													<button type="sumbit" class="btn btn-primary">
														确认添加</button>
												</div>
												  </form>
											</div>

										</div>

									</div>
									<!--使用一个弹幕-->
					                <!--使用一个弹幕，用于添加自定义商品-->
									<div class="modal fade" id="reles11" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">

													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">添加自定义SKU</h4>
												</div>
											   <form action="${pageContext.request.contextPath}/addDefineSUK" method="post">
												<div class="modal-body">
											    所属商品小类:<select id="SKUselectDefine" class="input-xlarge">
													<option>----请选择商品小类----</option>
													<c:forEach items="${exts }" var="temp">
														<option>${temp.extendClassName }</option>
													</c:forEach>
												</select>
											    
											   
												<div id="forSKUUse"></div>
                                              
                                                   <!-- 添加自定义种类的面板 -->
                                                </div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">等下再添加</button>
													<button type="sumbit" class="btn btn-primary">
														确认添加</button>
												</div>
												  </form>
											</div>

										</div>

									</div>
									<!--使用一个弹幕-->
					<div class="tab-pane fade " id="reles2">
						<!-- 页面内容的制作  数据来源于数据库，可能用到循环-->
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<table class="table table-striped">
											<thead>

												<tr>
													<th><font color="#FFFAFA">订单编号</font></th>
													<th><font color="#FFFAFA">商品编号</font></th>
													<th><font color="#FFFAFA">品名</font></th>
													<th><font color="#FFFAFA">订单提交日期</font></th>
													<th><font color="#FFFAFA">详情</font></th>
													<th><font color="#FFFAFA">订单状态</font></th>
													<th><font color="#FFFAFA">是否退款</font></th>

												</tr>

											</thead>
											<tbody>
												<tr class="success">
													<td>156156156</td>
													<td>15189</td>
													<td>华硕电脑550c笔记本包邮</td>
													<td>2013/1/21</td>
													<td><a href="#modal-container-1002"
														data-toggle="modal"> 详情 </a></td>
													<td>配送在途</td>
													<td><button type="button" class="btn btn-danger">收到退货并退款</button></td>
													<td></td>
												</tr>
											</tbody>
										</table>
									</div>
									<!--使用一个弹幕-->
									<div class="modal fade" id="modal-container-1002" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">

													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">订单详情</h4>
												</div>
												<div class="modal-body">...</div>
												<div class="modal-footer">

													<button type="button" class="btn btn-default"
														data-dismiss="modal">朕知道了</button>
													<button type="button" class="btn btn-danger">同意退款</button>
												</div>
											</div>

										</div>

									</div>
									<!--使用一个弹幕-->
									<div class="col-md-1"></div>
								</div>
							</div>
						</div>
						<!-- 页面内容的制作 -->
						<!-- 页面内容的制作 -->
					</div>
					<div class="tab-pane fade " id="reles3">
						<!--商品管理导航栏-->
						<ul class="nav nav-pills">
							<li class="active"><a href="#onstock" data-toggle="tab">
									<span class="badge pull-right">42</span> 已上架商品
							</a></li>
							<!-- 如果这个商店没有自定义同类，就不能添加商品！这是业务guizhe -->
							<c:if test="${!empty similarClasses }">
							<li><a href="#modal-container-498592"  data-toggle="modal">
									<span class="badge pull-right">42</span> 添加商品
							</a></li>
							</c:if>
							<li><a href="#reles11"  data-toggle="modal">
									<span class="badge pull-right">42</span> 添加自定义SKU 
							</a></li>
							<li><a href="#reles10"  data-toggle="modal">
									<span class="badge pull-right">42</span> 添加自定义SKU值
							</a></li>
							<li><a href="#reles9"  data-toggle="modal">
									<span class="badge pull-right">42</span> 添加自定义同类商品
							</a></li>
							
						</ul>
						<!--商品管理导航栏-->
						<!--上架商品，添加商品的管理-->
						<div class="tab-content">
							<!-- 已经上架的商品的管理 -->
							<div class="tab-pane fade in active" id="onstock">
								<!-- 页面内容的制作  数据来源于数据库，可能用到循环-->
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-1"></div>
											<div class="col-md-10">
												<table class="table table-striped">
													<thead>

														<tr>
															<th><font color="#FFFAFA">店铺商品编号</font></th>
															<th><font color="#FFFAFA">品名</font></th>
															<th><font color="#FFFAFA">库存数量</font></th>
															<th><font color="#FFFAFA">商品详情</font></th>
															<th><font color="#FFFAFA">添加商品自定义</font></th>
															<th><font color="#FFFAFA">修改商品</font></th>

														</tr>

													</thead>
													<tbody>
													<!-- 遍历Map<ShopProduct, ProductManagerDetail> -->
													     <c:forEach items="${products }" var="temp">
														<tr class="success">
															<td>${temp.key.shopProductNo }</td>
															<td>${temp.key.shopProductName }</td>
															<td><font color="#CD9B1D">${temp.key.stock }</font></td>
															<td><a href="#modal-container-${temp.key.shopProductNo }"
																data-toggle="modal"> 详情 </a></td>
															<c:if test="${ empty temp.key.productUserDefine  }">
															 <td><a href="${pageContext.request.contextPath}/userDefine.jsp?shopProductNo=${temp.key.shopProductNo }"> 商品自定义</a></td>
															</c:if>
															<c:if test="${!empty  temp.key.productUserDefine }">
															 <td>已经定义</td>
															</c:if>
															<td><button type="button" class="btn btn-success">修改商品</button></td>
															<td></td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<c:forEach items="${products }" var="temp">
											<!--使用一个弹幕-->
											<div class="modal fade" id="modal-container-${temp.key.shopProductNo }"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">

															<button type="button" class="close" data-dismiss="modal"
																aria-hidden="true">×</button>
															<h4 class="modal-title" id="myModalLabel">${temp.key.shopProductName }</h4>
														</div>
														<div class="modal-body">
														
														<table border="0" >
														   <tbody>
														       <td>
														       <img alt="没有找到图片" width="150px" height="200px" src="${pageContext.request.contextPath}/pdt_image/${temp.key.imagPath }">
														       </td>
														       <td>
														       <div style="width: 50px"></div>
														       </td>
														        <td align="center">
														        <table border="0">
														        <tbody>
														           <td>
														            会员价:&nbsp;&nbsp;&nbsp;${temp.value.memberPrice }
														           </td>
														           <tr>
														           <td>
														             促销价:&nbsp;&nbsp;&nbsp;${temp.value.barginPrice }
														           </td>
														           <tr>
														          <td>
														                      库存:&nbsp;&nbsp;&nbsp;${temp.key.stock }
														          </td>
														           <tr>
														          <td>
														                    商品状态:&nbsp;&nbsp;&nbsp;${temp.key.state.stateName }
														          </td>
														           <tr>
														       <td >
														       点击量:&nbsp;&nbsp;&nbsp;${temp.key.clickCount }
														       </td>
														       <tr>
														           <td>
														             上次修改日期:&nbsp;&nbsp;&nbsp;${temp.value.productDateManager.dateIdentify }
														           </td>
														        </tbody>
														        </table>
														        </td>
														       <tr/>
														       
														   </tbody>
														</table>
                                                        </div>
														<div class="modal-footer">

															<button type="button" class="btn btn-default"
																data-dismiss="modal">朕知道了</button>
																<!-- 
															<button type="button" class="btn btn-primary">
																赏给他吧</button>
																 -->
														</div>
													</div>

												</div>

											</div>
											<!--使用一个弹幕-->
											</c:forEach>
											<div class="col-md-1"></div>
										</div>
									</div>
								</div>
								<!-- 页面内容的制作 -->
							</div>
							<div class="tab-pane fade " id="reles2">
								<!-- 页面内容的制作  数据来源于数据库，可能用到循环-->
							</div>
							<!-- 已经上架的商品的管理 -->
							<!--使用一个弹幕-->
							<div class="modal fade" id="modal-container-498592" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">

											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="myModalLabel">添加商品</h4>
										</div>
										<div class="modal-body">
											<div class="container-fluid">
												<div class="row-fluid">
													<div class="span12">
														<div class="row-fluid">
															<div class="span2"></div>
															<div class="span8">
																<!-- 重新构造面板 -->

																<form class="form-horizontal" action="${pageContext.request.contextPath}/addProduct" method="post" enctype="multipart/form-data">
																	<fieldset>
																		<!-- 隐含一个SellerID -->
																		<input type="hidden" name="sellerId"
																			value="${USER_ID }" />
																		<div class="control-group">

																			<!-- Text input-->
																			<label class="control-label" for="input01">商品名称</label>
																			<div class="controls">
																				<input placeholder="请输入商品名称*" class="input-xlarge"
																					type="text" name="productName">
																			</div>
																		</div>

																		<div class="control-group">

																			<!-- Text input-->
																			<label class="control-label" for="input01">商品库存</label>
																			<div class="controls">
																				<input placeholder="该种商品可售数量" class="input-xlarge"
																					type="text" name="stock">
																			</div>
																		</div>



																		<div class="control-group">

																			<!-- Select Basic -->
																			<label class="control-label"><font color="#191970">商品状态</font></label>
																			<div class="controls">
																				<select name="state" class="input-xlarge">
																					<c:forEach items="${productStates }" var="temp">
																						<option value="${temp.stateNo }">${temp.stateName }</option>
																					</c:forEach>
																				</select>
																			</div>

																		</div>

																		<div class="control-group">
																			<label class="control-label"><font color="#191970">商品描述图片</font></label>

																			<!-- File Upload -->
																			<div class="controls">
																				<input class="input-file" id="fileInput" type="file" name="imag">
																			</div>
																		</div>

																		<div class="control-group">
																			<!-- Select Basic -->
																			
																			<label class="control-label"><font color="#191970">请选择商品小类</font></label>
																			<div class="controls">
																				<select id="extendsClass" class="input-xlarge">
																					<option>----请选择商品小类----</option>
																					<c:forEach items="${exts }" var="temp">
																						<option>${temp.extendClassName }</option>
																					</c:forEach>
																				</select>
																				<br/>
																				<font color="#191970">描述属性:</font><br/> 
																				<div id="forUse"></div>
																			</div>
																			<br/>
																			<label class="control-label"><font color="#191970">请选择商品分类</font></label>
																			<br/>
																			<select id="similarClass" class="input-xlarge">
																					<option>----请选择商品分类----</option>
																			</select>
																			<br/>
																			<font color="#191970">SKU属性:</font><br/>
																			<div id="forskuUseadd"></div>
                                                                           
																		</div>
																		
																		<div class="control-group">

																			<!-- Text input-->
																			<label class="control-label" for="input01"><font color="#191970">会员价</font></label>
																			<div class="controls">
																				<input placeholder="该种商品对普通会员价格" class="input-xlarge"
																					type="text" name="memberPrice">
																			</div>
																		</div>
																		
																		<div class="control-group">

																			<!-- Text input-->
																			<label class="control-label" for="input01"><font color="#191970">促销价</font></label>
																			<div class="controls">
																				<input placeholder="活动促销价" class="input-xlarge"
																					type="text" name="barginPrice">
																			</div>
																		</div>

																		<!-- 重新构造面板 -->
																		<div class="modal-footer">

																			<button type="button" class="btn btn-default"
																				data-dismiss="modal">我暂时不想添加了</button>
																			<button type="submit" class="btn btn-primary">
																				确认添加</button>
																		</div>
																	</fieldset>
																</form>

															</div>
															<div class="span2"></div>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>

								</div>

							</div>
							<!--使用一个弹幕-->


						</div>
					</div>
					<div class="tab-pane fade " id="reles4">
						<h1>44444</h1>
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
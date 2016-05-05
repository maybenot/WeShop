	$(document).ready(function() {
		$("#extendsClass").change(function() {
			var ec=$.trim($(this).val())  ;
			//����һ��ajax,������������뵽��̨
			//�����ȥ������Ҫ�ģ��мǣ��м�
			 var myurl="http://localhost:8080/WeShop_SE/returnDescri";
			$.ajax({
				type : "GET",
				url :myurl,
				data :{'eca':ec},
				dataType : "json",
				//contentType: "application/json",
				success : function(msg) {
					//msg��һ��map
					//Map<String,Map<Integer,String>>
					//���ԭ���еĶ���
					$("#forUse select").remove();
					$("#forUse br").remove();
					$("#forUse label").remove();
					$("#similarClass").empty();
					//选取特定区域，加入sku属性
					$("#forskuUseadd").empty();
					for(attrName in msg['descAttr']){
						//temp�����Ե�����   msg[temp]��������һ��map
					    	$("#forUse").append('<label>'+attrName+'</label>'); 
							$("#forUse").append('<br/>'); 
							$("#forUse").append('<select id='+attrName+'  name='+attrName+'>'); 
						 
							for(attrValues in  msg['descAttr'][attrName]){
								//������Ǿ��������ֵ������msg[attrName][attrValues]
								//alert(msg[attrName][attrValues]);
								 $("#"+attrName).append();
						        $("#"+attrName).append('<option value='+attrValues+'>'+msg['descAttr'][attrName][attrValues]+'</option>'); 
							}
							$("#forUse").append('</select> '); 
							$("#forUse").append('<br/>'); 
					}
					 
					$("#similarClass").append('<option>--请选择--</option>'); 
					for(attrName in msg['similarClass']['simi']){
							$("#similarClass").append('<option value='+attrName+'>'+msg['similarClass']['simi'][attrName]+'</option>'); 
					} 
				}
			});
			 
			
		});
		
		$("#SKUeClass").change(function() {
			var skuEClass=$.trim($(this).val())  ;
			//����һ��ajax,������������뵽��̨
			//�����û���sku���ԵĻ�ȡ
			$.ajax({
				type : "GET",
				//�����ȥ������Ҫ�ģ��мǣ��м�
				url :"http://localhost:8080/WeShop_SE/SKU_Descri",
				data :{'skuEClass':skuEClass},
				dataType : "json",
				//contentType: "application/json",
				success : function(msg) {
					//��һ��ѭ������skuattributeȡ����
					//ɾ��֮ǰ�Ķ���
					$("#forSKU").empty();
					
					
					for(attrName in msg){
						var tempID=attrName;
//						alert(tempID);
						//���뵽��ѡ����
						$("#forSKU").append('<label style="color: #F4A460"  id='+attrName+'>'+attrName+'</label>');
						$("#forSKU").append("<br/>");
						//�ڶ���ѭ��
						 var i=0;
						for(attrValue in msg[attrName]){
							i++;
							$("#forSKU").append('<input type="checkbox" name="skuValue" value='+attrValue+' ><span>&nbsp;'+msg[attrName][attrValue]+'</span>&nbsp;&nbsp;');
					        if(i>7){
					        	$("#forSKU").append("<br/>");
					        }
						}
						$("#forSKU").append("<br/>");
					}
				}
			});
			
			
		});
		$("#similarClass").change(function() {
			var simclass=$.trim($(this).val())  ;
			//����һ��ajax,������������뵽��̨
			//�����û���sku���ԵĻ�ȡ
			$.ajax({
				type : "GET",
				//�����ȥ������Ҫ�ģ��мǣ��м�
				url :"http://localhost:8080/WeShop_SE/addShopProductSku",
				data :{'simclass':simclass},
				dataType : "json",
				//contentType: "application/json",
				success : function(msg) {
					$("#forskuUseadd").empty();
					for(attrName in msg){
						
						$("#forskuUseadd").append('<label style="color: #F4A460"  id='+attrName+'>'+attrName+'</label>');
						$("#forskuUseadd").append("<br/>");
						//�ڶ���ѭ��
						 var i=0;
						for(attrValue in msg[attrName]){
							i++;
							$("#forskuUseadd").append('<input type="radio" name="skuValue'+attrName+'" value='+attrValue+' ><span>&nbsp;'+msg[attrName][attrValue]+'</span>&nbsp;&nbsp;');
					        if(i>7){
					        	$("#forskuUseadd").append("<br/>");
					        }
						}
						$("#forskuUseadd").append("<br/>");
					}
					
					  
				}
			});
			
			
		});
		$("#SKUselectDefine").change(function() {
			var skuEClass=$.trim($(this).val());
			$("#forSKUUse").empty();
			$("#forSKUUse").append('<span id="thisisdivforclik" class="glyphicon glyphicon-plus" onclick="SKU_Plus3()"></span>');
			$("#forSKUUse").append('<input type="hidden" name="ExtName" value="'+skuEClass+'">')
		 
			
		});
		//�û�����Զ���S
		$("#UserDefineClassSelect").change(function() {
			var skuEClass=$.trim($(this).val())  ;
			//����һ��ajax,������������뵽��̨
			//�����û���sku���ԵĻ�ȡ
			$.ajax({
				type : "GET",
				//�����ȥ������Ҫ�ģ��мǣ��м�
				url :"http://localhost:8080/WeShop_SE/SKU_UserDefine",
				data :{'skuEClass':skuEClass},
				dataType : "json",
				//contentType: "application/json",
				success : function(msg) {
					//��һ��ѭ������skuattributeȡ����
					//ɾ��֮ǰ�Ķ���
					$("#forDefineSKU").empty();
					for(attrName in msg){
						//���뵽��ѡ����
						$("#forDefineSKU").append('<br/>');
						$("#forDefineSKU").append('<label style="color: #F4A460"  id="'+attrName+'">'+attrName+'(alreadyHave:)</label>');
						$("#forDefineSKU").append("<br/>");
						//�ڶ���ѭ��
						var i=0;
						for(attrValue in msg[attrName]){
							i++;
							$("#forDefineSKU").append('<span style="color: #8B4513"  id='+attrValue+'>'+msg[attrName][attrValue]+'</span>&nbsp;&nbsp;');
							if(i>7){
								$("#forDefineSKU").append("<br/>");
							}
						}
						$("#forDefineSKU").append('<div id="'+attrValue+'" ></div>');
						
						$("#forDefineSKU").append('<span id="'+attrValue+'" class="glyphicon glyphicon-plus" onclick="SKU_Plus('+attrValue+')"></span>');
						
						
					}
				}
			});
			
			
		});
	});

function SKU_Plus(attrValue){
	//�����Ѿ��ܹ���ȡattrValue
	//alert(attrValue);
	$("#"+attrValue).append('<br/>');
	$("#"+attrValue).append('<input type="text"  name="'+attrValue+'"  placeholder="your ower SKUValue*"/>');
	//alert(attrValue);
	
}
function SKU_Plus3(){
	//�����Ѿ��ܹ���ȡattrValue
	 $("#forSKUUse").append('<br/>');
	 $("#forSKUUse").append('<input type="text"  name="SKUAttr"  placeholder="your ower SKU*"/>');
	//alert(attrValue);
}
	
	
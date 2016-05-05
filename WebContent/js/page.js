//���ڷ�ҳ�ĺ���

		function page(targetPage,lastPage){
			var myurl="http://localhost:8080/WeShop_SE/receivePage";
			 
			
			
			if(targetPage<1){
				 return false;
				
			}
			if(targetPage>lastPage){
				return false;
			}
			//ʹ��ajax�������ж�̬�Ļ�ȡ�����ҳ����Ϣ
			

			$.ajax({
				type : 'GET',
				url :myurl,
				data :{'targetPage':targetPage},
			   //dataType : "json",
			   //contentType: "application/json",
				success : function(returnMap) { 
					$("#fistListDiv").empty();
					$("#secondListDiv").empty();
					
					
					var list=returnMap["list"];
					
					var rootPath=getRootPath();
					
					
					 
					for(count in list){
						var temp=list[count];
						
						
						
						
						var i=1;
						if(i<=3){
					       $("#fistListDiv").append('<div class="col-md-4"><div class="thumbnail"><img style="width:250px;height: 200px "   alt="图片缺失"   src="'+rootPath+'/pdt_image/'+temp.imagPath +'"><div class="caption"><h6>'+temp.shopProductName +'</h6><a class="btn btn-primary" href="#">查看详情</a> <a class="btn" href="#?'+temp.shopProductNo +'">立即购买</a></div></div></div>');
						}
						if(i>3&&i<=6){
						   $("#secondListDiv").append('<div class="col-md-4"><div class="thumbnail"><img style="width:250px;height: 200px "   alt="图片缺失" src="'+rootPath+'/pdt_image/'+temp.imagPath +'"><div class="caption"><h6>'+temp.shopProductName +'</h6><a class="btn btn-primary" href="#">查看详情</a> <a class="btn" href="#?'+temp.shopProductNo +'">立即购买</a></div></div></div>');
						}
						i++;
						    
					} 
					
					
					 $("#paginat").empty();
					 var totalPage=returnMap["totalPage"];
					 $("#paginat").append('<div id="inthis"></div><ul class="pagination">   <li class="previous"><a href="#inthis"   onclick="page('+(targetPage-1)+','+totalPage+')">上一页</a></li><li><span class="label label-primary"><font color="green" >当前第&nbsp;'+targetPage+'&nbsp;页</font></span></li><li ><span class="label label-primary"><font color="green">共&nbsp;'+totalPage+'&nbsp;页</font></span></li><li class="next"><a href="#inthis"  onclick="page('+(targetPage+1)+','+totalPage+')">下一页</a></li></ul>');
					 
					 
					
					
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
				}
			});
			return ;
		}
		
		//js��ȡ��Ŀ��·�����磺 http://localhost:8083/uimcardprj
		function getRootPath(){
		    //��ȡ��ǰ��ַ���磺 http://localhost:8083/uimcardprj/share/meun.jsp
		    var curWwwPath=window.document.location.href;
		    //��ȡ�����ַ֮���Ŀ¼���磺 uimcardprj/share/meun.jsp
		    var pathName=window.document.location.pathname;
		    var pos=curWwwPath.indexOf(pathName);
		    //��ȡ�����ַ���磺 http://localhost:8083
		    var localhostPaht=curWwwPath.substring(0,pos);
		    //��ȡ��"/"����Ŀ���磺/uimcardprj
		    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		    return(localhostPaht+projectName);
		}
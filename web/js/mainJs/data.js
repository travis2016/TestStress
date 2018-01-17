/**
 *获取用户数据 
 */
function lodaUserData(searchParam){
	var url = window.location.href+"/selectAction";
	$.ajax( {
		type : "get",
		url : url,
		data: "search="+searchParam,
		contentType: "application/json",
		success : function(data) {
			//data为后台返回的Json信息
			if(data && data.status == "1"){
				var tr = $("#dataString");  //获取dataString的表格参数
				if(searchParam == 'searchUserList'){
					$.each(data.data, function(index,item){
							//产生新的tr
							 var clonedTr = tr.clone(true).attr("id","userinfo"+index).insertAfter($("#dataString"));
							 clonedTr.children("td").each(function(inner_index){
							   switch(inner_index){
									 case(0):
										$(this).html(data.data[index].username);
										break;
									 case(1):
										 if(data.data[index].sex==0){
											 $(this).html("男");
										 }else{
											 $(this).html("女");
										 }
										//$(this).html(data.data[index].sex);
										break;
									case(2):
										$(this).html(data.data[index].usernickname);
										break;
									case(3):
										if(data.data[index].group==100){
											$(this).html("管理组");
										}else if(data.data[index].group==101){
											$(this).html("测试组");
										}else if(data.data[index].group==102){
											$(this).html("普通组");
										}
										//$(this).html(data.data[index].group);
										break;
									case(4):
										$(this).html("<button type=\"button\" class=\"btn btn-success\" onclick=\"location.href='edituserperssion.jsp?userid='+data.data[index].userId\">修改</button>&nbsp;&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-success\" id = \"deleteUser\">删除</button>");
										break;
								  }
						});
						//把克隆好的tr追加原来的tr后面
						clonedTr.insertAfter(tr);
					});
					$("#dataString").hide();
					reloadRightPage();
				}else if(searchParam == 'searchGroupList'){
					$.each (data.data,function(index,item){
						//产生新的tr
						var clonedTr = tr.clone(true);
						clonedTr.children("td").each(function(inner_index){
							switch(inner_index){
								case(0):
									$(this).html(data.data[index].groupname);
									break;
								case(1):
									$(this).html("<button id=\"editGroup\" type=\"button\" class=\"btn btn-success\" onclick=\"editlocations(this);\" >修改权限</button>&nbsp;&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-success\" id = \"deleteUser\">删除</button>");
									break;
							}
						});
						clonedTr.insertAfter(tr);
					});
					$("#dataString").hide();
					reloadRightPage();
				}
			}else if(data.status == 0 && data.error_code.equal('20001')){
				//20001：参数格式错误
				alert("发生错误");
			}
		},
		error: function(error){
	            ///请求出错处理 	             
	            alert('错误信息：'+error.status);
	     }
	});
}

//修改权限js
function editPersion(){

}

//delete userData
function deleteUser(){
	
}

//修改主页面右则页面大小
function reloadRightPage() {
	var main = $(window.parent.document).find("#iframeright");
	var thisheight = $(document).height() + 30;
	main.height(thisheight);
	$(window.parent.document).find("#middle_right").height(thisheight+30);
}

function gerdata(groupnames,urlparent) {
	//中文需要两次编码
	var url = urlparent+"group/selectGroupAction";
	$.ajax( {
		type : "get",
		url : url,
		data: "search=searchSingerGroup&groupname="+encodeURI(encodeURI(groupnames)),
		contentType: "application/json",
	});
	console.log('url=='+url);
	$(function() {
		var defaultData = [
			{
				text: 'Parent 1',
				href: '#parent1',
				tags: ['4'],
				nodes: [
					{
						text: 'Child 1',
						href: '#child1',
						tags: ['2'],
						nodes: [
							{
								text: 'Grandchild 1',
								href: '/1.html',
								tags: ['0'],
								enableLinks: true,
							},
							{
								text: 'Grandchild 2',
								href: '#grandchild2',
								tags: ['0']
							}
						]
					},
					{
						text: 'Child 2',
						href: '#child2',
						tags: ['0']
					}
				]
			},
			{
				text: 'Parent 2',
				href: '#parent2',
				tags: ['0']
			},
			{
				text: 'Parent 3',
				href: '#parent3',
				tags: ['0']
			},
			{
				text: 'Parent 4',
				href: '#parent4',
				tags: ['0']
			},
			{
				text: 'Parent 5',
				href: '#parent5',
				tags: ['0']
			}
		]
		var $checkableTree = $('#treeview-checkable').treeview({
			data: defaultData,
			color: "#428bca",
			showIcon: false,
			showCheckbox: true,
			onNodeChecked: function(event, node) {
				$('#checkable-output').prepend('<p>' + node.text + ' was checked</p>');
			},
			onNodeUnchecked: function (event, node) {
				$('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
			}
		});
	});
}
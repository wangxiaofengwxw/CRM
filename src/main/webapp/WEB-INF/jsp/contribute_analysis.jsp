<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

	$(function(){//别忘了写这个
		/*展示数据的datagrid表格*/
		//这样写就不用在标签里加class，
		$("#datagrid").datagrid({
			url:'${ctx}/analysis/findCustomerContribute.action',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'customerName',title:'客户名称',width:80,align:'center'},    
			     {field:'customerTotal',title:'订单总金额',width:100,align:'center'},    
			]]  
		});
		
		/*添加和修改弹出的dialog */
		$("#dialog").dialog({
			closed:'true',
			buttons:[
				{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						doSave();
					}
				},
				{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#dialog").dialog("close");
					}
				}
				
			]
			
		});
	});

	/* 查找 */
	function doSearch(){
		$("#datagrid").datagrid("load",{
			'name':$("#name").val(),
			'trueName':$("#trueName").val()
		})
	}
	
</script>
</head>
<body>
   
    <!--  主体开始 写到js里面 -->
	<table id="datagrid" ></table>
	<!--  主体结束 -->
	
	<!-- toolbar 开始-->
	<div id="toolbar">
		
		<input id="name" class="easyui-textbox"  data-options="prompt:'客户名称'" style="width:150px"></input>
		<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:doSearch()"></a>
	</div>
	<!-- toolbar 结束-->

	
	

</body>
</html>
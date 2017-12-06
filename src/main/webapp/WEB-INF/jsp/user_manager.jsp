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
			url:'${ctx}/user/findAll.action',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'id',title:'编号',width:80,align:'center'},    
			     {field:'name',title:'用户名',width:100,align:'center'},    
			     {field:'password',title:'密码',width:80,align:'center'},    
			     {field:'trueName',title:'真实姓名',width:80,align:'center'},    
			     {field:'email',title:'邮件',width:100,align:'center'},    
			     {field:'phone',title:'联系电话',width:100,align:'center'},    
			     {field:'roleName',title:'角色',width:100,align:'center'}    
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
	/* 删除 */
	function doDelete(){
		var ids = Util.getSelectionsIds("#datagrid");
		if (ids.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据");
			return;
		}
		$.messager.confirm("系统提示", "您确认要删除么", function(r){
			if (r){
				$.post(
					"${ctx}/user/delete.action",
					{ids:ids}, 
					function(result) {
						$.messager.alert("系统提示", result.msg);
						if(result.status == Util.SUCCESS) {
							$("#datagrid").datagrid("reload");
						}
					},
					"json"
				);
			}
		})
	}
	
	//打开添加窗口
	var url;
    function openAddDialog() {
    	$("#dialog").dialog("open").dialog("setTitle","添加信息");//链式编程，提前设置属性
		url = "${ctx}/user/add.action";
		$('#form').form("clear");//清除上一次输入的数据
	}
	
	//打开修改窗口
	function openUpdateDialog() {
		var selections = $("#datagrid").datagrid("getSelections");
		if(selections.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据");
			return;
		}
		var row = selections[0];
		$("#dialog").dialog("open").dialog("setTitle","修改信息");
		url = "${ctx}/user/update.action";
		$('#form').form("load", row);//把数据填到表格了
	}
	
	//关闭窗口
	function closeDialog(){
		 $("#dialog").dialog("close");
	}
	
	//提交添加或修改的数据
	function doSave() {
		$("#form").form('submit',{
			url:url,
			onSubmit: function() {
				//检查下拉框是否有值
				if($("#roleName").combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择用户角色");
	        		return false;
				}
				//validate none 做表单字段验证，当所有字段都有效的时候返回true。该方法使用validatebox(验证框)插件。 
		        // return false to prevent submit;  
		        return $(this).form("validate");
			},
			/* 这是ServerResponse返回的json格式数据，但EasyUI又
			为了接收变成了string类型的，所以我们要手动转为json类型 */
			success:function(data){
				var data = eval('('+ data +')');//注意加单引号
				if(data.status == Util.SUCCESS) {
					$.messager.alert("系统提示", data.msg);
		    		$("#dialog").dialog("close");
		    		$("#datagrid").datagrid("reload");
				}
			}
		})
	}
		 

	/* //上传Excel表格
	function uploadExcel() {
		location.href="${ctx}/saleChance/inputExcel.action"
	} */
</script>
</head>
<body>
   
    <!--  主体开始 写到js里面 -->
	<table id="datagrid" ></table>
	<!--  主体结束 -->
	
	<!-- toolbar 开始-->
	<div id="toolbar">
		<a class="easyui-linkbutton" iconCls="icon-add" href="javascript:openAddDialog()">添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" href="javascript:openUpdateDialog()">修改</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" href="javascript:doDelete()">删除</a>
		<form name="serForm" action="${ctx}/saleChance/inputExcel.action" method="post"  enctype="multipart/form-data">
			<input type="file" name="file" id="file"/>
			<input type="submit" value="导入"/>
		</form>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="name" class="easyui-textbox"  data-options="prompt:'用户名'" style="width:150px"></input>
		<input id="trueName"  class="easyui-textbox"  data-options="prompt:'真实姓名'" style="width:150px"></input>
		<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:doSearch()"></a>
	</div>
	<!-- toolbar 结束-->

	<!-- 添加和修改共用的窗口开始 -->
	<div id="dialog" style="width:650;height:280,padding: 10px 20px" >
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>密码：</td>
					<td><input type="text" id="password" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td><input type="text" id="trueName" name="trueName" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>邮箱：</td>
					<td><input type="text" id="email" name="email" class="easyui-validatebox" required="true" validType="email"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>用户角色：</td>
					<td>
						<select class="easyui-combobox" id="roleName" name="roleName" editable="false"style="width:160">
							<option></option>
							<option value="系统管理员">系统管理员</option>
							<option value="销售主管">销售主管</option>
							<option value="客户经理">客户经理</option>
							<option value="高管">高管</option>
						</select><font color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加和修改共用的窗口结束 -->
	
	

</body>
</html>
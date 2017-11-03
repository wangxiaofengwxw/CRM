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
			url:"${ctx}/service/findAll.action?status=新创建",
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
			     {field:'customer',title:'客户',width:100,align:'center'},    
			     {field:'overview',title:'概要',width:80,align:'center'},    
			     {field:'serviceType',title:'服务类型',width:80,align:'center'},    
			     {field:'createPeople',title:'创建人',width:100,align:'center'},    
			     {field:'createTime',title:'创建日期',width:100,align:'center'},    
			]]  
		});

		/*  /* FieldTypeComment
		id            int(11)编号
		service_type           varchar(30)服务类型 1,咨询 2，建议 3，投诉
		overview         varchar(500)概要
		customer           varchar(30)客户
		status          varchar(20)1，新创建 2，已分配 3，已处理 4，已归档
		service_reques            tvarchar(500)服务请求
		create_people           varchar(100)创建人
		create_time         datetime创建日期
		assigner         varchar(100)分配人
		assign_time        datetime分配日期
		service_deal       varchar(500)服务处理描述
		service_deal_people     varchar(20)服务处理人
		service_deal_timedatetime编号
		service_deal_resultvarchar(500)编号
		satisfyvarchar(50)编号  
		添加和修改弹出的dialog  */
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

	//打开添加窗口
	var url;
    function openAddDialog() {
    	$("#dialog").dialog("open").dialog("setTitle","添加信息");//链式编程，提前设置属性
		url = "${ctx}/service/add.action";
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
		url = "${ctx}/service/update.action";
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
</script>
</head>
<body>
   
    <!--  主体开始 写到js里面 -->
	<table id="datagrid" ></table>
	<!--  主体结束 -->
	
	<!-- toolbar 开始-->
	<div id="toolbar">
		<a class="easyui-linkbutton" iconCls="icon-edit" href="javascript:openUpdateDialog()">分配</a>
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
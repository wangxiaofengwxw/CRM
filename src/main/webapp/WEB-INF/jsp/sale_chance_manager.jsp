<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

	$(function(){
		/*展示数据的datagrid表格*/
		//这样写就不用在标签里加class，
		$("#datagrid").datagrid({
			url:'${ctx}/saleChance/findAll.action',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:false,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'id',title:'编号',width:80,align:'center'},    
			     {field:'chanceSource',title:'机会来源',width:100,align:'center'},    
			     {field:'customerName',title:'客户名称',width:80,align:'center'},    
			     {field:'successRate',title:'成功几率',width:80,align:'center'},    
			     {field:'overview',title:'概要',width:100,align:'center'},    
			     {field:'linkMan',title:'联系人',width:100,align:'center'},    
			     {field:'linkPhone',title:'联系电话',width:100,align:'center'},    
			     {field:'description',title:'机会描述',width:100,align:'center'},
			     {field:'createMan',title:'创建人',width:100,align:'center'},
			     {field:'createTime',title:'创建时间',width:100,align:'center'},
			     {field:'assignMan',title:'指派人',width:100,align:'center'},
			     {field:'assignTime',title:'指派时间',width:120,align:'center'},
			     {field:'status',title:'分配状态',width:100,align:'center',formatter:function(value,row,index) {
			    	 if(value == 1) {
			    		 return "已分配";
			    	 } else {
			    		 return "未分配";
			    	 }
			     }},
			     {field:'devResult',title:'客户开发状态',width:100,align:'center',formatter:function(value,row,index) {
			    	 /* 客户开发状态 0 未开发 1 开发中 2 开发成功 3 开发失败 */
			    	 if(value == 0) {
			    		 return "未开发";
			    	 } 
			    	 if(value == 1) {
			    		 return "开发中";
			    	 }
			    	 if(value == 2) {
			    		 return "开发成功";
			    	 }
			    	 if(value == 3) {
			    		 return "开发失败";
			    	 }
			     }}
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
			'customer_name':$("#customer_name").val(),
			'overview':$("#overview").val(),
			'createMan':$("#createMan").val(),
			'status':$("#status").val(),
			'beginTime':$("#beginTime").val(),
			'endTime':$("#endTime").val(),
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
					"${ctx}/saleChance/delete.action",
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
    	$("#dialog").dialog("open").dialog("setTitle","添加信息");
		$('#form').form("clear");
		$("#add_createMan").val('${admin.name}');
		$("#createTime").val(Util.getCurrentDateTime());
		url = "${ctx}/saleChance/add.action";
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
		url = "${ctx}/saleChance/update.action";
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
				/*  if($("#assignMan").combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择指派人");
	        		return false;
				}  */
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
	//如果分配指派人，指派时间为当前时间
	 $(function(){
	    $("#assignMan").combobox({
	        onSelect:function(record){//record就是User对象
	            if(record.trueName!=''){
	                $("#assignTime").val(Util.getCurrentDateTime());
	            }else{
	                $("#assignTime").val("");
	            }
	        }
	    });
	 }); 

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
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="customerName" class="easyui-textbox"  data-options="prompt:'客户名称'" style="width:150px"></input>
		<input id="overview"  class="easyui-textbox"  data-options="prompt:'概要'" style="width:150px"></input>
		<input id="createMan"  class="easyui-textbox"  data-options="prompt:'创建人'" style="width:150px"></input>
		分配状态 : <select id="status" class="easyui-combobox"
					    data-options="panelHeight:'auto',editable:false  ">
					    <option value="-1">-未选择-</option>
					    <option value="1">已分配</option>
					    <option value="0">未分配</option>
					    </select>
		<span>按创建时间段查询</span>
		<input id="beginTime" name="beginTime" class="easyui-datetimebox" style="width:150px">--
		<input id="endTime" name="endTime" class="easyui-datetimebox" style="width:150px">
		<a class="easyui-linkbutton" iconCls="icon-search" href="javascript:doSearch()"></a>
	</div>
	<!-- toolbar 结束-->

	<!-- 添加和修改共用的窗口开始 -->
	<div id="dialog" style="width:650;height:500,padding: 10px 20px" >
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
					<span>机会来源：</span>
					<input type="text" id="chanceSource" name="chanceSource" class="easyui-validatebox" required="true"/><font color="red">*</font>
				    <span>客户名称：</span>
					<input type="text" id="customerName" name="customerName" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
					<span>概率：</span>
					<input type="text" id="successRate" name="successRate" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
					<span>概要：</span>
					<input type="text" id="overview" name="overview" class="easyui-validatebox" required="true"/><font color="red">*</font>
					<span>联系人：</span>
					<input type="text" id="linkMan" name="linkMan" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
					<span>联系电话：</span>
					<input type="text" id="linkPhone" name="linkPhone" class="easyui-validatebox" required="true"/><font color="red">*</font>
					<span>机会描述：</span>
					<input type="text" id="description" name="description" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
					<span>创建人：</span>
					<input type="text" id="add_createMan" name="createMan"  class="easyui-validatebox"/><font color="red">*</font>
					<span>创建时间：</span>
					<input type="text" id="createTime" name="createTime" class="easyui-validatebox" /><font color="red">*</font><br>
						指派人：<select style="width: 80px" id="assignMan" name="assignMan" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/user/findAssignMan.action',
					 	valueField: 'trueName',
					 	textField: 'trueName',
					 	panelHeight:'auto',
					 	"></select>
					 	<span>指派时间：</span>
					<input type="text" id="assignTime" name="assignTime" class="easyui-validatebox" /><font color="red">*</font>
		</form>
	</div>
	<!-- 添加和修改共用的窗口结束 -->
	
	

</body>
</html>
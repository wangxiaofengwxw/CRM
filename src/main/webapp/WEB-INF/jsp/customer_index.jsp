<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<style>
			* {
				margin: 0;
				padding: 0;
			}
		</style>
		<script type="text/javascript">
		
		function godelete(){
			array = Util.getSelected("#datagrid");
			if(array.length == 0){
				$.messager.alert('系统提示',"请选择您要删除的数据");
				return;
			}
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){ $.post(
						  "${ctx}/customer/delete.action", //url
						   {ids:array}, //data
						    function(data) { //callback
						      $.messager.alert('系统提示',data.msg);
						        if(data.status == Util.SUCCESS){
						           $('#datagrid').datagrid('reload');
						           }
						     },
						   "json" //type
					 );
			    }    
			}); 
			
		}
		
		function doSearch(){
			$('#datagrid').datagrid('load',{
				'name':$("#name").val(),
				'num':$("#num").val(),
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				url = "${ctx}/customer/add.action";
				$('#form').form("clear");
			}
			
			function openUpdateDialog(){
				var row = $('#datagrid').datagrid('getSelected');
				if(row == null){
					$.messager.alert("系统提示","请选择你要修改的对象");
					return;
				}
				$("#dialog").dialog("open").dialog("setTitle","修改信息");
				url = "${ctx}/customer/update.action";
				$('#form').form('load',row);
			}
			
			function dosave(){
				$('#form').form('submit', {    
				    url:url,    
				    onSubmit: function(){    
				        return $(this).form("validate");
				    },       
				    success:function(data){    
				    	var data = eval('(' + data + ')');
				        if(data.status == Util.SUCCESS){
				        	$.messager.alert("系统提示",data.msg);
				        	$("#dialog").dialog('close');
				        	$('#datagrid').datagrid('reload');
				        }    
				    }    
				});  

			}
			$(function(){
				$("#datagrid").datagrid({
				 	url:'${ctx}/customer/findAll.action',
					fit:true,
					singleSelect:true,
					toolbar:'#toolbar',
					rownumbers:true,
					fitColumns :false,
					pagination:true,
					columns:[[    
					     {field:'ck',checkbox:true},   
					     {field:'id',title:'编号',width:50,align:'center'},    
					     {field:'num',title:'客户编号',width:200,align:'center'},    
					     {field:'name',title:'客户名称',width:150,align:'center'},    
					     {field:'managerName',title:'客户经理',width:100,align:'center'}, 
					     {field:'level',title:'客户等级',width:250,align:'center'},
					     {field:'phone',title:'联系电话',width:150,align:'center'},
					     {field:'region',title:'客户地区',width:100,align:'center'}, 
					     {field:'satisfy',title:'客户满意度',width:100,align:'center'}, 
					     {field:'credit',title:'客户信用度',width:100,align:'center'}, 
					     {field:'postCode',title:'邮政编码',width:100,align:'center'}, 
					     {field:'phone',title:'联系电话',width:150,align:'center'},
					     {field:'fax',title:'传真',width:150,align:'center'},
					     {field:'webSite',title:'网址',width:150,align:'center'},
					     {field:'address',title:'客户地址',width:150,align:'center'},
					     {field:'licenceNo',title:'营业执照注册号',width:150,align:'center'},
					     {field:'legalPerson',title:'法人',width:150,align:'center'},
					     {field:'bankroll',title:'注册资金',width:150,align:'center'}, 
					     {field:'turnover',title:'年营业额',width:150,align:'center'},
					     {field:'bankName',title:'开户银行',width:150,align:'center'}  ,
					     {field:'bankAccount',title:'开户帐号',width:150,align:'center'},  
					     {field:'localTaxNo',title:'地税登记号',width:150,align:'center'}, 
					     {field:'nationalTaxNo',title:'国税登记号',width:150,align:'center'}, 
					     {field:'status',title:'客户状态',width:150,align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value == 0){
					    			 return "正常";
					    		 }else{
					    			 return "客户流失";
					    		 }
					    	 }
					     }  
					     
					]]
				});
		});
			function openLinkManTab() {
				var ids = Util.getSelectionsIds("#datagrid");
				if (ids.length == 0) {
					$.messager.alert("系统提示", "请选择数据");
					return;
				}
				window.parent.openTab('客户联系人管理','${ctx}/customer/linkMan.action?customerIds='+ids,'icon-khkfjh');
			}
			
			function openCustomerContactTab() {
				var ids = Util.getSelectionsIds("#datagrid");
				if (ids.length == 0) {
					$.messager.alert("系统提示", "请选择数据");
					return;
				}
				window.parent.openTab('客户交往记录管理 ','${ctx}/customer/customerContact.action?customerIds='+ids,'icon-khkfjh');
			}
		</script>
	</head>
	
	<body>
		<!--  主体开始 写到js里面 -->
		<table id="datagrid" ></table>
		<!--  主体结束 -->
			
			<!-- toolbar -->
			<div id="toolbar">
					<a href="javascript:openaddDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
					<a href="javascript:godelete();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
					<a href="javascript:openUpdateDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">修改</a>
					<a href="javascript:openLinkManTab();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">联系人管理</a>
					<a href="javascript:openCustomerContactTab();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">交往记录管理</a>
					<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">历史订单查看</a>
					<input  class="easyui-textbox" id="name"data-options="prompt:'客户名称'" />
					<input  class="easyui-textbox" id="num"data-options="prompt:'客户编号'" />
					<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
	</body>
	<!-- 添加用户隐藏的div开始 -->
	<div id="dialog" class="easyui-dialog" style="width:700px;height:500px;" closed="true" buttons="#dialog-button">
    		<form id="form" action="" method="post">   
    			<input type="hidden" id="id" name="id"/>
			    <table cellspacing="8px">
		    		<tr>
		    			<td>客户名称:</td>
		    			<td><input id="nameId" class="easyui-textbox" type="text" name="name" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>地区:</td>
		    			<td>
		    				<select class="easyui-combobox" panelHeight='auto' style="width: 170px;" data-options="required:true" name="region">
		    					<option value="">请选择</option>
		    					<option value="北京">北京</option>
		    					<option value="上海">上海</option>
		    					<option value="青岛">青岛</option>
		    					<option value="深圳">深圳</option>
		    				</select><font color="red">*</font>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>客户经理:</td>
		    			<td><input id="managerNameId" class="easyui-combobox" name="managerName"
		    				valueField='managerName' textField='trueName' panelHeight='auto'  url="${ctx}/user/findAssignMan.action" data-options="required:true"></input>
		    				<font color="red">*</font></td>
		    			<td>客户等级:</td>
		    			<td><input id="levelId" class="easyui-combobox" name="level" 
		    				valueField='level'textField='dataDicName' panelHeight='auto'  url="${ctx}/dataDic/findDataDicName.action" data-options="required:true"></input>
		    				<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>客户满意度:</td>
		    			<td>
		    				<select class="easyui-combobox" panelHeight='auto' style="width: 170px;" data-options="required:true" name="satisfy">
		    					<option value="☆">☆</option>
		    					<option value="☆☆">☆☆</option>
		    					<option value="☆☆☆">☆☆☆</option>
		    					<option value="☆☆☆☆">☆☆☆☆</option>
		    					<option value="☆☆☆☆☆">☆☆☆☆☆</option>
		    				</select><font color="red">*</font>
		    			</td>
		    			<td>客户信用度:</td>
		    			<td>
		    				<select class="easyui-combobox" panelHeight='auto' style="width: 170px;" data-options="required:true" name="credit">
		    					<option value="☆">☆</option>
		    					<option value="☆☆">☆☆</option>
		    					<option value="☆☆☆">☆☆☆</option>
		    					<option value="☆☆☆☆">☆☆☆☆</option>
		    					<option value="☆☆☆☆☆">☆☆☆☆☆</option>
		    				</select><font color="red">*</font>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>邮政编码:</td>
		    			<td><input id="postCodeId" class="easyui-textbox" name="postCode" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>联系电话:</td>
		    			<td><input id="phoneId" class="easyui-textbox" name="phone" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>传真:</td>
		    			<td><input id="faxId" class="easyui-textbox" name="fax" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>网址:</td>
		    			<td><input id="webSiteId" class="easyui-textbox" name="webSite" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>客户地址:</td>
		    			<td colspan="4"><input id="addressId" class="easyui-textbox" name="address" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>营业执照注册号:</td>
		    			<td><input id="licenceNoId" class="easyui-textbox" name="licenceNo" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>法人:</td>
		    			<td><input id="legalPersonId" class="easyui-textbox" name="legalPerson" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>注册资金（万元）:</td>
		    			<td><input id="bankrollId" class="easyui-textbox" name="bankroll" ></input></td>
		    			<td>年营业额（万元）:</td>
		    			<td><input id="turnoverId" class="easyui-textbox" name="turnover" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>开户银行:</td>
		    			<td><input id="bankNameId" class="easyui-textbox" name="bankName" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>开户帐号:</td>
		    			<td><input id="bankAccountId" class="easyui-textbox" name="bankAccount" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>地税登记号:</td>
		    			<td><input id="localTaxNoId" class="easyui-textbox" name="localTaxNo"></input></td>
		    			<td>国税登记号:</td>
		    			<td><input id="nationalTaxNoId" class="easyui-textbox" name="nationalTaxNo"></input></td>
		    		</tr>
		    	</table>
			    <div id="dialog-button">   
			    	<a href="javascript:dosave();" class="easyui-linkbutton" iconCls = "icon-add">提交</a>
			    	<a  class="easyui-linkbutton" iconCls = "icon-cancel">关闭</a>
			    </div>    
			</form>  
    	</div>
    	<!-- 添加用户隐藏的div结束 -->
</html>
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
		 $("#add_createMan").val('${admin.name}');
		$("#createTime").val(Util.getCurrentDateTime());
	}) 
</script> 
	
</head>
<body>

	<div id="p" class="easyui-panel" title="销售机会信息" style="width: 600px;height: 350px">
		<form action="" id="form">
		 	<table cellspacing="8px">
		   		<tr>
		   			<td>服务类型：</td>
		   			<td><select style="width: 80px" id="assignMan" name="assignMan" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic/findService.action',
					 	valueField: 'dataDicValue',
					 	textField: 'dataDicValue',
					 	panelHeight:'auto',
					 	"></select></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>客户 : </td>
		   			<td><input type="text" id="chanceSource" name="chanceSource" readonly="readonly"/></td>
		   		</tr>
		   		
		   		<tr>
		   			<td>概要：</td>
		   			<td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px" readonly="readonly"/></td>
		   		</tr>
		   		<tr>
		   			<td>服务要求：</td>
		   			<td colspan="4">
		   				<textarea rows="5" cols="50" id="description" name="description" readonly="readonly"></textarea>
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>创建人：</td>
		   			<td><input type="text" readonly="readonly" id="add_createMan" name="createMan" /></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>创建时间：</td>
		   			<td><input type="text" id="createTime" name="createTime" readonly="readonly"/></td>
		   		</tr>
		   	  </table>
		   	  <!-- dialog-button 开始-->
				<div id="dialog-button" align="right">
					<a href="javascript:doSave()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
					<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
				</div>
				<!-- dialog-button 结束-->
		  </form>
	 </div>
	
	

</body>
</html>
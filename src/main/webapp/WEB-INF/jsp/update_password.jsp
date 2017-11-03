<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	$("#dialog").dialog({
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
	$("#oldPassword").blur(function() {
		if($("#oldPassword").val() != ${admin.password}) {
			/* window.location.href='${ctx}/admin/updatePassword.action'; */
			$.messager.alert('警告','密码输入错误!','error');
			document.getElementById("oldPassword").value="";
		} 
	});
	function doSave() {
		if($("#newPassword1").val() != $("#newPassword2").val()) {
			$.messager.alert('警告','密码输入不一致!','error');
			return;
		}
		$("#form").form('submit',{
			url:"${ctx}/admin/toUpdatePassword.action",
			onSubmit: function() {
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
})
</script>
</head>
<body >

	<!-- 修改密码的窗口开始 -->
	<div id="dialog" class="easyui-dialog" style="width:400px;height:400px,padding: 10px 20px"
			title="Basic Dialog"
			data-options="iconCls:'icon-save'" >
		<form action="" id="form" method="post">
		        <input type="hidden" id="id" name="id" value="${admin.id }"/><br>
				用户名：&nbsp;&nbsp;&nbsp;
				<input type="text" id="name"  value="${admin.name }" class="easyui-validatebox" /><font color="red">*</font><br>
				原密码：&nbsp;&nbsp;&nbsp;
				<input type="text" id="oldPassword" class="easyui-validatebox" required="true"/><font color="red">*</font>
				<br>
				新密码：&nbsp;&nbsp;&nbsp;
				<input type="text" id="newPassword1" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
				新密码：&nbsp;&nbsp;&nbsp;
				<input type="text" id="newPassword2" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
	</form>
	</div>
    <!-- 修改密码的窗口结束 -->
		
</body>
</html>
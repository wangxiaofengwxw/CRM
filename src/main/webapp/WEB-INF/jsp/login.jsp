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
				text:'登录',
				iconCls:'icon-ok',
				handler:function(){
					doSave();
				}
			},
			{
				text:'注册',
				iconCls:'icon-cancel',
				handler:function(){
					$("#dialog").dialog("close");
				}
			}
			
		]
	});
	
	function doSave() {
		$("#form").form('submit',{
			url:"${ctx}/admin/toLogin.action",
			onSubmit: function() {
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
		    		window.location.href='${ctx}/index/index.action';
				}
			}
		})
		
	}
	
})
</script>
</head><%-- ${ctx}/resources/img/sky.jpg --%>
<body>
	<div id="dialog" class="easyui-dialog" style="width:300px;height:400px,padding: 10px 20px"
			title="登录"
			data-options="iconCls:'icon-save'" >
		<form action="" id="form" method="post">
		        <input type="hidden" id="id" name="id"/><br>
				用户名：&nbsp;&nbsp;&nbsp;
				<input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
				密&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;&nbsp;
				<input type="text" id="password" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font><br>
	</form>
	</div>
</body>
</html>
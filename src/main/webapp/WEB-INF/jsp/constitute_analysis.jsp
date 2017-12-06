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
	 // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById('main'));

   // 异步加载数据
   $.post('${ctx}/analysis/findCustomerConstitute.action',
   		function(result){
   			if(result.status == Util.SUCCESS) {
   				var xAxisData=new Array();
					var seriesData=new Array();
					var data = result.data;
					for(var i=0;i<data.length;i++){
						xAxisData.push(data[i].customerLevel);
						seriesData.push(data[i].customerNum);
					}
					console.log(xAxisData);
		        	// 填入数据
		            myChart.setOption({
		                title: {
		                    text: '客户贡献分析'
		                },
		                tooltip: {},
		                legend: {
		                    data:['销量']
		                },
		                xAxis: {
		                    data: xAxisData
		                },
		                yAxis: {},
		                series: [{
		                    // 根据名字对应到相应的系列
		                    name: '销量',
		                    type: 'bar',
		                    data: seriesData
		                }]
		            });
   			} else {
   				alert("查询失败");
   			}
	        	
   		},
   		'json'
   	);
   
});
</script>
</head>
<body>

   <div id="main" style="width: 600px;height:400px;"></div>
    <!-- <script type="text/javascript">

		//基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		
		// 指定图表的配置项和数据
		var option = {
		    title: {
		        text: 'ECharts 入门示例'
		    },
		    tooltip: {},
		    legend: {
		        data:['销量']
		    },
		    xAxis: {
		        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
		    },
		    yAxis: {},
		    series: [{
		        name: '销量',
		        type: 'bar',
		        data: [5, 20, 36, 10, 10, 20]
		    }]
		};
		
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
</script> -->
</body>
</html>
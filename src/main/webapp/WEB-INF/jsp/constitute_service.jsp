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
			var arr = [];
			var myChart = echarts.init(document.getElementById('main'));
            option = {
            	     title : {
            	         text: '客户服务分析',
            	         //subtext: '纯属虚构',
            	         x:'center'
            	     },
            	     tooltip : {
            	         trigger: 'item',
            	         formatter: "{a} <br/>{b} : {c} ({d}%)"
            	     },
            	     legend: {
            	         orient : 'vertical',
            	         x : 'left',
            	         data:['0-10000','10000-20000','20000-30000','30000-40000','40000以上']
            	     },
            	     toolbox: {
            	         show : true,
            	         feature : {
            	             mark : {show: true},
            	             dataView : {show: true, readOnly: false},
            	             magicType : {
            	                 show: true, 
            	                 type: ['pie', 'funnel'],
            	                 option: {
            	                     funnel: {
            	                         x: '25%',
            	                         width: '50%',
            	                         funnelAlign: 'left',
            	                         max: 1548
            	                     }
            	                 }
            	             },
            	             restore : {show: true},
            	             saveAsImage : {show: true}
            	         }
            	     },
            	     calculable : true,
            	     series : [
            	          {
            	             name:'访问来源',
            	             type:'pie',
            	             radius : '55%',
            	             center: ['50%', '60%'],
            	             data : (function(){
            	             var arr=[];
            	             $.ajax({
            	                  type : "post",
            	                   async : false, //同步执行
            	                  url : "${ctx}/analysis/findCustomerService.action",
            	                  data : {},
            	                 dataType : "json", //返回数据形式为json
            	                   success : function(result) {
            	                		var data = result.data;
            	                		console.log(data);
							    		if(result.status == Util.SUCCESS){
							    			for(var i=0;i<data.length;i++){
							    				arr.push({value:data[i].serviceTotal,
							    				name:data[i].serviceType});
											}
							    			console.log(arr);
							    		}else{
							    			$.messager.alert('系统提示','查询失败');
							    		}	
            	                                          
            	                  },
            	                 })
            	                   return arr;
            	                })() 
            	             
            	          }
            	      ]
            	  };
			myChart.setOption(option);
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
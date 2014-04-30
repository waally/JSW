<%@ page language="java" import="java.util.*,com.hn.jsw.bean.Dept" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Dept> depts = (List<Dept>)request.getAttribute("depts");
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="shortcut icon" href="<%=path%>/images/yun.jpg">
    <title>统计科</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path%>/css/bootstrap/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=path%>/css/bootstrap/justified-nav.css" rel="stylesheet">
    <script type="text/javascript">
    var msgdata = {};
    var deptid = 0;
    var sort = "asc";
    var showTS = "false";
    var picType="column";
    var pheight=500;
    var sts = [];
    function showPAjax(){
	    $.ajax({
			   type: "POST",
			   url: "show",
			   data: "id="+deptid+"&sort="+sort+"&showTSort="+showTS+"&picType="+picType,
			   success: function(msg){
				   msgdata=msg;
				   sts=msg.sts;
				   if(picType=="column"){
					   showPicH(msg);
				   }else{
					   showPicC(msg);
				   }
			   }
		});
    }
    
    function showPicH(msg){
    	$('#container').highcharts({
            chart: {
                borderWidth :1,
                //height: 500,
                //height: 804,
                height: pheight,
                backgroundColor: '#F4F4F4',
                marginBottom: 110,
                marginTop: 100
            },
            colors: [
                     '#2f7ed8', 
                     '#008100', 
                     '#8bbc21', 
                     '#910000', 
                     '#1aadce', 
                     '#492970',
                     '#f28f43', 
                     '#77a1e5', 
                     '#c42525', 
                     '#a6c96a'
                  ],
            exporting: {
                enabled: false
            },
            title: {
            	text:"<h2 style='margin-top: 5px;'>"+msg.title+"</h2>",
                useHTML:true
            },
            subtitle:{
            	text:msg.zavg==null?"县平均值:"+msg.xavg:"县平均值:"+msg.xavg+"<br/>本乡镇平均值:"+msg.zavg,
            	align:"right",
            	y:60
            },
            xAxis: {
                categories: msg.name
            },
            yAxis: {
                min: 0,
                title: {
                    text: '得分'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    },
                    formatter: function() {
                        return sts[this.x];//Math.round(this.total*10)/10;
                    }
                }
            },
            
            legend: {
            	align: 'center',
                verticalAlign: 'bottom',
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function() {
                	if(this.point.stackTotal){
	                    return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +': '+ this.y +'<br/>'+
	                        '总分: '+ this.point.stackTotal;
                	}else{
	                    return '平均值: '+ this.y ;
                	}
                }
            },
            plotOptions: {
            	 column: {
                     stacking: 'normal',
                     dataLabels: {
                    	 enabled: true,
                    	 formatter: function() {
			                    return this.point.pm;},
                         color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                     }
                 }
            },
            series:msg.series
        });
    }
    function showPicC(msg){
    	$('#container').highcharts({
            chart: {
                borderWidth :1,
                height: 750,
                backgroundColor: '#F4F4F4',
                marginBottom: 90,
                marginTop: 70
            },
            colors: [
                     '#2f7ed8', 
                     '#008100', 
                     '#8bbc21', 
                     '#910000', 
                     '#1aadce', 
                     '#492970',
                     '#f28f43', 
                     '#77a1e5', 
                     '#c42525', 
                     '#a6c96a'
                  ],
            exporting: {
                enabled: false
            },
            title: {
                text:"<h2 style='margin-top: 5px;'>"+msg.title+"</h2>",
                useHTML:true
            },
            subtitle:{
            	text:msg.zavg==null?"县平均值:"+msg.xavg:"县平均值:"+msg.xavg+"<br/>本乡镇平均值:"+msg.zavg,
            	align:"right",
            	y:40
            },
            xAxis: {
                categories: msg.name
            },
            yAxis: {
                min: 0,
                title: {
                    text: '得分'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    },
                    formatter: function() {
                        return sts[this.x];//Math.round(this.total*10)/10;
                    }
                }
            },
            legend: {
            	align: 'center',
                verticalAlign: 'bottom',
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function() {
                	if(this.point.stackTotal){
	                    return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +': '+ this.y +'<br/>'+
	                        '总分: '+ this.point.stackTotal;
                	}else{
	                    return '平均值: '+ this.y ;
                	}
                }
            },
            plotOptions: {
            	 bar: {
                     stacking: 'normal',
                     pointWidth: 20,
                     dataLabels: {
                    	 enabled: true,
                    	 formatter: function() {
			                    return this.point.pm;},
                         color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                     }
                 }
            },
            series:msg.series
        });
    }
	</script>
  </head>
  <body>
<div class="container" style="text-align:center">
      <div class="masthead">
        <h3 class="text-muted" style="display: inline;">统计科</h3>
        <div class="btn-group" style="margin-left:400px;">
		  <button type="button" class="btn btn-primary" onclick="changeSort('asc')">顺序</button>
		  <button type="button" class="btn btn-primary" onclick="changeSort('')">无序</button>
		  <button type="button" class="btn btn-primary" onclick="changeSort('desc')">倒序</button>
		  <button type="button" class="btn btn-primary" onclick="changeShowTS('true')">排名</button>
		  <button type="button" class="btn btn-primary" onclick="changeShowTS('false')">无排名</button>
		  <button type="button" class="btn btn-primary" onclick="changePicType('bar')">条形图</button>
		  <button type="button" class="btn btn-primary" onclick="changePicType('column')">柱形图</button>
		  <button type="button" class="btn btn-primary" onclick="printPicH(500)">正常</button>
		  <button type="button" class="btn btn-primary" onclick="printPicH(804)">打印1</button>
		  <button type="button" class="btn btn-primary" onclick="printPicH(1414)">打印2</button>
		</div>
		<br>
		&nbsp;
        <ul class="nav nav-justified" style="max-height: 152px;">
         <%for(Dept dept:depts){%>
        	 <li><a href='javascript:void(0)' onclick="showPAjaxId(<%=dept.getId()%>,'asc')"><%=dept.getName()%></a></li>
         <%}%>
        </ul>
      </div>
      <div class="jumbotron" style="padding-left:0px;padding-right: 0px">
         <div id="container"></div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=path%>/js/bootstrap/jquery.min.js"></script>
    <script src="<%=path%>/js/bootstrap/bootstrap.min.js"></script>
    <script src="<%=path%>/js/highcharts.js"></script>
	<script src="<%=path%>/js/modules/exporting.js"></script>
	<script>
	$(function () {
		showPAjax();
	});
	function showPAjaxId(zid){
		deptid=zid;
		showPAjax();
	}
	function changeShowTS(sts){
		showTS = sts;
		showPAjax();
	}
	function changeSort(zsort){
		sort=zsort;
		showPAjax();
	}
	function changePicType(type){
		picType = type;
		showPAjax();
	}
	function printPicH(phigh){
		pheight=phigh;
		showPAjax();
	}
	</script>
  </body>
</html>

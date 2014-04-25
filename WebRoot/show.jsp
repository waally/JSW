<%@ page language="java" import="java.util.*,com.hn.jsw.tj.Dept" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//System.out.println(((List)request.getAttribute("list")).size());
Map<String,String> depts = (Map<String,String>)request.getAttribute("map");
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '<%=depts.get("title")%>'
            },
            xAxis: {
                categories: [<%=depts.get("name")%>]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '总得分'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +': '+ this.y +'<br/>'+
                        'Total: '+ this.point.stackTotal;
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black, 0 0 3px black'
                        }
                    }
                }
            },
            series: [{
                name: '新婚',
                data: [<%=depts.get("xh")%>]
            }, {
                name: '二多孩',
                data: [<%=depts.get("edh")%>]
            }, {
                name: '无孕情',
                data: [<%=depts.get("wrq")%>]
            }, {
                name: '早孕发现',
                data: [<%=depts.get("zr")%>]
            }, {
                name: '长效节育',
                data: [<%=depts.get("cx")%>]
            }, {
                name: '结扎',
                data: [<%=depts.get("jz")%>]
            }]
        });
    });
		</script>
	</head>
	<body>
<script src="js/highcharts.js"></script>
<script src="js/modules/exporting.js"></script>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	</body>
</html>

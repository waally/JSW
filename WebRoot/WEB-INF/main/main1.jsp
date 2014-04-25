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
    <link rel="shortcut icon" href="/td/images/yun.jpg">
    <title>工具平台</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/css/bootstrap/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=path %>/css/bootstrap/signin.css" rel="stylesheet">
  </head>

  <body>

    <div class="container" style="height:25px;width:100%;text-align:center;line-height:25px;">

      <form class="form-signin" role="form" action="upload" enctype="multipart/form-data" method="post">
        <h2 class="form-signin-heading">导入Excel文件</h2>
        <input type="file" class="form-control" id="exampleInputFile" name="image">
        <br/>
        <button class="btn btn-lg btn-primary " type="submit">导入</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-lg btn-primary " type="submit">查看</button>
      </form>

    </div>
  </body>
</html>
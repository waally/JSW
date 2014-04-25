<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>工具平台登录</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/css/bootstrap/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=path %>/css/bootstrap/signin.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">

      <form class="form-signin" role="form" action="login">
        <h2 class="form-signin-heading">怀宁县计生委用户登录</h2>
        <input type="text" name="name" class="form-control" placeholder="用户名" required autofocus>
        <input type="password" name="passwd" class="form-control" placeholder="密码" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> 记住我
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>

    </div>
  </body>
</html>
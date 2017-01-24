<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Web页面</title>
</head>
<body >
username : <sec:authentication property="name"/>
<h2>Hello World!jsp</h2>

<form action="http://localhost:8080/users/import" method="post"
      enctype="multipart/form-data">
    <p>
        选择文件:<input type="file" name="files">
    <p>
        <input type="submit" value="提交">
</form>
</body>
</html>

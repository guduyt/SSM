<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Web页面</title>
</head>
<body >
<h2>Hello World!</h2>

<form action="http://localhost:8080/users/import" method="post"
      enctype="multipart/form-data">
    <p>
        选择文件:<input type="file" name="files">
    <p>
        <input type="submit" value="提交">
</form>
</body>
</html>

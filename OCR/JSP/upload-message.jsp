<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>消息提示</title>
</head>
<body>
    tesseract-ocr output:<br/><br/>
    ${message}
    <br/><br/>
    ${Filename}
    <br/><br/>
    <a href="${pageContext.request.contextPath}/upload.jsp">返回：上传文件</a>
</body>
</html>

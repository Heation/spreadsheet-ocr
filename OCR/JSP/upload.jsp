<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>上传文件</title>
</head>
<body>
    提交需要 tesseract-ocr 识别的图片。<br/><br/>
    <form action="${pageContext.request.contextPath}/UploadServlet" enctype="multipart/form-data" method="post">
        上传文件：<input type="file" name="file1"><br/><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>

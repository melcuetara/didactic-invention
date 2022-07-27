<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
</head>

<body>
    <h1>Invalid Session!</h1>
    <h2>Please login again.</h2>
    <s:url var="url" action="loginAccount">
    </s:url>
    <s:a href="%{url}">Login Account</s:a>
</body>

</html>
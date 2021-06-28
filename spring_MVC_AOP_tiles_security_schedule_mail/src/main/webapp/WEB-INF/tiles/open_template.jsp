<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<tiles:insertAttribute name="style" />
</head>
<body>

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="js" />
</body>
</html>
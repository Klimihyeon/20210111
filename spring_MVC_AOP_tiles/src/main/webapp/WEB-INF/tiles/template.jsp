<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<title></title>
<tiles:insertAttribute name="style" />
</head>

<body class="hold-transition sidebar-mini" onload="init();">
	<div class="wrapper">
		<tiles:insertAttribute name="header" />
		
		<tiles:insertAttribute name="aside" />
		
		
	    <tiles:insertAttribute name="body" />    
	    
	    <tiles:insertAttribute name="footer" />
	</div>    
<tiles:insertAttribute name="js" />
</body>
</html>




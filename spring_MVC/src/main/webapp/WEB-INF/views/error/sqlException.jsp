<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>에러페이지</title>

<body>
	<!-- Content Header (Page header) -->
   	<section class="content-header">
      <h1>error_common
        <small>Optional description</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

      <!--------------------------
        | Your Page Content Here |
        -------------------------->
        
        <h4>${exception.getMessage()}</h4>

		<ul>
			<c:forEach items="${exception.getStackTrace() }" var="est">			
				<li>${est.toString() }</li>
			</c:forEach>
		</ul>

    </section>
    <!-- /.content -->	

</body>
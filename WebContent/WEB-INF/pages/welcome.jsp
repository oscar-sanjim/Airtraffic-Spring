<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="view_component" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<view_component:scripts/>		
	<link href="<c:url value="/resources/css/welcome.css"/>" rel="stylesheet">		
</head>
<body>

	<view_component:navigation/>
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10 welcome_message">
				Welcome to Air Traffic System
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>

</body>
</html>
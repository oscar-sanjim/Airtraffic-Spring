<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="view_component" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
	
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
		integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
		crossorigin="anonymous">
	<title>Airports</title>
</head>
<body>
	<view_component:navigation />
	<span style="display:none">${parameter}</span>

	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-5">
				<p>Air Traffic System</p>
			</div>
			<div class="col-lg-4">
				${req.requestURI}
				<form role="form" action="<c:url value ="/views/renderAiport/Information"/>">
					<div class="form-group">
						<label for="email">Airport code:</label> 
						<input type="text"	class="form-control" id="airportCode" name="airportCode">
					</div>
					<input type="submit" value="Search" class="btn btn-default">
				</form>
				
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
	<c:choose>
		<c:when test="${parameter=='Information'}">
			
		</c:when>
	</c:choose>
	
</body>
</html>
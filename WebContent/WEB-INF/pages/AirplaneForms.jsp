<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="view_component" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<view_component:scripts/>	
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
				<c:choose>
					<c:when test="${parameter=='Information'}">
						<form role="form" action="<c:url value ="/views/renderAirplane/Information"/>">
					</c:when>
				</c:choose>
				
					<div class="form-group">
						<label for="email">Airplane plate:</label> 
						<input type="text"	class="form-control" id="airplanePlate" name="airplanePlate" required>
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
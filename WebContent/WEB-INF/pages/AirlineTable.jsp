<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="view_component" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<view_component:scripts />
<title>Air Traffic</title>
</head>
<body>

	<view_component:navigation />

	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10 welcome_message">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Airline Code</th>
								<th>Airline Name</th>
								<th>Airline Base</th>
							</tr>
						</thead>
						<c:forEach items="${airlines}" var="airline">
							<tr>
								<td>${airline.airlineCode}</td>
								<td>${airline.name}</td>
								<td>${airline.base}</td>
							</tr>
						</c:forEach>
						<tr>
							<td>${singleAirline.airlineCode}</td>
							<td>${singleAirline.name}</td>
							<td>${singleAirline.base}</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>

</body>
</html>
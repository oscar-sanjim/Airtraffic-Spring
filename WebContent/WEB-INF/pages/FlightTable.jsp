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

	<h1>${airport.name}</h1>
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10 welcome_message">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Flight</th>
								<th>Status</th>
								<th>Plane</th>
								<th>Origin</th>
								<th>Destination</th>
								<th>Departure</th>
								<th>Arrival</th>
							</tr>
						</thead>
						<c:forEach items="${flights}" var="flight">
							<tr>
								<td>${flight.flightNumber}</td>
								<td>${flight.status}</td>
								<td>
									<a href="<c:url value='/views/renderAirplane/Information?airplanePlate=${flight.plane}'/>">${flight.plane}</a>
								</td>
								<td>${flight.origin}</td>
								<td>${flight.destination}</td>
								<td>${flight.departure}</td>
								<td>${flight.arrival}</td>
							</tr>
						</c:forEach>
						<tr>
							<td>${singleFlight.flightNumber}</td>
							<td>${singleFlight.status}</td>
							<td>${singleFlight.plane}</td>
							<td>${singleFlight.origin}</td>
							<td>${singleFlight.destination}</td>
							<td>${singleFlight.departure}</td>
							<td>${singleFlight.arrival}</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>

</body>
</html>
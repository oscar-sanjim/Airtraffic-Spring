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
								<th>Airplane Plate</th>
								<th>Airplane Owner</th>
								<th>Airplane Model</th>
								<th>Airplane Hours</th>
							</tr>
						</thead>
						<c:forEach items="${airplanes}" var="airplane">
							<tr>
								<td>
									<a href="<c:url value='/views/renderAirplane/Information?airplanePlate=${airplane.plate}'/>">${airplane.plate}</a>
								</td>
								<td>
									<a href="<c:url value='/views/renderAirline/Information?airlineCode=${airplane.owner}'/>">${airplane.owner}</a>
								</td>
								<td>${airplane.model}</td>
								<td>${airplane.hoursOnFlight}</td>
							</tr>
						</c:forEach>
						<tr>
							<td>${singleAirplane.plate}</td>
							<td>${singleAirplane.owner}</td>
							<td>${singleAirplane.model}</td>
							<td>${singleAirplane.hoursOnFlight}</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>

</body>
</html>
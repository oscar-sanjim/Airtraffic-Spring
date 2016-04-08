<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="view_component" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<view_component:scripts />
<script
	src="<c:url value="/resources/scripts/flights.js"/>">
</script>
<title>Airports</title>
</head>
<body>

	<view_component:navigation />

	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<form role="form" action="<c:url value ="/flights/add"/>" method="post" name="flight">
					<div class="form-group" >
						<label for="flightNumber">Flight Number:</label> 
						<input type="number" class="form-control" id="flightNumber" name="flightNumber" required/>
					</div>

					<div class="form-group">
						<label for="status">Status:</label> <select
							class="form-control" id="status" name="status">
							<option value="ON TIME">ON TIME</option>
							<option value="DELAYED">DELAYED</option>
							<option value="ARRIVED">ARRIVED</option>
							<option value="FLYING">FLYING</option>
						</select>
					</div>

					<div class="form-group">
						<label for="plane">Plane:</label> 
						<input type="text"	class="form-control" id="plane" name="plane" required/>
					</div>
					
					<div class="form-group">
						<label for="origin">Origin:</label> 
						<input type="text"	class="form-control" id="origin" name="origin" required/>
					</div>
					
					<div class="form-group">
						<label for="destination">Destination:</label> 
						<input type="text"	class="form-control" id="destination" name="destination" required/>
					</div>
					
					<div class="form-group">
						<label for="departure">Departure:</label> 
						<input type="datetime-local"  class="form-control" id="departure" name="departure" required/>
					</div>
					
					<div class="form-group">
						<label for="arrival">Arrival:</label> 
						<input type="datetime-local"  class="form-control" id="arrival" name="arrival" required/>
					</div>
					
					<input type="submit" class="btn btn-default" value="Submit"/>
				</form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>

</body>
</html>
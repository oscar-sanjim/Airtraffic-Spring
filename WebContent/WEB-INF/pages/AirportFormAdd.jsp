<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="view_component" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<view_component:scripts />
<title>Airports</title>
</head>
<body>

	<view_component:navigation />

	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<form role="form" action="<c:url value ="/airports/add"/>" method="post" name="airport">
					<div class="form-group" >
						<label for="airportCode">AirportCode:</label> 
						<input type="text"	class="form-control" id="airportCode" name="airportCode" required/>
					</div>
					
					<div class="form-group">
						<label for="name">Airport Name:</label> 
						<input type="text"	class="form-control" id="name" name="name" required/>
					</div>
					
					<div class="form-group">
						<label for="address">Address:</label> 
						<input type="text"	class="form-control" id="address" name="address" required/>
					</div>
					
					<input type="submit" class="btn btn-default" value="Submit"/>
				</form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
</body>
</html>
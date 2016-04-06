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
<title>Airports</title>
</head>
<body>

	<view_component:navigation />

	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<form role="form" action="<c:url value ="/airlines/add"/>" method="post" name="airline">
					<div class="form-group" >
						<label for="airlineCode">Airline Code:</label> 
						<input type="text"	class="form-control" id="airlineCode" name="airlineCode" required/>
					</div>
					
					<div class="form-group">
						<label for="name">Airline Name:</label> 
						<input type="text"	class="form-control" id="name" name="name" required/>
					</div>
					
					<div class="form-group">
						<label for="base">Base:</label> 
						<input type="text"	class="form-control" id="base" name="base" required/>
					</div>
					
					<input type="submit" class="btn btn-default" value="Submit"/>
				</form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>

</body>
</html>
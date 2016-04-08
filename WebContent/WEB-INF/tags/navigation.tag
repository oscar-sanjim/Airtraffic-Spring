<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Airtraffic</a>
			</div>
	
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				
					<!-- Airports Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Airports <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/views/renderAiport/All"/>">Get All Airports</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showAirportForm/Information"/>">Airport Information</a></li>
							<li><a href="<c:url value="/showAirportForm/Departures"/>">Airport Departures</a></li>
							<li><a href="<c:url value="/showAirportForm/Arrivals"/>">Airport Arrivals</a></li>
							<li role="separator" class="/divider"></li>
							<li><a href="<c:url value="/showAirportForm/Add"/>">Add Airport</a></li>
						</ul>
					</li>
					
					
					<!-- Airlines Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Airlines<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/views/renderAirline/All"/>">Get All Airlines</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showAirlineForm/Information"/>">Airline Information</a></li>
							<li><a href="<c:url value="/showAirlineForm/Flights"/>">Airline Flights</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showAirlineForm/Add"/>">Add Airline</a></li>
						</ul>
					</li>
					
					<!-- Airplanes Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Airplanes <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/views/renderAirplane/All"/>">Get All Airplanes</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showAirplaneForm/Information"/>">Airplane Information</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showAirplaneForm/Add"/>">Add Airplane</a></li>
						</ul>
					</li>
					
					<!-- Flights Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Flights<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/views/renderFlight/All"/>">Get All Flights</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showFlightForm/Status"/>">Flights by Status</li>							
							<li><a href="<c:url value="/showFlightForm/Information"/>">Flight by Number</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/showFlightForm/Add"/>">Add Flight</a></li>
						</ul>
					</li>
					
					<li><a href="<c:url value="/logout"/>">Logout</a></li>
					<li><a href="<c:url value="/excel/logs"/>">Download</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid --> 
	</nav> <!-- Ends nav -->
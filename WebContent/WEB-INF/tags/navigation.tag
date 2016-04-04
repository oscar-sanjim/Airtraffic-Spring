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
							<li><a href="/AirTraffic/api/airports">Get All Airports</a></li>
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
							<li><a href="api/airlines">Get All Airlines</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Airline Information</a></li>
							<li><a href="#">Airline Flights</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Add Airline</a></li>
						</ul>
					</li>
					
					<!-- Airplanes Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Airplanes <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="api/airplanes">Get All Airplanes</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Airplane Information</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Add Airport</a></li>
						</ul>
					</li>
					
					<!-- Flights Dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Airlines<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="api/flights">Get All Flights</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Flights by Status</a></li>							
							<li><a href="#">Flight by Number</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Add Flight</a></li>
						</ul>
					</li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid --> 
	</nav> <!-- Ends nav -->
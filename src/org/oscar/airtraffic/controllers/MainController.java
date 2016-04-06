package org.oscar.airtraffic.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oscar.airtraffic.controllers.Tester;
import org.oscar.airtraffic.model.*;
import org.oscar.airtraffic.modelDaos.*;
import org.oscar.airtraffic.modelAirTrafficData.*;
import org.oscar.airtraffic.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.ui.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController{

	private Login_DAO loginDao;
	private static final String ROOT_ADDRESS = "http://10.25.201.230:9080/AirTraffic";
	private RestTemplate rest = new RestTemplate();

	/* JDBC*/
	@Autowired
	public void setDao(Login_DAO dao){
		this.loginDao = dao;
	}

	/*Sessions*/
	@Autowired
	public SessionBean session;

	@RequestMapping("/")
	public ModelAndView showLogin(){
		return new ModelAndView("login");
	}

	@RequestMapping("/welcome")
	public ModelAndView forcedWelcome(){
		return new ModelAndView("welcome");
	}	

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView showWelcome(@ModelAttribute("userPost") User userPost){

		User resultUser = (User)loginDao.searchUser(userPost);
		if(resultUser != null)
		{
			if(session != null){
				int userId = session.getId();
				System.out.println("User id: "+ userId);
			}else{
				session.message();
			}
			return new ModelAndView("welcome");
		}else{
			return new ModelAndView("login", "userNotFound", "User doesn't exist");
		}
	}

	//To Retrieve different forms for the Airtraffic functionality
	@RequestMapping(value = "/showAirportForm/{parameter}", method=GET)
	public ModelAndView showAirportForms(@PathVariable String parameter){
		if(parameter.equals("Add")){
			return new ModelAndView("AirportFormAdd");
		}
		return new ModelAndView("AirportForms", "parameter", parameter);
	}

	@RequestMapping(value="/views/renderAiport/{parameter}", method=GET)
	public ModelAndView renderAirport(@PathVariable String parameter, 
			@RequestParam(value="airportCode", required=true, defaultValue="") String airportCode){

		ModelAndView mvAirports = new ModelAndView("AirportTable");
		ModelAndView mvFlights = new ModelAndView("FlightTable");

		List<Airport> airportInfo = new ArrayList<Airport>();
		List<Flight> flights = new ArrayList<Flight>();

		//airportInfo = new Tester().getAirports();
		//flights = new Tester().getFlights();

		if(parameter.equals("All")){
			airportInfo = getAllAirports();
			mvAirports.addObject("airports", airportInfo);
			return mvAirports;
		}
		else if(parameter.equals("Information")){
			Airport airport = getAirportInfo(airportCode);
			mvAirports.addObject("singleAirport", airport);
			return mvAirports;

		}else if(parameter.equals("Departures")){
			flights = getAirportDepartures(airportCode);			
			mvFlights.addObject("flights", flights);
			return mvFlights;

		}else if(parameter.equals("Arrivals")){
			flights = getAirportArrival(airportCode);
			mvFlights.addObject("flights", flights);
			return mvFlights;
		}

		return new ModelAndView("exception", "message", "Error fetching data");
	}

	@RequestMapping(value = "/airports/add", method = POST)
	public ModelAndView addAirport(@ModelAttribute("airport") Airport airport){
		try{
			//Boolean result = false;
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/airports/add", airport, Boolean.class);
			if(result == true){
				return new ModelAndView("redirect:/views/renderAiport/All");
			}else{
				return new ModelAndView("exception", "message", "Error: Couldn't add Airport");
			}

		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}

	@RequestMapping(value = "/showAirlineForm/{parameter}", method=GET)
	public ModelAndView showAirlineForms(@PathVariable String parameter){
		if(parameter.equals("Add")){
			return new ModelAndView("AirlineFormAdd");
		}
		return new ModelAndView("AirlineForms", "parameter", parameter);
	}

	@RequestMapping(value="/views/renderAirline/{parameter}", method=GET)
	public ModelAndView renderAirline(@PathVariable String parameter, 
			@RequestParam(value="airlineCode", required=true, defaultValue="") String airlineCode){

		ModelAndView mvAirlines = new ModelAndView("AirlineTable");
		ModelAndView mvFlights = new ModelAndView("FlightTable");

		List<Airline> airlinetInfo = new ArrayList<Airline>();
		List<Flight> flights = new ArrayList<Flight>();

		//airlinetInfo = new Tester().getAirlines();
		//flights = new Tester().getFlights();

		if(parameter.equals("All")){
			airlinetInfo = getAllAirLines();
			mvAirlines.addObject("airlines", airlinetInfo);
			return mvAirlines;
		}
		else if(parameter.equals("Information")){
			Airline airline = getAirlineInfo(airlineCode);
			//Airline airline = new Airline("ALN","ALN_Name", "MEx");
			mvAirlines.addObject("singleAirline", airline);
			return mvAirlines;

		}else if(parameter.equals("Flights")){
			flights = getAirlineFlights(airlineCode);
			mvFlights.addObject("flights", flights);
			return mvFlights;
		}

		return new ModelAndView("exception", "message", "Error fetching data");
	}

	@RequestMapping(value = "/airlines/add", method = POST)
	public ModelAndView addAirline(@ModelAttribute("airline") Airline airline){
		try{
			System.out.println("Llega: "+airline.getName());
			//Boolean result = true;
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/airlines/add", airline, Boolean.class);
			if(result == true){
				return new ModelAndView("redirect:/views/renderAirline/All");
			}else{
				return new ModelAndView("exception", "message", "Error: Couldn't add Airline");
			}
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}

	@RequestMapping(value = "/showAirplaneForm/{parameter}", method=GET)
	public ModelAndView showAirplaneForms(@PathVariable String parameter){
		if(parameter.equals("Add")){
			return new ModelAndView("AirplaneFormAdd");
		}
		return new ModelAndView("AirplaneForms", "parameter", parameter);
	}

	@RequestMapping(value="/views/renderAirplane/{parameter}", method=GET)
	public ModelAndView renderAirplane(@PathVariable String parameter, 
			@RequestParam(value="airplanePlate", required=true, defaultValue="") String airplanePlate){

		ModelAndView mvAirplanes = new ModelAndView("AirplaneTable");

		List<Airplane> airplaneInfo = new ArrayList<Airplane>();

		//airplaneInfo = new Tester().getAirplanes();

		if(parameter.equals("All")){
			airplaneInfo = getAllAirplanes();
			mvAirplanes.addObject("airplanes", airplaneInfo);
			return mvAirplanes;
		}
		else if(parameter.equals("Information")){
			Airplane airplane = getAirplaneInfo(airplanePlate);
			//Airplane airplane = new Airplane("123", "Oscar", "Boeing", 100);
			mvAirplanes.addObject("singleAirplane", airplane);
			return mvAirplanes;

		}		
		return new ModelAndView("exception", "message", "Error fetching data");
	}

	@RequestMapping(value = "/airplanes/add", method = POST)
	public ModelAndView addAirplane(@ModelAttribute("airplane") Airplane airplane){
		try{
			System.out.println("Llega: "+airplane.getHoursOnFlight());
			//Boolean result = true;
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/airplanes/add", airplane, Boolean.class);
			if(result == true){
				return new ModelAndView("redirect:/views/renderAirplane/All");
			}else{
				return new ModelAndView("exception", "message", "Error: Couldn't add Airplane");
			}
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}

	@RequestMapping(value = "/showFlightForm/{parameter}", method=GET)
	public ModelAndView showFlightForms(@PathVariable String parameter){
		if(parameter.equals("Add")){
			return new ModelAndView("FlightFormAdd");
		}
		return new ModelAndView("FlightForms", "parameter", parameter);
	}

	@RequestMapping(value="/views/renderFlight/{parameter}", method=GET)
	public ModelAndView renderFlight(@PathVariable String parameter, 
			@RequestParam(value="value", required=true, defaultValue="") String value){

		ModelAndView mvFlights = new ModelAndView("FlightTable");

		List<Flight> flightInfo = new ArrayList<Flight>();

		//flightInfo = new Tester().getFlights();

		if(parameter.equals("All")){
			flightInfo = getAllFlights();
			mvFlights.addObject("flights", flightInfo);
			return mvFlights;

		}else if(parameter.equals("Information")){
			Flight flight = getFlightInfo(value);
			//String data = "666_DELAYED_B0002JBC_GDL_MEX_1396310400000_396310400000";
			//Flight flight = new Flight(data);
			mvFlights.addObject("singleFlight", flight);
			return mvFlights;

		}else if(parameter.equals("Status")){
			flightInfo = getFlights(value);
			mvFlights.addObject("flights", flightInfo);
			return mvFlights;
		}
		return new ModelAndView("exception", "message", "Error fetching data");
	}

	@RequestMapping(value = "/flights/add", method = POST)
	public ModelAndView addFlight(@ModelAttribute("flight") Flight flight){
		try{
			System.out.println("Llega: "+flight.getOrigin());
			System.out.println("Llega: "+flight.getDeparture());
			//Boolean result = true;
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/flights/add", flight, Boolean.class);
			if(result == true){
				return new ModelAndView("redirect:/views/renderFlight/All");
			}else{
				return new ModelAndView("exception", "message", "Error: Couldn't add Airplane");
			}
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}

	//Airports	
	public List<Airport> getAllAirports(){
		ResponseEntity<List<Airport>> response = rest.exchange(ROOT_ADDRESS+"/airports", HttpMethod.GET, 
				null, new ParameterizedTypeReference<List<Airport>>(){
		});
		List<Airport> airportList = response.getBody();
		return airportList;
	}

	public Airport getAirportInfo(String airport){
		Airport airportInfo = rest.
				getForObject(ROOT_ADDRESS+"/airports/"+airport, Airport.class);
		return airportInfo;
	}

	public List<Flight> getAirportDepartures(String airport){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airports/"+airport+"/departures",
					HttpMethod.GET,	null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> departureList = response.getBody();
			return departureList;
		}catch(Exception e){
			System.err.println("Exception: "+e);
			return null;
		}

	}

	public List<Flight> getAirportArrival( String airport){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airports/"+airport+"/arrivals",
					HttpMethod.GET,	null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> arrivalsList = response.getBody();
			return arrivalsList;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}

	//Airlines

	public List<Airline> getAllAirLines(){
		try{
			ResponseEntity<List<Airline>> response = rest.exchange(ROOT_ADDRESS+"/airlines", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airline>>(){
			});
			List<Airline> airlineList = response.getBody();
			return airlineList;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}


	//Airlines    
	public Airline getAirlineInfo(String airline){
		System.out.println("Entered");
		try{
			Airline airlineInfo = rest.getForObject(ROOT_ADDRESS+"/airlines/"+airline, Airline.class);
			return airlineInfo;
		}catch(Exception e){
			return null;
		}
	}

	public List<Flight> getAirlineFlights(String airline){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airlines/"+airline+"/flights",
					HttpMethod.GET,null, new ParameterizedTypeReference<List<Flight>>(){
			});
			System.out.println(response.getBody());
			List<Flight> airline_FlightList = response.getBody();
			return airline_FlightList;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}

	//Airplanes

	public List<Airplane> getAllAirplanes(){
		try{
			ResponseEntity<List<Airplane>> response = rest.exchange(ROOT_ADDRESS+"/airplanes", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airplane>>(){
			});
			List<Airplane> airplaneList = response.getBody();
			return airplaneList;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}

	public Airplane getAirplaneInfo(String airplane){
		try{
			Airplane airplaneInfo = rest.getForObject(ROOT_ADDRESS+"/airplanes/"+airplane, Airplane.class);
			return airplaneInfo;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}

	//Flights
	public List<Flight> getAllFlights(){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/flights", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> flightList = response.getBody();
			return flightList;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}

	public Flight getFlightInfo(String flightNumber){
		try{
			Flight flightInfo = rest.getForObject(ROOT_ADDRESS+"/flights/"+flightNumber, Flight.class);
			return flightInfo;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}

	public List<Flight> getFlights(@PathVariable String status){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/flights/status/"+status, HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> flightList = response.getBody();
			return flightList;
		}catch(Exception e){
			System.err.println("Error: "+e);
			return null;
		}
	}
}


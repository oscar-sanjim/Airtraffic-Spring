package org.oscar.airtraffic.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oscar.airtraffic.model.*;
import org.oscar.airtraffic.modelDaos.*;
import  org.oscar.airtraffic.modelAirTrafficData.*;
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
public class Airtraffic_Controller{
	
	private Login_DAO loginDao;
	private static final String ROOT_ADDRESS = "http://10.25.196.35:9080/AirTraffic";
	private RestTemplate rest = new RestTemplate();
	
	/* JDBC*/
	@Autowired
	public void setDao(Login_DAO dao){
		this.loginDao = dao;
	}
	
	@RequestMapping("/")
	public ModelAndView showLogin(){
		return new ModelAndView("login");
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView showWelcome(@ModelAttribute("userPost") User userPost){
		
		User resultUser = (User)loginDao.searchUser(userPost);
		if(resultUser != null)
		{
			return new ModelAndView("welcome");
		}else{
			return new ModelAndView("login", "userNotFound", "User doesn't exist");
		}
	}
	
	
	//Implementing API Functionality
	//Air
	
	//Object allAirports = rest.getForObject(ROOT_ADDRESS+"/airports", Airport.class, map);
	
	@RequestMapping(value = "api/airports", method = GET)
    public Object getAllAirports(){
		try{
			ResponseEntity<List<Airport>> response = rest.exchange(ROOT_ADDRESS+"/airports", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airport>>(){
			});
			List<Airport> airportList = response.getBody();
			return airportList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airports/{airport}", method = GET)
    public Object getAirportInfo(@PathVariable String airport){
		try{
			Object airportInfo = rest.
					getForObject(ROOT_ADDRESS+"/airports/"+airport, Airport.class);
			return airportInfo;
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
    }
	
	@RequestMapping(value = "api/airports/{airport}/departures", method = GET)
    public Object getAirportDepartures(@PathVariable String airport){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airports/"+airport+"/departures",
					HttpMethod.GET,	null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> departureList = response.getBody();
			return departureList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airports/{airport}/arrivals", method = GET)
	public Object getAirportArrival(@PathVariable String airport){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airports/"+airport+"/arrivals",
					HttpMethod.GET,	null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> arrivalsList = response.getBody();
			return arrivalsList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airports/add", method = POST)
    public Object addAirport(@RequestBody Airport airport){
		try{
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/airports/add", airport, Boolean.class);
			System.out.println("Result: "+result);
			return result;
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}
	
	
	//Airlines
	@RequestMapping(value = "api/airlines", method = GET)
	public Object getAllAirLines(){
		try{
			ResponseEntity<List<Airline>> response = rest.exchange(ROOT_ADDRESS+"/airlines", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airline>>(){
			});
			List<Airline> airlineList = response.getBody();
			return airlineList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airlines/{airline}", method = GET)
	public Object getAirlineInfo(@PathVariable String airline){
		System.out.println("Entered");
		try{
			Object airlineInfo = rest.getForObject(ROOT_ADDRESS+"/airlines/"+airline, Airline.class);
			return airlineInfo;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }

	@RequestMapping(value = "api/airlines/{airline}/flights", method = GET)
    public Object getAirlineFlights(@PathVariable String airline){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airlines/"+airline+"/flights",
					HttpMethod.GET,null, new ParameterizedTypeReference<List<Flight>>(){
			});
			System.out.println(response.getBody());
			List<Flight> airline_FlightList = response.getBody();
			return airline_FlightList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airlines/add", method = POST)
    public Object addAirport(@RequestBody Airline airline){
		try{
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/airlines/add", airline, Boolean.class);
			System.out.println("Result: "+result);
			return result;
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}
	
	//Airplanes
	@RequestMapping(value = "api/airplanes", method = GET)
	public Object getAllAirplanes(){
		try{
			ResponseEntity<List<Airplane>> response = rest.exchange(ROOT_ADDRESS+"/airplanes", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airplane>>(){
			});
			List<Airplane> airplaneList = response.getBody();
			return airplaneList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airplanes/{airplane}", method = GET)
	public Object getAirplaneInfo(@PathVariable String airplane){
		try{
			Object airplaneInfo = rest.getForObject(ROOT_ADDRESS+"/airplanes/"+airplane, Airplane.class);
			return airplaneInfo;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/airplanes/add", method = POST)
	public Object addAirplane(@RequestBody Airplane airplane){
		try{
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/airplanes/add", airplane, Boolean.class);
			return result;
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}
	
	//Flights
	@RequestMapping(value = "api/flights", method = GET)
	public Object getAllFlights(){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/flights", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> flightList = response.getBody();
			return flightList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }

	@RequestMapping(value = "api/flights/status/{status}", method = GET)
	public Object getFlights(@PathVariable String status){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/flights/status/"+status, HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> flightList = response.getBody();
			return flightList;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/flights/{flightNumber}", method = GET)
	public Object getFlightInfo(@PathVariable String flightNumber){
		try{
			Object flightInfo = rest.getForObject(ROOT_ADDRESS+"/flights/"+flightNumber, Flight.class);
			return flightInfo;
		}catch(Exception e){
			return new ModelAndView("exception", "exception", e);
		}
    }
	
	@RequestMapping(value = "api/flights/add", method = POST)
	public Object addAirplane(@RequestBody Flight flight){
		try{
			Boolean result = rest.postForObject(ROOT_ADDRESS+"/flights/add", flight, Boolean.class);
			return result;
		}catch(Exception e){
			return new ModelAndView("exception", "message", e);
		}
	}
}


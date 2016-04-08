package org.oscar.airtraffic.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.oscar.airtraffic.model.CustomException;
import org.oscar.airtraffic.model.ObjectWrapper;
import org.oscar.airtraffic.modelAirTrafficData.Airline;
import org.oscar.airtraffic.modelAirTrafficData.Airplane;
import org.oscar.airtraffic.modelAirTrafficData.Airport;
import org.oscar.airtraffic.modelAirTrafficData.Flight;
import org.oscar.airtraffic.modelDaos.ApiKeyDao;
import org.oscar.airtraffic.modelDaos.Login_DAO;
import org.oscar.airtraffic.modelDaos.LogsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class ApiController {
	
	private static final String ROOT_ADDRESS = "http://10.25.201.230:9080/AirTraffic";
	
	private RestTemplate rest = new RestTemplate();
	
	private ApiKeyDao keyDao;
	
	@Autowired
	public void setDao(ApiKeyDao keyDao){
		this.keyDao = keyDao;
	}
	
	/**********************************************/
	/************ API Functionality ***************/
	/**********************************************/
	
	//Object allAirports = rest.getForObject(ROOT_ADDRESS+"/airports", Airport.class, map);
	
	@ModelAttribute("log")
	public void log(){
		
	}	
	
	//Airports
	@RequestMapping(value = "airports", method = GET)
    public Object getAllAirports(){
		
		try{
			ResponseEntity<List<Airport>> response = rest.exchange(ROOT_ADDRESS+"/airports", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airport>>(){
			});
			List<Airport> airportList = response.getBody();
			return airportList;
		}catch(Exception e){
			System.out.println("Exception: "+e);
			return new CustomException("Exception "+e);
		}
	}
	
	@RequestMapping(value = "/airports/{airport}", method = GET)
    public Object getAirportInfo(@PathVariable String airport){
		try{
			Object airportInfo = rest.
					getForObject(ROOT_ADDRESS+"/airports/"+airport, Airport.class);
			return airportInfo;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airports/{airport}/departures", method = GET)
    public Object getAirportDepartures(@PathVariable String airport){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airports/"+airport+"/departures",
					HttpMethod.GET,	null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> departureList = response.getBody();
			return departureList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airports/{airport}/arrivals", method = GET)
	public Object getAirportArrival(@PathVariable String airport){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airports/"+airport+"/arrivals",
					HttpMethod.GET,	null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> arrivalsList = response.getBody();
			return arrivalsList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airports/add", method = POST)
    public Object addAirport(@RequestBody ObjectWrapper objectWrapper){
		try{
			if(keyDao.searchApiKey(objectWrapper.getApikey())){
				Boolean result = rest.postForObject(ROOT_ADDRESS+"/airports/add", objectWrapper.getData(), Boolean.class);
				System.out.println("Result: "+result);
				return result;
			}else{
				return new CustomException("Error: APIKEY Fail");
			}
		}catch(Exception e){
			return new CustomException("Exception: "+e);
		}
	}
	
	
	//Airlines
	@RequestMapping(value = "/airlines", method = GET)
	public Object getAllAirLines(){
		try{
			ResponseEntity<List<Airline>> response = rest.exchange(ROOT_ADDRESS+"/airlines", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airline>>(){
			});
			List<Airline> airlineList = response.getBody();
			return airlineList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airlines/{airline}", method = GET)
	public Object getAirlineInfo(@PathVariable String airline){
		System.out.println("Entered");
		try{
			Object airlineInfo = rest.getForObject(ROOT_ADDRESS+"/airlines/"+airline, Airline.class);
			return airlineInfo;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }

	@RequestMapping(value = "/airlines/{airline}/flights", method = GET)
    public Object getAirlineFlights(@PathVariable String airline){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/airlines/"+airline+"/flights",
					HttpMethod.GET,null, new ParameterizedTypeReference<List<Flight>>(){
			});
			System.out.println(response.getBody());
			List<Flight> airline_FlightList = response.getBody();
			return airline_FlightList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airlines/add", method = RequestMethod.POST)
    public Object addAirline(@RequestBody ObjectWrapper objectWrapper){
		try{
			if(keyDao.searchApiKey(objectWrapper.getApikey())){
				Boolean result = rest.postForObject(ROOT_ADDRESS+"/airlines/add", objectWrapper.getData(), Boolean.class);
				return result;
			}else{
				return new CustomException("Error: APIKEY Fail");
			}
			
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
	}
	
	//Airplanes
	@RequestMapping(value = "/airplanes", method = GET)
	public Object getAllAirplanes(){
		try{
			ResponseEntity<List<Airplane>> response = rest.exchange(ROOT_ADDRESS+"/airplanes", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Airplane>>(){
			});
			List<Airplane> airplaneList = response.getBody();
			return airplaneList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airplanes/{airplane}", method = GET)
	public Object getAirplaneInfo(@PathVariable String airplane){
		try{
			Object airplaneInfo = rest.getForObject(ROOT_ADDRESS+"/airplanes/"+airplane, Airplane.class);
			return airplaneInfo;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/airplanes/add", method = POST)
	public Object addAirplane(@RequestBody ObjectWrapper objectWrapper){
		try{
			if(keyDao.searchApiKey(objectWrapper.getApikey())){
				Boolean result = rest.postForObject(ROOT_ADDRESS+"/airplanes/add", objectWrapper.getData(), Boolean.class);
				return result;
			}else{
				return new CustomException("Error: APIKEY Fail");
			}
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
	}
	
	//Flights
	@RequestMapping(value = "/flights", method = GET)
	public Object getAllFlights(){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/flights", HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> flightList = response.getBody();
			return flightList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }

	@RequestMapping(value = "/flights/status/{status}", method = GET)
	public Object getFlights(@PathVariable String status){
		try{
			ResponseEntity<List<Flight>> response = rest.exchange(ROOT_ADDRESS+"/flights/status/"+status, HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Flight>>(){
			});
			List<Flight> flightList = response.getBody();
			return flightList;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/flights/{flightNumber}", method = GET)
	public Object getFlightInfo(@PathVariable String flightNumber){
		try{
			Object flightInfo = rest.getForObject(ROOT_ADDRESS+"/flights/"+flightNumber, Flight.class);
			return flightInfo;
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
    }
	
	@RequestMapping(value = "/flights/add", method = POST)
	public Object addFlight(@RequestBody ObjectWrapper objectWrapper){
		try{
			if(keyDao.searchApiKey(objectWrapper.getApikey())){
				Boolean result = rest.postForObject(ROOT_ADDRESS+"/flights/add", objectWrapper.getData(), Boolean.class);
				return result;
			}else{
				return new CustomException("Error: APIKEY Fail");
			}
		}catch(Exception e){
			return new CustomException("Exception "+e);
		}
	}
}

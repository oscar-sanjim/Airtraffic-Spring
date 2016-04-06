package org.oscar.airtraffic.controllers;

import java.util.ArrayList;

import org.oscar.airtraffic.modelAirTrafficData.Airline;
import org.oscar.airtraffic.modelAirTrafficData.Airplane;
import org.oscar.airtraffic.modelAirTrafficData.Airport;
import org.oscar.airtraffic.modelAirTrafficData.Flight;

public class Tester {

	ArrayList toReturnArrayList = new ArrayList();
	
	public ArrayList<Airport> getAirports(){
		Airport toListAirport = new Airport("OSC", "Oscar", "Izcalli");
		for(int i=0; i <10; i++){
			toReturnArrayList.add(toListAirport);
		}
		return toReturnArrayList;
	} 
	
	public ArrayList getFlights(){
		String data = "666_DELAYED_B0002JBC_GDL_MEX_1396310400000_396310400000";
		Flight flight = new Flight(data);
		for(int i=0; i <10; i++){
			toReturnArrayList.add(flight);
			
		}
		return toReturnArrayList;
	}
	
	public ArrayList getAirlines(){
		Airline airline = new Airline("New", "NewName", "newBase");
		for(int i=0; i <10; i++){
			toReturnArrayList.add(airline);
			
		}
		return toReturnArrayList;
	}
	
	public ArrayList getAirplanes(){
		Airplane airplane = new Airplane("123", "Oscar", "Boeing", 100);
		for(int i=0; i <10; i++){
			toReturnArrayList.add(airplane);
			
		}
		return toReturnArrayList;
	}
	
}

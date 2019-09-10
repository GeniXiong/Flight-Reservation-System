/**
 * 
 */
package jellycat.flight.domain.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jellycat.flight.domain.flight.*;
import jellycat.flight.domain.trip.Trip;
import jellycat.flight.domain.airport.Airports;
import jellycat.flight.domain.airplane.Airplanes;
import jellycat.flight.domain.utils.DateTransfer;
import jellycat.flight.domain.utils.QueryFactory;
import jellycat.flight.domain.utils.TimeZoneMap;

/**
 * This class provides an interface to the CS509 server. It provides sample methods to perform
 * HTTP GET and HTTP POSTS
 *   
 * @author jellycat
 * @version 1.1
 * @since 2019-03-16
 *
 */
public enum ServerInterface {
	INSTANCE;
	
	/** 
	 * mUrlBase is the Universal Resource Locator (web address) of the CS509 reservation server
	 */
	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

	/**
	 * Return a collection of all the airports from server
	 * 
	 * Retrieve the list of airports available to the specified teamName via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @return collection of Airports from server or null if error.
	 */
	public Airports getAirports (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirports;
		Airports airports;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getAirports(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlAirports = result.toString();
		airports = DaoAirport.addAll(xmlAirports);
		return airports;
		
	}
	
	/**
	 * Return a collection of all the airplanes from server
	 * 
	 * Retrieve the list of airplanes available to the specified teamName via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airplanes
	 * @return collection of Airplanes from server or null if error.
	 */	
	
	public Airplanes getAirplanes (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirplanes;
		Airplanes airplanes;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getAirplanes(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlAirplanes = result.toString();
		airplanes = DaoAirplane.addAll(xmlAirplanes);
		return airplanes;
		
	}
	/**
	 * Return a collection of all the flights from server
	 * 
	 * Retrieve the list of flights available to the specified teamName via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @param code refers to the airport code (3 capital letters)
	 * @param departDate refers to the flight departure local time
	 * @return collection of Flights from server or null if error.
	 */
	public Flights retriveFlightsFromGMTDate (String teamName, String code, String departDate) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		String xmlFlights;
		Flights flights;
		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getDepartureFlights(teamName,code,departDate));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlFlights = result.toString();
		flights = DaoFlight.addAll(xmlFlights);
		return flights;
	}
	/**
	 * Return a collection of all the flights from server
	 * 
	 * Retrieve the list of flights available to the specified teamName via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @param code refers to the airport code (3 capital letters)
	 * @param departDate refers to the flight departure local time
	 * @return collection of Flights from server or null if error.
	 */
	public Flights userGetDepartFlights (String teamName, String code, String departDate) {
		TimeZoneMap tzm = TimeZoneMap.getTimeZoneMap();
		Flights currFlights = this.retriveFlightsFromGMTDate(teamName, code, departDate).flightsFilter(departDate);
		DateTransfer dt = new DateTransfer();
		int offset = tzm.getMap().get(code);
		String overDate = "";
		if (offset < 0) {
			overDate = dt.addOneDay(departDate);
		}
		else {
			overDate = dt.subtractOneDay(departDate);
		}
		Flights overDayFlights = this.retriveFlightsFromGMTDate(teamName, code, overDate).flightsFilter(departDate);
		currFlights.extendFlights(overDayFlights);
		return currFlights;
	}
	
	/**
	 * Return a collection of all the flights from server
	 * 
	 * Retrieve the list of flights available to the specified teamName via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @param code refers to the airport code (3 capital letters)
	 * @param departDate refers to the flight departure date (GMT)
	 * @return collection of Flights from server or null if error.
	 */
	public Flights getDepartFlights (String teamName, String code, String departDate) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		String xmlFlights;
		Flights flights;
		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getDepartureFlights(teamName,code,departDate));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlFlights = result.toString();
		flights = DaoFlight.addAll(xmlFlights);
		return flights;
	}
	
//	/**
//	 * Return a collection of all the flights from server
//	 * 
//	 * Retrieve the list of flights available to the specified teamName via HTTPGet of the server
//	 * @param teamName identifies the name of the team requesting the collection of airports
//	 * @param code refers to the airport code (3 capital letters)
//	 * @param Arrivedate refers to the flight arrival date (GMT)
//	 * @return collection of Flights from server or null if error.
//	 */
//	public Flights getArriveFlights (String teamName, String code, String Arrivedate) {
//		URL url;
//		HttpURLConnection connection;
//		BufferedReader reader;
//		String line;
//		StringBuffer result = new StringBuffer();
//		String xmlFlights;
//		Flights flights;
//
//		try {
//			/**
//			 * Create an HTTP connection to the server for a GET 
//			 * QueryFactory provides the parameter annotations for the HTTP GET query string
//			 */
//			url = new URL(mUrlBase + QueryFactory.getArrivalFlights(teamName,code,Arrivedate));
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("GET");
//			connection.setRequestProperty("User-Agent", teamName);
//
//			/**
//			 * If response code of SUCCESS read the XML string returned
//			 * line by line to build the full return string
//			 */
//			int responseCode = connection.getResponseCode();
//			if (responseCode >= HttpURLConnection.HTTP_OK) {
//				InputStream inputStream = connection.getInputStream();
//				String encoding = connection.getContentEncoding();
//				encoding = (encoding == null ? "UTF-8" : encoding);
//
//				reader = new BufferedReader(new InputStreamReader(inputStream));
//				while ((line = reader.readLine()) != null) {
//					result.append(line);
//				}
//				reader.close();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		xmlFlights = result.toString();
//		flights = DaoFlight.addAll(xmlFlights);
//		return flights;
//	}
	
	/**
	 * Lock the database for updating by the specified team. The operation will fail if the lock is held by another team.
	 * 
	 * @post database locked
	 * 
	 * @param teamName is the name of team requesting server lock
	 * @return true if the server was locked successfully, else false
	 */
	public boolean lock (String teamName) {
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String params = QueryFactory.lock(teamName);
			
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to lock database");
			System.out.println(("\nResponse Code : " + responseCode));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			
			System.out.println(response.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Unlock the database previous locked by specified team. The operation will succeed if the server lock is held by the specified
	 * team or if the server is not currently locked. If the lock is held be another team, the operation will fail.
	 * 
	 * The server interface to unlock the server interface uses HTTP POST protocol
	 * 
	 * @post database unlocked if specified teamName was previously holding lock
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return true if the server was successfully unlocked.
	 */
	public boolean unlock (String teamName) {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String params = QueryFactory.unlock(teamName);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to unlock database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Make reservation based on a trip(multiple flights in a row)
	 * @param teamName is the team name
	 * @param t is the Trip that will be reserved
	 * @return true if reservation success, false if reservation failed
	 */
	public boolean reserveTrip(String teamName, Trip t) {
//		DaoFlight df = new DaoFlight();
//		String xmlString = df.buildReservationString(t);
		String xmlString  = DaoFlight.buildReservationString(t);
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String params = QueryFactory.reserve(teamName, xmlString);
			
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to reserve trip");
			System.out.println(("\nResponse Code : " + responseCode));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			
			System.out.println(response.toString());
		}
		catch (Exception ex) {
			//ex.printStackTrace();
			return false;
		}
	
		System.out.println("reserve success");
		return true;
	}
	
}

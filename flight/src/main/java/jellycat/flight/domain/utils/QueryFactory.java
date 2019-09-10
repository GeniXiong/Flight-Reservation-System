package jellycat.flight.domain.utils;

/**
 * Build query String from requested data
 * to avail operations on server database
 *  
 * @author jellycat
 * @version 1.2
 * @since 2019-04-20
 *
 */
public class QueryFactory {
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirports(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airports";
	}
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airplanes
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirplanes(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airplanes";
	}
	/**
	 * Lock the server database so updates can be written
	 * 
	 * @param teamName is the name of the team to acquire the lock
	 * @return the String written to HTTP POST to lock server database 
	 */
	public static String lock (String teamName) {
		return "team=" + teamName + "&action=lockDB";
	}
	
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of flights
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @param code is the 3 character departing airport code
	 * @param date is the departing date in GMT
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getDepartureFlights(String teamName, String code, String date) {
		return "?team=" + teamName + "&action=list&list_type=departing&airport=" + code + "&day=" + date;
	}
	
//	/**
//	 * Return a query string that can be passed to HTTP URL to request list of flights
//	 * 
//	 * @param teamName is the name of the team to specify the data copy on server
//	 * @param code is the 3 character arrival airport code
//	 * @param date is the arrival date in GMT
//	 * @return the query String which can be appended to URL to form HTTP GET request
//	 */
//	public static String getArrivalFlights(String teamName, String code, String date) {
//		return "?team=" + teamName + "&action=list&list_type=arriving&airport=" + code + "&day=" + date;
//	}
	
	/**
	 * Unlock the server database after updates are written
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return the String written to the HTTP POST to unlock server database
	 */
	public static String unlock (String teamName) {
		return "team=" + teamName + "&action=unlockDB";
	}
	
	/**
	 * reserve tickets based on parsed xml string
	 * @param teamName is the name of the team to reserve
	 * @param xmlString the String written to the HTTP POST to reserve flights
	 * @return the String written to the HTTP POST to make reservation on database
	 */
	public static String reserve(String teamName, String xmlString) {
		return "team=" + teamName + "&action=buyTickets&flightData=" + xmlString;
	}

}

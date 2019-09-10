package jellycat.flight.domain.utils;
import java.util.HashMap;

/**
 * TimeZoneMap holds all the information
 * mapping airport code to time offset (seconds) to GMT time
 * Information is save locally for the sake of quick respond time
 * @author genixiong
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class TimeZoneMap {
	private static TimeZoneMap timeZoneMap;
	private HashMap<String, Integer> airportmap;
	/**
	 * Constructor for the class
	 */
	private TimeZoneMap() {}
	
	/**
	 * Singleton accessed by all packages in the system
	 * @return TimeZoneMap
	 */
	public static TimeZoneMap getTimeZoneMap() {
		if (timeZoneMap == null) {
			timeZoneMap = new TimeZoneMap();
			timeZoneMap.airportmap = new HashMap<String, Integer>();
			timeZoneMap.airportmap.put("ANC",-28800);
		    timeZoneMap.airportmap.put("ATL",-14400);
		    timeZoneMap.airportmap.put("AUS",-28800);
		    timeZoneMap.airportmap.put("BDL",-21600);
		    timeZoneMap.airportmap.put("BNA",-18000);
		    timeZoneMap.airportmap.put("BOS",-14400);
		    timeZoneMap.airportmap.put("BWI",-14400);
		    timeZoneMap.airportmap.put("CLE",-14400);
		    timeZoneMap.airportmap.put("CLT",-14400);
		    timeZoneMap.airportmap.put("CMH",-14400);
		    timeZoneMap.airportmap.put("CVG",-14400);
		    timeZoneMap.airportmap.put("DCA",-14400);
		    timeZoneMap.airportmap.put("DEN",-21600);
		    timeZoneMap.airportmap.put("DFW",-18000);
		    timeZoneMap.airportmap.put("DTW",-14400);
		    timeZoneMap.airportmap.put("EWR",-14400);
		    timeZoneMap.airportmap.put("FLL",-14400);
		    timeZoneMap.airportmap.put("HNL",-36000);
		    timeZoneMap.airportmap.put("HOU",-18000);
		    timeZoneMap.airportmap.put("IAD",-14400);
		    timeZoneMap.airportmap.put("IAH",-18000);
		    timeZoneMap.airportmap.put("IND",-14400);
		    timeZoneMap.airportmap.put("JFK",-14400);
		    timeZoneMap.airportmap.put("LAS",-28800);
		    timeZoneMap.airportmap.put("LAX",-25200);
		    timeZoneMap.airportmap.put("LGA",-14400);
		    timeZoneMap.airportmap.put("MCI",-18000);
		    timeZoneMap.airportmap.put("MCO",-14400);
		    timeZoneMap.airportmap.put("MDW",-18000);
		    timeZoneMap.airportmap.put("MEM",-18000);
		    timeZoneMap.airportmap.put("MIA",-14400);
		    timeZoneMap.airportmap.put("MSP",-18000);
		    timeZoneMap.airportmap.put("MSY",-18000);
		    timeZoneMap.airportmap.put("OAK",-25200);
		    timeZoneMap.airportmap.put("ONT",-25200);
		    timeZoneMap.airportmap.put("ORD",-18000);
		    timeZoneMap.airportmap.put("PDX",-25200);
		    timeZoneMap.airportmap.put("PHL",-14400);
		    timeZoneMap.airportmap.put("PHX",-25200);
		    timeZoneMap.airportmap.put("PHX",-25200);
		    timeZoneMap.airportmap.put("PIT",-14400);
		    timeZoneMap.airportmap.put("RDU",-14400);
		    timeZoneMap.airportmap.put("RSW",-14400);
		    timeZoneMap.airportmap.put("SAN",-25200);
		    timeZoneMap.airportmap.put("SAT",-18000);
		    timeZoneMap.airportmap.put("SEA",-25200);
		    timeZoneMap.airportmap.put("SFO",-25200);
		    timeZoneMap.airportmap.put("SJC",-25200);
		    timeZoneMap.airportmap.put("SLC",-21600);
		    timeZoneMap.airportmap.put("SMF",-25200);
		    timeZoneMap.airportmap.put("SNA",-25200);
		    timeZoneMap.airportmap.put("STL",-18000);
		    timeZoneMap.airportmap.put("TPA",-14400);
		}
		return timeZoneMap;
	}
	
	/**
	 * Get the pre-saved airport code to offset time mapping
	 * @return airport code to offset time mapping
	 */
	public HashMap<String, Integer> getMap(){
		return this.airportmap;
	}

}

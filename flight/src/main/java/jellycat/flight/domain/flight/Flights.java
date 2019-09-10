package jellycat.flight.domain.flight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jellycat.flight.domain.utils.DateTransfer;

/**
 * Flight class holds arraylist of flights 
 * @author genixiong
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class Flights extends ArrayList<Flight> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Filter all the flight in Flights to be within a local date
	 * @param localDate date to filter, date in the form "yyyy_MM_dd"
	 * @return Flights that all the flights are in one day
	 */
	public Flights flightsFilter(String localDate) {
		DateTransfer dt = new DateTransfer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Flights flights = new Flights();
		for (Flight f : this) {
			String thisLocalDate = sdf.format(dt.GMTtoLocalTime(f.departurecode(), f.departuredate()));
			if (thisLocalDate.equals(localDate)) {
				flights.add(f);
			}
		}
		return flights;
	}
	
	/**
	 * Concatenate two Flights into one Flight object
	 * @param flights arrayList of Flights that is being added to the current Flights
	 */
	public void extendFlights(Flights flights) {
		for (Flight f : flights) {
			this.add(f);
		}
	}
}

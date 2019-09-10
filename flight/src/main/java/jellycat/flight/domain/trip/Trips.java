package jellycat.flight.domain.trip;

import java.util.ArrayList;

/**
 * Describes ArrayList of Trips from departure airport
 * to arrival airport on a certain day
 * @author jellycat
 * @version 1.0
 * @since 2019-04-20
 *
 */

public class Trips  extends ArrayList<Trip>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Index of a certain trip in a list of trips
	 * @param t trip to look up
	 * @return index of a certain trip in a list of trips
	 */
	public int indexOfTrip(Trip t) {
		return this.indexOf(t);
	}
}

package jellycat.flight.domain.airplane;

import java.util.ArrayList;

import jellycat.flight.domain.airplane.Airplane;

/**
 * This class aggregates a number of Airport. The aggregate is implemented as an ArrayList.
 * Airports can be added to the aggregate using the ArrayList interface. Objects can 
 * be removed from the collection using the ArrayList interface.
 * 
 * @author jellycat
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class Airplanes extends ArrayList<Airplane> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Checks the number of seats have been reserved in an airplane
	 * @param airplane is the model of the airplane
	 * @param seatType represents the seat type: 0(coach), 1(first class)
	 * @return the number of seats have been reserved in an airplane
	 */
	public int seatsCapacity(String airplane, int seatType) {
		for (Airplane a: this) {
			if (a.model().equals(airplane)) {
				if (seatType == 0) {
					return a.coachseats();
				}
				else {
					return a.firstclassseats();
				}
			}
		}
		return -1;
	}		
}
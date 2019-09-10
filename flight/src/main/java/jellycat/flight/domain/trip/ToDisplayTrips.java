package jellycat.flight.domain.trip;

/**
 * ToDisplayTrips class holds all the information that is to display on the HTML page
 * singleton served as cache for the use of future reservation
 * @author genixiong
 * @version 1.0
 * @since 2019-04-20
 *
 */
public enum ToDisplayTrips {
	INSTANCE;
	
	Trips toTrips;
	Trips returnTrips;
	
	/**
	 * Set available Trips to class 
	 * @param t is the depart trips (to go trips)
	 * @return new set trips
	 */
	public Trips setToTrips(Trips t) {
		this.toTrips = t;
		return this.toTrips;
	}
	
	/**
	 * Set available Trips to class 
	 * @param t is the arrival trip (return trips)
	 * @return new set trips
	 */
	public Trips setReturnTrips(Trips t) {
		this.returnTrips = t;
		return this.returnTrips;
	}
	
	/**
	 * Get all depart trips (to go trips)
	 * @return all depart trips (to go trips)
	 */
	public Trips toTrips() {
		return this.toTrips;
	}
	
	/**
	 * Get all return trips (return trips)
	 * @return all return trips (return trips)
	 */
	public Trips returnTrips() {
		return this.returnTrips;
	}
	
	/**
	 * Add a single Trip to depart trips (to go trips)
	 * @param t single Trip to add to depart trips
	 */
	public void addToTrips(Trip t) {
		this.toTrips.add(t);
	}
	
	/**
	 * Add a single Trip to return trips (return trips)
	 * @param t single Trip to add to return trips
	 */
	public void addReturnTrips(Trip t) {
		this.returnTrips.add(t);
	}
	
	/**
	 * Clear all the information (cache) for a new search
	 */
	public void reset() {
		this.toTrips = new Trips();
		this.returnTrips = new Trips();
	}

}

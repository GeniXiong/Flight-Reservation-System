package jellycat.flight.domain.trip;

/**
 * TripData class holds all the information from HTML customer's request
 * @author genixiong
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class TripData {

    int tripType;
    String departureCode;
    String arrivalCode;
    String departureDate;
    String returnDate;
    int seatType;
    int layover;
    public TripData() {
    }
    
    /**
     * Get the number of layovers
     * @return
     */
    public int getLayover() {
    	return this.layover;
    }
    
    /**
     * Set the number of layovers
     * @param layover is the number of layovers
     */
    public void setLayover(int layover) {
    	this.layover = layover;
    }
    
    /**
     * Get trip type one way or round trip
     * @return tripType 1: OneWay  2: Round trip
     *
     */
    public int getTripType() {
        return this.tripType;
    }

    /**
     * Set trip type one way or round trip
     * @param tripType 1: OneWay  2: Round trip
     */
    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    /**
     * Get the trip departure airport (3 letter code)
     * @return trip departure airport (3 letter code)
     */
    public String getDepartureCode() {
        return this.departureCode;
    }

    /**
     * Set the trip departure airport (3 letter code)
     * @param departureCode is departure airport (3 letter code)
     */
    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    /**
     * Get the trip arrival airport (3 letter code)
     * @return trip arrival airport (3 letter code)
     */
    public String getArrivalCode() {
        return this.arrivalCode;
    }

    /**
     * Set the trip arrival airport (3 letter code)
     * @param arrivalCode is trip arrival airport (3 letter code)
     */
    public void setArrivalCode(String arrivalCode) {
        this.arrivalCode = arrivalCode;
    }

    /**
     * Get departure date string in the form "yyyy-MM-dd"
     * @return departure date string in the form "yyyy-MM-dd"
     */
    public String getDepartureDate() {
        return this.departureDate;
    }

    /**
     * Set departure date string in the form "yyyy-MM-dd"
     * @param departureDate is departure date string in the form "yyyy-MM-dd"
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Get return date string in the form "yyyy-MM-dd"
     * @return return date string in the form "yyyy-MM-dd"
     */
    public String getReturnDate() {
        return this.returnDate;
    }

    /**
     * Set return date string in the form "yyyy-MM-dd"
     * @param returnDate is return date string in the form "yyyy-MM-dd"
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Get seatType  0: Coach seat , 1: First Class
     * @return seat type
     */
    public int getSeatType() {
        return this.seatType;
    }

    /**
     * Set seat type to first class or coach
     * @param seatType 0: Coach seat , 1: First Class
     */
    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

}

package jellycat.flight.domain.flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jellycat.flight.domain.utils.DateTransfer;


/**
 * This class holds values pertaining to a single flight. Class member attributes
 * are the same as defined by the CS509 server API and store values after conversion from
 * XML received from the server to Java primitives. Attributes are accessed via getter and 
 * setter methods.
 * 
 * @author jellycat
 * @version 1.0 
 * @since 2019-03-16
 */
public class Flight {
	/**
	 * Airplane attributes as defined by the CS509 server interface XML
	 */
	/** airplane of the flight */
	private String mAirplane;
	
	/** duration of the flight, flight time (in minutes) */
	private int mFlightTime;              
	
	/** Flight number, each flight has a unique number */
	private String mNumber;
	/** Departure info for the flight */
	private String mDepartureCode;
	private Date mDepartureDate;
	/** Arrival info for the flight */
	private String mArrivalCode;
	private Date mArrivalDate;
	/** Seating info */
	private float mFirstClassSeatPrice;
	private float mCoachSeatPrice;
	private int mFirstClassSeatReserved;
	private int mCoachSeatReserved;
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 */	
	public Flight () {
		this.mAirplane = "";
		this.mFlightTime = 0;
		this.mNumber = "";
		this.mDepartureCode = "";
		this.mDepartureDate = new Date();
		this.mArrivalCode = "";
		this.mArrivalDate = new Date();
		this.mFirstClassSeatPrice = 0;
		this.mCoachSeatPrice = 0;
		this.mCoachSeatReserved = 0;
		this.mFirstClassSeatReserved = 0;
		}
	
	/**
	 * Get airplane name
	 * @return name of airplane
	 */
	public String airplane() {
		return this.mAirplane;
	}
	
	/**
	 * Check and set name of the airplane
	 * @param airplane is name of airplane
	 * @throws illegal or null name of airplane
	 */
	public void airplane(String airplane){
		if (isValidString (airplane))
			mAirplane = airplane;
		else{
			System.out.println("airplanename:" + airplane);
			throw new IllegalArgumentException (airplane);
			}
	}
	
	/**
	 * Get flight time of this flight in minutes
	 * @return time of this flight in minutes
	 */
	public int flighttime() {
		return this.mFlightTime;
	}
	
	/**
	 * Check and set flight time of this flight in minutes
	 * @param flightTime time of this flight in minutes
	 * @throws negative or invalid time
	 */
	public void flighttime(int flightTime)
	{
		if (isValidInt(flightTime))
			mFlightTime = flightTime;
		else
			throw new IllegalArgumentException (Integer.toString(flightTime));
	}
	
	/**
	 * Check and set flight time of this flight in minutes
	 * @param flightTime time of this flight in minutes (string)
	 * @throws negative or invalid time
	 */
	public void flighttime(String flightTime)
	{
		if (isValidStringInt(flightTime))
			mFlightTime = Integer.parseInt(flightTime);
		else
			throw new IllegalArgumentException(flightTime);
	}
	
	/**
	 * Get flight number
	 * @return flight number
	 */
	public String number() {
		return this.mNumber;
	}
	
	/**
	 * Check and set flight number of this flight
	 * @param flightNnumber is the flight number of this flight
	 * @throws null or invalid flight number
	 */
	public void number(String flightNnumber) {
		if (isValidString (flightNnumber))
			mNumber = flightNnumber;
		else
			throw new IllegalArgumentException (flightNnumber);
	}

	/**
	 * Get departure code
	 * @return departure code
	 */
	public String departurecode() {
		return this.mDepartureCode;
	}
	
	/**
	 * Check and set departure code of this flight
	 * @param departureCode departure code of this flight
	 * @throws null or invalid departure code
	 */
	public void departurecode(String departureCode) {
		if (isValidString (departureCode))
			mDepartureCode = departureCode;
		else
			throw new IllegalArgumentException (departureCode);
	}

	/**
	 * Get departure date
	 * @return Date format of departure date
	 */
	public Date departuredate() {
		return this.mDepartureDate;
	}
	
	/**
	 * Set departure date
	 * @param departDate is the Date format of departure date
	 */
	public void departuredate(Date departDate) {
		this.mDepartureDate = departDate;
	}
	
	/**
	 * Check and set departure date
	 * @param departureDate is the date in the form "yyyy-MM-dd HH:mm"
	 * @throws illegal date format
	 */
    public void departuredate(String departureDate) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	if (isValidStringDate(departureDate))
    		{try {
				mDepartureDate = sdf.parse(departureDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}}
		else
			throw new IllegalArgumentException(departureDate);
	}
	
	/**
	 * display departure date to the user interface
	 * @return departure date in local time in the form of "yyyy-MM-dd HH:mm"
	 */
	public String displayDepartureDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		DateTransfer dt = new DateTransfer();
		return sdf.format(dt.GMTtoLocalTime(this.mDepartureCode, this.mDepartureDate));
	}

	/**
	 * Get arrival code
	 * @return arrival code
	 */
	public String arrivalcode() {
		return this.mArrivalCode;
	}
	
	/**
	 * Check and set arrival code of this flight
	 * @param arrivalCode arrival code of this flight
	 * @throws null or invalid arrival code
	 */
	public void arrivalcode(String arrivalCode) {
		if (isValidString (arrivalCode))
			mArrivalCode = arrivalCode;
		else
			throw new IllegalArgumentException (arrivalCode);
	}
	
	/**
	 * Get arrival date
	 * @return Date format of arrival date
	 */
	public Date arrivaldate() {
		return this.mArrivalDate;
	}
	
	/**
	 * Set arrival date in Date format
	 * @param arrivalDate arrival date in Date format
	 */
	public void arrivaldate(Date arrivalDate) {
    	this.mArrivalDate = arrivalDate;
	}
	
	/**
	 * Check and set arrival date
	 * @param arrivalDate is the date in the form "yyyy-MM-dd HH:mm"
	 * @throws illegal date format
	 */	
	 public void arrivaldate(String arrivalDate) {
	 	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
     	 if (isValidStringDate(arrivalDate)){
     		 try {
     	 	 	mArrivalDate = sdf.parse(arrivalDate);
	 	 	 } catch (ParseException e) {
	 	 	 	e.printStackTrace();
	 	 	 	}
     		 }
	 	 else
	 	 	 throw new IllegalArgumentException(arrivalDate);
	 }

	/**
	 * display arrival date to the user interface
	 * @return arrival date in local time in the form of "yyyy-MM-dd HH:mm"
	 */
	public String displayArrivalDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		DateTransfer dt = new DateTransfer();
		return sdf.format(dt.GMTtoLocalTime(this.mArrivalCode, this.mArrivalDate));
	}
	
	/**
	 * Get first class price of this flight
	 * @return first class price of this flight
	 */
	public float firstclassprice() {
		return this.mFirstClassSeatPrice;
	}
	
	/**
	 * Check and set first class price of this flight
	 * @param firstClassPrice first class price of this flight
	 */
	public void firstclassprice(float firstClassPrice)
	{
		if (isValidFloat (firstClassPrice))
			mFirstClassSeatPrice = firstClassPrice;
		else
			throw new IllegalArgumentException (Float.toString(firstClassPrice));
	}
	
	/**
	 * Check and set first class price of this flight
	 * @param firstClassPrice first class price of this flight
	 */
	public void firstclassprice(String firstClassPrice)
	{
		if (isValidStringFloat(firstClassPrice))
			mFirstClassSeatPrice = Float.parseFloat(firstClassPrice);
		else
			throw new IllegalArgumentException(firstClassPrice);
	}
	
	/**
	 * Get number of first class seat that have been reserved
	 * @return number of first class seat that have been reserved
	 */
	public int firstclassseatreserved() {
		return this.mFirstClassSeatReserved;
	}
	
	/**
	 * Check and set number of first class seat that have been reserved
	 * @param firstClassSeatReserved is the number of first class seat that have been reserved
	 */
	public void firstclassseatreserved(int firstClassSeatReserved)
	{
		if (isValidInt (firstClassSeatReserved))
			mFirstClassSeatReserved = firstClassSeatReserved;
		else
			throw new IllegalArgumentException (Integer.toString(firstClassSeatReserved));
	}
	
	/**
	 * Check and set number of first class seat that have been reserved
	 * @param firstClassSeatReserved is the number of first class seat that have been reserved
	 */
	public void firstclassseatreserved(String firstClassSeatReserved)
	{
		if (isValidStringInt(firstClassSeatReserved))
			mFirstClassSeatReserved = Integer.parseInt(firstClassSeatReserved);
		else
			throw new IllegalArgumentException(firstClassSeatReserved);
	}
	
	/**
	 * Get coach class price of this flight
	 * @return coach class price of this flight
	 */
	public float coachprice() {
		return this.mCoachSeatPrice;
	}
	
	/**
	 * Check and set coach class price of this flight
	 * @param coachPrice coach class price of this flight
	 */
	public void coachprice(float coachPrice)
	{
		if (isValidFloat (coachPrice))
			mCoachSeatPrice = coachPrice;
		else
			throw new IllegalArgumentException (Float.toString(coachPrice));
	}
	
	/**
	 * Check and set coach class price of this flight
	 * @param coachPrice coach class price of this flight
	 */
	public void coachprice(String coachPrice)
	{
		if (isValidStringFloat(coachPrice))
			mCoachSeatPrice = Float.parseFloat(coachPrice);
		else
			throw new IllegalArgumentException(coachPrice);
	}
	
	/**
	 * Get number of coach class seat that have been reserved
	 * @return number of coach class seat that have been reserved
	 */
	public int coachseatreserved() {
		return this.mCoachSeatReserved;
	}
	
	/**
	 * Check and set number of coach class seat that have been reserved
	 * @param coachSeatReserved is the number of coach class seat that have been reserved
	 */
	public void coachseatreserved(int coachSeatReserved)
	{
		if (isValidInt (coachSeatReserved))
			mCoachSeatReserved = coachSeatReserved;
		else
			throw new IllegalArgumentException (Integer.toString(coachSeatReserved));
	}
	
	/**
	 * Check and set number of coach class seat that have been reserved
	 * @param coachSeatReserved is the number of coach class seat that have been reserved
	 */
	public void coachseatreserved(String coachSeatReserved)
	{
		if (isValidStringInt(coachSeatReserved))
			mCoachSeatReserved = Integer.parseInt(coachSeatReserved);
		else
			throw new IllegalArgumentException(coachSeatReserved);
	}
	
	/**
	 * Check whether a string is valid
	 * @param va is the string being checked
	 * @return true or false
	 */
	public boolean isValidString(String va)
	{
		// If the string is empty, the object isn't valid
		if ((va == null) || (va == ""))
			return false;

		return true;
	}
	
	/**
	 * Check whether an integer is valid
	 * @param va is the integer being checked
	 * @return true or false
	 */
	public boolean isValidInt(int va)
	{
		// If the int is smaller than 0, the object isn't valid
		if (va < 0)
			return false;
		return true;
	}

	/**
	 * Check whether an float is valid
	 * @param va is the float being checked
	 * @return true or false
	 */
	public boolean isValidFloat(float va)
	{
		// If the int is smaller than 0, the object isn't valid
		if (va <= 0.0)
			return false;
		return true;
	}
	/**
	 * Validation of a String if it need to be casted into Float
	 * @param va is the string being checked
	 * @return true or false
	 */
	public boolean isValidStringFloat(String va)
	{
		float cs;
		try {
			cs = Float.parseFloat(va);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidFloat (cs);
	}
	
	/**
	 * Validation of a String if it need to be casted into Integer
	 * @param va is the string being checked
	 * @return true or false
	 */
	public boolean isValidStringInt(String va)
	{
		int cs;
		try {
			cs = Integer.parseInt(va);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidInt (cs);
	}
	
	/**
	 * Validation of a date
	 * @param va is the Date format being checked
	 * @return whether it's a valid date string
	 */
	public boolean isValidStringDate(String va)
	{   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date cs;
		try {
			cs = sdf.parse(va);	
		} catch (ParseException px) {
			return false;
		}
		return cs != null;
	}
	
	/**
	 * check whether a flight has available seat that is requested
	 * @param seatType is the requested seat type: 0 represents coach seat, 1 represents first class seat
	 * @param capacity is the maximum number of seats on the plane
	 * @return whether there is available seat
	 */
	public boolean canReserve(int seatType, int capacity) {
		if (seatType == 0) {
			return this.mCoachSeatReserved < capacity;
		}
		else {
			return this.mFirstClassSeatReserved < capacity;
		}
	}

	/**
	 * @overrides toString() 
	 * @return print flight data, including transform date format into desired local time
	 * and fetch time zone from map
	 * 
	 */
	public String toString() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		StringBuffer sb = new StringBuffer();
		DateTransfer dt = new DateTransfer();
		sb.append(mAirplane).append(", ");
		sb.append(String.format("%d", mFlightTime)).append(" minutes, ");
		sb.append(mNumber).append(", ");
		sb.append(mDepartureCode).append(", ");
		sb.append("Departure local time: ").append(sdf.format(dt.GMTtoLocalTime(mDepartureCode, mDepartureDate))).append(" ");
		sb.append(mArrivalCode).append(", ");
		sb.append("Arrival local time: ").append(sdf.format(dt.GMTtoLocalTime(mArrivalCode, mArrivalDate))).append(" ");
		sb.append(String.format("%d", mFirstClassSeatReserved)).append(", ");
		sb.append(String.format("%f", mFirstClassSeatPrice)).append(", ");
		sb.append(String.format("%d", mCoachSeatReserved)).append(", ");
		sb.append(String.format("%f", mCoachSeatPrice));
		return sb.toString();
	}
}
	

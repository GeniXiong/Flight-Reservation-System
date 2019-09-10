package jellycat.flight.domain.airplane;

/**
 * This class holds values pertaining to a single Airplane. Class member attributes
 * are the same as defined by the CS509 server API and store values after conversion from
 * XML received from the server to Java primitives. Attributes are accessed via getter and 
 * setter methods.
 * 
 * @author jellycat
 * @version 1.0 2019-03-16
 * @since 2019-04-20
 * 
 */
public class Airplane {
	/**
	 * Airplane attributes as defined by the CS509 server interface XML
	 */
	/** Manufacturer of the airplane */
	private String mManufacturer;
	
	/** Model of the airplane */
	private String mModel;
	
	/** Total number of first class seats */
	private int mFirstClassSeats;
	
	/** Total number of coach seats */
	private int mCoachSeats;
	
	/**
	 * Default constructor
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 */	
	public Airplane () {
		mManufacturer = "";
		mModel = "";
		mFirstClassSeats = -1;
		mCoachSeats = -1;
	}
	
	/** 
	 * Check and set manufacturer of the airplane
	 * 
	 * @param manufacturer is the manufacturer name
	 * @throws illegal or invalid manufacturer name
	 */
	public void manufacturer (String manufacturer) {
		if (isValidManufacturerOrModel (manufacturer))
			mManufacturer = manufacturer;
		else
			throw new IllegalArgumentException (manufacturer);
	}

	/**
	 * Get manufacturer name of the plane
	 * 
	 * @return manufacturer name of the plane
	 */
	public String manufacturer() {
		return mManufacturer;
	}
	
	/** 
	 * Check and set model of the airplane
	 * 
	 * @param model is the model name
	 * @throws illegal or invalid model name
	 */
	public void model (String model) {
		if (isValidManufacturerOrModel (model))
			mModel = model;
		else
			throw new IllegalArgumentException (model);
	}
	
	/**
	 * Get model of the plane
	 * 
	 * @return model of the plane
	 */
	public String model () {
		return mModel;
	}

	/**
	 * Check and set available first class seats of the airplane
	 * @param firstclassseats is available number of first class seats
	 * @throws illegal or invalid number
	 */
	public void firstclassseats (int firstclassseats) {
		if (isValidSeats(firstclassseats))
			mFirstClassSeats = firstclassseats;
		else
			throw new IllegalArgumentException (Integer.toString(firstclassseats));
	}
	
	/**
	 * Check and set available first class seats of the airplane
	 * @param firstclassseats is available number(string) of first class seats
	 * @throws illegal or invalid number
	 */
	public void firstclassseats (String firstclassseats) {
		if (isValidSeats(firstclassseats))
			mFirstClassSeats = Integer.parseInt(firstclassseats);
		else
			throw new IllegalArgumentException(firstclassseats);
	}
	
	/**
	 * Get number of first class seats of the airplane
	 * @return first class seats number
	 */
	public int firstclassseats()
	{
		return mFirstClassSeats;
	}
	
	
	/**
	 * Check and set available coach seats of the airplane
	 * @param coachseats is available number of coach seats
	 * @throws illegal or invalid number
	 */
	public void coachseats (int coachseats) {
		if (isValidSeats(coachseats))
			mCoachSeats = coachseats;
		else
			throw new IllegalArgumentException (Integer.toString(coachseats));
	}

	/**
	 * Check and set available coach seats of the airplane
	 * @param coachseats is available number(string) of coach seats
	 * @throws illegal or invalid number
	 */
	public void coachseats (String coachseats) {
		if (isValidSeats(coachseats))
			mCoachSeats = Integer.parseInt(coachseats);
		else
			throw new IllegalArgumentException(coachseats);
	}
		
	/**
	 * Get number of coach seats of the airplane
	 * @return coach seats number
	 */
	public int coachseats () {
		return mCoachSeats;
	}
	
	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(mManufacturer).append(", ");
		sb.append(mModel).append(", ");
		sb.append(String.format("%d", mFirstClassSeats)).append(", ");
		sb.append(String.format("%d", mCoachSeats));
		return sb.toString();
	}
	
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with specified input values following validation for reasonableness.
	 *  
	 * @param manufacturer of the airplane
	 * @param model of the airplane
	 * @param firstclassseats number of first class seats on the airplane
	 * @param coachseats number of coach seats on the airplane
	 * @throws IllegalArgumentException if any parameter is determined invalid
	 */
	public Airplane (String manufacturer, String model, int firstclassseats, int coachseats) {


		if (!isValidManufacturerOrModel(manufacturer))
			throw new IllegalArgumentException(manufacturer);
		if (!isValidManufacturerOrModel(model))
			throw new IllegalArgumentException(model);
		if (!isValidSeats(firstclassseats))
			throw new IllegalArgumentException(Integer.toString(firstclassseats));
		if (!isValidSeats(coachseats))
			throw new IllegalArgumentException(Integer.toString(coachseats));
		
		this.mManufacturer = manufacturer;
		this.mModel = model;
		this.mFirstClassSeats = firstclassseats;
		this.mCoachSeats = coachseats;
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with specified input values following validation for reasonableness.
	 *  
	 * @param manufacturer of the airplane
	 * @param model of the airplane
	 * @param firstclassseats number of first class seats on the airplane
	 * @param coachseats number of coach seats on the airplane
	 * @throws IllegalArgumentException if any parameter is determined invalid
	 */
	public Airplane (String manufacturer, String model, String firstclassseats, String coachseats) {

		int tmpFCS, tmpCS;
		try {
			tmpFCS = Integer.parseInt(firstclassseats);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException ("firstclassseats must be an integer larger than 0", ex);
		}

		try {
			tmpCS = Integer.parseInt(coachseats);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException ("coachseats must be an integer larger than 0", ex);
		}

		if (!isValidManufacturerOrModel(manufacturer))
			throw new IllegalArgumentException(manufacturer);
		if (!isValidManufacturerOrModel(model))
			throw new IllegalArgumentException(model);
		if (!isValidSeats(firstclassseats))
			throw new IllegalArgumentException(firstclassseats);
		if (!isValidSeats(coachseats))
			throw new IllegalArgumentException(coachseats);

		this.mManufacturer = manufacturer;
		this.mModel = model;
		this.mFirstClassSeats = tmpFCS;
		this.mCoachSeats = tmpCS;
	}
	

	/**
	 * Determine if two airplane objects are the same airplane
	 * 
	 * Compare another object to this airplane and return true if the other 
	 * object specifies the same airplane as this object. String comparisons are
	 * case insensitive Airbus is same as airbus
	 * 
	 * @param obj is the object to compare against this object
	 * @return true if the param is the same airplane as this, else false
	 */
	@Override
	public boolean equals (Object obj) {
		// every object is equal to itself
		if (obj == this)
			return true;
		
		// null not equal to anything
		if (obj == null)
			return false;
		
		// can't be equal if obj is not an instance of Airplane
		if (!(obj instanceof Airplane)) 
			return false;
		
		// if all fields are equal, the Airports are the same
		Airplane rhs = (Airplane) obj;
		if ((rhs.mManufacturer.equalsIgnoreCase(mManufacturer)) &&
				(rhs.mModel.equalsIgnoreCase(mModel)) &&
				(rhs.mFirstClassSeats == mFirstClassSeats) &&
				(rhs.mCoachSeats == mCoachSeats)) {
			return true;
		}
		
		return false;	
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the manufacturer name or model name are not null and not an empty string. 
	 * @param manufacturerOrmodelToCheck is the manufacturer name or model name string
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValidManufacturerOrModel(String manufacturerOrmodelToCheck) {
		
		// If the name isn't valid, the object isn't valid
		if ((manufacturerOrmodelToCheck == null) || (manufacturerOrmodelToCheck == ""))
			return false;
		
		return true;
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the number of first class or coach seats is larger than zero. 
	 * @param numberOfSeats is the number of first class or coach seats
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValidSeats (int numberOfSeats) {
		if (numberOfSeats<=0)
			return false;
		return true;
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the number of first class or coach seats is larger than zero. 
	 * @param numberOfSeats is the number of first class or coach seats
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValidSeats (String numberOfSeats) {
		int fcs;
		try {
			fcs = Integer.parseInt(numberOfSeats);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidSeats (fcs);
	}


}

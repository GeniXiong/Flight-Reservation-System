package jellycat.flight.domain.trip;

import java.util.ArrayList;
import java.util.Date;

import jellycat.flight.domain.airplane.Airplanes;
import jellycat.flight.domain.flight.Flight;

/**
 * Trip class holds all available flights of one search
 * from depart airport to arrival airport at certain date
 * and stores the seat preference of the trip
 * @author jellycat
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class Trip {
	private ArrayList<Flight> mLegs=new ArrayList<Flight>();
	private float mTotalPrice=0;
	private long mTravelTime=0;
	private boolean isCoachSeat = true;

	/**
	 * Set a list of connecting flights in a Trip
	 * @param legs ArrayList of connecting Flights
	 */
    public void legs(ArrayList<Flight> legs)
    {
    	this.mLegs=legs;
    }
    
    /**
     * Get a list of connecting flights in a Trip
     * @return a list of connecting flights in a Trip
     */
    public ArrayList<Flight> legs()
    {
 	    return this.mLegs;
    }
    
    /**
     * Set the total price of this trip including all connecting flights
     * @param tprice is total price of this trip
     */
    public void totalprice(float tprice)
    {
 	    this.mTotalPrice=tprice;
    }
    
    /**
     * Get the total price of this trip including all connecting flights
     * @return the total price of this trip including all connecting flights
     */
    public double totalprice()
    {
    	this.caltotalprice();
    	double res = Math.round(mTotalPrice);
 	    return res;
    }
    
    /**
     * Add one leg(Flight) to the current trip
     * @param f a leg(Flight) to be added
     */
    public void addflight(Flight f)
    {
 	    this.mLegs.add(f);
 	    caltotaltraveltime();
 	    caltotalprice();
    }
    
    /**
     * Calculate price for the whole trip based on seat type
     */
    public void caltotalprice(){
    	this.mTotalPrice = 0;
    	if (this.isCoachSeat) {
    		for (Flight f: this.mLegs) {
    			this.mTotalPrice += f.coachprice();
    		}
    	}else {
    		for (Flight f: this.mLegs) {
    			this.mTotalPrice += f.firstclassprice();
    		}
    	}
    }
    
    /**
     * Get total time of this trip in minutes including layover time
     * @return total time of this trip in minutes including layover time
     */
    public long totaltraveltime()
    {
    	if (!(this.mTravelTime > 0)) {
    		caltotaltraveltime();
    	}
    	return this.mTravelTime;
    }

    /**
     * Calculate total travel time of this trip in minutes including layover time
     */
    public void caltotaltraveltime()
    {
    	
 	   switch(this.mLegs.size())
 	   {
 	   case 0:
 	   {
 		   this.mTravelTime=0;
 		   break;
 	   }
 	   case 1:
 	   { 
 		   this.mTravelTime=this.mLegs.get(0).flighttime();
 		   break;
 	   }
 	   case 2:
 	   {
 		   Date arrivalt = this.mLegs.get(1).arrivaldate();
 		   Date departt = this.mLegs.get(0).departuredate();
 		   long diff = (arrivalt.getTime() - departt.getTime())/60000;
 		   this.mTravelTime=diff;
 		   break;
 	   }
 	   case 3:
 	   {
 	   Date arrivalt = this.mLegs.get(2).arrivaldate();
 	   Date departt = this.mLegs.get(0).departuredate();
 	   long diff = (arrivalt.getTime() - departt.getTime())/60000;
 	   this.mTravelTime=diff;
 	   break;
 	   }
 	   }
    }
    
    /**
     * Set the trip to desired seat type
     * 
     * @param seatType: 0 represents coach seat, 1 represents first class seat
     */
    public void setSeat(int seatType) {
    	if (seatType == 0) {
    		this.isCoachSeat = true;
    	}
    	else {
    		this.isCoachSeat = false;
    	}
    }
    
    /**
     * Get whether the trip is coach seat
     * @return true or false
     */
    public boolean isCoachSeat() {
 	   return this.isCoachSeat;
    }
    
    /**
     * Check whether all the connecting flight in the trip have available seat
     * @param airplanes is an ArrayList of airplanes
     * @param seatType represents the seat type, 0 represents coach seat, 1 represents first class seat
     * @return true or false
     */
    public boolean hasSeat(Airplanes airplanes, int seatType) {
 	   for (Flight f: this.mLegs) {
 		   int capacity = airplanes.seatsCapacity(f.airplane(), seatType);
 		   if (!f.canReserve(seatType, capacity)) {
 			   return false;
 		   }
 	   }
 	   return true;
    }
    
    /**
     * Build seat type string for query
     * @return string representing seat type
     */
    public String seatType() {
 	   if (this.isCoachSeat) {
 		   return "CoachSeat";
 	   }
 	   else {
 		   return "FirstClass";
 	   }
    }
    
    /**
     * Index of a Trip in the depart trips (to go trips)
     * @return Index of a Trip in the depart trips
     */
    public int toTripIndexOfTrip() {
 	   ToDisplayTrips tt = ToDisplayTrips.INSTANCE;
 	   return tt.toTrips.indexOf(this);
    }
    
    /**
     * Index of a Trip in the return trips (return trips)
     * @return Index of a Trip in the return trips
     */
    public int returnTripIndexOfTrip() {
 	   ToDisplayTrips tt = ToDisplayTrips.INSTANCE;
 	   return tt.returnTrips.indexOf(this);
        }
}

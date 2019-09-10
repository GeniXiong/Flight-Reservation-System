package jellycat.flight;

import jellycat.flight.domain.trip.SearchTrip;
import jellycat.flight.domain.trip.ToDisplayTrips;
import jellycat.flight.domain.trip.Trip;
import jellycat.flight.domain.trip.TripData;
import jellycat.flight.domain.trip.Trips;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testTravelSearch {
	
	private String departCode = "BOS";
	private String arrivalCode = "SFO";
	private String date = "2019_05_05";
	private int layovers = 2;
	private int seatType = 0;
	private String teamName = "jellycat";
	private int tripType = 2;
	private String returnDate = "2019-05-10";
	SearchTrip searchTrip = new SearchTrip();
	private TripData tripData;

    @Test
    public void testSearchTrips(){
        
        Trips trips = searchTrip.oneWaySearchTrip(departCode,arrivalCode,date,seatType,layovers);
        for (Trip t : trips) {
        	assertTrue(t.legs().size() == layovers + 1);
        	int currSeat = t.isCoachSeat()? 0 : 1;
        	assertTrue(currSeat == seatType);
        }
    }
    
    @Before
    public void buildTripData() {
    	TripData td = new TripData();
    	td.setTripType(tripType);
    	td.setSeatType(seatType);
    	td.setDepartureCode(departCode);
    	String departureDate = date.replace("_", "-");
    	td.setDepartureDate(departureDate);
    	td.setArrivalCode(arrivalCode);
    	td.setLayover(layovers);
    	td.setReturnDate(returnDate);
    	
    	this.tripData = td;
    }
    
    @Test
    public void testRoundTripSearch() {
    	TripData tripData = this.tripData;
    	ToDisplayTrips cache = searchTrip.searchTrip_loop(tripData);
//    	Trips toTrips = cache.toTrips();
    	Trips returnTrips = cache.returnTrips();
    	assertTrue(returnTrips != null);
    	for (Trip t: returnTrips) {
    		assertTrue(t.legs().size() == layovers + 1);
        	int currSeat = t.isCoachSeat()? 0 : 1;
        	assertTrue(currSeat == seatType);;
    	}
    	cache.reset();
    	assertTrue(cache.toTrips().size() == 0 && cache.returnTrips().size() == 0);
    }
    

}

package jellycat.flight.web;

import jellycat.flight.domain.dao.ServerInterface;
import jellycat.flight.domain.driver.Driver;
import jellycat.flight.domain.flight.Flight;
import jellycat.flight.domain.trip.Trip;
import jellycat.flight.domain.trip.TripData;
import jellycat.flight.domain.trip.SearchTrip;
import jellycat.flight.domain.trip.ToDisplayTrips;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FlightController is the controller class using Spring boot to connect
 * interactions between java to HTML pages
 * @author jellycat
 * @version 1.0
 * @since 2019-04-20
 *
 */
@Controller
public class FlightController {

    @Autowired
    private Driver driver = new Driver();
    ToDisplayTrips displayTrips = ToDisplayTrips.INSTANCE;

    /**
     * For test page connection
     * @return test.html
     */
    @GetMapping("/")
    public String page(){
        return "test";
    }
    
    /**
     * Main index page for customer search 
     * @param model spring boot model
     * @return index.html page
     */
    @GetMapping("/index")
    public String mainPage(Model model){  	
    	displayTrips.reset();
        model.addAttribute("tripData", new TripData());
        return "index";
    }

    
    /**
     * Search for flights and available Flights displayed to the customer
     * @param tripData customer's request send to build query and fetch available flights
     * @param model spring boot model
     * @return Flights displayed to the page
     */
    @PostMapping("/flights")
    public String searchFlight(TripData tripData, Model model){
    	SearchTrip st = new SearchTrip();
    	displayTrips = st.searchTrip_loop(tripData);
        model.addAttribute("trips", displayTrips);
        return "flightList";
    }

    /**
     * Make reservation based on customer's request on the page
     * @param toTripIndex which trip customer selected as depart trip (to go trip)
     * @param returnTripIndex which trip customer selected as return trip (return trip)
     * @return reserve page
     */
    @PostMapping("/reserve")
    public String reserveSeats(@RequestParam int toTripIndex,
    		@RequestParam int returnTripIndex, 
    		String togoInfo, String returnInfo, Model model){
    	ServerInterface flightInterface = ServerInterface.INSTANCE;
    	Trip togo = displayTrips.toTrips().get(toTripIndex);
    	togoInfo = "";
    	returnInfo = "";
    	for (Flight f : togo.legs()) {
			togoInfo += f.departurecode() + " to " + f.arrivalcode() + "\n"
			+ " depart at: " + f.displayDepartureDate() + " arrive at: " + f.displayArrivalDate()
			+ "\n";
		}
    	flightInterface.lock("jellycat");
    	if(flightInterface.reserveTrip("jellycat", togo)) {
    		
    		togoInfo = togoInfo + "\n" + "Successfully reserved!";
    		System.out.println("to go trip successfully reserved!");
    	}
    		
    	else {
    		togoInfo = togoInfo + "\n" + "Reservation failed! Please try again!";
    		System.out.println("Not enough seat!");
    	}
    		
    	flightInterface.unlock("jellycat");
    	//if it's a one way trip, index of returnTripIndex is default as -1
    	if (returnTripIndex >= 0) {
    		returnInfo = "";
    		Trip ret = displayTrips.returnTrips().get(returnTripIndex);
    		for (Flight f : ret.legs()) {
    			returnInfo += f.departurecode() + " to " + f.arrivalcode() + "\n"
    			+ " depart at: " + f.displayDepartureDate() + " arrive at: " + f.displayArrivalDate()
    			+ "\n";
    		}
    		flightInterface.lock("jellycat");
    		if(flightInterface.reserveTrip("jellycat", ret)) {
    			returnInfo = returnInfo + "\n" + "Successfully reserved!";
    			System.out.println("return trip successfully reserved!");
    		}	
    		else {
    			returnInfo = returnInfo + "\n" +  "Reservation failed! Please try again!";
    			System.out.println("Not enough seat!");
    		}
        		
    		flightInterface.unlock("jellycat");
    	}
    	model.addAttribute("togoInfo", togoInfo);
    	model.addAttribute("returnInfo", returnInfo);
    	return "reserve";
    }
}

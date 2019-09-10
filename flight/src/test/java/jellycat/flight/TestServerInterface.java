package jellycat.flight;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jellycat.flight.domain.dao.ServerInterface;
import jellycat.flight.domain.flight.Flight;
import jellycat.flight.domain.flight.Flights;
import jellycat.flight.domain.trip.Trip;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServerInterface {
	
	private ServerInterface serverInterface = ServerInterface.INSTANCE;
	private String teamName = "jellycat";
	private String code = "BOS";
	private String departDate = "2019_05_05";
	private int seatType = 0;
	
	@Test
	public void testUserGetDepartFlights() {
		
		Flights flights = serverInterface.userGetDepartFlights(teamName, code, departDate);
		System.out.println(flights.size());
		for (Flight f: flights) {
			assertFalse(!f.departurecode().equals(code));
			assertFalse(!dateEquals(f.displayDepartureDate(), departDate));
		}
	}
	
	private boolean dateEquals(String dateTime, String date) {
		String timeRemoved = dateTime.split(" ")[0].replace("-", "_");
		return timeRemoved.equals(date);
	}
	
	@Test
	public void testLock(){
        assertTrue(serverInterface.lock("jellycat"));
    }
	
	@Test
    public void testUnLock(){
		assertTrue(serverInterface.unlock("jellycat"));
    }
	
    @Test
    public void testReserveTrip() {
    	Flights flights = serverInterface.userGetDepartFlights(teamName, code, departDate);
    	Trip trip = new Trip();
    	trip.addflight(flights.get(0));
    	trip.setSeat(seatType);
    	serverInterface.lock(teamName);
        assertTrue(serverInterface.reserveTrip(teamName, trip));
        serverInterface.unlock(teamName);
    }

}

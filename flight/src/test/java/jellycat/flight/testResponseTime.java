package jellycat.flight;

import jellycat.flight.domain.trip.SearchTrip;
import jellycat.flight.domain.trip.Trips;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testResponseTime {

    @Test
    public void testSearchResponseTime(){
        Instant instant1 = Instant.now();
        SearchTrip searchTrip = new SearchTrip();
        Trips trips=searchTrip.oneWaySearchTrip("BOS","SFO","2019_05_05",0,2);
        Instant instant2 = Instant.now();

        long gap = instant2.getEpochSecond()-instant1.getEpochSecond();
        assertTrue("Response Time less than 20 seconds", gap<20);
    }

    @Test
    public void reportResponseTime(){
        Instant instant1 = Instant.now();
        SearchTrip searchTrip = new SearchTrip();
        searchTrip.oneWaySearchTrip("BOS","SFO","2019_05_05",0,2);
        Instant instant2 = Instant.now();
        long gap = instant2.getEpochSecond()-instant1.getEpochSecond();

        System.out.println(gap);
    }
}

package jellycat.flight;

import jellycat.flight.domain.airplane.Airplanes;
import jellycat.flight.domain.airport.Airports;
import jellycat.flight.domain.dao.DaoAirplane;
import jellycat.flight.domain.dao.ServerInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testDataLoad {

    private ServerInterface serverInterface = ServerInterface.INSTANCE;

    @Test
    public void testAirplaneLoad(){
        Airplanes testAirplanes = serverInterface.getAirplanes("jellycat");
        assertTrue("load airplanes successfully",testAirplanes.size() == 11);
    }

    @Test
    public void testAirportsLoad(){
        Airports testData = serverInterface.getAirports("jellycat");
        assertTrue("load Airports successfully", testData.size() == 52);
    }

}

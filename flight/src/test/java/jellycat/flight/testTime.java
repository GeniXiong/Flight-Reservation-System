package jellycat.flight;

import jellycat.flight.domain.utils.DateTransfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testTime {

    private DateTransfer dateTransfer =  new DateTransfer();

    /*
    Test time transfer
     */
    @Test
    public void transferTime(){

        // Original test Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String testDateStr = "2019-05-05 11:00:00";
        String targetDateStr = "2019-05-05 07:00:00";
        Date testDate = stringToDate(testDateStr);
        Date targetDate = stringToDate(targetDateStr);


        // Test
        Date BostonTime = dateTransfer.GMTtoLocalTime("BOS",testDate);
        assertTrue("Date Transfer success",BostonTime.equals(targetDate));
    }


    private Date stringToDate(String s){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date res = new Date();
        try {
            res = dateFormat.parse(s);
        } catch (Exception e){
            System.out.println("Date Format Error: "+s);
        }
        return res;
    }
}

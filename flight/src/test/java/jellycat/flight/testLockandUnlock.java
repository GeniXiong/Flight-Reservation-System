package jellycat.flight;

import jellycat.flight.domain.dao.ServerInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testLockandUnlock {

    private ServerInterface serverInterface = ServerInterface.INSTANCE;

    @Test
    public void testLock(){
    	assertTrue(serverInterface.lock("jellycat"));
    }

    @Test
    public void testUnLock(){
    	assertTrue(serverInterface.unlock("jellycat"));
    }
}

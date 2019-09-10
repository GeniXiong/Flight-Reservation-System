package jellycat.flight.domain.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Entry point for CS509 sample code driver
 * This driver will retrieve the list of airports from the CS509 server and print the list
 * to the console sorted by 3 character airport code
 * 
 * @author jellycat
 * @since 2019-3-17
 * @version 2.0
 *
 */

@SpringBootApplication
@ComponentScan(basePackageClasses = jellycat.flight.web.FlightController.class)
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) {
		SpringApplication.run(Driver.class, args);
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}

	}
}

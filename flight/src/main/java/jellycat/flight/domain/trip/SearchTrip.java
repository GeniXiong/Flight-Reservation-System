package jellycat.flight.domain.trip;

import java.text.SimpleDateFormat;

import jellycat.flight.domain.airplane.Airplanes;
import jellycat.flight.domain.dao.ServerInterface;
import jellycat.flight.domain.flight.Flight;
import jellycat.flight.domain.flight.Flights;
import jellycat.flight.domain.utils.DateTransfer;

/**
 * SearchTrip class holds all the data grabbed from HTML customer input
 * includes travel type: one way or round trip
 * depart date arrival date
 * seat type: first class or coach seat etc.
 * @author jellycat
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class SearchTrip {

	/**
	 * Transform date string (yyyy-MM-dd) from HTML into "yyyy_MM_dd"
	 * @param dateString is the date string from HTML
	 * @return date string in the form "yyyy_MM_dd"
	 */
	private static String transformDate(String dateString){
		String[] data = dateString.split("-");
		String res = "";
		for (String d: data){
			res += d + "_";
		}
		res = res.substring(0,res.length()-1);
//		System.out.println(res);
		return res;

	}
	
	/**
	 * Search available flights from server based on customer's request
	 * and saved to ToDisplayTrips class
	 * @param tripData data from HTML
	 * @return ToDisplayTrips class that saved all the available flights
	 */
	public ToDisplayTrips searchTrip_loop(TripData tripData) {
		ToDisplayTrips displayTrips = ToDisplayTrips.INSTANCE;
		displayTrips.toTrips = oneWaySearchTrip(tripData.departureCode, tripData.arrivalCode, tripData.departureDate, tripData.seatType, tripData.layover);
//		1:one way
//		2:round
		if (tripData.tripType > 1) {
			displayTrips.returnTrips = oneWaySearchTrip(tripData.arrivalCode, tripData.departureCode, tripData.returnDate, tripData.seatType, tripData.layover);
		}
		return displayTrips;		
	}

	/**
	 * One way search from server database based on requested data
	 * 
	 * @param departureCode departure code representing departure airport
	 * @param arrivalCode arrival code representing arrival airport
	 * @param departureDate is the day to depart in airport's local date time
	 * @param seatType : 0: coach seat; 1: first class
	 * @return Trips class containing all available flights
	 */
	public Trips oneWaySearchTrip(String departureCode, String arrivalCode, String departureDate, int seatType, int layover)
	{
		//Syntax: 2019-05-05
		String departDate= transformDate(departureDate);
//		System.out.println("depart date: " + departDate);

		String departurecode = departureCode;
		String arrivalcode = arrivalCode;

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd");
		Trips allTrips = new Trips();
		Trips ret = new Trips();
		Flights firstFs=ServerInterface.INSTANCE.userGetDepartFlights("jellycat", departureCode, departDate);
	
		for(Flight firstF:firstFs){
			if(firstF.arrivalcode().equals(arrivalCode))
		   	{
				Trip temp=new Trip();
				temp.addflight(firstF);
				allTrips.add(temp);
				continue;
		   	}
			else
			{
				String arr1=sdf.format(firstF.arrivaldate());
				Flights secondFs=ServerInterface.INSTANCE.getDepartFlights("jellycat",firstF.arrivalcode(),arr1);
				for(Flight secondF:secondFs){
					if(secondF.arrivalcode().equals(arrivalCode) && DateTransfer.verifyLeg(firstF.arrivaldate(), secondF.departuredate()))
					   {Trip temp=new Trip();
						temp.addflight(firstF);
						temp.addflight(secondF);
						allTrips.add(temp);
						continue;
			   }
				else if((!secondF.arrivalcode().equals(departurecode)) && DateTransfer.verifyLeg(firstF.arrivaldate(), secondF.departuredate()))
				{
					String arr2=sdf.format(secondF.arrivaldate());
					Flights thirdFs = ServerInterface.INSTANCE.getDepartFlights("jellycat",secondF.arrivalcode(),arr2);
					for(Flight thirdF:thirdFs)
					{
						if(thirdF.arrivalcode().equals(arrivalcode)&&DateTransfer.verifyLeg(secondF.arrivaldate(), thirdF.departuredate()))
					   	{
					   		Trip temp=new Trip();
							temp.addflight(firstF);
							temp.addflight(secondF);
							temp.addflight(thirdF);
							allTrips.add(temp);
							continue;
					   	}
						else
							continue;
						}
					}
				}
			}
		}
		//filter all trips to see if it has desired layovers and 
		//if it can be reserved
		Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes("jellycat");
		for(Trip t: allTrips) {
			if (t.hasSeat(airplanes, seatType) && t.legs().size() == (layover + 1)){
				t.setSeat(seatType);
//				t.caltotalprice();
//				t.caltotaltraveltime();
				ret.add(t);
			}
		}
		return ret;
	}

}

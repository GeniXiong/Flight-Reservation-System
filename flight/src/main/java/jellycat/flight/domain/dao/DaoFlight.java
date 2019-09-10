package jellycat.flight.domain.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import jellycat.flight.domain.airplane.*;
import jellycat.flight.domain.flight.*;
import jellycat.flight.domain.trip.Trip;

/**
 * Builds a collection of airports from airports described in XML
 *
 * Parses an XML string to read each of the airports and adds each valid airport
 * to the collection. The method uses Java DOM (Document Object Model) to convert
 * from XML to Java primitives.
 *
 * Method iterates over the set of Airport nodes in the XML string and builds
 * an Airport object from the XML node string and add the Airport object instance to
 * the Airports collection.
 * 
 * @author jellycat
 * @version 1.1 2019-01-21
 * @since 2019-04-20
 *
 */
public class DaoFlight {
    /**
     * Builds a collection of airports from airports described in XML
     *
     * Parses an XML string to read each of the airports and adds each valid airport
     * to the collection. The method uses Java DOM (Document Object Model) to convert
     * from XML to Java primitives.
     *
     * Method iterates over the set of Airport nodes in the XML string and builds
     * an Airport object from the XML node string and add the Airport object instance to
     * the Airports collection.
     *
     * @param xmlFlights XML string containing set of airports
     * @return [possibly empty] collection of Airports in the xml string
     * @throws NullPointerException included to keep signature consistent with other addAll methods
     *
     * @pre the xmlAirports string adheres to the format specified by the server API
     * @post the [possibly empty] set of Airports in the XML string are added to collection
     */
    public static Flights addAll (String xmlFlights) throws NullPointerException {
        Flights flights = new Flights();

        // Load the XML string into a DOM tree for ease of processing
        // then iterate over all nodes adding each airport to our collection
        Document docFlights = buildDomDoc (xmlFlights);
        NodeList nodesFlights = docFlights.getElementsByTagName("Flight");

        for (int i = 0; i < nodesFlights.getLength(); i++) {
            Element elementFlight = (Element) nodesFlights.item(i);
            Flight flight = buildFlight (elementFlight);

            //if (flight.isValid()) {
                flights.add(flight);
            //}
        }

        return flights;
    }
    

    /**
     * Creates an Airport object from a DOM node
     *
     * Processes a DOM Node that describes an Airport and creates an Airport object from the information
     * @param nodeAirport is a DOM Node describing an Airport
     * @return Airport object created from the DOM Node representation of the Airport
     *
     * @pre nodeAirport is of format specified by CS509 server API
     * @post airport object instantiated. Caller responsible for deallocating memory.
     */
    static private Flight buildFlight (Node nodeFlight) {
        String airplane = null;
        int flighttime=0;
        String number = null;
        String departurecode;
        Date departuretime = null;
        String arrivalcode;
        Date arrivaltime = null;
        float firstclassprice = 0;
        int reservefirstclass;
        float coachprice = 0;
        int reservecoachseats;

        Element elementFlight=(Element)nodeFlight;
        // The airport element has attributes of Name and 3 character airport code
        airplane = elementFlight.getAttributeNode("Airplane").getValue();
        flighttime = Integer.parseInt(elementFlight.getAttributeNode("FlightTime").getValue());
        number=elementFlight.getAttributeNode("Number").getValue();
        Element elementDeparture = (Element)elementFlight.getElementsByTagName("Departure").item(0);

        Element elementDeparturerCode=(Element)elementDeparture.getElementsByTagName("Code").item(0);

        String temp_rfc;
        String temp_rcs;
        //SimpleDateFormat time_format=new SimpleDateFormat("yyyy MMM dd HH:mm z");
        SimpleDateFormat time_format=new SimpleDateFormat("yyyy MMM dd HH:mm",Locale.ENGLISH);
        //time_format.setTimeZone(TimeZone.getTimeZone("GMT"));

        departurecode=getCharacterDataFromElement(elementDeparturerCode);

        Element elementDeparturerTime=(Element)elementDeparture.getElementsByTagName("Time").item(0);
        try {
			departuretime=time_format.parse(getCharacterDataFromElement(elementDeparturerTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        Element elementArrival = (Element)elementFlight.getElementsByTagName("Arrival").item(0);

        Element elementArrivalCode=(Element)elementArrival.getElementsByTagName("Code").item(0);
        arrivalcode=getCharacterDataFromElement(elementArrivalCode);
        Element elementArrivalTime=(Element)elementArrival.getElementsByTagName("Time").item(0);
        try {
			arrivaltime=time_format.parse(getCharacterDataFromElement(elementArrivalTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



        Element elementSeating = (Element)elementFlight.getElementsByTagName("Seating").item(0);

        Element elementFirstClass = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);



        DecimalFormat num_format=new DecimalFormat("$#,###.##");

        try {
			firstclassprice=num_format.parse(elementFirstClass.getAttributeNode("Price").getValue()).floatValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        temp_rfc=getCharacterDataFromElement(elementFirstClass);
        reservefirstclass=Integer.parseInt(temp_rfc);

        Element elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);



        try {
			coachprice=num_format.parse(elementCoach.getAttributeNode("Price").getValue()).floatValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        temp_rcs=getCharacterDataFromElement(elementCoach);
        reservecoachseats=Integer.parseInt(temp_rcs);

        /**
         * Instantiate an empty Airport object and initialize with data from XML node
         */
        Flight flight = new Flight();
        flight.airplane(airplane);
        flight.flighttime(flighttime);
        flight.number(number);
        flight.departurecode(departurecode);
        flight.departuredate(departuretime);
        flight.arrivalcode(arrivalcode);
        flight.arrivaldate(arrivaltime);
        flight.coachprice(coachprice);
        flight.coachseatreserved(reservecoachseats);
        flight.firstclassprice(firstclassprice);
        flight.firstclassseatreserved(reservefirstclass);
        return flight;
    }

    /**
     * Builds a DOM tree from an XML string
     *
     * Parses the XML file and returns a DOM tree that can be processed
     *
     * @param xmlString XML String containing set of objects
     * @return DOM tree from parsed XML or null if exception is caught
     */
    static private Document buildDomDoc (String xmlString) {
        /**
         * load the xml string into a DOM document and return the Document
         */
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlString));

            return docBuilder.parse(inputSource);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieve character data from an element if it exists
     *
     * @param e is the DOM Element to retrieve character data from
     * @return the character data as String [possibly empty String]
     */
    private static String getCharacterDataFromElement (Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }
    
    /**
     * Based on the information from one trip
     * build xml string file for future query from database  
     * @param trip is a trip class
     * @return xml string
     */
    public static String buildReservationString(Trip trip){
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	
    	try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
        	
            doc.setXmlStandalone(true);
            // create the root element node
            Element element = doc.createElement("Flights");
            doc.appendChild(element);
          
            int count=0;
            for (Flight f : trip.legs())
            {
            	if(!checkseatavailable(f,trip.isCoachSeat()))  //check if available for the seat 
            		return "";
            	String seatType = trip.isCoachSeat()? "Coach" : "FirstClass";
                Element itemElement = doc.createElement("Flight");      
                itemElement.setAttribute("number", f.number());
                itemElement.setAttribute("seating", seatType);
                element.appendChild(itemElement);
                count++;
            }
            TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(doc);
			// xml transform String
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			transformer.transform(domSource, new StreamResult(bos));
		    String	xmlString = bos.toString();
		    xmlString=xmlString.substring(38);// delete header
            return xmlString;    
    	}catch (ParserConfigurationException pce) {
    		pce.printStackTrace();
    	} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }
    /**
     * Based on the information from one flight
     * judge if there is available seat
     * @param f is a Flight object
     * @param coach is a boolean value if iscoach true, else false
     * @return true if available else return false
     */
    public static boolean checkseatavailable(Flight f, boolean coach)
    {
    	Airplanes a=ServerInterface.INSTANCE.getAirplanes("jellycat");
    	HashMap<String, Integer> airpl_c=new HashMap<>();
    	HashMap<String, Integer> airpl_f=new HashMap<>();
        for(Airplane a1:a)
        {
        	airpl_c.put(a1.model(),a1.coachseats());
        	airpl_f.put(a1.model(),a1.firstclassseats());
        }
       if(coach)
       {    System.out.println("check"+f.airplane());
            System.out.println(airpl_c.get(f.airplane()));
            System.out.println(f.coachseatreserved());
    	   if(airpl_c.get(f.airplane())<f.coachseatreserved()+1)
    		   return false;
    	   else
    		   return true;
       }
       else
       {
    	   if(airpl_f.get(f.airplane())<f.firstclassseatreserved()+1)
    		   return false;
    	   else
    		   return true;
       }
    }
    
    /**
     * Build reservation xml format string
     * @param fnumber is the flight number that will be reserved
     * @param seatType is "FirstClass" or "Coach"
     * @return xml string to query reservation on the database
     */
    public String buildReservationString(String fnumber, String seatType) {
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
             
            // create the root element node
            Element element = doc.createElement("Flights");
            doc.appendChild(element);
            
        	// add element after the first child of the root element
            Element itemElement = doc.createElement("Flight");
            element.appendChild(itemElement);
            // add number and seating attributes to the node
            itemElement.setAttribute("number", fnumber);
            itemElement.setAttribute("seating", seatType);
//            System.out.println(doc.toString());
            return doc.toString();            
    	}catch (ParserConfigurationException pce) {
    		pce.printStackTrace();
    	}
    	return "";
    }
    
    
}



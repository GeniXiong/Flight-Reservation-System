package jellycat.flight.domain.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * DateTransfer contains all methods related to 
 * date format, local date transfer, etc.
 * 
 * @author jellycat
 * @version 1.0
 * @since 2019-04-20
 *
 */
public class DateTransfer {
	/**
	 * Parse date string to Date format
	 * @param dateString in the form "yyyy_MM_dd"
	 * @return Date format
	 */
	public Date stringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Date dt = new Date();
		try {
			dt = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public Date stringToDateTime(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = new Date();
		try {
			dt = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	/**
	 * Parse Date to string format
	 * @param dt Date format
	 * @return string of date in the form "yyyy_MM_dd"
	 */
	public String dateToString(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		return sdf.format(dt);
	}
	/**
	 * Add one more day to the current date
	 * 
	 * @param currDate in the form of 2019_05_05
	 * @return string also in the form of 2019_05_06
	 */
	public String addOneDay(String currDate) {
		Date dt = stringToDate(currDate);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt);
		//add one more date to the current date
		c.add(Calendar.DATE, 1);
		return dateToString(c.getTime());
	}
	
	/**
	 * Subtract one more day to the current date
	 * 
	 * @param currDate in the form of 2019_05_05
	 * @return string also in the form of 2019_05_04
	 */
	public String subtractOneDay(String currDate) {
		Date dt = stringToDate(currDate);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt);
		c.add(Calendar.DATE, -1);
		return dateToString(c.getTime());
	}
	
	 /**
     * Transfer GMT time in the database to local time
     *
     * @param code airport code (3 capital letters) 
     * @param GMTTime GMT time in the form of Date 
     * @return local time in the form of Date
     */
	public Date GMTtoLocalTime(String code, Date GMTTime) {
		Calendar cal = Calendar.getInstance();
		TimeZoneMap tzm = TimeZoneMap.getTimeZoneMap();
		int offset = tzm.getMap().get(code);
		cal.setTime(GMTTime);
	    cal.add(Calendar.SECOND, offset);
	    return cal.getTime();
	}
	
	 /**
     * Create query for timezone API
     *
     * @param lat Latitude you want to look up 
     * @param log Longitude you want to look up 
     * @return query url will be used
     */
	public static String assignQuery(float lat,float log)
	{
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		String slog=decimalFormat.format(log);
		String slat=decimalFormat.format(lat);
		String query = "http://api.timezonedb.com/v2.1/get-time-zone?key=4CVEIY5CY4Y7&format=xml&by=position&lat="+slat+"&lng="+slog;
		return query;
	}
	
	/**
     * Calculate the local time given GMT time and corresponded query url.
     *
     * @param lat The latitute of the airport
     * @param log The longitute of the airport
     * @return offset to the GMT time (in seconds) 
     */
	public static int getTimes (float lat,float log) {
		String query = assignQuery(lat, log);
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();	
		String xmlTimezone;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "4CVEIY5CY4Y7");

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		xmlTimezone = result.toString();
		return offCall(xmlTimezone);		
	}
	
	/**
     * Get the GMTOffset from the XML file.
     *
     * @param xmlTimezone The XML string get from API.
     * @return offset of the timezone from GMT. 
     */
	 public static int offCall (String xmlTimezone) throws NullPointerException {
	        // Load the XML string into a DOM tree for ease of processing
	        // then iterate over all nodes adding each airport to our collection
	        Document docTime = buildDomDoc (xmlTimezone);
	        NodeList nodesTime = docTime.getElementsByTagName("gmtOffset");
            int timeoffset=0;
            String off=getCharacterDataFromElement((Element)nodesTime.item(0));
            try {
	        timeoffset = Integer.parseInt(off);}
            catch (NullPointerException | NumberFormatException ex) {
    			throw new IllegalArgumentException ("Database Server erro!", ex);
    		}
	        if(timeoffset==0)
	        	System.out.println(xmlTimezone);
	        return timeoffset;
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
     * Verify the leg is effective
     *
     * @param arrD the pre-flight arrive time
     * @param deparD the post-flight departure time
     * @return If it is effective return true, else return false
     */
    public static boolean verifyLeg (Date arrD, Date deparD)
    {
    	long arr=arrD.getTime();
    	long dep=deparD.getTime();
    	long diff=(dep-arr)/60000;
    	if(diff>120||diff<30)
    	{
    		return false;
    	}
    	else
    		return true;

    }

}

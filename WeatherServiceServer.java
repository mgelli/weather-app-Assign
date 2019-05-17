// WeatherServiceImpl.java
// WeatherServiceImpl implements the WeatherService remote
// interface to provide a WeatherService remote object.
//package com.deitel.advjhtp1.rmi.weather;

// Java core packages
import java.io.*;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

// JDK1.5
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class WeatherServiceServer  extends TimerTask implements WeatherService {
                                   
   public WeatherServiceServer ()
   {
      super();
      updateWeatherConditions();
   };

   private List weatherInformation;  // WeatherBean objects
   
   private List allLists;

   // get weather information from NWS
   private void updateWeatherConditions()
   {
      try {
         System.err.println( "Update weather information..." );
		//Mycode: to add four links
		 ArrayList <URL> al = new ArrayList();
         // National Weather Service Travelers Forecast page
         URL url = new URL(
            //"http://iwin.nws.noaa.gov/iwin/us/traveler.html" );
            //"http://www.weather.gov/view/prodsByState.php?state=TX&prodtype=hourly" );
	    //  "http://www.weather.gov/view/national.php?prodtype=tempprecip");
	    //  "http://www.nws.noaa.gov/xml/tpex/scs.php");
	      "https://forecast.weather.gov/product.php?site=CRH&product=SCS&issuedby=01");
		  // URLS's to display four sections of weather report
		  URL url2 = new URL("https://forecast.weather.gov/product.php?site=CRH&product=SCS&issuedby=02");
		  URL url3 = new URL("https://forecast.weather.gov/product.php?site=CRH&product=SCS&issuedby=03");
		  URL url4 = new URL("https://forecast.weather.gov/product.php?site=CRH&product=SCS&issuedby=04");
		  
			//Mycode:adding four url's to list object
		  al.add(url);
		  al.add(url2);
		  al.add(url3);
		  al.add(url4);
		  
		  allLists = new ArrayList(); 
			//Mycode:iterating 4 URL's
			for(int itr=0;itr<al.size();itr++){
			
         // set up text input stream to read Web page contents
         BufferedReader in = new BufferedReader(
            new InputStreamReader( al.get(itr).openStream() ) );

         // helps determine starting point of data on Web page
         //String separator = "TAV10";  
         //String separator = "TXZ083>090-098>101-113>116-127>130-139>142-154-155-030000-";  
	 //String separator = "CENTRAL AND SOUTH CENTRAL TEXAS";  

         System.err.println( "Update weather information...1" );

         String separator = "TEMPERATURES INDICATE";

	 String line = "";
         // locate the line start starts with the separate string in Web page
         //while ( ! (line = in.readLine().startsWith( separator ) ))
          //  System.err.println( line );    // do nothing
         for ( line = in.readLine(); !line.startsWith(separator ); line = in.readLine() )
            System.err.println( line );    // do nothing

         separator = "CITY";

         for ( line = in.readLine(); !line.startsWith(separator ); line = in.readLine() )
            System.err.println( line );    // do nothing

         // locate the line start starts with the separate string in Web page
         //while ( !in.readLine().startsWith( separator ) )
          //  ;    // do nothing

         // strings representing headers on Travelers Forecast
         // Web page for daytime and nighttime weather
         //String dayHeader =
         //  "CITY            WEA     HI/LO   WEA     HI/LO";
	    //"CITY            WEA     LO/HI   WEA     LO/HI";
         //String nightHeader =
         //   "CITY            WEA     LO/HI   WEA     LO/HI";
        // String dayHeader = "CITY             LO/HI   PCPN   WEA     LO/HI   WEA     LO/HI";
/*
Selected Cities

SELECTED CITIES WEATHER SUMMARY AND FORECASTS...PART 1 OF 4
NWS/NDFD TELECOMMUNICATION OPERATIONS CENTER SILVER SPRING MD
850 AM EDT FRI JUN 23 2017

TEMPERATURES INDICATE DAYTIME HIGH...NIGHTTIME LOW
B INDICATES TEMPERATURES BELOW ZERO
PRECIPITATION FOR 24 HOURS ENDING AT 8 AM EDT

                                FORECAST        FORECAST
                 THU...JUN 22   FRI....JUN 23   SAT....JUN 24
CITY             HI/LO   PCPN   WEA     HI/LO   WEA     HI/LO

ABILENE TX       97  73         PTCLDY 101/68   TSTRMS  80/67
AKRON CANTON     87  70   .19   TSTRMS  77/62   PTCLDY  76/55
ALBANY NY        81  69         TSTRMS  85/69   SHWRS   82/60
ALBUQUERQUE     103  70         PTCLDY  98/66   TSTRMS  90/66
ALLENTOWN        88  71         SHWRS   83/73   SHWRS   84/60
AMARILLO        100  70         TSTRMS  78/55   TSTRMS  76/56
ANCHORAGE        58  50         CLOUDY  59/50   MOCLDY  65/52
ASHEVILLE        72  70   .51   SHWRS   82/67   TSTRMS  81/61
ATLANTA          84  72   .12   TSTRMS  88/73   TSTRMS  82/68
ATLANTIC CITY    86  75         SHWRS   86/75   SHWRS   85/65
AUSTIN           99  77         PTCLDY  99/76   TSTRMS  91/72
BALTIMORE        88  71   .13   TSTRMS  89/74   SHWRS   88/66
BATON ROUGE      86  79   .55   TSTRMS  87/75   TSTRMS  88/73
BILLINGS         75  46         PTCLDY  73/48   PTCLDY  75/49
BIRMINGHAM       79  75  1.80   SHWRS   84/72   TSTRMS  82/66
BISMARCK         79  55         WINDY   67/45   PTCLDY  66/43
BOISE            82  51         SUNNY   83/57   SUNNY   89/63
BOSTON           84  65         TSTRMS  84/70   SHWRS   82/67
BRIDGEPORT       84  71         SHWRS   81/73   SHWRS   89/66
BROWNSVILLE     100  81         PTCLDY  99/80   TSTRMS  93/78
BUFFALO          80  73   .06   TSTRMS  76/62   TSTRMS  74/57
BURLINGTON VT    80  64  1.03   TSTRMS  84/67   SHWRS   79/60
CARIBOU          75  50         SHWRS   68/64   SHWRS   77/57
CASPER           78  40         SUNNY   71/42   PTCLDY  72/44
CHARLESTON SC    89  76         TSTRMS  90/78   TSTRMS  92/76
CHARLESTON WV    82  70   .43   TSTRMS  83/67   SHWRS   82/60
CHARLOTTE        79  74   .19   PTCLDY  89/74   TSTRMS  88/69
CHATTANOOGA      77  74  1.15   SHWRS   85/73   SHWRS   83/63
CHEYENNE         77  45   .02   PTCLDY  64/46   MOCLDY  69/46
CHICAGO          88  68   .71   PTCLDY  81/59   PTCLDY  73/54
CINCINNATI       82  69   .28   SHWRS   77/62   PTCLDY  78/56
CLEVELAND        89  72   .62   TSTRMS  80/63   PTCLDY  77/59
COLORADO SPGS    93  56         SHWRS   64/49   TSTRMS  74/49
COLUMBIA SC      88  77         TSTRMS  94/77   TSTRMS  92/74
COLUMBUS GA      88  73   .28   TSTRMS  91/75   TSTRMS  86/72
COLUMBUS OH      85  70   .09   SHWRS   78/63   PTCLDY  80/
*/

/* 2018:

532
FPUS20 KWBN 251250
SCS01
SELECTED CITIES WEATHER SUMMARY AND FORECASTS...PART 1 OF 4
NWS/NDFD TELECOMMUNICATION OPERATIONS CENTER SILVER SPRING MD
850 AM EDT MON JUN 25 2018

TEMPERATURES INDICATE DAYTIME HIGH...NIGHTTIME LOW
B INDICATES TEMPERATURES BELOW ZERO
PRECIPITATION FOR 24 HOURS ENDING AT 8 AM EDT

                                FORECAST        FORECAST
                 SUN...JUN 24   MON....JUN 25   TUE....JUN 26
CITY             HI/LO   PCPN   WEA     HI/LO   WEA     HI/LO

ABILENE TX      100  76         SUNNY   97/75   SUNNY   98/76
AKRON CANTON     81  60         PTCLDY  78/56   PTCLDY  81/67
ALBANY NY        76  61   .44   SUNNY   76/50   SUNNY   78/58
ALBUQUERQUE      97  67         SUNNY   94/66   SUNNY   98/68
ALLENTOWN        89  65         SUNNY   83/52   SUNNY   81/60
AMARILLO         86  59   .05   MOCLDY  86/68   SUNNY   97/70
ANCHORAGE        59  51   .01   SHWRS   63/52   MOCLDY  63/53
ASHEVILLE        89  64         TSTRMS  84/66   TSTRMS  82/66
ATLANTA          92  75         TSTRMS  92/75   TSTRMS  91/74
ATLANTIC CITY    91  69         SUNNY   84/58   SUNNY   78/62
AUSTIN           99  77         TSTRMS  93/76   MOCLDY  96/75
BALTIMORE        89  66   .08   PTCLDY  82/60   SUNNY   80/64
BATON ROUGE      93  77         TSTRMS  93/76   TSTRMS  93/76
BILLINGS         77  55         SUNNY   88/61   PTCLDY  80/53
BIRMINGHAM       92  77   .02   TSTRMS  93/74   TSTRMS  94/74
BISMARCK         82  67   .30   TSTRMS  77/60   TSTRMS  83/58
BOISE            87  65         SUNNY   92/55   SUNNY   82/58
BOSTON           77  67   .59   SHWRS   71/56   SUNNY   75/60
BRIDGEPORT       77  65   .36   PTCLDY  81/57   SUNNY   77/62
BROWNSVILLE      94  80         TSTRMS  93/79   TSTRMS  93/79
BUFFALO          69  55   .38   SUNNY   76/50   SUNNY   82/66
BURLINGTON VT    69  57   .09   SUNNY   73/49   SUNNY   79/57
CARIBOU          75  52   .06   SHWRS   64/43   SUNNY   74/52
CASPER           66  46   .06   SUNNY   82/52   SUNNY   92/51
CHARLESTON SC    97  71   .48   TSTRMS  95/76   TSTRMS  90/75
CHARLESTON WV    84  66   .64   TSTRMS  81/65   SHWRS   84/68
CHARLOTTE        96  69   .16   TSTRMS  92/72   TSTRMS  85/69
CHATTANOOGA      93  69         TSTRMS  90/73   TSTRMS  90/74
CHEYENNE         63  49   .06   SUNNY   80/51   SUNNY   91/54
CHICAGO          80  61         PTCLDY  79/66   TSTRMS  80/68
CINCINNATI       85  71         SHWRS   79/65   TSTRMS  83/70
CLEVELAND        80  65   .02   PTCLDY  74/58   PTCLDY  82/70
COLORADO SPGS    70  48   .26   PTCLDY  80/55   SUNNY   94/61
COLUMBIA SC      99  73   .11   TSTRMS  99/75   TSTRMS  92/72
COLUMBUS GA      94  76         TSTRMS  96/75   TSTRMS  94/76
COLUMBUS OH      84  62         PTCLDY  82/62   TSTRMS  84/70


$$
*/
	 //nightHeader = "CITY           SKY/WX    TMP DP  RH WIND       PRES   REMARKS"; 
	 //nightHeader = "OTHER LOCATIONS IN TEXAS"; 
	 //nightHeader = "WESTERN NORTH TEXAS"; 

         String inputLine = "";

         System.err.println( "Update weather information...2" + inputLine);

         // locate header that begins weather information
         //do {
         //   inputLine = in.readLine();
         //} while ( !inputLine.equals( dayHeader ) &&
         //          !inputLine.equals( nightHeader ) );
	 //
         weatherInformation = new ArrayList(); // create List
         
         // create WeatherBeans containing weather data and 
         // store in weatherInformation Vector

         inputLine = in.readLine();  // skip an empty line
         inputLine = in.readLine();  // first city info line
         System.err.println( "Update weather information...3" + inputLine);
         //inputLine = in.readLine();  // skip the title of the section
         //inputLine = in.readLine();  // get first city's info
 
         // The portion of inputLine containing relevant data is
         // 45 characters long. If the line length is not at 
         // least 40 characters long, done processing data.

	 String cityName = "";
	 String temperatures = "";
	 String condition = "";

         System.err.println( "Update weather information...4" );

         while ( inputLine.length() > 10 ) {

            //System.err.println( "Update weather information...3.5"+inputLine);
            //System.err.println( "Update weather information...3.6"+inputLine.substring(0,15)+"ppp");
            //System.err.println( "1qqq");
            //System.err.println( "qqq"+inputLine.substring(15,1)+"rrrr");
            // Create WeatherBean object for city. First 16 
            // characters are city name. Next, six characters
            // are weather description. Next six characters
            // are HI/LO or LO/HI temperature.
         //System.err.println( "Update weather information...4"+inputLine.substring( 0, 16 )+"22"+inputLine.substring( 16, 24 )+"333"+inputLine.substring( 24, 32 ) +"44" +inputLine.substring( 40, 45  )+"EEE");
         //System.err.println( "Update weather information...----11111--"+inputLine.substring( 0, 17 )+"--2222--"+inputLine.substring( 17, 20 )+"--333--"+inputLine.substring( 21, 23 ) +"---4444---" +inputLine.substring( 24, 32 )+"---555---"+inputLine.substring( 32, 38 )+"----66666----"+inputLine.substring( 36, 40 )+"----66666----");
			System.err.println(inputLine.substring( 0, 17 )+"--"+inputLine.substring( 33, 38 )+"--"+inputLine.substring( 17, 20 ));

	
		  cityName = inputLine.substring( 0, 16 );
		  temperatures = inputLine.substring( 16, 23 );
		  condition = inputLine.substring( 32, 37 );

		  cityName = cityName.trim();
		  temperatures = temperatures.trim();

			 System.err.println( "City:" + cityName + ", condition:"+condition+",temp:"+temperatures);
			  WeatherBean weather = new WeatherBean(
				   cityName,   
				   condition,   
				   temperatures);

				// add WeatherBean to List
			  weatherInformation.add( weather ); 
			  
				
				inputLine = in.readLine();  // get next city's info
			 }
			allLists.add(weatherInformation);

         System.err.println( "Initializing WeatherService...5" );

         in.close();  // close connection to NWS Web server  
         
         System.err.println( "Weather information updated." );
			}
      }
      
      // process failure to connect to National Weather Service
      catch( java.net.ConnectException connectException ) {
         connectException.printStackTrace();
         System.exit( 1 );
      }
      
      // process other exceptions
      catch( Exception exception ) {
         exception.printStackTrace();
         System.exit( 1 );
      }
	  
   }
   
   //Updating weather for certain time intervals
   public void run(){
	   
	   updateWeatherConditions();
	   System.out.println("Updating the data for every one hour "+new Date());
   }
	   

   // implementation for WeatherService interface method
   public List getWeatherInformation() throws RemoteException 
   {
      return allLists;
   }

   // launch WeatherService remote object
   public static void main( String args[] )
   {     
	try {
		System.setSecurityManager (new SecurityManager());
		System.err.println( "Initializing WeatherService..." );

		WeatherServiceServer obj = new WeatherServiceServer();
		WeatherService stub = (WeatherService) UnicastRemoteObject.exportObject(obj, 0);
	      // create remote object
	      //WeatherService service = new WeatherServiceImpl();

	      // specify remote object name
	      //String serverObjectName = "rmi://localhost/WeatherService";
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("WeatherService", stub);
	      
	      // bind WeatherService remote object in rmiregistry
	      //Naming.rebind( serverObjectName, service );
	      
	      System.err.println( "WeatherService running." );
		  TimerTask task = new WeatherServiceServer();
		  Timer timer = new Timer();
		  timer.schedule(task ,0,60*60*1000);

	} catch (Exception e) {
		System.err.println("Server exception: " + e.toString());
		e.printStackTrace();
	}

   }
}


/**************************************************************************
 * (C) Copyright 2001 by Deitel & Associates, Inc. and Prentice Hall.     *
 * All Rights Reserved.                                                   *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

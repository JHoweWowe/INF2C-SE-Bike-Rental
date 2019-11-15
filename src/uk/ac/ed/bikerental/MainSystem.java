package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

//Acts as the Main class- aka DBHandler in the system design?
//Think of it as UI
//Currently on implementing the first use case scenario

public class MainSystem {
    
    protected static List<Quote> showListofQuotes() {
        
        
    }
    
    protected static List<Quote> filterQuotes (List<Quote> quotes, DateRange dates) {
        HashSet<Quote> set = new HashSet<Quote>();
        if (quotes.isEmpty()) {
            return set;
        }

        List<Quote> list = new ArrayList<Quote>();
        for (Quote q : quotes) {
            //TODO: Should check the dates when the Bike is unavailable
            //Where to put Bike getDates?
            String cat = e.getCategory();
            if (map.containsKey(cat)) {
                map.get(cat).add(e);
            }
            else {
                List<ExpenseItem> newlist = new ArrayList<ExpenseItem>();
                newlist.add(e);
                map.put(cat, newlist);
            }
        }
        
        return set;
    }
    
    //Testing data for now
    public static void main(String[] args) {
        List<Quote> list = new ArrayList<Quote>();

        Location loc1 = new Location("EH16 5AJ","101 Dalkeith Road");
        LocalTime openingHours1 = LocalTime.of(10, 30);
        
        Provider testProvider1 = new Provider("The Bike Station",loc1,openingHours1);
        Bike bike1 = new Bike(new BigDecimal(900), 3, new BigDecimal(0.1));
        Quote quote1 = new Quote(testProvider1, bike1, new BigDecimal(900), new BigDecimal(126));
        
        list.add(quote1);
        
        
        //Use different data please
        Location loc2 = new Location("EH16 5AJ","103 Dalkeith Road");
        LocalTime openingHours2 = LocalTime.of(10, 30);
        
        Provider testProvider2 = new Provider("The Bike Station",loc2,openingHours2);
        Bike bike2 = new Bike(new BigDecimal(1800), 3, new BigDecimal(0.1));
        Quote quote2 = new Quote(testProvider2, bike2, new BigDecimal(1800), new BigDecimal(252));
        
        list.add(quote2);
        
        //Use different data please
        Location loc3 = new Location("EH16 5AY","5 Dalkeith Road");
        LocalTime openingHours3 = LocalTime.of(11, 30);
        
        Provider testProvider3 = new Provider("The Bike Station",loc3,openingHours3);
        Bike bike3 = new Bike(new BigDecimal(2700), 3, new BigDecimal(0.1));
        Quote quote3 = new Quote(testProvider3, bike3, new BigDecimal(2700), new BigDecimal(378));
        
        list.add(quote3);

        
    }

}

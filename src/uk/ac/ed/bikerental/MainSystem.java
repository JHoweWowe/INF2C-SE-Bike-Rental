package uk.ac.ed.bikerental;
import java.util.*;

public class MainSystem {



    public List<Quote> filterByDate(List<Quote> listOfQuotes, DateRange requestedDates){
        for (Quote q : listOfQuotes){
            if (search(q)){
                listOfQuotes.remove(q);
            }
        }
    }

    public boolean search(Quote q){
        for (Bike b : q.getCollectionOfBikes()){
            for (bd : b.getBookedDates()){
                if (bd.overlaps(requestedDates)){
                    return true;
                }
            }
        }
        return false;
    }
}

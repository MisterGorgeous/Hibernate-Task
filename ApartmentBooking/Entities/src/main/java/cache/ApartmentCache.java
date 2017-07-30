package cache;

import dao.ApartmentsDAO;
import entities.Apartment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ApartmentCache {
    private static List<Apartment> apartments;
    private static Map<String,AtomicInteger> apartmentsStatus;
    private ApartmentsDAO apartmentsDAO;

    @Autowired
    public ApartmentCache(ApartmentsDAO apartmentsDAO){
        this.apartmentsDAO = apartmentsDAO;
        apartments = apartmentsDAO.getAll();
        apartmentsStatus = new ConcurrentHashMap<>();

        for(Apartment apartment:apartments){
            apartmentsStatus.put(apartment.getName(),new AtomicInteger(apartment.getIsBooked()));
        }
    }

    public Apartment getApartByName(String name){
        for(Apartment apartment :apartments){
            if(apartment.getName().equals(name)){
                return apartment;
            }
        }

        return new Apartment();
    }

    public boolean bookApart(Apartment apartment,int value){
        if(apartmentsStatus.get(apartment.getName()).compareAndSet(0,value)){
            apartmentsDAO.update(apartment);
            apartment.setIsBooked(value);
            return true;
        }
        return false;
    }


}

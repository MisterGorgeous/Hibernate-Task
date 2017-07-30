package service;

import dao.ApartmentsDAO;
import entities.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SearchApartmentService {
    private ApartmentsDAO apartmentsDAO;

    @Autowired
    public void setApartmentsDAO(ApartmentsDAO apartmentsDAO) {
        this.apartmentsDAO = apartmentsDAO;
    }


    public List<Apartment> searchByApartmentName(String apartmentName){
        return apartmentsDAO.getByName(apartmentName);

    }

    public List<Apartment> searchByLocationAdress(String locationAdress){
        return apartmentsDAO.getByLocationAdress(locationAdress);

    }

}

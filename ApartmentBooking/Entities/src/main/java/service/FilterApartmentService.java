package service;

import dao.ApartmentsDAO;
import entities.Apartment;
import entities.SearchParametrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FilterApartmentService {
    private ApartmentsDAO apartmentsDAO;

    @Autowired
    public void setApartmentsDAO(ApartmentsDAO apartmentsDAO) {
        this.apartmentsDAO = apartmentsDAO;
    }

    public List<Apartment> filterByPrice(int lowBoundary, int highBoundary) {
        if(lowBoundary > highBoundary){
            //TO DO
        }
        List<Apartment> lowerCostApartment = apartmentsDAO.getAllWherePriceLowerThen(highBoundary);
        List<Apartment> highCostApartmentt = apartmentsDAO.getAllWherePriceHigherThen(lowBoundary);

        highCostApartmentt.removeIf(t -> !lowerCostApartment.contains(t));

        return highCostApartmentt;
    }

    public List<Apartment> filterByPrice(int price, SearchParametrs parametr) {
        List<Apartment> apartments;

        if(parametr == SearchParametrs.HIGHER){
            apartments  = apartmentsDAO.getAllWherePriceHigherThen(price);
        } else if(parametr == SearchParametrs.LOWER){
            apartments  = apartmentsDAO.getAllWherePriceLowerThen(price);
        } else {
            apartments = apartmentsDAO.getAllWherePriceEqual(price);
        }

        return apartments;
    }

}

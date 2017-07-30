package service;

import dao.ApartmentsDAO;
import entities.Apartment;
import org.springframework.beans.factory.annotation.Autowired;


public class BookingApartmentService {
    private ApartmentsDAO apartmentsDAO;

    @Autowired
    public void setApartmentsDAO(ApartmentsDAO apartmentsDAO) {
        this.apartmentsDAO = apartmentsDAO;
    }

    public boolean book(Apartment apartment){
        apartmentsDAO.update(apartment);
        return true;
    }
}

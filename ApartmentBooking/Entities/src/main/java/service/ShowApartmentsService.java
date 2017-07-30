package service;


import dao.ApartmentsDAO;
import entities.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowApartmentsService {
    private ApartmentsDAO apartmentsDAO;

    @Autowired
    public void setApartmentsDAO(ApartmentsDAO apartmentsDAO) {
        this.apartmentsDAO = apartmentsDAO;
    }


    public List<Apartment> show(){
        return apartmentsDAO.getAll();
    }
}

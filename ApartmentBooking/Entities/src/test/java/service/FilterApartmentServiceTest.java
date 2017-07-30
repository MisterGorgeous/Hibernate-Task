package service;

import dao.ApartmentsDAO;
import dao.TestAppartmentConfiguration;
import entities.Apartment;
import entities.SearchParametrs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppartmentConfiguration.class)
public class FilterApartmentServiceTest {

    @Mock
    private ApartmentsDAO apartmentsDAO;

    @InjectMocks
    @Autowired
    private FilterApartmentService filterApartmentService;

    @Autowired
    private Apartment firstApartmentEqual;

    @Autowired
    private Apartment seccondApartmentEqual;

    private List<Apartment> equalApartments;

    @Autowired
    private Apartment firstApartmentGreater;

    @Autowired
    private Apartment seccondApartmentGreater;

    private List<Apartment> greaterApartments;

    @Autowired
    private Apartment firstApartmentLower;

    @Autowired
    private Apartment seccondApartmentLower;

    private List<Apartment> lowerApartments;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        equalApartments = new ArrayList<Apartment>(){{
            firstApartmentEqual.setPricePerNight(150);
            seccondApartmentEqual.setPricePerNight(150);
            add(firstApartmentEqual);
            add(seccondApartmentEqual);
        }};

        greaterApartments = new ArrayList<Apartment>(){{
            firstApartmentGreater.setPricePerNight(275);
            seccondApartmentGreater.setPricePerNight(250);
            add(firstApartmentGreater);
            add(seccondApartmentGreater);
        }};

        lowerApartments = new ArrayList<Apartment>(){{
            firstApartmentLower.setPricePerNight(75);
            seccondApartmentLower.setPricePerNight(50);
            add(firstApartmentLower);
            add(seccondApartmentLower);
        }};

        when(apartmentsDAO.getAllWherePriceEqual(150)).thenReturn(equalApartments);
        when(apartmentsDAO.getAllWherePriceHigherThen(200)).thenReturn(greaterApartments);
        when(apartmentsDAO.getAllWherePriceLowerThen(100)).thenReturn(lowerApartments);
    }

    @Test
    public void filterByPrice() throws Exception {
        Assert.assertEquals(equalApartments,filterApartmentService.filterByPrice(150, SearchParametrs.EQUAL));
        Assert.assertEquals(greaterApartments,filterApartmentService.filterByPrice(200, SearchParametrs.HIGHER));
        Assert.assertEquals(lowerApartments,filterApartmentService.filterByPrice(100, SearchParametrs.LOWER));

    }

}
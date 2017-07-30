package entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @Column(name = "COUNTRY_ID_PK")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUNTRY_NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "country")
    private List<ApartmentLocation> apartmentLocations = new ArrayList<>();

    @Autowired
    public Country() {}


    /**
     * Call this method only by direct link.
     */
    public List<ApartmentLocation> getApartmentLocations() {
        return apartmentLocations;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApartmentLocations(List<ApartmentLocation> apartmentLocations) {
        this.apartmentLocations = apartmentLocations;
    }
}

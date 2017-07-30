package entities;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "LOCATIONS")
public class ApartmentLocation {

    @Id
    @Column(name = "LOCATION_ID_PK")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "LOCATION_ADRESS")
    private String address;

    @Column(name = "LOCATION_CITY")
    private String city;

    @Column(name = "LOCATION_POST_CODE")
    private String postCode;

    @Column(name = "LOCATION_PHONE")
    private String phone;

    @OneToOne(mappedBy = "location")
    Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "LOCATIONS_COUNTRY_ID_FK")
    private Country country;


    @Autowired
    public ApartmentLocation() {
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public Country getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(obj == this){
            return true;
        }

        if(!(obj instanceof ApartmentLocation)){
            return false;
        }

        ApartmentLocation location = (ApartmentLocation) obj;

        if(this.id != location.id){
            return false;
        }

        return this.address.equals(location.address) && this.city.equals(location.city) && this.postCode.equals(location.postCode) &&
                this.country.equals(location.country);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = result *23 + (int) (this.id ^ (this.id >>> 32));
        result = result *23 + address.hashCode();
        result = result*23 + city.hashCode();
        result = result*23 + country.hashCode();
        return result*23 + postCode.hashCode();
    }
}

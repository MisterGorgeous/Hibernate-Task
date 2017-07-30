package entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Table(name = "APARTMENTS")
public class Apartment {

    @Id
    @Column(name = "APARTMENT_ID_PK")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "APARTMENT_NAME")
    private String name;

    @Column(name = "APARTMENT_SLEEPING_PLACES")
    private int sleepingPlaces;

    @Column(name = "APARTMENT_PRICE_PER_NIGTH")
    private int pricePerNight;

    @Column(name = "APARTMENT_IMAGE")
    private String image;

    @Column(name = "APARTMENT_IS_BOOKED")
    private int isBooked;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "APARTMENT_ID_PK")
    private ApartmentLocation location;

    @Autowired
    public Apartment() {
    }


    public void setIsBooked(int isBooked) {
        this.isBooked = isBooked;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSleepingPlaces(int sleepingPlaces) {
        this.sleepingPlaces = sleepingPlaces;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setLocation(ApartmentLocation location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSleepingPlaces() {
        return sleepingPlaces;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public ApartmentLocation getLocation() {
        return location;
    }

    public long getLocationId() {
        return getId();
    }

    public String getImage() {
        return image;
    }

    public int getIsBooked() {
        return isBooked;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(obj == this){
            return true;
        }

        if(!(obj instanceof Apartment)){
            return false;
        }

        Apartment apartment = (Apartment) obj;

        if(this.id != apartment.id){
            return false;
        }

        return this.name.equals(apartment.name) && this.sleepingPlaces == apartment.sleepingPlaces && this.pricePerNight == apartment.pricePerNight &&
                this.location.equals(apartment.location);

    }

    @Override
    public int hashCode() {
        int result = 11;
        result = result *23 + (int) (this.id ^ (this.id >>> 32));
        result = result *23 + sleepingPlaces;
        result = result*23 + pricePerNight;
        return result*23 + location.hashCode();
    }
}

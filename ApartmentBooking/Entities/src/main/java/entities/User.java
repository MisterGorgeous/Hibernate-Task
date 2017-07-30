package entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "USER_ID_PK")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_BIRTHDAY")
    private Date  birthday;

    @Autowired
    public User(){}


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(long id) {
        this.id = id;
    }
}

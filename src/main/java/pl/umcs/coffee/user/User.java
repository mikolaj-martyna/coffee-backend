package pl.umcs.coffee.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    // Personal data
    private String name;
    private String surname;

    private String email;
    private String hashedPassword;

    // Address
    private String country;
    private String city;
    private String street;

    private String zipCode;

    private int buildingNumber;
    private int apartmentNumber;
}

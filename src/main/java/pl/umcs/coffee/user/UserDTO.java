package pl.umcs.coffee.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    // Personal data
    private String name;
    private String surname;

    private String email;

    // Address
    private String country;
    private String city;
    private String street;

    private String zipCode;

    private int buildingNumber;
    private int apartmentNumber;
}

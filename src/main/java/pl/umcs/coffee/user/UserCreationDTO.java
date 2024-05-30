package pl.umcs.coffee.user;

import lombok.*;

@Getter
@Setter
public class UserCreationDTO extends UserDTO {
  // Security
  private String password;

  UserCreationDTO(
      String name,
      String surname,
      String email,
      String country,
      String city,
      String street,
      String zipCode,
      int buildingNumber,
      int apartmentNumber) {
    super(name, surname, email, country, city, street, zipCode, buildingNumber, apartmentNumber);
  }
}

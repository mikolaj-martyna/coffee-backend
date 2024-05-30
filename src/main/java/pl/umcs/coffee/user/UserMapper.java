package pl.umcs.coffee.user;

import org.jetbrains.annotations.NotNull;

public class UserMapper {
  public static UserDTO toUserDTO(@NotNull User user) {
    return UserDTO.builder()
            .name(user.getName())
            .surname(user.getSurname())
            .email(user.getEmail())
            .country(user.getCountry())
            .city(user.getCity())
            .street(user.getStreet())
            .zipCode(user.getZipCode())
            .buildingNumber(user.getBuildingNumber())
            .apartmentNumber(user.getApartmentNumber())
            .build();
  }

  public static User toUser(@NotNull UserCreationDTO userDTO) {
    return User.builder()
            .name(userDTO.getName())
            .surname(userDTO.getSurname())
            .email(userDTO.getEmail())
            .hashedPassword(userDTO.getPassword())
            .country(userDTO.getCountry())
            .city(userDTO.getCity())
            .street(userDTO.getStreet())
            .zipCode(userDTO.getZipCode())
            .buildingNumber(userDTO.getBuildingNumber())
            .apartmentNumber(userDTO.getApartmentNumber())
            .build();}

  public static User toUser(@NotNull UserDTO userDTO) {
    return User.builder()
            .name(userDTO.getName())
            .surname(userDTO.getSurname())
            .email(userDTO.getEmail())
            .country(userDTO.getCountry())
            .city(userDTO.getCity())
            .street(userDTO.getStreet())
            .zipCode(userDTO.getZipCode())
            .buildingNumber(userDTO.getBuildingNumber())
            .apartmentNumber(userDTO.getApartmentNumber())
            .build();
  }
}

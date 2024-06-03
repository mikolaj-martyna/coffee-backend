package pl.umcs.coffee.user;

public interface UserService {
  UserDTO createUser(UserCreationDTO user, boolean isAdmin);

  UserDTO getUser(String token);

  UserDTO getUserById(Long id);

  UserDTO updateUser(UserDTO user);

  UserDTO deleteUser(Long id);
}

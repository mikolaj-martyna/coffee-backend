package pl.umcs.coffee.user;

public interface UserService {
  UserDTO createUser(UserCreationDTO user);

  UserDTO getUser(String token);

  UserDTO getUserById(Long id);

  UserDTO updateUser(String token, UserDTO user);

  UserDTO deleteUser(String token);

  UserDTO updateUserById(long id, UserDTO userDTO);

  UserDTO deleteUserById(long id);
}

package pl.umcs.coffee.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.security.AuthService;
import pl.umcs.coffee.security.JwtService;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final AuthService authService;
  private final JwtService jwtService;

  UserServiceImpl(
      final UserRepository userRepository, AuthService authService, JwtService jwtService) {
    this.userRepository = userRepository;
    this.authService = authService;
    this.jwtService = jwtService;
  }

  @Override
  public UserDTO createUser(@NotNull UserCreationDTO userDTO) {
    User foundUser = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

    if (foundUser != null) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    userDTO.setPassword(authService.encodePassword(userDTO.getPassword()));

    return UserMapper.toUserDTO(userRepository.save(UserMapper.toUser(userDTO)));
  }

  @Override
  public UserDTO getUser(String token) {
    User foundUser = userRepository.findByEmail(jwtService.extractUsername(token)).orElse(null);

    if (foundUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return UserMapper.toUserDTO(foundUser);
  }

  @Override
  public UserDTO getUserById(Long id) {
    User foundUser = userRepository.findById(id).orElse(null);

    if (foundUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return UserMapper.toUserDTO(foundUser);
  }

  // TODO: implement update user functionality
  @Override
  public UserDTO updateUser(@NotNull UserDTO userDTO) {
    User foundUser = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

    if (foundUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return UserMapper.toUserDTO(userRepository.save(foundUser));
  }

  @Override
  public UserDTO deleteUser(Long id) {
    User deletedUser = userRepository.findById(id).orElse(null);

    if (deletedUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    userRepository.deleteById(id);

    return UserMapper.toUserDTO(deletedUser);
  }
}

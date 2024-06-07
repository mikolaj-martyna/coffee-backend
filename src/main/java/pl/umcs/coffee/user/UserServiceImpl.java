package pl.umcs.coffee.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.cart.Cart;
import pl.umcs.coffee.cart.CartRepository;
import pl.umcs.coffee.security.AuthService;
import pl.umcs.coffee.security.JwtService;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final AuthService authService;
  private final JwtService jwtService;
  private final CartRepository cartRepository;

  UserServiceImpl(
          final UserRepository userRepository, AuthService authService, JwtService jwtService, CartRepository cartRepository) {
    this.userRepository = userRepository;
    this.authService = authService;
    this.jwtService = jwtService;
    this.cartRepository = cartRepository;
  }

  @Override
  public UserDTO createUser(@NotNull UserCreationDTO userDTO, boolean isAdmin) {
    User foundUser = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

    if (foundUser != null) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    userDTO.setPassword(authService.encodePassword(userDTO.getPassword()));

    User user = UserMapper.toUser(userDTO);
    user.setCart(cartRepository.save(new Cart(0, user, new ArrayList<>())));

    if (isAdmin) {
      user.setRole(Role.ADMIN);
    }

    return UserMapper.toUserDTO(userRepository.save(user));
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
  public UserDTO updateUser(String token, @NotNull UserDTO userDTO) {
    User foundUser = userRepository.findByEmail(jwtService.extractUsername(token)).orElse(null);

    if (foundUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // TODO: how to do this better xd
    if (!userDTO.getName().isEmpty())
      foundUser.setName(userDTO.getName());

    if (!userDTO.getSurname().isEmpty())
      foundUser.setSurname(userDTO.getSurname());

    if (!userDTO.getEmail().isEmpty())
      foundUser.setEmail(userDTO.getEmail());

    if (!userDTO.getCountry().isEmpty())
      foundUser.setCountry(userDTO.getCountry());

    if (!userDTO.getCity().isEmpty())
      foundUser.setCity(userDTO.getCity());

    if (!userDTO.getStreet().isEmpty())
      foundUser.setStreet(userDTO.getStreet());

    if (!userDTO.getZipCode().isEmpty())
      foundUser.setZipCode(userDTO.getZipCode());

    if (userDTO.getBuildingNumber() != 0)
      foundUser.setBuildingNumber(userDTO.getBuildingNumber());

    if (userDTO.getApartmentNumber() != 0)
      foundUser.setApartmentNumber(userDTO.getApartmentNumber());

    return UserMapper.toUserDTO(userRepository.save(foundUser));
  }

  @Override
  public UserDTO deleteUser(String token) {
    User deletedUser = userRepository.findByEmail(jwtService.extractUsername(token)).orElse(null);

    if (deletedUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    userRepository.deleteById(deletedUser.getId());

    return UserMapper.toUserDTO(deletedUser);
  }

  @Override
  public UserDTO getUserById(Long id) {
    User foundUser = userRepository.findById(id).orElse(null);

    if (foundUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return UserMapper.toUserDTO(foundUser);
  }

  @Override
  public UserDTO updateUserById(long id, UserDTO userDTO) {
    User foundUser = userRepository.findById(id).orElse(null);

    if (foundUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return UserMapper.toUserDTO(userRepository.save(foundUser));
  }

  @Override
  public UserDTO deleteUserById(long id) {
    User deletedUser = userRepository.findById(id).orElse(null);

    if (deletedUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    userRepository.deleteById(deletedUser.getId());

    return UserMapper.toUserDTO(deletedUser);
  }
}

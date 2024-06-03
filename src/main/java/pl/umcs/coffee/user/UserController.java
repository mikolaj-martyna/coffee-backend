package pl.umcs.coffee.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
  private final UserServiceImpl userService;

  UserController(final UserServiceImpl userService) {
    this.userService = userService;
  }

  // All
  @PostMapping("create")
  public UserDTO createUser(@RequestBody UserCreationDTO userDTO, @RequestParam(required = false, name = "admin", defaultValue = "false") boolean isAdmin) {
    return userService.createUser(userDTO, isAdmin);
  }

  // User
  @GetMapping("get")
  public UserDTO getUser(@RequestHeader(name = "Authorization") String token) {
    return userService.getUser(token.split(" ")[1]);
  }

  @PutMapping("edit")
  public UserDTO updateUser(@RequestHeader(name = "Authorization") String token, @RequestBody UserDTO userDTO) {
    return userService.updateUser(token, userDTO);
  }

  @DeleteMapping("delete")
  public UserDTO deleteUser(@RequestHeader(name = "Authorization") String token) {
    return userService.deleteUser(token.split(" ")[1]);
  }

  // Admin
  @GetMapping("get/{id}")
  public UserDTO getUserById(@PathVariable long id) {
    return userService.getUserById(id);
  }

  @PutMapping("edit/{id}")
  public UserDTO updateUserById(@PathVariable long id, @RequestBody UserDTO userDTO) {
    return userService.updateUserById(id, userDTO);
  }

  @DeleteMapping("delete/{id}")
  public UserDTO deleteUserById(@PathVariable long id) {
    return userService.deleteUserById(id);
  }
}

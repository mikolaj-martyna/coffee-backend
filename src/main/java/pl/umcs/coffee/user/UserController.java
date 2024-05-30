package pl.umcs.coffee.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserServiceImpl userService;

    UserController(final UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public UserDTO createUser(@RequestBody UserCreationDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("get")
    public UserDTO getUser(@RequestHeader (name="Authorization") String token) {
        return userService.getUser(token.split(" ")[1]);
    }

    @GetMapping("get/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("edit")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("delete/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}

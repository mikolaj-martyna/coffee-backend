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
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("edit")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("delete/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}

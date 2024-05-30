package pl.umcs.coffee.user;

public interface UserService {
    User createUser(User user);

    User getUser(String token);

    User getUserById(Long id);

    User updateUser(User user);

    User deleteUser(Long id);
}

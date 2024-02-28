package pl.umcs.coffee.user;

public interface UserService {
    User createUser(User user);

    User getUser(Long id);

    User updateUser(User user);

    User deleteUser(Long id);
}

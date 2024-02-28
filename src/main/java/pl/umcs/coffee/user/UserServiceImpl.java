package pl.umcs.coffee.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(@NotNull User user) {
        User foundUser = userRepository.findById(user.getId()).orElse(null);

        if (foundUser != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(@NotNull User user) {
        User foundUser = userRepository.findById(user.getId()).orElse(null);

        if (foundUser != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long id) {
        User deletedUser = userRepository.findById(id).orElse(null);

        if (deletedUser == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userRepository.deleteById(id);

        return deletedUser;
    }
}

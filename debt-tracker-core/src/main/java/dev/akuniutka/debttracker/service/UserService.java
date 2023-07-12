package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.User;
import dev.akuniutka.debttracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        Optional<User> user = userRepository.findUserById(id);
        if (user.isEmpty()) {
            user = Optional.of(new User(id));
            userRepository.save(user.get());
        }
        return user.get();
    }
}

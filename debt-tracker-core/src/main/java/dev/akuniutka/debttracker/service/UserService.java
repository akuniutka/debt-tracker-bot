package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.User;
import dev.akuniutka.debttracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        User user = userRepository.findUserById(id).orElse(new User());
        if (user.getId() == null) {
            user.setId(id);
            userRepository.save(user);
        }
        return user;
    }
}

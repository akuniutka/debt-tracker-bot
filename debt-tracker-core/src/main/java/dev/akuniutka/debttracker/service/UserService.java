package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.User;
import dev.akuniutka.debttracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long chatId, String userName) {
        User user = userRepository.findUserByChatId(chatId).orElse(new User(chatId));
        user.setUserName(userName);
        user.setLastMessageAt(OffsetDateTime.now());
        userRepository.save(user);
        return user;
    }
}

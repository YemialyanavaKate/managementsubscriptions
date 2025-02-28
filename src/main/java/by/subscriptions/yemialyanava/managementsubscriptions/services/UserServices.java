package by.subscriptions.yemialyanava.managementsubscriptions.services;

import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    @Transactional
    public Users create (Users users){
        users.setCreated(LocalDateTime.now());
        return userRepository.save(users);
    }

}

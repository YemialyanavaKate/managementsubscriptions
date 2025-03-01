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
        users.setUpdated(false);
        return userRepository.save(users);
    }
    public Users read (Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public Users update (Long id){
        return userRepository.findById(id)
                .map(l ->{
                    l.setUpdated(true);
                    return l;
                }).orElse(null);
    }

    @Transactional
    public Users delete (Long id){
        return userRepository.findById(id)
                .map(l ->{
                    userRepository.deleteById(id);
                    return l;
                }).orElse(null);
    }

}

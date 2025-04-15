package by.subscriptions.yemialyanava.managementsubscriptions.services;

import by.subscriptions.yemialyanava.managementsubscriptions.error.exceptions.UserNotFoundException;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.SubscriptionsRepository;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionsRepository subscriptionsRepository;

    @Transactional
    public Users create (@NotNull Users users){
        users.setCreated(LocalDateTime.now());
        users.setUpdated(false);
        return userRepository.save(users);
    }

    public Users read (@NotNull Integer id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public Users update (@NotNull Integer id, @NotNull Users user){
        return userRepository.findById(id)
                .map(UserUpdate ->{
                    user.setId(id);
                    user.setCreated(UserUpdate.getCreated());
                    userRepository.save(user);
                    UserUpdate.setUpdated(true);
                    return UserUpdate;
                }).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public void delete (@NotNull Integer id){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        userRepository.delete(user);
    }

    public List<Subscriptions> readSubscription(@NotNull Integer id) {
        return new ArrayList<>((subscriptionsRepository.findByUsers_Id(id)));
    }
}

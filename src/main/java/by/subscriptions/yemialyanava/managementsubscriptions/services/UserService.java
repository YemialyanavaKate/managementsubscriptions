package by.subscriptions.yemialyanava.managementsubscriptions.services;

import by.subscriptions.yemialyanava.managementsubscriptions.error.exceptions.UserNotFoundException;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.SubscriptionsRepository;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionsRepository subscriptionsRepository;

    @Transactional
    public Users create (Users users){
        users.setCreated(LocalDateTime.now());
        users.setUpdated(false);
        return userRepository.save(users);
    }

    public Users read (Integer id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public Users update (Integer id, Users user){
        return userRepository.findById(id)
                .map(l ->{
                    user.setId(id);
                    user.setCreated(l.getCreated());
                    userRepository.save(user);
                    l.setUpdated(true);
                    return l;
                }).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public void delete (Integer id){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        userRepository.delete(user);
    }

    public List<Subscriptions> readSubscription (Integer id){
        return StreamSupport.stream((subscriptionsRepository.findSubscriptionByUser(id)).spliterator(), false)
                .collect(Collectors.toList());
    }


}

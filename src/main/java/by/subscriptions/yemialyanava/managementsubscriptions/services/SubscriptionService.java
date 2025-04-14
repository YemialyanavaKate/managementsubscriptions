package by.subscriptions.yemialyanava.managementsubscriptions.services;

import by.subscriptions.yemialyanava.managementsubscriptions.error.exceptions.UserNotFoundException;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.SubscriptionsRepository;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionsRepository subscriptionsRepository;
    private final UserRepository userRepository;

    public Subscriptions addSubscription(Integer id, Subscriptions subscription) {

        return userRepository.findById(id)
                .map(user -> {
                    if (user.getSubscriptions() == null) {
                        user.setSubscriptions(new ArrayList<>());
                    }

                    user.getSubscriptions().add(subscription);

                    if (subscription.getUsers() == null) {
                        subscription.setUsers(new ArrayList<>());
                    }
                    subscription.getUsers().add(user);
                    subscriptionsRepository.save(subscription);
                    return subscription;
                }).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public void deleteSubscription(Integer userId, Integer subId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        Subscriptions subscription = subscriptionsRepository.findById(subId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        if (user.getSubscriptions().contains(subscription)) {
            user.getSubscriptions().remove(subscription);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Subscription not found for this user");
        }
    }

    public List<Object[]> getTopSubscriptions() {
        return subscriptionsRepository.findTop3();
    }
}

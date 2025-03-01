package by.subscriptions.yemialyanava.managementsubscriptions.services;

import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.SubscriptionsRepository;
import by.subscriptions.yemialyanava.managementsubscriptions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionsRepository subscriptionsRepository;
    private final UserRepository userRepository;

    public Subscriptions addSubscription(Integer id, Subscriptions subscription) {

        return userRepository.findById(id)
                .map(user -> {
                    if (user.getSubscriptions() == null){
                        user.setSubscriptions(new ArrayList<>());
                    }

                    user.getSubscriptions().add(subscription);

                    if (subscription.getUsers() == null){
                        subscription.setUsers(new ArrayList<>());
                    }
                    subscription.getUsers().add(user);
                    subscriptionsRepository.save(subscription);
                    return subscription;
                }).orElse(null);
    }
}

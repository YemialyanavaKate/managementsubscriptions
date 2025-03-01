package by.subscriptions.yemialyanava.managementsubscriptions.mappers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsDto;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionMapper {
    public SubscriptionsDto toDto (Subscriptions subscription){
        return SubscriptionsDto.builder()
                .id(subscription.getId())
                .service(subscription.getService())
                .build();
    }

    public Subscriptions toEntity (SubscriptionsDto subscriptionDto) {
        return Subscriptions.builder()
                .id(subscriptionDto.getId())
                .service(subscriptionDto.getService())
                .build();
    }
}

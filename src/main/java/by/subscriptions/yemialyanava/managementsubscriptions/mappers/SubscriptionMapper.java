package by.subscriptions.yemialyanava.managementsubscriptions.mappers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsDto;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;

public class SubscriptionMapper {
    public static SubscriptionsDto toDtoSubscriptions (Subscriptions subscription){
        return SubscriptionsDto.builder()
                .id(subscription.getId())
                .service(subscription.getService())
                .build();
    }

    public static Subscriptions toEntitySubscriptions (SubscriptionsDto subscriptionDto) {
        return Subscriptions.builder()
                .id(subscriptionDto.getId())
                .service(subscriptionDto.getService())
                .build();
    }
}

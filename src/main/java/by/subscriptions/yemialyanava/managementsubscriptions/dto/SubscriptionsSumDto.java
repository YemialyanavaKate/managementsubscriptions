package by.subscriptions.yemialyanava.managementsubscriptions.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class SubscriptionsSumDto {

    private String service;
    private long frequency;
}

package by.subscriptions.yemialyanava.managementsubscriptions.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubscriptionsSumDto {

    private String service;
    private long frequency;
}

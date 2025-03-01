package by.subscriptions.yemialyanava.managementsubscriptions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubscriptionsDto {
    private Integer id;
    @NotNull(message = "Service is a required field")
    private String service;

}

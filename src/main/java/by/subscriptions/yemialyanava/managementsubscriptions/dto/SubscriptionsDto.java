package by.subscriptions.yemialyanava.managementsubscriptions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class SubscriptionsDto {
    private Integer id;
    @NotNull(message = "Service is a required field")
    private String service;

}

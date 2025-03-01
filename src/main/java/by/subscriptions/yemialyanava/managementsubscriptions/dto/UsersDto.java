package by.subscriptions.yemialyanava.managementsubscriptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDto {
    private Integer id;
    @NotNull(message = "Name is a required field")
    private String name;
    @NotNull(message = "Email is a required field")
    @Email(message = "Email should be valid")
    private String email;
    private List<SubscriptionsDto> subscriptions;

}

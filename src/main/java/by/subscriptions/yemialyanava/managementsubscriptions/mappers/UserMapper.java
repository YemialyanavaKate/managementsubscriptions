package by.subscriptions.yemialyanava.managementsubscriptions.mappers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsDto;
import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class UserMapper {
    private final SubscriptionMapper subscriptionMapper;

    public UsersDto toDto (Users user){
        List<SubscriptionsDto> subscriptions = user.getSubscriptions() != null ?
                user.getSubscriptions()
                        .stream()
                        .map(subscriptionMapper::toDto)
                        .toList() :
                Collections.emptyList();

        return UsersDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .subscriptions(subscriptions)
                .build();
    }

    public Users toEntity (UsersDto usersDto) {
        return Users.builder()
                .id(usersDto.getId())
                .name(usersDto.getName())
                .email(usersDto.getEmail())
                .created(null)
                .updated(null)
                .build();
    }
}

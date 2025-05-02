package by.subscriptions.yemialyanava.managementsubscriptions.mappers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsDto;
import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;

import java.util.Collections;
import java.util.List;

public class UserMapper {

    public static UsersDto toDto(Users user) {
        List<SubscriptionsDto> subscriptions = user.getSubscriptions() != null ?
                user.getSubscriptions()
                        .stream()
                        .map(SubscriptionMapper::toDtoSubscriptions)
                        .toList() :
                Collections.emptyList();

        return UsersDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .subscriptions(subscriptions)
                .build();
    }

    public static Users toEntity(UsersDto usersDto) {
        return Users.builder()
                .id(usersDto.getId())
                .name(usersDto.getName())
                .email(usersDto.getEmail())
                .created(null)
                .updated(null)
                .build();
    }
}

package by.subscriptions.yemialyanava.managementsubscriptions.mappers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    public UsersDto toDto (Users users){
        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .email(users.getEmail())
                .build();
    }

    public Users toEntity (UsersDto usersDto) {
        return Users.builder()
                .id(usersDto.getId())
                .name(usersDto.getName())
                .email(usersDto.getEmail())
                .created(null)
                .build();
    }

}

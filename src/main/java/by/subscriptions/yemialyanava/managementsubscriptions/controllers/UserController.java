package by.subscriptions.yemialyanava.managementsubscriptions.controllers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.mappers.UserMapper;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserServices userServices;
    private final UserMapper userMapper;

    @PostMapping("/users")
    public UsersDto read(@RequestBody UsersDto userDto){
        Users user = userMapper.toEntity(userDto);
        return userMapper.toDto(userServices.create(user));
    }
}

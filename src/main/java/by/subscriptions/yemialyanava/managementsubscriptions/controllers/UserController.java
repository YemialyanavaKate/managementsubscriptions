package by.subscriptions.yemialyanava.managementsubscriptions.controllers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.mappers.UserMapper;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserServices userServices;
    private final UserMapper userMapper;

    @PostMapping("/users")
    public UsersDto create(@RequestBody UsersDto userDto){
        Users user = userMapper.toEntity(userDto);
        return userMapper.toDto(userServices.create(user));
    }

    @GetMapping("/users/{id}")
    public UsersDto read(@PathVariable(name = "id") Long id){
        return userMapper.toDto(userServices.read(id));
    }

    @PutMapping("/users/{id}")
    public UsersDto update(@PathVariable(name = "id") Long id){
        return userMapper.toDto(userServices.update(id));
    }

    @DeleteMapping("/users/{id}")
    public UsersDto delete(@PathVariable(name = "id") Long id){
        return userMapper.toDto(userServices.delete(id));
    }
}

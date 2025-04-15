package by.subscriptions.yemialyanava.managementsubscriptions.controllers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.SubscriptionsDto;
import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.mappers.SubscriptionMapper;
import by.subscriptions.yemialyanava.managementsubscriptions.mappers.UserMapper;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.services.SubscriptionService;
import by.subscriptions.yemialyanava.managementsubscriptions.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userServices;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<UsersDto> create(@Valid @RequestBody UsersDto userDto) {
        Users user = userMapper.toEntity(userDto);
        return ResponseEntity.ok(userMapper.toDto(userServices.create(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> read(@PathVariable(name = "id") Integer id) {
        Users user = userServices.read(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersDto> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody UsersDto usersDto) {
        Users user = userMapper.toEntity(usersDto);
        return ResponseEntity.ok(userMapper.toDto(userServices.update(id, user)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") Integer id) {
        userServices.delete(id);
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<SubscriptionsDto> addSubscription(@PathVariable(name = "id") Integer id, @Valid @RequestBody SubscriptionsDto subscriptionsDto) {
        Subscriptions subscription = subscriptionMapper.toEntity(subscriptionsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionMapper.toDto(subscriptionService.addSubscription(id, subscription)));

    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionsDto>> readSubscriptions(@PathVariable(name = "id") Integer id) {

        List<Subscriptions> subscriptions = userServices.readSubscription(id);
        return ResponseEntity.ok(subscriptions.stream()
                .map(subscriptionMapper::toDto)
                .toList());
    }

    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubscription(@PathVariable(name = "id") Integer userId, @PathVariable(name = "sub_id") Integer subId) {
        subscriptionService.deleteSubscription(userId, subId);
    }

}

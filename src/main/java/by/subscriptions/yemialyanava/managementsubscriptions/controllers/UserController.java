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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userServices;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UsersDto userDto, BindingResult result) {
        Users user = userMapper.toEntity(userDto);
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.ok(userMapper.toDto(userServices.create(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(name = "id") Integer id) {
        Users user = userServices.read(id);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody UsersDto usersDto, BindingResult result) {
        Users user = userMapper.toEntity(usersDto);
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.ok(userMapper.toDto(userServices.update(id, user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Integer id) {
        Users user = userServices.delete(id);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }


    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Object> addSubscription(@PathVariable(name = "id") Integer id, @Valid @RequestBody SubscriptionsDto subscriptionsDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (userServices.read(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Subscriptions subscription = subscriptionMapper.toEntity(subscriptionsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionMapper.toDto(subscriptionService.addSubscription(id, subscription)));

    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<Object> readSubscriptions(@PathVariable(name = "id") Integer id) {
        Users user = userServices.read(id);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<Subscriptions> subscriptions = userServices.readSubscription(id);

        return ResponseEntity.ok(subscriptions.stream()
                .map(subscriptionMapper::toDto)
                .toList());
    }

    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable(name = "id") Integer userId, @PathVariable(name = "sub_id") Integer subId) {
        try {
            subscriptionService.deleteSubscription(userId, subId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}

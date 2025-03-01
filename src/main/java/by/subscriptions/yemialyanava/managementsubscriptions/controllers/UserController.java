package by.subscriptions.yemialyanava.managementsubscriptions.controllers;

import by.subscriptions.yemialyanava.managementsubscriptions.dto.UsersDto;
import by.subscriptions.yemialyanava.managementsubscriptions.mappers.UserMapper;
import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import by.subscriptions.yemialyanava.managementsubscriptions.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UsersDto userDto, BindingResult result){
        Users user = userMapper.toEntity(userDto);
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.ok(userMapper.toDto(userServices.create(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(name = "id") Long id){
        Users user = userServices.read(id);
        if (user == null){
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id,@Valid @RequestBody UsersDto usersDto, BindingResult result){
        Users user = userMapper.toEntity(usersDto);
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.ok(userMapper.toDto(userServices.update(id, user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id){
        Users user = userServices.delete(id);
        if (user == null){
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

   /* @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Object> addSubscription(@PathVariable(name = "id") Long id, @Valid @RequestBody UsersDto userDto, BindingResult result){
        Users user = userMapper.toEntity(userDto);
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.ok(userMapper.toDto(userServices.create(user)));
    }*/
}

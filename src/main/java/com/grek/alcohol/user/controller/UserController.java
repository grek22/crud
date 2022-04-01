package com.grek.alcohol.user.controller;

import com.grek.alcohol.user.service.UserService;
import com.grek.alcohol.validation.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final ValidationService validationService;

    public UserController(UserService userService, ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserInformation(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        validationService.validate(userDto);
        var user = userService.saveUser(userDto);
       return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // w zależności od założeń ta metoda może zwracać zaktualizowany obiekt
    @PatchMapping(value = "/users")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        validationService.validate(userDto);
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }
}

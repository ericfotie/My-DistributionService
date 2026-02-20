package dev.compteservice.Controller;

import dev.compteservice.Service.ServiceImpl.UserServiceImpl;
import dev.compteservice.dto.UserRequestDto;
import dev.compteservice.dto.UserRespondDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserRespondDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserRespondDto createdUser = userService.SaveUser(userRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<UserRespondDto>> getAllUsers() {
        List<UserRespondDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserRespondDto> getUserById(@PathVariable Long id) {
        UserRespondDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserRespondDto> updateUser(
            @RequestBody UserRequestDto userRequestDto,
            @PathVariable Long id) {
        UserRespondDto updatedUser = userService.Update(userRequestDto, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRespondDto> deleteUser(@PathVariable Long id) {
        return userService.DelleteUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

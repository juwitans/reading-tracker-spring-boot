package app.readingtracker.controller;

import app.readingtracker.entity.User;
import app.readingtracker.model.response.WebResponse;
import app.readingtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        User newUser = userService.create(user);
        WebResponse<User> response = WebResponse.<User>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create new user")
                .data(newUser)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserDetail(@PathVariable String id) {
        User user = userService.get(id);
        WebResponse<User> response = WebResponse.<User>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get user detail")
                .data(user)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateUserDetail(@RequestBody User user) {
        User updated = userService.update(user);
        WebResponse<User> response = WebResponse.<User>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully update user detail")
                .data(updated)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully delete user")
                .data("deleted")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

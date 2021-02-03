package net.frozor.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.frozor.restapi.dto.UserDto;
import net.frozor.restapi.dto.UserRequestDto;
import net.frozor.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> findUserById(@RequestBody(required = false) UserRequestDto requestBody) {
        try {
            UserDto user = userService.findUserById(requestBody);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>("NULL", HttpStatus.NOT_FOUND);
    }

}

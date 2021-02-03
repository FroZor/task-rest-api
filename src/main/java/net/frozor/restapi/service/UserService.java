package net.frozor.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.frozor.restapi.controller.UserController;
import net.frozor.restapi.dto.UserDto;
import net.frozor.restapi.dto.UserRequestDto;
import net.frozor.restapi.encrypt.Aes256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final Map<Long, UserDto> repository = Map.of(1L, new UserDto("Test Testov"));

    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserDto findUserById(UserRequestDto userDto) throws JsonProcessingException {
        String requestBody = (userDto != null) ? objectMapper.writeValueAsString(userDto) : "{}";
        logger.info("Request: {}", requestBody);

        if (userDto != null) {
            String encryptRequest = Aes256.encrypt(requestBody);
            String decryptRequest = Aes256.decrypt(encryptRequest);

            logger.info("=== encryption: {}", encryptRequest);
            logger.info("=== decryption: {}", decryptRequest);

            if (repository.containsKey(userDto.id)) {
                UserDto user = repository.get(userDto.id);
                String responseBody = objectMapper.writeValueAsString(user);

                String encryptResponse = Aes256.encrypt(responseBody);
                String decryptResponse = Aes256.decrypt(encryptResponse);

                logger.info("Response: {}", responseBody);
                logger.info("=== encryption: {}", encryptResponse);
                logger.info("=== decryption: {}", decryptResponse);

                return user;
            }
        }

        logger.info("Response: {}", "NULL");
        return null;
    }
}

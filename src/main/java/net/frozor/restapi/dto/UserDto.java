package net.frozor.restapi.dto;

public class UserDto {

    public String fio;

    public UserDto(String fullUserName) {
        this.fio = fullUserName;
    }

}

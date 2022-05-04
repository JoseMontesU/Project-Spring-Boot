package com.example.tiendabackend.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String user;
    private String email;
    private String password;
    private Boolean active;
}

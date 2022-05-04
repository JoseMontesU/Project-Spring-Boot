package com.example.tiendabackend.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.tiendabackend.dto.UserDto;
import com.example.tiendabackend.entity.User;
import com.example.tiendabackend.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto addUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        return mapToDto(userRepository.addUser(user));
    }

    public List<UserDto> getUsers() {
        return userRepository.getUsers().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Boolean session(String userName, String password) {
        return userRepository.session(userName, password);
    }

    private UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User mapToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}

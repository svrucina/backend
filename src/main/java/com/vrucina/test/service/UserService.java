package com.vrucina.test.service;

import com.vrucina.test.model.command.UserRegisterCommand;
import com.vrucina.test.model.modelDTO.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findUserByUsername(String username);
    Optional<UserDTO> createUser(UserRegisterCommand command);
}

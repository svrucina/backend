package com.vrucina.test.service;

import com.vrucina.test.config.SecurityConfiguration;
import com.vrucina.test.controller.exceptions.UsernameAlreadyExistsException;
import com.vrucina.test.model.Authority;
import com.vrucina.test.model.User;
import com.vrucina.test.model.command.UserRegisterCommand;
import com.vrucina.test.model.modelDTO.UserDTO;
import com.vrucina.test.repository.AuthorityRepository;
import com.vrucina.test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private static final Logger logger = LoggerFactory.getLogger("LOGGER");

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Optional<UserDTO> findUserByUsername(String username) {
        logger.info("Loading user by username ['{}']", username);
        return userRepository.findOneByUsername(username).flatMap(UserServiceImpl::mapUserToUserDTO);
    }

    @Override
    public Optional<UserDTO> createUser(UserRegisterCommand command) {
        Optional<User> user = userRepository.findOneByUsername(command.getUsername());
        if(user.isEmpty()){

            logger.info("Creating user ['{}']", command.getUsername());

            PasswordEncoder encoder = SecurityConfiguration.passwordEncoder();
            String password = encoder.encode(command.getPassword());

            Set<Authority> authorities = new HashSet<>();
            Optional<Authority> auth = authorityRepository.findById(2);
            auth.ifPresent(authorities::add);

            User newUser = new User(command.getUsername(), password, command.getFirst_name(), command.getLast_name(), authorities);
            return mapUserToUserDTO(userRepository.save(newUser));
        }
        throw new UsernameAlreadyExistsException(command.getUsername());
    }


    public static Optional<UserDTO> mapUserToUserDTO(User user){
        Set<Authority> authorities = new HashSet<>(user.getAuthorities());
            return Optional.of(
                    new UserDTO(user.getId(), user.getUsername(),
                            user.getFirst_name(), user.getLast_name(), authorities));
    }

}
package com.SecurityApp.SecurityApplication.services;

import com.SecurityApp.SecurityApplication.dto.SignupDTO;
import com.SecurityApp.SecurityApplication.dto.UserDTO;
import com.SecurityApp.SecurityApplication.entities.User;
import com.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.SecurityApp.SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new BadCredentialsException("User with email: " + username + "not found"));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User not found with id: " + userId
        ));
    }

    public UserDTO signUp(SignupDTO signupDTO) {
        Optional<User> user = userRepository.findByEmail(signupDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with the given email is already present "
                    + signupDTO.getEmail());
        }

        User toBeCreated = modelMapper.map(signupDTO, User.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));
        User savedUser = userRepository.save(toBeCreated);

        return modelMapper.map(savedUser, UserDTO.class);
    }

}

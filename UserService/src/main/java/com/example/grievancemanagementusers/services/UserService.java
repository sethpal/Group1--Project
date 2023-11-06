package com.example.grievancemanagementusers.services;

import com.example.grievancemanagementusers.dtos.UserDto;
import com.example.grievancemanagementusers.dtos.SignupRequestDto;
import com.example.grievancemanagementusers.dtos.UserUpdateRequestDto;
import com.example.grievancemanagementusers.models.Role;
import com.example.grievancemanagementusers.models.User;
import com.example.grievancemanagementusers.repositories.RoleRepository;
import com.example.grievancemanagementusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public User getUserById(String id){
        Optional<User> user = userRepository.findById(UUID.fromString(id));

        if(!user.isPresent()){
            throw new RuntimeException("User not found");
        }

        return user.get();
    }

    public UserDto createUser(SignupRequestDto signupRequestDto){

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(signupRequestDto.getPassword());

        signupRequestDto.getRoles().forEach(stringRole -> {
            Role role = new Role();
            role.setName(stringRole);

            roleRepository.save(role);
            user.getRoles().add(role);
        });

        userRepository.save(user);

        return UserDto.from(user);
    }

    public UserDto updateUser(String id, UserUpdateRequestDto userUpdateRequestDto){
       User user = getUserById(id);

       if(userUpdateRequestDto.getName() != null)
           user.setName(userUpdateRequestDto.getName());

       if(userUpdateRequestDto.getEmail() != null)
           user.setEmail(userUpdateRequestDto.getEmail());

       if(userUpdateRequestDto.getPhone() != null)
           user.setPhone(userUpdateRequestDto.getPhone());

       if(userUpdateRequestDto.getRole() != null) {
           Role role = new Role();
           role.setName(userUpdateRequestDto.getRole());
           roleRepository.save(role);
           user.getRoles().add(role);
       }

       userRepository.save(user);

       return UserDto.from(user);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }
}

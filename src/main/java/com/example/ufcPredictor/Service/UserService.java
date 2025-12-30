package com.example.ufcPredictor.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ufcPredictor.Model.User;
import com.example.ufcPredictor.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUser(User user) throws Exception{
        if(userRepository.existsByUsername(user.getUsername())){
            throw new Exception("Username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        //Creating a user object and using the repository to find the userId. If not found throw an exception
        User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

        //If userId is valid set new username and password and save the new user
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword((user.getPassword()));
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
       //Checking if the user Id exists
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        
        //Deleting user by Id
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User Not found"));
        return user;
    }

    public List<User> getAllUsers(){
        List<User> user = userRepository.findAll();
        return user;
    }

}

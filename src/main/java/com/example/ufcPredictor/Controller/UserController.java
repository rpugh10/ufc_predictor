package com.example.ufcPredictor.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ufcPredictor.DTO.LoginDTO;
import com.example.ufcPredictor.DTO.UserDTO;
import com.example.ufcPredictor.Model.User;
import com.example.ufcPredictor.Service.UserService;


@RestController
@RequestMapping("/users") //Class mapping
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Using path variable the id comes from the URL and using request body saves what the user inputted
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User info){
        User user = userService.updateUser(id, info);
        return ResponseEntity.ok(user);
    }

    //? means "any type". In the context of ResponseEntity, it means the body can hold any type of object
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO)throws Exception{
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            return ResponseEntity
                    .badRequest()
                    .body("Passwords do not match");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getConfirmPassword());

        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto){
        userService.login(loginDto);
        return ResponseEntity.ok("Login successful");
    }   
    
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    

}

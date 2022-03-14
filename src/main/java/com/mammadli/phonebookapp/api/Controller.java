package com.mammadli.phonebookapp.api;

import com.mammadli.phonebookapp.db.entities.User;
import com.mammadli.phonebookapp.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class Controller {
    private final UserServices userServices;

    @PostMapping("/user")
    public User create(@RequestBody User user){
       return userServices.create(user);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user){
        return userServices.update(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId){
         userServices.delete(userId);
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userServices.getAll();
    }

   @GetMapping("/{userId}")
    public User get(@PathVariable String userId){
        return userServices.get(userId);
   }
}

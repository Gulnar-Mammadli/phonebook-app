package com.mammadli.phonebookapp.api;

import com.mammadli.phonebookapp.db.entities.User;
import com.mammadli.phonebookapp.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/api")
public class Controller {
    private final UserServices userServices;

    @PostMapping
    public User create(@RequestBody User user){
       return userServices.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return userServices.update(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId){
         userServices.delete(userId);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userServices.getAll();
    }


}

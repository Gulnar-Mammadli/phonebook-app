package com.mammadli.phonebookapp.api;

import com.mammadli.phonebookapp.db.entities.Users;
import com.mammadli.phonebookapp.model.ResponseData;
import com.mammadli.phonebookapp.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class Controller {
    private final UserServices userServices;

    @PostMapping("/add")
    public ResponseEntity<ResponseData<Users>> create(@RequestBody Users user){
       return ResponseEntity.ok(userServices.create(user));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseData<Users>> update(@RequestBody Users user){
        return ResponseEntity.ok(userServices.update(user));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseData<String>> delete(@PathVariable String userId){
        return  ResponseEntity.ok(userServices.delete(userId));
    }
//TODO
    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<Users>>> getAllUsers(){
        return ResponseEntity.ok(userServices.getAll());
    }


}

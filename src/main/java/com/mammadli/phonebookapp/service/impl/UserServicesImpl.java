package com.mammadli.phonebookapp.service.impl;

import com.mammadli.phonebookapp.db.entities.Users;
import com.mammadli.phonebookapp.db.repo.UserRepo;
import com.mammadli.phonebookapp.model.ResponseData;
import com.mammadli.phonebookapp.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepo userRepo;


    @Override
    public ResponseData<Users> create(Users user) {
        Users newUser = userRepo.save(user);
        return ResponseData.<Users>builder()
                .code(201)
                .message("created")
                .body(newUser)
                .build();

    }

    @Override
    public ResponseData<Users> update(Users user) {
        Users findUser = userRepo.findByUserIdAndDeletedIsFalse(user.getUserId());
    if(findUser!=null){
        Users updatedUser = userRepo.save(user);
        return ResponseData.<Users>builder()
                .code(200)
                .message("success")
                .body(updatedUser)
                .build();
    }
    return ResponseData.<Users>builder()
            .code(404)
            .message("not_found")
            .body(null)
            .build();
    }

    @Override
    public ResponseData<String> delete(String userId){
        Users user = userRepo.findByUserIdAndDeletedIsFalse(userId);
        if(user!=null){
        user.setDeleted(true);
        userRepo.save(user);
        return ResponseData.<String>builder()
                .code(200)
                .message("success")
                .body("delete success")
                .build();

        }
        return ResponseData.<String >builder()
                .code(404)
                .message("not_found")
                .body("delete failed")
                .build();
    }

//    public ResponseData<Void> delete(String userId) {
//        Users activeUser = userRepo.findByUserIdAndDeletedIsFalse(userId);
//        if(activeUser==null){
//            return ResponseData.<Void>builder()
//                    .code(404)
//                    .message("not_found")
//                    .body(null)
//                    .build();
//        }
//        activeUser.setDeleted(true);
//        userRepo.save(activeUser);
//        return ResponseData.<Users>builder()
//                .code(200)
//                .message("success")
//                .body()
//                .build();
//    }

    @Override
    public ResponseData<List<Users>> getAll() {
        List<Users>  listOfUsers= userRepo.findAllByDeletedIsFalse();
        if(listOfUsers.isEmpty()){
            return ResponseData.<List<Users>>builder()
                    .code(404)
                    .message("not_found")
                    .body(null)
                    .build();

        }
       return ResponseData.<List<Users>>builder()
                .code(200)
                .message("success")
                .body(listOfUsers)
                .build();
    }
}

package com.mammadli.phonebookapp.service;

import com.mammadli.phonebookapp.db.entities.Users;
import com.mammadli.phonebookapp.model.ResponseData;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServices {
    ResponseData<Users> create(Users user);
    ResponseData<Users> update(Users user);
    ResponseData<String> delete(String userId);
    ResponseData<List<Users>> getByUserId();
}

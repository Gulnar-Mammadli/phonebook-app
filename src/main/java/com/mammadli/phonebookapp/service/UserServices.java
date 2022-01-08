package com.mammadli.phonebookapp.service;

import com.mammadli.phonebookapp.db.entities.User;

import java.util.List;

public interface UserServices {
   User create(User user);
  User update(User user);
  void delete(String userId);
   List<User> getAll();
}

package com.mammadli.phonebookapp.service.impl;

import com.mammadli.phonebookapp.db.entities.User;
import com.mammadli.phonebookapp.db.repo.UserRepo;
import com.mammadli.phonebookapp.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepo userRepo;


    @Override
    public User create(User user) {
        User newUser = userRepo.save(user);
        return newUser;
    }

    @Override
    public User update(User user) {
        User findUser = userRepo.findByUserIdAndDeletedIsFalse(user.getUserId());
    if(findUser!=null){
        User updatedUser = userRepo.save(user);
        return updatedUser;
    }
    return null;
    }

    @Override
    public void delete(String userId){
        User user = userRepo.findByUserIdAndDeletedIsFalse(userId);
        if(user!=null){
        user.setDeleted(true);
            User save = userRepo.save(user);
        }
    }


    @Override
    public List<User> getAll() {
        List<User>  listOfUsers= userRepo.findAllByDeletedIsFalse();
        if(listOfUsers.isEmpty()){
            return null;

        }
       return listOfUsers;
    }
}

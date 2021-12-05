package com.mammadli.phonebookapp.db.repo;

import com.mammadli.phonebookapp.db.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,String> {
    List<User> findAllBy();
    User findByUserIdAndDeletedIsFalse(String userId);
    List<User> findAllByDeletedIsFalse();


}

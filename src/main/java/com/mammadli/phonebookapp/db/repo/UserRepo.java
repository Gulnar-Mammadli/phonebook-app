package com.mammadli.phonebookapp.db.repo;

import com.mammadli.phonebookapp.db.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<Users,String> {
    List<Users> findAllBy();
    Users findByUserIdAndDeletedIsFalse(String userId);
    List<Users> findAllByDeletedIsFalse();
}

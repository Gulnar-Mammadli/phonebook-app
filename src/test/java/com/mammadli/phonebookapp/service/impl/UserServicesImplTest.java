package com.mammadli.phonebookapp.service.impl;

import com.mammadli.phonebookapp.db.entities.User;
import com.mammadli.phonebookapp.db.repo.UserRepo;
import com.mammadli.phonebookapp.service.impl.UserServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServicesImpl.class})
@ExtendWith(SpringExtension.class)
class UserServicesImplTest {

    private static final String  ID = "1";
    private static final int SIZE = 1;
    private static final String NAME = "Gulnar Mammadli";
    private static final String PHONE = "0505354746";

    @MockBean
    private UserRepo userRepository;

    @Autowired
    private UserServicesImpl userServiceImpl;

    private User user;

    @BeforeEach
    void setUp() {
        setUpUser();
    }

    @Test
    void getUsersThenSuccessResult() {
        // arrange
        when(userRepository.findAllByDeletedIsFalse()).thenReturn(Collections.singletonList(user));

        // act
        List<User> actualUsers = userServiceImpl.getAll();

        // assert
        assertThat(actualUsers.size()).isSameAs(SIZE);
        User getResult = actualUsers.get(0);
        assertThat(getResult.getName()).isSameAs(NAME);
        assertThat(getResult.getUserId()).isSameAs(ID);
        assertThat(getResult.getPhone()).isSameAs(PHONE);
        verify(userRepository).findAllByDeletedIsFalse();

    }
    @Test
    void getUsersThenSuccessResult2() {
        // arrange
        when(userRepository.findAllByDeletedIsFalse()).thenReturn(Collections.emptyList());

        // act
        List<User> actualUsers = userServiceImpl.getAll();

        // assert
        assertThat(actualUsers).isEmpty();
        verify(userRepository).findAllByDeletedIsFalse();
    }


        @Test
    void addUserThenSuccessResult() {
        // arrange
        when(userRepository.save(any())).thenReturn(user);

        // act
        User actualAddUserResult = userServiceImpl.create(user);

        // assert
            assertThat(actualAddUserResult.getName()).isSameAs(NAME);
        assertThat(actualAddUserResult.getUserId()).isSameAs(ID);
        assertThat(actualAddUserResult.getPhone()).isSameAs(PHONE);
        verify(userRepository).save(any());
    }


    @Test
    void updateUserThenSuccessResult() {
        // arrange
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(user);

        // act
        User actualUpdateUserResult = userServiceImpl.update(user);

        // assert
        assertThat(actualUpdateUserResult.getUserId()).isSameAs(ID);
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
        verify(userRepository).save(any());

    }

    @Test
    void updateUserThenFailResult() {
        // arrange
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(null);

        // act
        User actualUpdateUserResult = userServiceImpl.update(user);

        // assert
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
        assertNull(actualUpdateUserResult);
    }

    @Test
    void deleteUserThenSuccessResult() {
        // arrange
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(user);

        // act
          userServiceImpl.delete(ID);

        // assert
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
        assertThat(userRepository.findAllByDeletedIsFalse()).isEmpty();
    }

    @Test
    void deleteUserThenSuccessResult2() {
        // arrange
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(null);

        // act
        userServiceImpl.delete(ID);

        // assert
        verify(userRepository).findByUserIdAndDeletedIsFalse(any());
    }

    private void setUpUser() {
        user = User.builder()
                .userId(ID)
                .name(NAME)
                .phone(PHONE)
                .build();
    }

    @Test
    void testGet() {
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(user);
        User userResponse = userServiceImpl.get(ID);
        assertThat(userResponse.getName()).isSameAs(NAME);
        assertThat(userResponse.getUserId()).isSameAs(ID);
        assertThat(userResponse.getPhone()).isSameAs(PHONE);
        verify(userRepository).findByUserIdAndDeletedIsFalse(ID);
    }


    @Test
    void getUserThenNullResult() {
        // arrange
        when(userRepository.findByUserIdAndDeletedIsFalse(any())).thenReturn(null);

        // act
        User userResponse = userServiceImpl.get(ID);

        // assert
        assertThat(userResponse).isSameAs(null);
        verify(userRepository).findByUserIdAndDeletedIsFalse(ID);
    }

}

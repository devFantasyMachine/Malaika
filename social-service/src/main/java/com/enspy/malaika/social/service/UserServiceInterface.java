package com.enspy.malaika.social.service;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import com.enspy.malaika.social.entities.actor.UserType;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {


    List<User> getAll();
    List<User> getAllByUserTypeAndUserNameLike(UserType userType, String name);
    List<User> getAllByUserTypeAndUserNameContaining (UserType userType,String name);
    List<User> getAllByUserCreatedAtAfter(LocalDateTime time);
    List<User> getAllByUserCreatedAtBefore(LocalDateTime time);
    List<User> getAllByUserTypeAndUserLastViewBefore(UserType userType,LocalDateTime time);

    List<User> getAllByUserTypeAndUserCountry(UserType userType,Country country);
    List<User> getAllByUserCountry(Country country);

    User getByUserLoginAndUserPassword(String userLogin, String userPass);

    User updateUser(User user);

    List<User> getByUserNameAndUserType(String userName, UserType userType);
}

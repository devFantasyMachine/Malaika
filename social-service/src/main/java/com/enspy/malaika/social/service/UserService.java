package com.enspy.malaika.social.service;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import com.enspy.malaika.social.entities.actor.UserType;
import com.enspy.malaika.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements UserServiceInterface{


    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public List<User> getAllByUserTypeAndUserNameLike(UserType userType, String name) {

        return userRepository.findAllByUserTypeAndUserNameLike(userType, name).get();
    }

    @Override
    public List<User> getAllByUserTypeAndUserNameContaining(UserType userType, String name) {

        return userRepository.findAllByUserTypeAndUserNameContaining(userType,name).get();
    }

    @Override
    public List<User> getAllByUserCreatedAtAfter(LocalDateTime time) {

        return userRepository.findAllByUserCreatedAtAfter(time).get();
    }

    @Override
    public List<User> getAllByUserCreatedAtBefore(LocalDateTime time) {

        return userRepository.findAllByUserCreatedAtBefore(time).get();
    }

    @Override
    public List<User> getAllByUserTypeAndUserLastViewBefore(UserType userType, LocalDateTime time) {

        return userRepository.findAllByUserTypeAndUserLastViewBefore(userType,time).get();
    }

    @Override
    public List<User> getAllByUserTypeAndUserCountry(UserType userType, Country country) {

        return userRepository.findAllByUserTypeAndUserCountry(userType, country).get();
    }

    @Override
    public List<User> getAllByUserCountry(Country country) {

        return userRepository.findAllByUserCountry(country).get();
    }

    @Override
    public User getByUserLoginAndUserPassword(String userLogin, String userPass) {

        return userRepository.findByUserLoginAndUserPassword(userLogin, userPass).get();
    }

    @Override
    public User updateUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getByUserNameAndUserType(String userName, UserType userType) {

        return userRepository.findAllByUserNameLikeAndUserType(userName, userType).get();
    }
}

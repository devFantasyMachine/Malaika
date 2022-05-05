package com.enspy.malaika.social.controller;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import com.enspy.malaika.social.entities.actor.UserType;
import com.enspy.malaika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "", produces = "application/json")
    public List<User> getAllUsers(){

        return userService.getAll();
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public List<User> getAllByUserTypeAndUserNameLike(
            @RequestParam("userType") UserType userType, @PathVariable("name") String name) {

        return userService.getAllByUserTypeAndUserNameLike(userType, name);
    }

    @GetMapping(value = "/contain/{name}", produces = "application/json")
    public List<User> getAllByUserTypeAndUserNameContaining(
            @RequestParam("userType") UserType userType, @PathVariable("name") String name ) {

        return userService.getAllByUserTypeAndUserNameContaining(userType,name);
    }

    @GetMapping(value = "/after-date", produces = "application/json")
    public List<User> getAllByUserCreatedAtAfter(@RequestParam(value = "after") LocalDateTime time) {

        return userService.getAllByUserCreatedAtAfter(time);
    }

    @GetMapping(value = "/before-date", produces = "application/json")
    public List<User> getAllByUserCreatedAtBefore(@RequestParam(value = "before")LocalDateTime time) {

      return userService.getAllByUserCreatedAtBefore(time);
    }

    @GetMapping(value = "/{userName}", produces = "application/json")
    public List<User> getByUserNameAndUserType(
            @PathVariable("userName")String userName, @RequestParam("userType") UserType userType) {

        return userService.getByUserNameAndUserType(userName, userType);
    }

    @GetMapping(value = "/last-view", produces = "application/json")
    public List<User> getAllByUserTypeAndUserLastViewBefore(
            @RequestParam(value = "userType")UserType userType, @RequestParam(value = "time") LocalDateTime time) {

        return userService.getAllByUserTypeAndUserLastViewBefore(userType,time);
    }

    @GetMapping(value = "/usertype-country", produces = "application/json")
    public List<User> getAllByUserTypeAndUserCountry(
            @RequestParam(value = "userType")UserType userType, @RequestParam(value = "country") Country country) {

        return userService.getAllByUserTypeAndUserCountry(userType, country);
    }

    @GetMapping(value = "/user-country", produces = "application/json")
    public List<User> getAllByUserCountry(@RequestParam("country") Country country) {

        return userService.getAllByUserCountry(country);
    }

    @GetMapping(value = "/{userLogin}/{userPass}", produces = "application/json")
    public User getByUserLoginAndUserPassword(String userLogin, String userPass) {

        return userService.getByUserLoginAndUserPassword(userLogin, userPass);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json" )
    public User updateUser(@RequestBody  User user) {

        return userService.updateUser(user);
    }

}

package com.enspy.malaika.social.repository;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<List<User>> findAllByCountry(Country country);


}

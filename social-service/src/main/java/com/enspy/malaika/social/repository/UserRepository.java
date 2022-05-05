package com.enspy.malaika.social.repository;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import com.enspy.malaika.social.entities.actor.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<List<User>> findAllByUserTypeAndUserNameLike(UserType userType, String name);
    Optional<List<User>> findAllByUserTypeAndUserNameContaining (UserType userType,@Param("name") String name);
    Optional<List<User>> findAllByUserCreatedAtAfter(LocalDateTime time);
    Optional<List<User>> findAllByUserCreatedAtBefore(LocalDateTime time);
    Optional<List<User>> findAllByUserTypeAndUserLastViewBefore(UserType userType,LocalDateTime time);

    Optional<List<User>> findAllByUserTypeAndUserCountry(UserType userType,Country country);
    Optional<List<User>> findAllByUserCountry(Country country);

    Optional<User> findByUserLoginAndUserPassword(String userLogin, String userPass);


    Optional<List<User>> findAllByUserNameLikeAndUserType(String userName, UserType userType);
}

package com.enspy.malaika.social.repository;


import com.enspy.malaika.social.entities.actor.Auditor;
import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, String> {

    Optional<List<Auditor>> findAllByUserNameLike(String name);
    Optional<List<Auditor>> findAllByUserNameContaining (@Param("name") String name);


}

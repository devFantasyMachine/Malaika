package com.enspy.malaika.social.repository;


import com.enspy.malaika.social.entities.communication.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, String> {
}

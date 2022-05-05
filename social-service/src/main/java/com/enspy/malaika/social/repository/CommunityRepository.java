package com.enspy.malaika.social.repository;


import com.enspy.malaika.social.entities.communication.AuditorCommunity;
import com.enspy.malaika.social.entities.communication.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<UserCommunity, String> {
}

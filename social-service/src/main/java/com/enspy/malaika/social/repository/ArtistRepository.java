package com.enspy.malaika.social.repository;


import com.enspy.malaika.social.entities.actor.Artist;
import com.enspy.malaika.social.entities.actor.ArtistType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {

    Optional<List<Artist>> findAllByArtistTypeAndUserNameLike(ArtistType artistType, String name);
    Optional<List<Artist>> findAllByArtistTypeAndUserNameContaining (ArtistType artistType, @Param("name") String name);

}

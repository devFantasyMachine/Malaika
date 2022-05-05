package com.enspy.malaika.social.repository;



import com.enspy.malaika.social.entities.actor.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findByCountryName(String name);

    boolean existsByCountryName(String name);

    void deleteByCountryName(String name);
}

package com.enspy.malaika.social.service;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;


    public Country getCountryById(int id){
        return  countryRepository.getById(id);
    }

    public Country getCountryByName(String name){

        return  countryRepository.findByCountryName(name);
    }

    public List<Country> getAll(){
        return  countryRepository.findAll();
    }

    public Boolean deleteCountryById(Integer id){

        if(countryRepository.existsById(id)){

            countryRepository.deleteById(id);
            return  Boolean.TRUE;
        }

        return  Boolean.FALSE;

    }

    public Boolean deleteCountryByName(String name){

        if(countryRepository.existsByCountryName(name)){

            countryRepository.deleteByCountryName(name);
            return  Boolean.TRUE;
        }

        return  Boolean.FALSE;

    }

    public Country updateCountry(Country country) throws Exception {

        if(country != null && country.getCountryId() != 0 && countryRepository.existsById(country.getCountryId())){

            return countryRepository.save(country);
        }

        throw new Exception();

    }

    public Country createCountry(Country country){

        return countryRepository.save(country);
    }
}

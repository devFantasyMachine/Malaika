package com.enspy.malaika.social.controller;


import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.repository.CountryRepository;
import com.enspy.malaika.social.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping(value = "/by-id/{id}", produces = "application/json")
    public Country getCountryById(@PathVariable("id")int id){
        return  countryService.getCountryById(id);
    }

    @GetMapping(value = "/by-name/{name}", produces = "application/json")
    public Country getCountryByName(@PathVariable("name") String name){

        return  countryService.getCountryByName(name);
    }

    @GetMapping(value = "",  produces = "application/json")
    public List<Country> getAll(){
        return  countryService.getAll();
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    public Boolean deleteCountryById(@PathVariable("id")Integer id){

        return  countryService.deleteCountryById(id);

    }

    @DeleteMapping(value = "/delete-by-name/{name}")
    public Boolean deleteCountryByName(@PathVariable("name")String name){

        return  countryService.deleteCountryByName(name);

    }

    @PutMapping(value = "", consumes = "application/json", produces = "application/json")
    public Country updateCountry(@RequestBody Country country) throws Exception {

        return countryService.updateCountry(country);

    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Country createCountry(@RequestBody Country country){

        return countryService.createCountry(country);
    }




}

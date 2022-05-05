package com.enspy.malaika.social.controller;


import com.enspy.malaika.social.entities.actor.Auditor;
import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import com.enspy.malaika.social.repository.AuditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auditors")
public class AuditorController {


    @Autowired
    private AuditorRepository auditorRepository;

    @GetMapping(value = "",  produces = "application/json")
    public List<Auditor> getAll(){
        return  auditorRepository.findAll(Sort.by("USER_CREATED_AT").ascending() );
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCountryById(@PathVariable("id")String id){

        auditorRepository.deleteById(id);

    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Auditor createAuditor(@RequestBody Auditor auditor){

        return auditorRepository.save(auditor);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json" )
    public Auditor updateAuditor(@RequestBody Auditor user) {

        if( user != null && user.getUserId() != "" && auditorRepository.existsById(user.getUserId())){

            return auditorRepository.save(user);

        }

        return  new Auditor();
    }



}

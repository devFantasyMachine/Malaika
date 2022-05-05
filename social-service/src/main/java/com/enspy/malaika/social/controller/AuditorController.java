package com.enspy.malaika.social.controller;


import com.enspy.malaika.social.entities.actor.Auditor;
import com.enspy.malaika.social.entities.actor.Country;
import com.enspy.malaika.social.entities.actor.User;
import com.enspy.malaika.social.entities.actor.UserType;
import com.enspy.malaika.social.entities.communication.AuditorCommunity;
import com.enspy.malaika.social.entities.communication.Discussion;
import com.enspy.malaika.social.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/auditors")
public class AuditorController {


    @Autowired
    private AuditorRepository auditorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private CommunityRepository  communityRepository ;




    @GetMapping(value = "",  produces = "application/json")
    public List<Auditor> getAll(){
        return  auditorRepository.findAll(Sort.by("userCreatedAt").ascending() );
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCountryById(@PathVariable("id")String id){

        auditorRepository.deleteById(id);

    }

    @PostMapping(value = "/{countryName}/add", consumes = "application/json", produces = "application/json")
    public Auditor createAuditor(@RequestBody Auditor auditor, @PathVariable("countryName") String countryName){
        auditor.setUserType(UserType.AUDITOR);
        auditor.setUserCreatedAt(LocalDateTime.now());
        auditor.setUserLastView(LocalDateTime.now());
        auditor.setUserCountry(countryRepository.findByCountryName(countryName));

        return auditorRepository.save(auditor);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json" )
    public Auditor updateAuditor(@RequestBody Auditor user) {

        if( user != null && user.getUserId() != "" && auditorRepository.existsById(user.getUserId())){

            return auditorRepository.save(user);

        }

        return  new Auditor();
    }

    @PutMapping(value = "/add-friend/{auditorId}", produces = "application/json", consumes = "application/json" )
    public Auditor addFriend(@RequestBody Auditor user, @PathVariable("auditorId") String auditorId) {

        if( user != null && user.getUserId() != "" && auditorRepository.existsById(user.getUserId()) && auditorRepository.existsById(auditorId)){

            Auditor aud = auditorRepository.getById(auditorId);
            aud.addFriend(user);
            return auditorRepository.save(aud);

        }

        return  new Auditor();
    }

    @PutMapping(value = "/add-community/{auditorId}", produces = "application/json", consumes = "application/json" )
    public Auditor addCommunity(@RequestBody AuditorCommunity community, @PathVariable("auditorId") String auditorId) {

        if( community != null && community.getIdCommunity() != ""
                && communityRepository.existsById(community.getIdCommunity()) && auditorRepository.existsById(auditorId)){

            Auditor aud = auditorRepository.getById(auditorId);
            aud.addCommunity(community);
            return auditorRepository.save(aud);

        }

        return  new Auditor();
    }

    @PutMapping(value = "/add-discussion/{auditorId}", produces = "application/json", consumes = "application/json" )
    public Auditor addDiscussion(@RequestBody Discussion discussion, @PathVariable("auditorId") String auditorId) {

        if( discussion != null && discussion.getIdDiscussion() != "" && discussionRepository.existsById(discussion.getIdDiscussion()) && auditorRepository.existsById(auditorId)){

            Auditor aud = auditorRepository.getById(auditorId);
            aud.addDiscussion(discussion);
            return auditorRepository.save(aud);

        }

        return  new Auditor();
    }




}

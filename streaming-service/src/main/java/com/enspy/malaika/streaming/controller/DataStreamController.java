package com.enspy.malaika.streaming.controller;


import com.enspy.malaika.streaming.service.FileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Controller
@RequestMapping("/stream/*")
public class DataStreamController {


    @Autowired
    FileEntityService fileEntityService;


    @GetMapping("/{id}")
    public Mono<ResponseEntity<byte[]>> streamData(@RequestHeader(value="Range", required = false) String httpRangeList,
                                                         @PathVariable("id") String id){

        try {

            return fileEntityService.getContent(id,httpRangeList);

        } catch (IOException e) {

            return Mono.empty();
        }
    }






}

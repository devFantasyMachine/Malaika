package com.enspy.malaika.streaming.handler;


import com.enspy.streaming.data.api.entities.FileEntity;
import com.enspy.streaming.data.api.service.FileEntityService;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
public class FileHandler {

    private static final Logger log = LoggerFactory.getLogger(FileEntityService.class);

    @Autowired
    FileEntityService fileEntityService;


    public Mono<ServerResponse> getFileHandler(ServerRequest serverRequest){

        String fileId = serverRequest.pathVariable("fileId");

        //log.info("FileHandler :: get file {}", fileId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.fileEntityService.getFile(fileId), FileEntity.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllFileHandler(ServerRequest serverRequest){

        //log.info("FileHandler :: get all file ");

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(this.fileEntityService.getAllFile(), FileEntity.class);
    }

    public Mono<ServerResponse> getALLFileByIdHandler(ServerRequest serverRequest) {

        Mono<String> lis = serverRequest.bodyToMono(String.class);
        System.out.println("ppppp" + lis.map(s -> { return s;}).toString());

       return null /*serverRequest.bodyToMono(JsonArray.class)
            .map(jsonArray -> {

                List<String> list = new ArrayList<String>();

                jsonArray.forEach( jsonElement -> {

                    list.add(jsonElement.getAsJsonObject().get("fileId").getAsString());
                    System.out.println(jsonElement.getAsJsonObject().get("fileId").getAsString());

                });
                return list;
            })
            .flatMap(listIds -> ServerResponse.ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(this.fileEntityService.getAllById(listIds), FileEntity.class))*/;

    }


    public Mono<ServerResponse> streamFileHandler(ServerRequest serverRequest) {

        String fileId = serverRequest.pathVariable("fileId");
        return ServerResponse.ok()
                .body(this.fileEntityService.getFileRegion(serverRequest.headers(), fileId), ResourceRegion.class)
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> deleteOneFile(ServerRequest serverRequest) {

        String fileId = serverRequest.pathVariable("fileId");
        return ServerResponse.ok()
                .body(this.fileEntityService.deleteFile(fileId), Boolean.class)
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> uploadOneFileHandle(ServerRequest serverRequest){

        return  serverRequest.multipartData()
                .map(MultiValueMap::toSingleValueMap)
                .flatMap(formData -> {
                    return ServerResponse.ok()
                            .body(this.fileEntityService.saveFile((FilePart) formData.get("file")),FileEntity.class)
                            .switchIfEmpty(ServerResponse.badRequest().build());
                } );

    }





    public Mono<ServerResponse> uploadMultiplesFiles(ServerRequest serverRequest){

        return  serverRequest.multipartData()
                .flatMap(formData -> {

                    formData.forEach( (name, value) -> {



                    } );

                    return this.fileEntityService.saveFile((FilePart) formData.get("file"));

                })
                .flatMap(fileEntity -> {

                    return ServerResponse.ok()
                            .body(fileEntity,FileEntity.class)
                            .switchIfEmpty(ServerResponse.badRequest().build());
                });

    }






    public Mono<ServerResponse> deleteMultipleFile(ServerRequest serverRequest) {

        return null;
    }



}

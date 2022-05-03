package com.enspy.malaika.streaming.repository;



import com.enspy.malaika.streaming.entities.FileEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface FileEntityRepository extends ReactiveMongoRepository<FileEntity, String> {

	   Flux<FileEntity> findByFileType(final String contentType);
	   Flux<FileEntity> findByFileSize(final Long fileSize);
	   Mono<Void>   deleteByFileId(final String id);
	   Flux<FileEntity>findAllByFileId(Iterable<String> list);
	   Mono<FileEntity> findByFileId(String id);
}   





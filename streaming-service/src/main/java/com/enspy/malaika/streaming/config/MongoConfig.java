package com.enspy.malaika.streaming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.enspy.streaming.data.api.repository.FileEntityRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = FileEntityRepository.class)
public class MongoConfig extends AbstractReactiveMongoConfiguration {

	  @Bean
	  public MongoClient mongoClient() {
	    return MongoClients.create();
	  }

	  @Override
	  protected String getDatabaseName() {
	    return "DataStream";
	  }

	  @Bean
	  public ReactiveMongoTemplate reactiveMongoTemplate() {
	    return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
	  }
}
package com.enspy.malaika.streaming.config;

import com.enspy.streaming.data.api.entities.FileEntity;
import com.enspy.streaming.data.api.handler.FileHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.*;



@Configuration
@Component
public class RouterConfig {

	@Autowired
	FileHandler fileHandler;

	@Bean
	@RouterOperations(
	{
		@RouterOperation(path = "/files/{fileId}", produces = {MediaType.APPLICATION_JSON_VALUE},
			method = RequestMethod.GET, beanClass = FileHandler.class, beanMethod = "getFileHandler",
			operation = @Operation(operationId = "getFileHandler", responses = {
				@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = FileEntity.class))),
				@ApiResponse(responseCode = "400", description = "Invalid FileEntity ID supplied"),
				@ApiResponse(responseCode = "404", description = "FileEntity not found")}, parameters = {
				@Parameter(in = ParameterIn.PATH, name = "fileId")})
		),
		@RouterOperation(path = "/files", produces = {MediaType.APPLICATION_JSON_VALUE},
			method = RequestMethod.GET, beanClass = FileHandler.class, beanMethod = "getAllFileHandler",
			operation = @Operation(operationId = "getAllFileHandler", responses = {
				@ApiResponse(responseCode = "200", description = "successful operation"),
			})),
/*		@RouterOperation(path = "/files/list", produces = {MediaType.APPLICATION_JSON_VALUE},
				method = RequestMethod.GET, beanClass = FileHandler.class, beanMethod = "getALLFileByIdHandler",
				operation = @Operation(operationId = "getALLFileByIdHandler", responses = {
						@ApiResponse(responseCode = "200", description = "successful operation"),
				}))*/
	})

	 public RouterFunction<ServerResponse> router() {

        return RouterFunctions.route()
			.GET("/files/{fileId}", fileHandler::getFileHandler)
			.GET("/files/list", fileHandler::getALLFileByIdHandler)
			.GET("/files", fileHandler::getAllFileHandler)
			.GET("/files/stream/{fileId}",fileHandler::streamFileHandler)
			.POST("/files/upload-one-file", RequestPredicates.accept(MediaType.MULTIPART_FORM_DATA), fileHandler::uploadOneFileHandle)
			.POST("/files/upload-multiples-files", RequestPredicates.accept(MediaType.MULTIPART_FORM_DATA), fileHandler::uploadMultiplesFiles)
			.DELETE("/files/delete-one/{fileId}", fileHandler::deleteOneFile)
			.DELETE("/files/delete-multiple", fileHandler::deleteMultipleFile)
			.build();
    }




}




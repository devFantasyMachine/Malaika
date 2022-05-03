/***********************************************************************
 * Module:  FileStorageService.java
 * Author:  FantasyMachine
 * Purpose: Defines the Service FileStorageService
 ***********************************************************************/
package com.enspy.malaika.streaming.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


import com.enspy.streaming.data.api.entities.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;


@Service
public class FileStorageService {


    public static final String FILES_FOLDER = "/static/files";  //System.getProperty("user.dir") + "/Uploads/Videos"; //"/static/audios"; //;

    private final Path root = Paths.get(FILES_FOLDER);
    private static final long CHUNK_SIZE = 1000000L;

    // function to delete all files
    public void deleteRoot() {

        FileSystemUtils.deleteRecursively(root.toFile());

        try {

            Files.deleteIfExists(root);

        } catch (IOException e) {

            throw new RuntimeException("Could not delete root directory!");
        }

    }

    // function to delete file
    public void delete(String filename) {

        try {

            FileSystemUtils.deleteRecursively(root.resolve(filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * init function
     */
    public void init() {

        if (!Files.exists(root)) {

            try {

                Files.createDirectories(root);

            } catch (IOException e) {

                throw new RuntimeException("Could not initialize folder for upload!");
            }
        }
    }

    // function to get size of one file by a path of this file
    public Long sizeFromFile(Path path) {

        try {

            return Files.size(path);

        } catch (IOException ex) {

            return 0L;
        }
    }

    // function to save file in the filesystem root
    public Mono<Path> save(FilePart file, String fileName) {

        Path filePath = this.root.resolve(fileName);
        try {

            return file.transferTo(Files.createFile(filePath)).thenReturn(filePath);

        } catch (IOException e) {

            return Mono.empty();

        }
    }


    public Resource load(String filename) {

        try {

            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {

                return resource;

            } else {

                throw new RuntimeException("Could not read the file!");
            }

        } catch (MalformedURLException e) {

            throw new RuntimeException("Error: " + e.getMessage());
        }

    }


    public void deleteAll() {

        FileSystemUtils.deleteRecursively(root.toFile());
    }


    public Stream<Path> loadAll() {

        try {

            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);

        } catch (IOException e) {

            throw new RuntimeException("Could not load the files!");
        }

    }


    public ResourceRegion getResourceRegion(UrlResource resource, ServerRequest.Headers headers) throws IOException {

        ResourceRegion resourceRegion = null;
        long contentLength = resource.contentLength();
        String range = headers.firstHeader("Range");

        long start = 0;
        long end = 0;
		
        if (!(range == null) && ! range.isEmpty() && !  range.isBlank() ) {

            String[] ranges = range.substring("bytes=".length()).split("-");

            start = Integer.valueOf(ranges[0]);

            if (ranges.length > 1) {

                end = Integer.valueOf(ranges[1]);

            } else {

                end = (int) (contentLength - 1);
            }
        }

        if (start > 0) {

            long rangeLength = Math.min(CHUNK_SIZE, end - start + 1);
            resourceRegion = new ResourceRegion(resource, start, rangeLength);

        } else {

            long rangeLength = Math.min(CHUNK_SIZE, contentLength);
            resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }
        return resourceRegion;
    }

    public ResourceRegion getResourceRegion(UrlResource resource, String range) throws IOException {

        ResourceRegion resourceRegion = null;
        long contentLength = resource.contentLength();

        long start = 0;
        long end = 0;

         if (!(range == null) && ! range.isEmpty() && !  range.isBlank() ) {

            String[] ranges = range.substring("bytes=".length()).split("-");

            start = Integer.valueOf(ranges[0]);

            if (ranges.length > 1) {

                end = Integer.valueOf(ranges[1]);

            } else {

                end = (int) (contentLength - 1);
            }
        }

        if (start > 0) {

            long rangeLength = Math.min(CHUNK_SIZE, end - start + 1);
            resourceRegion = new ResourceRegion(resource, start, rangeLength);

        } else {

            long rangeLength = Math.min(CHUNK_SIZE, contentLength);
            resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }
        return resourceRegion;
    }


}




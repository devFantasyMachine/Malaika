package com.enspy.malaika.streaming.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.enspy.malaika.streaming.entities.FileEntity;
import com.enspy.malaika.streaming.repository.FileEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;






@Service
public class FileEntityService {
	

	@Autowired
    private FileStorageService fileStorageService;
	
	
	@Autowired
    private FileEntityRepository fileEntityRepository;



	public Mono<FileEntity> getFile(String fileId){

		return fileEntityRepository.findByFileId(fileId);
	}


	public Flux<FileEntity> getAllFile() {

		return fileEntityRepository.findAll();
	}


	public Flux<FileEntity> getAllById(List<String> ids) {

		return fileEntityRepository.findAllByFileId(ids);
	}


	public Mono<FileEntity> saveFile(FilePart file) {

		String _fileName = StringUtils.cleanPath(file.filename());
		String extension = _fileName.substring(_fileName.lastIndexOf("."));
		String fileId = UUID.randomUUID().toString();

		return fileStorageService.save(file, String.format("%s%s", fileId, extension))
				.flatMap(filePath -> {
					return fileEntityRepository.save(
							new FileEntity( fileId, file.headers().getContentType().toString(),
									filePath.toString(), fileStorageService.sizeFromFile(filePath) )
					);
				});
	}


	public Mono<Boolean> deleteFile(String fileId) {

		return  fileEntityRepository.deleteByFileId(fileId).thenReturn(true);

	}






	public Mono<ResourceRegion> getFileRegion(ServerRequest.Headers headers , String id) {

		return getFile(id)
			.flatMap(fileEntity -> {

				try {

					return Mono.just( fileStorageService.getResourceRegion(
							new FileUrlResource(fileEntity.getFilePath()), headers) );

				} catch (IOException e) {

					return Mono.empty();
				}
			});
	}

	public Mono<ResourceRegion> getFileRegion(String ranges , String id) {

		return getFile(id)
				.flatMap(fileEntity -> {

					try {

						return Mono.just( fileStorageService.getResourceRegion(
								new FileUrlResource(fileEntity.getFilePath()), ranges) );

					} catch (IOException e) {

						return Mono.empty();
					}
				});
	}


	public Mono<ResponseEntity<byte[]>> getContent(String id , String range) throws IOException  {

		return getFile(id).map(fileEntity -> {

			long rangeStart = 0;
			long rangeEnd;
			byte[] data;

			if (range == null) {

				return ResponseEntity.status(HttpStatus.OK)
						.header("Content-Type", fileEntity.getFileType())
						.header("Content-Length", String.valueOf(fileEntity.getFileSize()))
						.body(readByteRange(fileEntity.getFilePath(),rangeStart, fileEntity.getFileSize() - 1));
			}

			String[] ranges = range.split("-");
			rangeStart = Long.parseLong(ranges[0].substring(6));

			if (ranges.length > 1) {

				rangeEnd = Long.parseLong(ranges[1]);

			} else {

				rangeEnd = fileEntity.getFileSize() - 1;

			}if (fileEntity.getFileSize() < rangeEnd) {

				rangeEnd =  fileEntity.getFileSize() - 1;

			}

			data = readByteRange(fileEntity.getFilePath(), rangeStart, rangeEnd);

			String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.header("Content-Type",  fileEntity.getFileType())
					.header("Accept-Ranges", "bytes")
					.header("Content-Length", contentLength)
					.header("Content-Range", "bytes" + " " + rangeStart + "-" + rangeEnd + "/" + fileEntity.getFileSize())
					.body(data);

		});


	}

	private String getFilePath(String location) {

		URL url = this.getClass().getResource(location);
		return new File(url.getFile()).getAbsolutePath();

	}

	public byte[] readByteRange(String location, long start, long end)  {

		Path path = Paths.get(getFilePath(location));
		try (InputStream inputStream = (Files.newInputStream(path));
			 ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream()) {
			byte[] data = new byte[1000000];
			int nRead;
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {

				bufferedOutputStream.write(data, 0, nRead);

			}
			bufferedOutputStream.flush();
			byte[] result = new byte[(int) (end - start) + 1];
			System.arraycopy(bufferedOutputStream.toByteArray(), (int) start, result, 0, result.length);

			return result;

		} catch (IOException e) {

			return new byte[0];
		}

	}





}





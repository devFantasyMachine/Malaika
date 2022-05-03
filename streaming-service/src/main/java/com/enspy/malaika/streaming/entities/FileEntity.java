package com.enspy.malaika.streaming.entities;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "FILESTOSTREAM")
public class FileEntity {
	
	@Indexed
	private String fileId;
	
	private String fileType;
	
	private Long fileSize;

	private String filePath;

	@Override
	public String toString() {
		return "FileEntity{" +
				"fileId='" + fileId + '\'' +
				", fileType='" + fileType + '\'' +
				", fileSize=" + fileSize +
				", filePath='" + filePath + '\'' +
				'}';
	}

	public FileEntity() {
		super();
	}

	public FileEntity(String fileId, String contentType, String path, Long fileSize) {
		super();
		this.setFileId(fileId);
		this.fileType = contentType;
		this.fileSize = fileSize;
		this.filePath = path;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String contentType) {
		this.fileType = contentType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long size) {
		this.fileSize = size;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String path) {
		this.filePath = path;
	}
	
	

}













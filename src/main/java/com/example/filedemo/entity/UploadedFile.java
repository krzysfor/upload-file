package com.example.filedemo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UploadedFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private FileTypes type;
	@Column(nullable = false)
	private UUID fileUuid;
	@Column(nullable = false)
	private UUID originDatabaseUuid;
	@Column(nullable = false)
	private int originDatabaseId;
	private LocalDateTime dateOfUpload;
	@Column(length = 255)
	private String uploaderName;
	@Column(length = 255, nullable = false)
	private String fileName;
	
	public UploadedFile() {
		
	}
	
}

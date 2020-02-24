package com.example.filedemo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "uploaded_files")
//@NamedQuery(name="FileDetails.findByLinkedDocId", query = "SELECT f FROM FileDetails f WHERE f.originLinkedDocId = :linkedDocId")
public class FileDetails {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private int id;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID uuid;
	
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private FileTypes type;
		
	@Column(nullable = false)
	private UUID originDatabaseUuid;
	
	@Column(nullable = false)
	private int originLinkedDocId;
	
	private LocalDateTime dateOfUpload;
	
	@Column(length = 255)
	private String uploaderName;
	
	@Column(length = 255, nullable = false)
	private String fileName;
	
	@Column(nullable = false)
	private long fileSize;

	public FileDetails() {

	}

}

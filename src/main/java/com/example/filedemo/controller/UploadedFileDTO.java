package com.example.filedemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.example.filedemo.entity.FileDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Data;

@Data
public class UploadedFileDTO {
	private MultipartFile file;
	private String fileDetails;
	private static final Logger logger = LoggerFactory.getLogger(UploadedFileDTO.class);

	public FileDetails getFileDetails() {
		FileDetails file = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			file = objectMapper.readValue(fileDetails, FileDetails.class);
		} catch (JsonMappingException e) {
			logger.error("mapping error");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			logger.error("processing error");
			e.printStackTrace();
		}
		return file;
	}

}

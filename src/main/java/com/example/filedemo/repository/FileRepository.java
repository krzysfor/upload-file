package com.example.filedemo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.filedemo.entity.FileDetails;
import com.example.filedemo.entity.FileTypes;

public interface FileRepository extends JpaRepository<FileDetails, UUID>{

	@Query("SELECT f FROM FileDetails f WHERE f.type = :docType AND f.originLinkedDocId = :linkedDocId")
	public List<FileDetails> findByLinkedDocTypeAndId(@Param("docType") FileTypes type, @Param("linkedDocId") int linkedDocId);
	
}

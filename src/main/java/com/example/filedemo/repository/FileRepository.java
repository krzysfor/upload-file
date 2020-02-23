package com.example.filedemo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.filedemo.entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, UUID>{

	@Query("SELECT f FROM FileDetails f WHERE f.originLinkedDocId = :linkedDocId")
	public List<FileDetails> findByLinkedDocId(@Param("linkedDocId") int linkedDocId);
	
}

package com.example.filedemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.filedemo.entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, UUID>{

}

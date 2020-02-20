package com.example.filedemo.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.filedemo.entity.FileDetails;
import com.example.filedemo.exception.ResourceNotFoundException;
import com.example.filedemo.payload.UploadFileResponse;
import com.example.filedemo.repository.FileRepository;
import com.example.filedemo.service.FileStorageService;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/file/{uuid}")
    public ResponseEntity<FileDetails> getFileByUUID(@PathVariable(value = "uuid") UUID fileUUID) throws ResourceNotFoundException {
    	FileDetails file = fileRepository.findById(fileUUID)
    			.orElseThrow( () -> new ResourceNotFoundException("Nie znaleziono pliku o podanym UUID :: " + fileUUID.toString()));
    	return ResponseEntity.ok().body(file);
    }

    @PostMapping("/file")
    public UploadFileResponse uploadFile(@ModelAttribute UploadedFileDTO fileDTO) {
    	
    	String fileName = StringUtils.cleanPath(fileDTO.getFile().getOriginalFilename());
        fileName = Paths.get(fileName).getFileName().toString();
    	
    	FileDetails file = fileDTO.getFileDetails();
    	file.setFileName(fileName);
        file = fileRepository.save(file);
        logger.info(file.getUuid().toString());
    	
        fileName = fileStorageService.storeFile(fileDTO.getFile(), file.getUuid().toString());
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                fileDTO.getFile().getContentType(), fileDTO.getFile().getSize());
    }
    
//    @PostMapping("/file")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam File uploadedFile) {
//        String fileName = fileStorageService.storeFile(file);
//        fileName = Paths.get(fileName).getFileName().toString();
//        
//        fileRepository.save(uploadedFile);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }

	/*
	 * @PostMapping("/uploadMultipleFiles") public List<UploadFileResponse>
	 * uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { return
	 * Arrays.asList(files) .stream() .map(file -> uploadFile(file),"")
	 * .collect(Collectors.toList()); }
	 */

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}

package com.jpabooks.controller;

import com.jpabooks.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files" )
@RequiredArgsConstructor
public class FileUploadController {

    private final  FileUploadService fileUploadService;
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam Long id, @RequestParam String pathType, @RequestParam MultipartFile file) {
        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file),id,pathType);
        return ResponseEntity.ok(fileName);
    }
}

package com.StudentCrud.File.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/{id}")
    public ResponseEntity<String> createFile(@PathVariable String id, @RequestBody FileRecord fileRecord) {
        try {
            if (fileService.fileExists(id)) return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            fileService.saveFile(id, fileRecord.getContent());
            return new ResponseEntity<>("File created", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> readFile(@PathVariable String id) {
        try {
            if (!fileService.fileExists(id)) return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            String content = fileService.readFile(id);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFile(@PathVariable String id, @RequestBody FileRecord fileRecord) {
        try {
            if (!fileService.fileExists(id)) return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            fileService.updateFile(id, fileRecord.getContent());
            return new ResponseEntity<>("File updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable String id) {
        try {
            if (!fileService.fileExists(id)) return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            fileService.deleteFile(id);
            return new ResponseEntity<>("File deleted", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

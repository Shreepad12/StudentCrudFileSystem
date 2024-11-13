package com.StudentCrud.File.system;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private static final String DIRECTORY = "fileRecords/";

    public FileService() {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) dir.mkdir();
    }

    public void saveFile(String id, String content) throws IOException {
        Path path = Paths.get(DIRECTORY + id + ".txt");
        Files.writeString(path, content);
    }

    public String readFile(String id) throws IOException {
        Path path = Paths.get(DIRECTORY + id + ".txt");
        return Files.readString(path);
    }

    public void updateFile(String id, String content) throws IOException {
        saveFile(id, content);
    }

    public void deleteFile(String id) throws IOException {
        Path path = Paths.get(DIRECTORY + id + ".txt");
        Files.deleteIfExists(path);
    }

    public boolean fileExists(String id) {
        return Files.exists(Paths.get(DIRECTORY + id + ".txt"));
    }
}


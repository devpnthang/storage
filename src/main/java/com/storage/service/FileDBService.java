package com.storage.service;

import com.storage.entity.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public interface FileDBService {
    FileDB store(MultipartFile file);

    FileDB storeFile(MultipartFile file);

    Stream<FileDB> getAllFiles();
}

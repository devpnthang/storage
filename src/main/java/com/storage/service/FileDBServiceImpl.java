package com.storage.service;

import com.storage.entity.FileDB;
import com.storage.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileDBServiceImpl implements FileDBService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes());
            return fileDBRepository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}

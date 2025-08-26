package com.example.extension_guard.service;

import com.example.extension_guard.domain.FileExtensionBlock;
import com.example.extension_guard.domain.FileExtensionBlockDao;
import com.example.extension_guard.dto.FileExtensionBlockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileExtensionBlockService {

    private final FileExtensionBlockDao fileExtensionBlockDao;

    @Autowired
    public FileExtensionBlockService(FileExtensionBlockDao fileExtensionBlockDao) {
        this.fileExtensionBlockDao = fileExtensionBlockDao;
    }

    @Transactional(readOnly = true)
    public List<FileExtensionBlockDto> getAllExtensions() {
        List<FileExtensionBlock> allExtensions = fileExtensionBlockDao.findAll();

        return allExtensions.stream()
                .map(FileExtensionBlockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FileExtensionBlockDto> updateFixedExtensionStatus(String name, boolean isChecked) {
        fileExtensionBlockDao.updateFixedExtensionStatus(name, isChecked);
        return fileExtensionBlockDao.findFixedExtensions().stream()
                .map(FileExtensionBlockDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 커스텀 확장자를 저장하고, 저장 후 모든 커스텀 확장자 목록을 반환합니다.
    @Transactional
    public List<FileExtensionBlockDto> saveCustomExtension(String name) {
        FileExtensionBlock request = FileExtensionBlock.builder()
                .name(name)
                .isFixed(false)
                .isChecked(true)
                .createdAt(LocalDateTime.now())
                .build();

        fileExtensionBlockDao.save(request);
        return fileExtensionBlockDao.findCustomExtensions().stream()
                .map(FileExtensionBlockDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 커스텀 확장자를 삭제하고, 삭제 후 모든 커스텀 확장자 목록을 반환합니다.
    @Transactional
    public List<FileExtensionBlockDto> deleteCustomExtension(String name) {
        fileExtensionBlockDao.deleteByName(name);
        return fileExtensionBlockDao.findCustomExtensions().stream()
                .map(FileExtensionBlockDto::fromEntity)
                .collect(Collectors.toList());
    }
}
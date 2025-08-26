package com.example.extension_guard.domain;

import java.util.List;

public interface FileExtensionBlockDao {
    List<FileExtensionBlock> findAll();
    void updateFixedExtensionStatus(String name, boolean isChecked);
    List<FileExtensionBlock> findFixedExtensions();
    List<FileExtensionBlock> findCustomExtensions();
    void save(FileExtensionBlock request);
    void deleteByName(String name);
}

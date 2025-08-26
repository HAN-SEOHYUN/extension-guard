package com.example.extension_guard.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FileExtensionBlock {

    private final Long id;
    private final String name;
    private final boolean isFixed;
    private final boolean isChecked;
    private final LocalDateTime createdAt;

    @Builder
    public FileExtensionBlock(Long id, String name, boolean isFixed, boolean isChecked, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.isFixed = isFixed;
        this.isChecked = isChecked;
        this.createdAt = createdAt;
    }
}

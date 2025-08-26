package com.example.extension_guard.dto;

import com.example.extension_guard.domain.FileExtensionBlock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileExtensionBlockDto {
    private String name;
    private boolean isFixed;
    private boolean isChecked;

    @Builder
    public FileExtensionBlockDto(String name, boolean isFixed, boolean isChecked) {
        this.name = name;
        this.isFixed = isFixed;
        this.isChecked = isChecked;
    }

    public static FileExtensionBlockDto fromEntity(FileExtensionBlock entity) {
        return FileExtensionBlockDto.builder()
                .name(entity.getName())
                .isFixed(entity.isFixed())
                .isChecked(entity.isChecked())
                .build();
    }

    public FileExtensionBlock toEntity() {
        return FileExtensionBlock.builder()
                .name(this.name)
                .isFixed(this.isFixed)
                .isChecked(this.isChecked)
                .build();
    }
}

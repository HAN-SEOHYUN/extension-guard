package com.example.extension_guard;

import com.example.extension_guard.domain.FileExtensionBlock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {

    public List<FileExtensionBlock> createAllExtensions() {
        return Arrays.asList(
                FileExtensionBlock.builder().id(1L).name("bat").isFixed(true).isChecked(true).createdAt(LocalDateTime.now()).build(),
                FileExtensionBlock.builder().id(2L).name("exe").isFixed(true).isChecked(true).createdAt(LocalDateTime.now()).build(),
                FileExtensionBlock.builder().id(3L).name("cmd").isFixed(false).isChecked(true).createdAt(LocalDateTime.now()).build()
        );
    }
}

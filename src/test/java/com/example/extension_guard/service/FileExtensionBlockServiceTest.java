package com.example.extension_guard.service;

import com.example.extension_guard.TestDataGenerator;
import com.example.extension_guard.domain.FileExtensionBlock;
import com.example.extension_guard.domain.FileExtensionBlockDao;
import com.example.extension_guard.dto.FileExtensionBlockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileExtensionBlockServiceTest {

    @Mock
    private FileExtensionBlockDao fileExtensionBlockDao;

    @InjectMocks
    private FileExtensionBlockService fileExtensionBlockService;

    private TestDataGenerator testDataGenerator;

    @BeforeEach
    void setUp() {
        testDataGenerator = new TestDataGenerator();
    }

    @Test
    @DisplayName("모든 확장자를 조회한다")
    void testGetAllExtensions() {
        // given
        List<FileExtensionBlock> allExtensions = testDataGenerator.createAllExtensions();
        when(fileExtensionBlockDao.findAll()).thenReturn(allExtensions);

        // when
        List<FileExtensionBlockDto> result = fileExtensionBlockService.getAllExtensions();

        // then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("bat");
        assertThat(result.get(1).getName()).isEqualTo("exe");
        assertThat(result.get(2).getName()).isEqualTo("cmd");
        verify(fileExtensionBlockDao, times(1)).findAll();
    }
}

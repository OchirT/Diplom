package ru.netology.diplomproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplomproject.dto.FileResponse;
import ru.netology.diplomproject.model.AppUser;
import ru.netology.diplomproject.model.FileCloud;
import ru.netology.diplomproject.repository.FileRepository;
import ru.netology.diplomproject.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class FileCloudServiceTest {

    @MockBean
    private FileCloudService fileCloudService;

    @MockBean
    private FileRepository fileRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testUpload() {
        String token = "token";
        String name = "test.txt";
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test content".getBytes());

        AppUser user = new AppUser();
        user.setEmail("ivanov");
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        FileCloud fileCloud = new FileCloud();
        fileCloud.setFileName(name);
        fileCloud.setSize(file.getSize());
        fileCloud.setDate(LocalDateTime.now());
        fileCloud.setAppUser(user);
        when(fileRepository.save(any(FileCloud.class))).thenReturn(fileCloud);

        fileCloudService.upload(token, name, file);

        verify(fileRepository, times(1)).save(any(FileCloud.class));
    }

    @Test
    void testGetAllFiles() {
        String token = "token";
        Pageable pageable = PageRequest.of(0, 10);

        AppUser user = new AppUser();
        user.setEmail("ivanov");
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        Page<FileCloud> filePage = new PageImpl<>(Collections.emptyList());
        when(fileRepository.findAllByUserDataEmail(anyString(), any(Pageable.class))).thenReturn(filePage);

        Page<FileResponse> result = fileCloudService.getAllFiles(token, pageable);

        verify(fileRepository, times(1)).findAllByUserDataEmail(anyString(), any(Pageable.class));
    }

    @Test
    public void testFileDownload() {
        String name = "test.txt";
        String token = "token";

        FileCloud fileCloud = new FileCloud();
        fileCloud.setFileName(name);
        when(fileRepository.findByFileName(anyString())).thenReturn(fileCloud);

        Resource result = fileCloudService.fileDownload(name, token);

        verify(fileRepository, times(1)).findByFileName(anyString());
    }

    @Test
    public void testFileDelete() {
        String name = "test.txt";
        String token = "token";

        FileCloud fileCloud = new FileCloud();
        fileCloud.setFileName(name);
        when(fileRepository.findByFileName(anyString())).thenReturn(fileCloud);

        fileCloudService.fileDelete(name, token);

        verify(fileRepository, times(1)).findByFileName(anyString());
    }
}
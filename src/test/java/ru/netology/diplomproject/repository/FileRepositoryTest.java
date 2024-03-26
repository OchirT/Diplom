package ru.netology.diplomproject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.netology.diplomproject.model.FileCloud;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;

    @Test
    public void testFindAllByUserDataEmail_nullEmail() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<FileCloud> result = fileRepository.findAllByUserDataEmail(null, pageable);
        assertEquals(0, result.getTotalElements());
    }

    @Test
    public void testFindAllByUserDataEmail_nullPageable() {
        Page<FileCloud> result = fileRepository.findAllByUserDataEmail("test@example.com", null);
        assertEquals(0, result.getTotalElements());
    }

    @Test
    public void testFindAllByUserDataEmail_nonExistentEmail() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<FileCloud> result = fileRepository.findAllByUserDataEmail("nonExistent@example.com", pageable);
        assertEquals(0, result.getTotalElements());
    }

    @Test
    public void testFindAllByUserDataEmail_existentEmail() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<FileCloud> result = fileRepository.findAllByUserDataEmail("test@example.com", pageable);
        assertEquals(1, result.getTotalElements());
    }

}



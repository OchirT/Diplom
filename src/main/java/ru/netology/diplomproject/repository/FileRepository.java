package ru.netology.diplomproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.diplomproject.model.FileCloud;

import java.util.List;

public interface FileRepository extends JpaRepository<FileCloud, Long> {

    Page<FileCloud> findAllByUserDataEmail(String email, Pageable pageable);

    FileCloud findByFileName(String fileName);
}

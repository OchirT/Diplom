package ru.netology.diplomproject.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplomproject.dto.FileResponse;
import ru.netology.diplomproject.service.FileCloudService;



@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileCloudService cloudService;


    @GetMapping("/files")
    public ResponseEntity<Page<FileResponse>> getAllFiles(@RequestHeader("auth-token") String token,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FileResponse> filePage = cloudService.getAllFiles(token, pageable);
        return ResponseEntity.ok(filePage);
        //return cloudService.getAllFiles(token, page, size);
    }

    @PostMapping("/file")
    public ResponseEntity<?> inputFile(@RequestHeader("auth-token") String token,
                                       @RequestParam("filename") String name,
                                       MultipartFile file) {
        cloudService.upload(token, name, file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> fileDownload(@RequestHeader("auth-token") String token,
                                                 @RequestParam("filename") String fileName) {

        return ResponseEntity.ok(cloudService.fileDownload(fileName, token));
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String token,
                                        @RequestParam("filename") String fileName) {
        cloudService.fileDelete(fileName, token);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}


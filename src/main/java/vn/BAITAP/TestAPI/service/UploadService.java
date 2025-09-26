package vn.BAITAP.TestAPI.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    public void uploadFile(MultipartFile img) {
        String pathAt = "D:\\D_A";
        File dir = new File(pathAt);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Paths.get(pathAt, img.getOriginalFilename());
        try {
            Files.write(path, img.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

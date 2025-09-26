package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.BAITAP.TestAPI.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UploadController {
    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public String postMethodName(@RequestParam("img") MultipartFile img) {
        this.uploadService.uploadFile(img);

        return "uploaded";
    }

}

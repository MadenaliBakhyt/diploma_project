package com.example.diplomaproject.controllers;

import com.example.diplomaproject.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageService.uploadImage(file));
    }

    @GetMapping("/downloadImageById/{id}")
    public ResponseEntity<?> downloadImageById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageService.downloadImageById(id));
    }

    @GetMapping("/downloadImageByName/{name}")
    public ResponseEntity<?> downloadImage(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageService.downloadImageByName(name));
    }
}

package com.example.diplomaproject.services;

import com.example.diplomaproject.dto.ImageDto;
import com.example.diplomaproject.entities.ImageEntity;
import com.example.diplomaproject.repositories.ImageRepository;
import com.example.diplomaproject.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;


    @Transactional
    public ImageDto uploadImage(MultipartFile multipartFile) throws IOException {
        byte[] image=ImageUtils.compressImage(multipartFile.getBytes());
        ImageEntity imageEntity=imageRepository.save(ImageEntity.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .imageData(image).build());
        return new ImageDto(imageEntity);
    }

    @Transactional
    public byte[] downloadImageByName(String fileName){
        Optional<ImageEntity> imageEntity =imageRepository.findByName(fileName);
        return ImageUtils.decompressImage(imageEntity.get().getImageData());
    }

    @Transactional
    public byte[] downloadImageById(Integer id){
        Optional<ImageEntity> imageEntity =imageRepository.findById(id);
        return ImageUtils.decompressImage(imageEntity.get().getImageData());
    }
}

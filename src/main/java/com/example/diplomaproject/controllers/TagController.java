package com.example.diplomaproject.controllers;

import com.example.diplomaproject.dto.JwtRequest;
import com.example.diplomaproject.dto.TagDto;
import com.example.diplomaproject.exceptions.AppError;
import com.example.diplomaproject.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TagDto tagDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tagService.create(tagDto.getTagName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTags(){
        return  ResponseEntity.ok(tagService.findAll());
    }

    @GetMapping("/get/tagName/{tagName}")
    public ResponseEntity<?> getTagByName(@PathVariable String tagName){
        return ResponseEntity.ok(tagService.findByName(tagName));
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Integer id){
        return ResponseEntity.ok(tagService.findById(id));
    }


    @DeleteMapping("/delete/tagName/{tagName}")
    public ResponseEntity<?> deleteTagByName(@PathVariable String tagName){
        try {
            tagService.deleteByTagName(tagName);
            return ResponseEntity.ok().body("Tag deleted successfully");
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> deleteTagById(@PathVariable Integer id){
        try {
            tagService.deleteById(id);
            return ResponseEntity.ok().body("Tag deleted successfully");
        }catch (Exception exception){
            return new ResponseEntity<>(new AppError(HttpStatus.REQUEST_TIMEOUT.value(), exception.getMessage()),HttpStatus.REQUEST_TIMEOUT);
        }
    }
}

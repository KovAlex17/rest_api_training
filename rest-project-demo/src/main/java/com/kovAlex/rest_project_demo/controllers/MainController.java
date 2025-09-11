package com.kovAlex.rest_project_demo.controllers;

import com.kovAlex.rest_project_demo.dto.Cat;
import com.kovAlex.rest_project_demo.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepo catRepo;

    @GetMapping("/api/ping")
    public String pingPongController(){
        return "pong";
    }

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat){
        log.info("New row: " + catRepo.save(cat));
    }

    @GetMapping("/api/all")
    public List<Cat> getAll(){
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id){
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id){
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String updateCat(@RequestBody Cat cat){
        if(!catRepo.existsById(cat.getId())){
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }
}

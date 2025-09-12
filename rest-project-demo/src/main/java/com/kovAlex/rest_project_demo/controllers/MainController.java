package com.kovAlex.rest_project_demo.controllers;

import com.kovAlex.rest_project_demo.dto.CatDTO;
import com.kovAlex.rest_project_demo.entity.Cat;
import com.kovAlex.rest_project_demo.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepo catRepo;

    @GetMapping("/api/ping")
    public String pingPongController(){
        return "pong";
    }

    @Operation(
            summary = "Кладет нового кота в базу",
            description = "Получает CatDTO, билдером собирает и сохраняет сущность в базу"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO){
        log.info(
                "New row: " + catRepo.save(
                        Cat.builder()
                            .name(catDTO.getName())
                            .weight(catDTO.getWeight())
                            .age(catDTO.getAge())
                            .build())
        );
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

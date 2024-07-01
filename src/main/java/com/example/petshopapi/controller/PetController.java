package com.example.petshopapi.controller;

import com.example.petshopapi.entity.Pet;
import com.example.petshopapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Long> createPet(@RequestBody CreatePetDto createPetDto) {
        Long petId = petService.createPet(createPetDto);
        return ResponseEntity.created(URI.create("/v1/pets/" + petId)).build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
        return petService.getPetById(petId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> updatePet(@PathVariable Long petId, @RequestBody UpdatePetDto updatePetDto) {
        petService.updatePet(petId, updatePetDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }
}

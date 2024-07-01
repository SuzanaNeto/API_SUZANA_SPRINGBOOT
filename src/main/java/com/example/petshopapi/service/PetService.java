package com.example.petshopapi.service;

import com.example.petshopapi.controller.CreatePetDto;
import com.example.petshopapi.controller.UpdatePetDto;
import com.example.petshopapi.entity.Pet;
import com.example.petshopapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Long createPet(CreatePetDto createPetDto) {
        Pet pet = new Pet();
        pet.setName(createPetDto.name());
        pet.setSpecies(createPetDto.species());
        pet.setAge(createPetDto.age());
        Pet savedPet = petRepository.save(pet);
        return savedPet.getId();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public void updatePet(Long id, UpdatePetDto updatePetDto) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            if (updatePetDto.name() != null) {
                pet.setName(updatePetDto.name());
            }
            if (updatePetDto.species() != null) {
                pet.setSpecies(updatePetDto.species());
            }
            if (updatePetDto.age() != null) {
                pet.setAge(updatePetDto.age());
            }
            petRepository.save(pet);
        }
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}

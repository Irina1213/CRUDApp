package com.example.CRUDApp.controller;

import com.example.CRUDApp.entity.Dog;
import com.example.CRUDApp.repository.DogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DogController {

    @Autowired
    private DogRepo dogRepo;

    @GetMapping("/getAllDogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        try{
            List<Dog> dogList = new ArrayList<>();
            dogRepo.findAll().forEach(dogList::add);

            if (dogList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dogList,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getDogById/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
       Optional<Dog> dogInfo = dogRepo.findById(id);

       if(dogInfo.isPresent()){
           return new ResponseEntity<>(dogInfo.get(), HttpStatus.OK);

       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addDog")
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog) {
        Dog dogObject = dogRepo.save(dog);
        return new ResponseEntity<>(dogObject, HttpStatus.OK);

    }

    @PostMapping("/updateDogById/{id}")
    public ResponseEntity<Dog> updateDogById(@PathVariable Long id, @RequestBody Dog newDogInfo) {
        Optional<Dog> oldDogInfo = dogRepo.findById(id);

        if(oldDogInfo.isPresent()){
            Dog updatedDogInfo =  oldDogInfo.get();
            updatedDogInfo.setAge(newDogInfo.getAge());

            Dog dogObj = dogRepo.save(updatedDogInfo);
            return new ResponseEntity<>(dogObj, HttpStatus.OK);


        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteDogById/{id}")
    public ResponseEntity<HttpStatus> deleteDogById(@PathVariable Long id) {
        dogRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

package com.example.SpringBootTutor.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootTutor.model.Pelanggan;
import com.example.SpringBootTutor.repository.PelangganInterface;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PelangganControler {
    @Autowired
    PelangganInterface pelangganInterface;

    @GetMapping("/pelanggan")
    public ResponseEntity<List<Pelanggan>> getPelangganAll(){
        List<Pelanggan> pelangganData = pelangganInterface.findAll();

        if(!pelangganData.isEmpty()){
            return new ResponseEntity<>(pelangganData,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/pelanggan/{id}")
    public ResponseEntity<List<Pelanggan>> getPelangganById(@PathVariable("id") int id){
        List<Pelanggan> pelangganData = pelangganInterface.findById(id);

        if(!pelangganData.isEmpty()){
            return new ResponseEntity<>(pelangganData,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/pelanggan")
    public ResponseEntity<Pelanggan> createPelanggan(@RequestBody Pelanggan pelanggan) {
        try {
            Pelanggan _pelanggan = pelangganInterface.save(new Pelanggan(pelanggan.getNama(),pelanggan.getHp()));
            return new ResponseEntity<>(_pelanggan, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pelanggan/{id}")
    public ResponseEntity<Pelanggan> updatePelanggan(@PathVariable("id") int id, @RequestBody Pelanggan pelanggan) {
        List<Pelanggan> pelangganlData = pelangganInterface.findById(id);

        if (!pelangganlData.isEmpty()) {
            Pelanggan _pelanggan = pelangganlData.get(0);
            _pelanggan.setNama(pelanggan.getNama());
            _pelanggan.setHp(pelanggan.getHp());
            return new ResponseEntity<>(pelangganInterface.save(_pelanggan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/pelanggan/{id}")
    public ResponseEntity<HttpStatus> deletePelanggan(@PathVariable("id") int id) {
        try {
            pelangganInterface.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pelanggan")
    public ResponseEntity<HttpStatus> deletePelangganAll() {
        try {
            pelangganInterface.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

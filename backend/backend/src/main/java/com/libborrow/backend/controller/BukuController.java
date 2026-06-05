package com.libborrow.backend.controller;

import com.libborrow.backend.model.Buku;
import com.libborrow.backend.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buku")
@CrossOrigin(origins = "*", allowedHeaders = "*") // PENTING: Agar halaman HTML/Frontend kamu diijinkan mengakses API ini
public class BukuController {
    @Autowired
    private BukuRepository bukuRepository;

    // 1. Endpoint untuk MENAMPILKAN semua buku (GET http://localhost:8080/api/buku)
    @GetMapping
    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    // 2. Endpoint untuk MENAMBAH buku baru (POST http://localhost:8080/api/buku)
    @PostMapping
    public Buku tambahBuku(@RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable Long id, @RequestBody Buku bukuDetails) {
        return bukuRepository.findById(id).map(buku -> {
            buku.setJudul(bukuDetails.getJudul());
            buku.setPenulis(bukuDetails.getPenulis());
            buku.setKategori(bukuDetails.getKategori());
            buku.setStok(bukuDetails.getStok());
            return ResponseEntity.ok(bukuRepository.save(buku));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuku(@PathVariable Long id) {
        return bukuRepository.findById(id).map(buku -> {
            bukuRepository.delete(buku);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

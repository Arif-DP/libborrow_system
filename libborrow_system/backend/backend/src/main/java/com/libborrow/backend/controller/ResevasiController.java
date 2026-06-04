package com.libborrow.backend.controller;

import com.libborrow.backend.model.Resevasi;
import com.libborrow.backend.repository.ResevasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservasi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResevasiController {

    @Autowired
    private ResevasiRepository resevasiRepository;

    // 1. Ambil semua data reservasi antrean buku
    @GetMapping
    public List<Resevasi> getAllReservasi() {
        return resevasiRepository.findAll();
    }

    // 2. Simpan booking/reservasi baru
    @PostMapping
    public Resevasi tambahReservasi(@RequestBody Resevasi resevasi) {
        return resevasiRepository.save(resevasi);
    }
}
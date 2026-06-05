package com.libborrow.backend.controller;

import com.libborrow.backend.model.Denda;
import com.libborrow.backend.repository.DendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denda")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DendaController {

    @Autowired
    private DendaRepository dendaRepository;

    // 1. Ambil semua data denda (untuk laporan Admin)
    @GetMapping
    public List<Denda> getAllDenda() {
        return dendaRepository.findAll();
    }

    // 2. Filter denda berdasarkan status tertentu (BELUM_BAYAR / LUNAS)
    @GetMapping("/status/{status}")
    public List<Denda> getDendaByStatus(@PathVariable String status) {
        return dendaRepository.findByStatus(status);
    }

    // 3. Update atau Simpan data denda baru
    @PostMapping
    public Denda simpanDenda(@RequestBody Denda denda) {
        return dendaRepository.save(denda);
    }
}
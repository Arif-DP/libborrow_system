package com.libborrow.backend.controller;

import com.libborrow.backend.model.*;
import com.libborrow.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservasi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResevasiController {

    @Autowired private ResevasiRepository resevasiRepository;
    @Autowired private BukuRepository bukuRepository;
    @Autowired private UsersRepository userRepository; // Pastikan nama repository Users Anda sesuai

    // 1. Ambil data reservasi (Bisa semua untuk Admin, atau filter per User Anggota)
    @GetMapping
    public List<Resevasi> getAllReservasi(@RequestParam(required = false) Long userId, @RequestParam(required = false) String role) {
        // Jika user adalah Admin, tampilkan semua antrean reservasi
        if ("Admin".equalsIgnoreCase(role)) {
            return resevasiRepository.findAll();
        } 
        // Jika user adalah Anggota, filter data miliknya sendiri
        else if (userId != null) {
            return resevasiRepository.findByUser_Id(userId);
        }
        
        return java.util.Collections.emptyList();
    }

    @PostMapping
    public ResponseEntity<?> tambahReservasi(@RequestBody ReservasiRequest req) {
        if (req.getUserId() == null || req.getBukuId() == null) {
            return ResponseEntity.badRequest().body("Gagal: ID User atau ID Buku tidak valid.");
        }

        Users user = userRepository.findById(req.getUserId()).orElse(null);
        Buku buku = bukuRepository.findById(req.getBukuId()).orElse(null);

        if (user == null || buku == null) {
            return ResponseEntity.badRequest().body("Gagal: Data User atau Buku tidak ditemukan.");
        }

        Resevasi reservasi = new Resevasi();
        reservasi.setUser(user);
        reservasi.setBuku(buku);
        reservasi.setTanggalReservasi(LocalDate.now());

        // 🔥 LOGIKA BARU: Cek ketersediaan stok
        if (buku.getStok() > 0) {
            // Jika stok ada, langsung amankan buku tersebut untuk user ini
            reservasi.setStatus("TERSEDIA");
            
            buku.setStok(buku.getStok() - 1); // Kurangi stok buku di perpustakaan
            bukuRepository.save(buku);
        } else {
            // Jika stok habis, baru statusnya PENDING (masuk daftar antrean)
            reservasi.setStatus("PENDING");
        }

        Resevasi hasilSave = resevasiRepository.save(reservasi);
        return ResponseEntity.ok(hasilSave);
    }
}

// 💡 DTO Helper untuk menangkap kiriman ID dari frontend JavaScript
class ReservasiRequest {
    private Long userId;
    private Long bukuId;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBukuId() { return bukuId; }
    public void setBukuId(Long bukuId) { this.bukuId = bukuId; }
}
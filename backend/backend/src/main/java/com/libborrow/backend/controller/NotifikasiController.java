package com.libborrow.backend.controller;

import com.libborrow.backend.model.Notifikasi;
import com.libborrow.backend.repository.NotifikasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifikasi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotifikasiController {

    @Autowired
    private NotifikasiRepository notifikasiRepository;

    // 1. Ambil semua notifikasi (Aman dengan ResponseEntity)
    @GetMapping
    public ResponseEntity<?> getAllNotifikasi() {
        try {
            List<Notifikasi> list = notifikasiRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal mengambil semua data: " + e.getMessage());
        }
    }

    // 2. Ambil semua notifikasi milik user tertentu berdasarkan ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getNotifikasiByUserId(@PathVariable Long userId) {
        try {
            List<Notifikasi> daftarNotif = notifikasiRepository.findByUser_IdOrderByTanggalKirimDesc(userId);
            
            // Mengembalikan status 200 dengan list data (walau list-nya kosong [] tidak akan eror 500)
            return ResponseEntity.ok(daftarNotif); 
        } catch (Exception e) {
            // 🔍 Cetak eror asli di terminal Spring Boot agar kamu bisa baca penyebabnya
            e.printStackTrace(); 
            
            // Kirim pesan eror yang rapi ke frontend
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Terjadi kendala di database backend: " + e.getMessage());
        }
    }

    // 3. Kirim notifikasi baru
    @PostMapping
    public ResponseEntity<?> kirimNotifikasi(@RequestBody Notifikasi notifikasi) {
        try {
            if (notifikasi.getTanggalKirim() == null) {
                notifikasi.setTanggalKirim(LocalDateTime.now());
            }
            notifikasi.setDibaca(false); 
            
            Notifikasi hasilSaves = notifikasiRepository.save(notifikasi);
            return ResponseEntity.status(HttpStatus.CREATED).body(hasilSaves);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Gagal mengirim notifikasi: " + e.getMessage());
        }
    }

    // 4. Ubah status dibaca menjadi true
    @PutMapping("/baca/{notifikasiId}")
    public ResponseEntity<?> tandaiSudahDibaca(@PathVariable Long notifikasiId) {
        try {
            return notifikasiRepository.findById(notifikasiId).map(notif -> {
                notif.setDibaca(true);
                notifikasiRepository.save(notif);
                return ResponseEntity.ok().body("Notifikasi berhasil ditandai dibaca.");
            }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID Notifikasi tidak ditemukan."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal memperbarui status: " + e.getMessage());
        }
    }
}
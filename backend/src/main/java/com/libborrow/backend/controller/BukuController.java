package com.libborrow.backend.controller;

import com.libborrow.backend.model.Buku;
import com.libborrow.backend.model.Resevasi; // ✨ Sudah disesuaikan dengan model Resevasi kamu
import com.libborrow.backend.model.Notifikasi;
import com.libborrow.backend.repository.BukuRepository;
import com.libborrow.backend.repository.ResevasiRepository; // ✨ Sudah disesuaikan dengan repo ResevasiRepository kamu
import com.libborrow.backend.repository.NotifikasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/buku")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Menghindari masalah CORS saat diakses frontend HTML
public class BukuController {

    @Autowired
    private BukuRepository bukuRepository;

    @Autowired
    private ResevasiRepository resevasiRepository; // ✨ Menggunakan ResevasiRepository

    @Autowired
    private NotifikasiRepository notifikasiRepository;

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

    // 3. Endpoint untuk UPDATE data/stok buku (PUT http://localhost:8080/api/buku/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable Long id, @RequestBody Buku bukuDetails) {
        return bukuRepository.findById(id).map(buku -> {
            buku.setJudul(bukuDetails.getJudul());
            buku.setPenulis(bukuDetails.getPenulis());
            buku.setKategori(bukuDetails.getKategori());
            buku.setStok(bukuDetails.getStok()); // Mengisi nomor stok baru dari input Admin

            // =========================================================================
            // 🚀 AWAL LOGIKA OTOMATISASI ANTREEAN RESERVASI & NOTIFIKASI
            // =========================================================================
            if (buku.getStok() > 0) {
                // Mencari daftar pengantre berstatus PENDING menggunakan format findByBuku_Id
                List<Resevasi> listAntrean = resevasiRepository
                        .findByBuku_IdAndStatusOrderByTanggalReservasiAsc(id, "PENDING");

                for (Resevasi reservasi : listAntrean) {
                    if (buku.getStok() > 0) {
                        // 1. Ubah status reservasi user terlama menjadi TERSEDIA
                        reservasi.setStatus("TERSEDIA");
                        resevasiRepository.save(reservasi);

                        // 2. Kurangi stok buku sebanyak 1 karena telah dipesan/dialokasikan ke user ini
                        buku.setStok(buku.getStok() - 1);

                        // 3. Buat pemberitahuan otomatis ke tabel notifikasi user tersebut
                        Notifikasi notif = new Notifikasi();
                        notif.setUser(reservasi.getUser());
                        notif.setPesan("Buku '" + buku.getJudul() + "' yang Anda reservasi kini SUDAH TERSEDIA! Silakan lakukan peminjaman di sikat sirkulasi.");
                        notif.setTanggalKirim(LocalDateTime.now());
                        notif.setDibaca(false);
                        notifikasiRepository.save(notif);
                    } else {
                        // Hentikan pembagian stok jika stok yang diinput admin sudah habis dibagi ke antrean awal
                        break; 
                    }
                }
            }
            // =========================================================================
            // 🚀 AKHIR LOGIKA OTOMATISASI RESERVASI
            // =========================================================================

            // Menyimpan final data buku (dan sisa stok setelah otomatisasi) ke database
            return ResponseEntity.ok(bukuRepository.save(buku));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 4. Endpoint untuk MENGHAPUS buku (DELETE http://localhost:8080/api/buku/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuku(@PathVariable Long id) {
        return bukuRepository.findById(id).map(buku -> {
            bukuRepository.delete(buku);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
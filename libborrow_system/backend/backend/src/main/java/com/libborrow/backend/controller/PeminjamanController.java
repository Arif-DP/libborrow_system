package com.libborrow.backend.controller;

import com.libborrow.backend.model.*;
import com.libborrow.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/peminjaman")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PeminjamanController {

    @Autowired private PeminjamanRepository peminjamanRepository;
    @Autowired private BukuRepository bukuRepository;
    @Autowired private UsersRepository userRepository;
    @Autowired private DendaRepository dendaRepository; 

    @GetMapping
    public List<Peminjaman> getAllPeminjaman(@RequestParam(required = false) Long userId, @RequestParam(required = false) String role) {
        // Jika user adalah Admin, tampilkan semua data
        if ("Admin".equalsIgnoreCase(role)) {
            return peminjamanRepository.findAll();
        } 
        // Jika user adalah Anggota (berdasarkan userId), filter datanya
        else if (userId != null) {
            return peminjamanRepository.findByUser_Id(userId);
        }
        
        return peminjamanRepository.findAll(); 
        
    }

    @PostMapping("/pinjam")
    public ResponseEntity<?> pinjamBuku(@RequestBody PeminjamanRequest req) {
        // Cari User berdasarkan email yang dikirim frontend
        Users user = userRepository.findByEmail(req.getEmailAnggota()).orElse(null);
        Buku buku = bukuRepository.findById(req.getBukuId()).orElse(null);

        if (buku == null || user == null || buku.getStok() <= 0) {
            return ResponseEntity.badRequest().body("Gagal: Buku/User tidak ditemukan atau stok habis.");
        }

        buku.setStok(buku.getStok() - 1);
        bukuRepository.save(buku);

        Peminjaman p = new Peminjaman();
        p.setUser(user);
        p.setBuku(buku);
        p.setTanggalPinjam(LocalDate.now());
        p.setBatasKembali(LocalDate.now().plusDays(7));
        p.setStatus("Dipinjam");
        p.setDenda(0);

        return ResponseEntity.ok(peminjamanRepository.save(p));
    }

   @PostMapping("/kembali/{id}")
   @Transactional
    public ResponseEntity<?> kembalikanBuku(@PathVariable Long id) {
        try {
            return peminjamanRepository.findById(id).map(p -> {
                
                // Logika hitung denda
                LocalDate hariIni = LocalDate.now();
                LocalDate batas = p.getBatasKembali();
                
                // Cek jika batas kembali null (Sering jadi penyebab error)
                if (batas == null) {
                    return ResponseEntity.badRequest().body("Error: Tanggal batas kembali tidak tersimpan di database.");
                }

                int dendaPerHari = 1000;
                int totalDenda = 0;
                
                if (hariIni.isAfter(batas)) {
                    long hariTerlambat = ChronoUnit.DAYS.between(batas, hariIni);
                    totalDenda = (int) (hariTerlambat * dendaPerHari);
                    
                    // Simpan denda
                    Denda d = new Denda();
                    d.setPeminjaman(p); 
                    d.setJumlah((double) totalDenda);
                    d.setHariTerlambat((int) hariTerlambat);
                    d.setStatus("BELUM_BAYAR");
                    dendaRepository.save(d); 
                }
                
                p.setStatus("Dikembalikan");
                p.setDenda(totalDenda);
                
                // Pengecekan null agar aman
                if (p.getBuku() != null) {
                    Buku b = p.getBuku();
                    b.setStok(b.getStok() + 1);
                    bukuRepository.save(b);
                } else {
                    return ResponseEntity.badRequest().body("Error: Data buku tidak ditemukan pada transaksi ini.");
                }
                
                peminjamanRepository.save(p);
                
                return ResponseEntity.ok("Buku berhasil dikembalikan. Denda: Rp " + totalDenda);
            }).orElse(ResponseEntity.notFound().build());
            
        } catch (Exception e) {
            // --- INI AKAN MENGIRIM PESAN ERROR KE BROWSER ---
            e.printStackTrace(); // Tampilkan di terminal
            return ResponseEntity.internalServerError().body("SERVER ERROR: " + e.getMessage());
        }
    }
}

// DTO ini harus cocok dengan JSON dari frontend
class PeminjamanRequest {
    private String emailAnggota;
    private Long bukuId;
    public String getEmailAnggota() { return emailAnggota; }
    public Long getBukuId() { return bukuId; }
}
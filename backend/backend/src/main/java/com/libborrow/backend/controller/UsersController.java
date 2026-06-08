package com.libborrow.backend.controller;

import com.libborrow.backend.model.Users;
import com.libborrow.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    // 1. Ambil semua data user/anggota
    @GetMapping
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    // 2. Register / Tambah User Baru
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        try {
            // A. Validasi: Cek apakah email sudah terdaftar menggunakan findByEmail yang sudah kamu punya
            Optional<Users> userExisting = usersRepository.findByEmail(user.getEmail());
            if (userExisting.isPresent()) {
                return ResponseEntity.badRequest().body("Gagal: Email tersebut sudah terdaftar!");
            }

            // B. Keamanan: Otomatis set Role sebagai "Anggota" (mencegah user daftar langsung jadi Admin)
            user.setRole("Anggota");

            // C. Otomatis Pembuatan Nomor Anggota (Contoh: AG001, AG002, dst.)
            long totalUser = usersRepository.count();
            String noAnggotaOtomatis = "AG" + String.format("%03d", totalUser + 1);
            user.setNoAnggota(noAnggotaOtomatis);

            // D. Pengaman Username: Jika di form register tidak mengisi username, ambil potongan depan email
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                user.setUsername(user.getEmail().split("@")[0]);
            }

            // Simpan ke database Laragon
            usersRepository.save(user);

            return ResponseEntity.ok("Registrasi berhasil! Nomor Anggota Anda: " + noAnggotaOtomatis + ". Silakan login.");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi kesalahan server: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        // Cari user berdasarkan email di database Laragon
        Optional<Users> user = usersRepository.findByEmail(email);
        
        // Validasi: Jika user ditemukan dan password-nya cocok cocok
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok(user.get()); // Kirim data user utuh dalam bentuk JSON ke frontend
        }
        
        // Jika salah, kirim status 401 (Unauthorized)
        return ResponseEntity.status(401).body("Email atau password salah!");
    }

    // 4. EDIT DATA ANGGOTA
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        return usersRepository.findById(id).map(user -> {
            user.setNamaLengkap(userDetails.getNamaLengkap());
            user.setEmail(userDetails.getEmail());
            user.setUsername(userDetails.getUsername());
            user.setRole(userDetails.getRole());

            user.setNoAnggota(userDetails.getNoAnggota());

            if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()){
                user.setPassword(userDetails.getPassword());
            }
            Users updatedUser = usersRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. HAPUS DATA ANGGOTA
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return usersRepository.findById(id).map(user -> {
            usersRepository.delete(user);
            return ResponseEntity.ok().body("User berhasil dihapus");
        }).orElse(ResponseEntity.notFound().build());
}
}
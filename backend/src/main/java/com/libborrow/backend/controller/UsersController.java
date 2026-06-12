package com.libborrow.backend.controller;

import com.libborrow.backend.model.Users;
import com.libborrow.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.libborrow.backend.config.JwtUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // 1. Ambil semua data user/anggota
    @GetMapping
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    // 2. Register / Tambah User Baru (SUDAH AMAN + FIX BUG HANIF)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        try {
            // A. Validasi Email
            Optional<Users> userExisting = usersRepository.findByEmail(user.getEmail());
            if (userExisting.isPresent()) {
                return ResponseEntity.badRequest().body("Gagal: Email tersebut sudah terdaftar!");
            }

            // B. Pengaman Username: Jika kosong, ambil depan email
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                user.setUsername(user.getEmail().split("@")[0]);
            }

            // FIX BUG HANIF: Cek apakah username sudah ada di DB agar tidak Crash 500
            if (usersRepository.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body("Gagal: Username '" + user.getUsername() + "' sudah digunakan oleh orang lain!");
            }

            // C. Keamanan Role
            user.setRole("Anggota");

            // D. Otomatis Pembuatan Nomor Anggota
            long totalUser = usersRepository.count();
            String noAnggotaOtomatis = "AG" + String.format("%03d", totalUser + 1);
            user.setNoAnggota(noAnggotaOtomatis);

            // INTEGRASI KEAMANAN: Hash password sebelum disimpan ke Laragon
            String passwordAman = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordAman);

            // Simpan ke database Laragon
            usersRepository.save(user);

            return ResponseEntity.ok("Registrasi berhasil! Nomor Anggota Anda: " + noAnggotaOtomatis + ". Silakan login.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan server: " + e.getMessage());
        }
    }

    // 3. Login User (SUDAH MENGGUNAKAN GENERATE JWT TOKEN 🛡️)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String passwordRaw = loginData.get("password");

        // Cari user berdasarkan email
        Optional<Users> userOpt = usersRepository.findByEmail(email);
        
        // Validasi dengan BCrypt
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            
            // Mencocokkan password mentah frontend dengan hash di database
            if (passwordEncoder.matches(passwordRaw, user.getPassword())) {
                
                // A. Bikin token JWT berdasarkan Email dan Role user
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
                
                // B. Bungkus token dan data user ke dalam Map agar menjadi JSON yang rapi
                Map<String, Object> responseData = Map.of(
                    "token", token,
                    "role", user.getRole(),
                    "namaLengkap", user.getNamaLengkap(),
                    "id", user.getId()
                );
                
                // C. Kirim token ke frontend
                return ResponseEntity.ok(responseData); 
            }
        }
        
        // Jika salah, kirim status 401 (Unauthorized)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email atau password salah!");
    }

    // 4. EDIT DATA ANGGOTA (PASSWORD JUGA DI-HASH JIKA DIGANTI)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        return usersRepository.findById(id).map(user -> {
            user.setNamaLengkap(userDetails.getNamaLengkap());
            user.setEmail(userDetails.getEmail());
            user.setUsername(userDetails.getUsername());
            user.setRole(userDetails.getRole());
            user.setNoAnggota(userDetails.getNoAnggota());

            // Jika admin/user mengganti password, hash juga password barunya
            if (userDetails.getPassword() != null && !userDetails.getPassword().trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
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
package com.libborrow.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // Kunci rahasia untuk menandatangani token (Ganti dengan teks acak panjang buatanmu sendiri)
    private final String SECRET_STRING = "KunciRahasiaPerpustakaanLibBorrowSangatAmanDanPanjangSekali123456!";
    
    // Mengubah string kunci rahasia menjadi SecretKey resmi algoritma HS256
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    
    // Durasi kedaluwarsa token: 1 Hari (dalam milidetik)
    private final long JWT_EXPIRATION_MS = 86400000; 

    // 1. FUNGSI UNTUK MENCETAK TOKEN (Dipanggil saat Login Sukses)
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role) // Mengunci data Role di dalam token agar tidak bisa dimanipulasi di browser
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(SECRET_KEY)
                .compact();
    }

    // 2. FUNGSI UNTUK MEMBACA EMAIL DARI TOKEN
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // 3. FUNGSI UNTUK MEMBACA ROLE DARI TOKEN
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // 4. FUNGSI UNTUK MENGECEK APAKAH TOKEN MASIH VALID ATAU SUDAH EXPIRED
    public boolean validateToken(String token) {
        try {
            return getClaims(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return false; // Token palsu, rusak, atau sudah kedaluwarsa otomatis ditolak
        }
    }

    // Fungsi pembantu untuk membedah isi komponen token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

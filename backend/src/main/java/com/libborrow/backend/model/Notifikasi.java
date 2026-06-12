package com.libborrow.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore; // 👈 1. TAMBAHKAN IMPORT INI DI SINI

@Entity
@Table(name = "notifikasi")
public class Notifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Menyamakan dengan primary key di SQL
    private Long notifikasiId;

    // 👈 2. TAMBAHKAN @JsonIgnore DI SINI (Bisa di atas @ManyToOne atau di bawah @JoinColumn)
    @JsonIgnore 
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @Column(name = "pesan", nullable = false)
    private String pesan;

    @Column(name = "tanggal_kirim", nullable = false) // Menyamakan dengan DATETIME tanggal_kirim
    private LocalDateTime tanggalKirim;

    @Column(name = "dibaca", nullable = false) // Menambahkan kolom status baca ke database
    private boolean dibaca; // false = belum dibaca, true = sudah dibaca

    // --- GETTER AND SETTER ---
    public Long getNotifikasiId() { return notifikasiId; }
    public void setNotifikasiId(Long notifikasiId) { this.notifikasiId = notifikasiId; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public String getPesan() { return pesan; }
    public void setPesan(String pesan) { this.pesan = pesan; }

    public LocalDateTime getTanggalKirim() { return tanggalKirim; }
    public void setTanggalKirim(LocalDateTime tanggalKirim) { this.tanggalKirim = tanggalKirim; }

    public boolean isDibaca() { return dibaca; }
    public void setDibaca(boolean dibaca) { this.dibaca = dibaca; }
}
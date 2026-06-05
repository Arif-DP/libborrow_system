package com.libborrow.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reservasi")
public class Resevasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "peminjaman", "listPeminjaman"})
    private Users user;

    @ManyToOne
    @JoinColumn(name = "buku_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "peminjaman"})
    private Buku buku;

    private LocalDate tanggalReservasi;
    private String status; // "PENDING", "TERSEDIA", atau "SELESAI"

    // --- GETTER AND SETTER ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public Buku getBuku() { return buku; }
    public void setBuku(Buku buku) { this.buku = buku; }

    public LocalDate getTanggalReservasi() { return tanggalReservasi; }
    public void setTanggalReservasi(LocalDate tanggalReservasi) { this.tanggalReservasi = tanggalReservasi; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

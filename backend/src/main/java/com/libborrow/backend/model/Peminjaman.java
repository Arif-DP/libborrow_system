package com.libborrow.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"password"})
    private Users user;

    @ManyToOne
    @JoinColumn(name = "buku_id", nullable = false)
    private Buku buku;

    private LocalDate tanggalPinjam;
    private LocalDate batasKembali; // Menggantikan tanggalKembali untuk durasi
    private String status; 
    private Integer denda;

    // --- GETTERS & SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
    public Buku getBuku() { return buku; }
    public void setBuku(Buku buku) { this.buku = buku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }
    public LocalDate getBatasKembali() { return batasKembali; }
    public void setBatasKembali(LocalDate batasKembali) { this.batasKembali = batasKembali; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getDenda() { return denda; }
    public void setDenda(Integer denda) { this.denda = denda; }
}
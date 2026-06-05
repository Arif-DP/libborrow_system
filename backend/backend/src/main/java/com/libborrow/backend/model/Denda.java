package com.libborrow.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "denda")
public class Denda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Menyamakan dengan primary key di SQL
    private Long dendaId;

    @OneToOne
    @JoinColumn(name = "peminjaman_id", nullable = false)
    @JsonIgnore
    private Peminjaman peminjaman;

    @Column(name = "jumlah_denda") // Menyamakan dengan nama kolom 'jumlah' di SQL
    private double jumlah;

    @Column(name = "hari_terlambat") // Menyamakan dengan kolom 'hari_terlambat' di SQL
    private int hariTerlambat;

    @Column(name = "status") // Menambahkan kolom status ke database
    private String status; // "BELUM_BAYAR" atau "LUNAS"

    // --- GETTER AND SETTER ---
    public Long getDendaId() { return dendaId; }
    public void setDendaId(Long dendaId) { this.dendaId = dendaId; }

    public Peminjaman getPeminjaman() { return peminjaman; }
    public void setPeminjaman(Peminjaman peminjaman) { this.peminjaman = peminjaman; }

    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }

    public int getHariTerlambat() { return hariTerlambat; }
    public void setHariTerlambat(int hariTerlambat) { this.hariTerlambat = hariTerlambat; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

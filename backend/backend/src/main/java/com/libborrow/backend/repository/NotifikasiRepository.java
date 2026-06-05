package com.libborrow.backend.repository;

import com.libborrow.backend.model.Notifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotifikasiRepository extends JpaRepository<Notifikasi, Long> {
    // Mencari notifikasi khusus untuk ID user tertentu
    List<Notifikasi> findByUser_IdOrderByTanggalKirimDesc(Long userId);
}
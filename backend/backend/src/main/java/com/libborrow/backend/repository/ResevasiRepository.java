package com.libborrow.backend.repository;

import com.libborrow.backend.model.Resevasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResevasiRepository extends JpaRepository<Resevasi, Long> {
    // 🛠️ PERBAIKAN: Ubah findByUserId menjadi findByUser_Id agar match dengan mapping JPA
    List<Resevasi> findByUser_Id(Long userId);

    Optional<Resevasi> findFirstByBuku_IdAndStatusOrderByTanggalReservasiAsc(Long bukuId, String status);
}
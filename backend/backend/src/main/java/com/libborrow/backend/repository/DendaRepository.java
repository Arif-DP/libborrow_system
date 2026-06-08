package com.libborrow.backend.repository;

import com.libborrow.backend.model.Denda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DendaRepository extends JpaRepository<Denda, Long> {
    // Tambahan fungsi jika nanti ingin mencari denda berdasarkan status (misal: "BELUM_BAYAR")
    List<Denda> findByStatus(String status);
}
package com.libborrow.backend.repository;

import com.libborrow.backend.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    List<Peminjaman> findByUser_Id(Long userId);
}
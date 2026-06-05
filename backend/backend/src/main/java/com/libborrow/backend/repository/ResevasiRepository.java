package com.libborrow.backend.repository;

import com.libborrow.backend.model.Resevasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResevasiRepository extends JpaRepository<Resevasi, Long> {
    List<Resevasi> findByUserId(Long userId);
}
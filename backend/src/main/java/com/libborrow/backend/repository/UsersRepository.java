package com.libborrow.backend.repository;

import com.libborrow.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // Fungsi tambahan untuk mencari user berdasarkan username saat login
    Optional<Users> findByEmail(String username);

    boolean existsByUsername(String username);
}
package com.carwash.washerservice.repository;

import com.carwash.washerservice.entity.Washer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasherRepository extends JpaRepository<Washer, Long> {
    Optional<Washer> findByEmail(String email);
}

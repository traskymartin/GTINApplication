package com.trasky.gtin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trasky.gtin.entity.Gtin;

@Repository
public interface GtinRepository extends JpaRepository<Gtin, Long> {
    List<Gtin> findByGtin(String gtin);
}

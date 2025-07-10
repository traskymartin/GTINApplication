package com.trasky.gtin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trasky.gtin.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query("SELECT b FROM Batch b WHERE b.availableQuantity > 0")
    List<Batch> findPositiveBatches();

    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0")
    List<Batch> findZeroOrNegativeBatches();

    @Query("SELECT b FROM Batch b WHERE b.product.productId = :productId AND b.availableQuantity > 0")
    List<Batch> findPositiveBatchesByProductId(@Param("productId") Long productId);

    List<Batch> findTop1ByProductProductIdAndAvailableQuantityLessThanEqualOrderByInwardedOnDesc(Long productId, Integer qty);
}

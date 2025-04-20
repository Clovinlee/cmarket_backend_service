package com.chris.cmarket.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chris.cmarket.Models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {

    // CHRIS: This is handled by spring JPA automatically
    // findBy{:FIELD}{:ACTION}
    // Underhood, this uses WHERE NAME like %name%
    Optional<ProductModel> findFirstByNameContaining(String name);

    @Query("SELECT p FROM ProductModel p " +
            "WHERE p.price > :minPrice AND p.price < :maxPrice " +
            "AND p.rarity = :rarity " +
            "ORDER BY p.id DESC")
    List<ProductModel> searchProduct();
}
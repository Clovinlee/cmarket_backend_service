package com.chris.cmarket.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.chris.cmarket.Models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {

    // CHRIS: This is handled by spring JPA automatically
    // findBy{:FIELD}{:ACTION}
    // Underhood, this uses WHERE NAME like %name%
    Optional<ProductModel> findFirstByNameContaining(String name);

    Optional<ProductModel> findBySlug(String slug);
}
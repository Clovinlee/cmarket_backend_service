package com.chris.cmarket.Product.Repository;

import java.util.Optional;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chris.cmarket.Product.Model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {

    // CHRIS: This is handled by spring JPA automatically
    // findBy{:FIELD}{:ACTION}
    // Underhood, this uses WHERE NAME like %name%
    Optional<ProductModel> findFirstByNameContaining(String name);

    Optional<ProductModel> findBySlug(String slug);

    // Pesismistic = wait until transaction is over
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ProductModel p WHERE p.slug = :slug")
    Optional<ProductModel> findBySlugForUpdate(@Param("slug") String slug);


}
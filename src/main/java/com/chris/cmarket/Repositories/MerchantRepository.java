package com.chris.cmarket.Repositories;

import com.chris.cmarket.Models.MerchantModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantModel, Long> {

}
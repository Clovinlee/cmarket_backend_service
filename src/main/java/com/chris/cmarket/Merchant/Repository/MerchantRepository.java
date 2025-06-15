package com.chris.cmarket.Merchant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chris.cmarket.Merchant.Model.MerchantModel;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantModel, Long> {

}
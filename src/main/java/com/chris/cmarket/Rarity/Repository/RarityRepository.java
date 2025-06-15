package com.chris.cmarket.Rarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chris.cmarket.Rarity.Model.RarityModel;

@Repository
public interface RarityRepository extends JpaRepository<RarityModel, Long> {

}

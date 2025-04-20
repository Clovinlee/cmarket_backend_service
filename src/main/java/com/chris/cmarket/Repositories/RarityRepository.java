package com.chris.cmarket.Repositories;

import com.chris.cmarket.Models.RarityModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RarityRepository extends JpaRepository<RarityModel, Long> {

}

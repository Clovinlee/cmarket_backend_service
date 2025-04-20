package com.chris.cmarket.Services;

import com.chris.cmarket.Dtos.RarityDTO;
import com.chris.cmarket.Models.RarityModel;
import com.chris.cmarket.Repositories.RarityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RarityService {

    private RarityRepository rarityRepository;

    public List<RarityDTO> getAllRarities() {
        List<RarityModel> rarities = rarityRepository.findAll();

        return rarities.stream()
                .map(r -> new RarityDTO(r))
                .collect(Collectors.toList());
    }
}

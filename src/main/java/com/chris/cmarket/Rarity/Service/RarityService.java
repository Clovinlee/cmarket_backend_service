package com.chris.cmarket.Rarity.Service;

import com.chris.cmarket.Rarity.Dto.RarityDTO;
import com.chris.cmarket.Rarity.Model.RarityModel;
import com.chris.cmarket.Rarity.Repository.RarityRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RarityService {

    private RarityRepository rarityRepository;

    /**
     * Retrieves all rarity records from the database.
     *
     * @return a list of {@link RarityDTO} representing all rarities
     */
    public List<RarityDTO> getAllRarities() {
        List<RarityModel> rarities = rarityRepository.findAll();

        return rarities.stream()
                .map(r -> new RarityDTO(r))
                .collect(Collectors.toList());
    }
}

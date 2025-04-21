package com.chris.cmarket.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chris.cmarket.Dtos.MerchantDTO;
import com.chris.cmarket.Models.MerchantModel;
import com.chris.cmarket.Repositories.MerchantRepository;

@Service
public class MerchantService {

    private MerchantRepository merchantRepository;

    /**
     * Retrieves all merchant records from the database.
     *
     * @return a list of {@link MerchantDTO} representing all merchants
     */
    public List<MerchantDTO> getAllMerchants() {
        List<MerchantModel> merchants = merchantRepository.findAll();

        return merchants.stream()
                .map(r -> new MerchantDTO(r))
                .collect(Collectors.toList());
    }
}

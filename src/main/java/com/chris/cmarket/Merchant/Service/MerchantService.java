package com.chris.cmarket.Merchant.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.chris.cmarket.Merchant.Dto.MerchantDTO;
import com.chris.cmarket.Merchant.Model.MerchantModel;
import com.chris.cmarket.Merchant.Repository.MerchantRepository;

@Service
@AllArgsConstructor
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

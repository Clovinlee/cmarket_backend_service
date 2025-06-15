package com.chris.cmarket.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chris.cmarket.Merchant.Dto.MerchantDTO;
import com.chris.cmarket.Merchant.Model.MerchantModel;
import com.chris.cmarket.Merchant.Repository.MerchantRepository;
import com.chris.cmarket.Merchant.Service.MerchantService;
import com.chris.cmarket.Rarity.Dto.RarityDTO;
import com.chris.cmarket.Rarity.Model.RarityModel;
import com.chris.cmarket.Rarity.Repository.RarityRepository;
import com.chris.cmarket.Rarity.Service.RarityService;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {
    @Mock
    private RarityRepository rarityRepository;

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private RarityService rarityService;

    @InjectMocks
    private MerchantService merchantService;

    @Test
    void rarityFilterFound() {
        List<RarityModel> mockData = List.of(
                new RarityModel(1L, "Common", 1, "FFFFFF", null, null),
                new RarityModel(2L, "Uncommon", 2, "008000", null, null));

        when(rarityRepository.findAll()).thenReturn(mockData);

        List<RarityDTO> result = rarityService.getAllRarities();

        assertEquals(2, result.size());
        assertEquals("Common", result.get(0).getName());
        assertEquals("FFFFFF", result.get(0).getColor());
        verify(rarityRepository).findAll();
    }

    @Test
    void rarityFilterNotFound() {
        when(rarityRepository.findAll()).thenReturn(List.of());

        List<RarityDTO> result = rarityService.getAllRarities();

        assertTrue(result.isEmpty());
    }

    @Test
    void merchantFilterFound() {
        List<MerchantModel> mockMerchants = List.of(
                new MerchantModel(1L, "Local Merchant", 1L, "4CAF50", null, null),
                new MerchantModel(2L, "Traveling Merchant", 2L, "FFC107", null, null));

        when(merchantRepository.findAll()).thenReturn(mockMerchants);

        List<MerchantDTO> result = merchantService.getAllMerchants();

        assertEquals(2, result.size());
        assertEquals("Local Merchant", result.get(0).getName());
        assertEquals("4CAF50", result.get(0).getColor());
        verify(merchantRepository).findAll();
    }

    @Test
    void merchantFilterNotFound() {
        when(merchantRepository.findAll()).thenReturn(List.of());

        List<MerchantDTO> result = merchantService.getAllMerchants();

        assertTrue(result.isEmpty());
    }
}

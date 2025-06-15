package com.chris.cmarket.Filter.Controller;

import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.Merchant.Dto.MerchantDTO;
import com.chris.cmarket.Merchant.Service.MerchantService;
import com.chris.cmarket.Rarity.Dto.RarityDTO;
import com.chris.cmarket.Rarity.Service.RarityService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filters") // base path for all filters
@RequiredArgsConstructor
public class FilterController {

    private final RarityService rarityService;
    private final MerchantService merchantService;

    @GetMapping("/rarityfilters")
    public ResponseEntity<APIResponse<List<RarityDTO>>> getRarityFilters() {
        List<RarityDTO> raritiesData = rarityService.getAllRarities();

        if (raritiesData.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(APIResponse.<List<RarityDTO>>builder()
                            .success(false)
                            .message("No rarities found")
                            .build());
        }

        return ResponseEntity.ok(
                APIResponse.success(raritiesData));
    }

    @GetMapping("/merchantfilters")
    public ResponseEntity<APIResponse<List<MerchantDTO>>> getMerchantFilters() {
        List<MerchantDTO> merchantsData = merchantService.getAllMerchants();

        if (merchantsData.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(APIResponse.notFound());
        }

        return ResponseEntity.ok(
                APIResponse.success(merchantsData));
    }
}

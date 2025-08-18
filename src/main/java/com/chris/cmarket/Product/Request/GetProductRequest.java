package com.chris.cmarket.Product.Request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductRequest {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String name;

    @Positive
    @Nullable
    private Long rarity;
    private List<Long> merchants;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    /**
     * Converts the 1-based page number to 0-based for use with Pageable.
     *
     * @return the zero-based page index
     */
    public Integer getZeroBasedPage() {
        return (this.page <= 0 ? 1 : this.page) - 1;
    }

    /**
     * Converts get product request to query params
     *
     * @return query param string
     */
    public String toQueryParam() {
        StringBuilder sb = new StringBuilder("?");

        if (minPrice != null)
            sb.append("minPrice=").append(minPrice).append("&");
        if (maxPrice != null)
            sb.append("maxPrice=").append(maxPrice).append("&");
        if (name != null)
            sb.append("name=").append(name).append("&");
        if (rarity != null)
            sb.append("rarity=").append(rarity).append("&");
        if (null != this.merchants) {
            for (Long merchantId : this.merchants) {
                sb.append("merchants=").append(merchantId).append("&");
            }
        }
        if (page != null)
            sb.append("page=").append(page).append("&");
        if (size != null)
            sb.append("size=").append(size).append("&");

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
            return sb.toString();
        } else {
            return "";
        }
    }
}

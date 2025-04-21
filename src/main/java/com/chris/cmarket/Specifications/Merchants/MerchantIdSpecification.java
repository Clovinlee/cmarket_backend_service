package com.chris.cmarket.Specifications.Merchants;

import org.springframework.data.jpa.domain.Specification;

public class MerchantIdSpecification {
    /**
     * Creates a JPA Specification to filter by the "id_merchant" field.
     *
     * @param id  the merchant ID to filter by
     * @param <T> the entity type
     * @return a Specification that matches entities with the given merchant ID
     */
    public static <T> Specification<T> whereId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id_merchant"), id);
    }
}

package com.chris.cmarket.Specifications.Merchants;

import java.util.List;

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

    /**
     * Creates a JPA Specification to filter by the "id_merchant" field using
     * multiple merchant IDs.
     *
     * @param ids a list of merchant IDs to filter by
     * @param <T> the entity type
     * @return a Specification that matches entities with any of the given merchant
     *         IDs
     */
    public static <T> Specification<T> whereId(List<Long> ids) {
        return (root, query, cb) -> {
            if (ids == null || ids.isEmpty()) {
                return null;
            }

            return root.get("id_merchant").in(ids);
        };
    }
}

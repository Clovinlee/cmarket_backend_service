package com.chris.cmarket.Specifications.Merchants;

import org.springframework.data.jpa.domain.Specification;

public class MerchantIdSpecification {
    public static <T> Specification<T> whereId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id_merchant"), id);
    }
}

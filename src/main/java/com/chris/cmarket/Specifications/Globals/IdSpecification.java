package com.chris.cmarket.Specifications.Globals;

import org.springframework.data.jpa.domain.Specification;

public class IdSpecification<T> {
    public static <T> Specification<T> whereId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}

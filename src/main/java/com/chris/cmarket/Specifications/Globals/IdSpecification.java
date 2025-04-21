package com.chris.cmarket.Specifications.Globals;

import org.springframework.data.jpa.domain.Specification;

public class IdSpecification<T> {
    /**
     * Creates a JPA Specification to filter entities by their ID.
     *
     * @param id the ID to filter by
     * @return a Specification filtering by ID
     */
    public static <T> Specification<T> whereId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}

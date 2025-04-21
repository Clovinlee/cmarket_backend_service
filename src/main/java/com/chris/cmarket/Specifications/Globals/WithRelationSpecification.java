package com.chris.cmarket.Specifications.Globals;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Fetch;

/**
 * Utility class for building JPA specifications that eagerly fetch related
 * entities.
 */
public class WithRelationSpecification {

    /**
     * Creates a specification to fetch one or more related entities using LEFT
     * JOINs.
     * Skips fetching when the query is a count query (e.g., for pagination).
     *
     * @param relations the paths of related entities to fetch (e.g., "rarity",
     *                  "productMerchants")
     * @param <T>       the root entity type
     * @return a JPA Specification that fetches the specified relations
     */
    public static <T> Specification<T> fetch(String... relations) {
        return (root, query, cb) -> {
            if (query.getResultType() != Long.class) {
                for (String relationPath : relations) {
                    fetchPath(root, relationPath);
                }
            }
            return cb.conjunction();
        };
    }

    /**
     * Recursively fetches nested relations using LEFT JOINs.
     *
     * @param root the root entity in the criteria query
     * @param path dot-separated path to the related entity (e.g.,
     *             "product.category")
     * @param <T>  the root entity type
     */
    private static <T> void fetchPath(Root<T> root, String path) {
        String[] parts = path.split("\\.");
        Fetch<?, ?> fetch = null;
        for (String part : parts) {
            fetch = (fetch == null)
                    ? root.fetch(part, JoinType.LEFT)
                    : fetch.fetch(part, JoinType.LEFT);
        }
    }
}

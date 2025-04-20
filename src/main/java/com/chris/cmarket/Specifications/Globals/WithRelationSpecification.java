package com.chris.cmarket.Specifications.Globals;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Fetch;

public class WithRelationSpecification {

    public static <T> Specification<T> fetch(String... relations) {
        return (root, query, cb) -> {
            // Avoid load relation on count query (e.g pagination count)
            if (query.getResultType() != Long.class) {
                for (String relationPath : relations) {
                    fetchPath(root, relationPath);
                }
            }
            return cb.conjunction();
        };
    }

    private static <T> void fetchPath(Root<T> root, String path) {
        String[] parts = path.split("\\.");
        Fetch<?, ?> fetch = null;
        // Start from root
        for (String part : parts) {
            if (fetch == null) {
                fetch = root.fetch(part, JoinType.LEFT);
            } else {
                fetch = fetch.fetch(part, JoinType.LEFT);
            }
        }
    }
}

package com.chris.cmarket.Common.Specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * Provides specifications for filtering entities by the "name" field.
 *
 * @param <T> The entity type.
 */
public class NameSpecification<T> {

    /**
     * Builds a specification to filter entities where the "name" matches exactly.
     *
     * @param name The name to match.
     * @param <T>  The entity type.
     * @return The specification or null if name is null.
     */
    public static <T> Specification<T> whereName(String name) {
        return (root, query, cb) -> null == name ? null : cb.equal(root.get("name"), name);
    }

    /**
     * Builds a specification to filter entities where the "name" contains the given
     * string (case-insensitive).
     *
     * @param name The partial name to search for.
     * @param <T>  The entity type.
     * @return The specification or null if name is null.
     */
    public static <T> Specification<T> nameLike(String name) {
        return (root, query, cb) -> null == name ? null
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}

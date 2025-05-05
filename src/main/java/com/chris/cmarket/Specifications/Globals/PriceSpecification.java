package com.chris.cmarket.Specifications.Globals;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

/**
 * Provides specifications for filtering entities by the "price" field.
 *
 * @param <T> The entity type.
 */
public class PriceSpecification<T> {

    /**
     * Builds a specification to filter entities with a price greater than or equal
     * to the given minimum.
     *
     * @param min The minimum price.
     * @param <T> The entity type.
     * @return The specification or null if min is null.
     */
    public static <T> Specification<T> withMin(BigDecimal min) {
        return (root, query, cb) -> min == null ? null : cb.greaterThanOrEqualTo(root.get("price"), min);
    }

    /**
     * Builds a specification to filter entities with a price less than or equal to
     * the given maximum.
     *
     * @param max The maximum price.
     * @param <T> The entity type.
     * @return The specification or null if max is null.
     */
    public static <T> Specification<T> withMax(BigDecimal max) {
        return (root, query, cb) -> max == null ? null : cb.lessThanOrEqualTo(root.get("price"), max);
    }
}

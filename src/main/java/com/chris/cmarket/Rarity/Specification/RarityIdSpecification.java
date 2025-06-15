package com.chris.cmarket.Rarity.Specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * Provides specifications for filtering entities by rarity ID.
 */
public class RarityIdSpecification {

    /**
     * Builds a specification to filter by rarity ID using the default column name
     * "idRarity".
     *
     * @param id  The rarity ID to match.
     * @param <T> The entity type.
     * @return The specification or null if ID is null.
     */
    public static <T> Specification<T> whereId(Long id) {
        return whereId(id, "idRarity");
    }

    /**
     * Builds a specification to filter by rarity ID using a custom column name.
     *
     * @param id         The rarity ID to match.
     * @param columnName The column name to use for filtering.
     * @param <T>        The entity type.
     * @return The specification or null if ID or column name is invalid.
     */
    public static <T> Specification<T> whereId(Long id, String columnName) {
        if (id == null || columnName == null || columnName.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.get(columnName), id);
    }
}

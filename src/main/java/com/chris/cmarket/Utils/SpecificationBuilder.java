package com.chris.cmarket.Utils;

import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class for building dynamic JPA {@link Specification} queries.
 *
 * @param <T> the entity type
 */
public class SpecificationBuilder<T> {

    private Specification<T> spec = Specification.where(null);

    /**
     * Adds a new {@link Specification} criteria to the current builder.
     * If the given specification is {@code null}, it will be ignored.
     *
     * @param spec the specification to add
     * @return the current builder instance for chaining
     */
    public SpecificationBuilder<T> addCriteria(Specification<T> spec) {
        if (spec != null) {
            this.spec = this.spec.and(spec);
        }

        return this;
    }

    /**
     * Adds a new {@link Specification} criteria to the current builder using OR
     * logic.
     * If the given specification is {@code null}, it will be ignored.
     *
     * @param spec the specification to add
     * @return the current builder instance for chaining
     */
    public SpecificationBuilder<T> addOrCriteria(Specification<T> spec) {
        if (spec != null) {
            this.spec = this.spec.or(spec);
        }

        return this;
    }

    /**
     * Builds the final {@link Specification} composed of all added criteria.
     *
     * @return the combined specification
     */
    public Specification<T> build() {
        return this.spec;
    }
}

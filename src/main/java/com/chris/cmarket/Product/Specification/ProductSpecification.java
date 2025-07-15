package com.chris.cmarket.Product.Specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.chris.cmarket.Merchant.Model.MerchantModel;
import com.chris.cmarket.ProductMerchant.Model.ProductMerchantModel;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

/**
 * Chris Note:
 * Multiple joins are expected when using withRelationSpecification to fetch
 * related entities.
 * JPA performs these joins to load related data in a single query.
 * <p>
 * The reason is because FETCH JOIN does not allow criteria of WHERE, as it will
 * only return the "correct" data
 * (for example, `employee where phone = 613`. Even
 * if employee has 2 phones, it wil only return phone with 613)
 * <p>
 * To avoid this, we do double JOIN:
 * First join to fetch data, second join to filter the joined data
 */
public class ProductSpecification {

    /**
     * Overload of {@link #hasMerchant(List, String)} using the default relation
     * name "productMerchants".
     *
     * @param ids list of merchant IDs to filter by
     * @param <T> the root entity type
     * @return a Specification that filters based on merchant IDs
     */
    public static <T> Specification<T> hasMerchant(List<Long> ids) {
        return hasMerchant(ids, "productMerchants");
    }

    /**
     * Filters entities that are related to merchants
     *
     * @param ids          list of merchant IDs to filter by
     * @param relationName the field name of the join entity (e.g.,
     *                     "productMerchants")
     * @param <T>          the root entity type
     * @return a Specification that filters based on merchant IDs
     */
    public static <T> Specification<T> hasMerchant(List<Long> ids, String relationName) {
        if (null == ids || ids.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            query.distinct(true);

            Join<T, ProductMerchantModel> productMerchantJoin = root.join(relationName, JoinType.INNER);
            Join<ProductMerchantModel, MerchantModel> merchantJoin = productMerchantJoin.join("merchant",
                    JoinType.INNER);

            return merchantJoin.get("id").in(ids);
        };
    }
}

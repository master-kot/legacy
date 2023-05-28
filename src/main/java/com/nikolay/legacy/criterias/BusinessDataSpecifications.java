package com.nikolay.legacy.criterias;

import com.nikolay.legacy.entities.BusinessData;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class BusinessDataSpecifications {

    public static Specification<BusinessData> eventHasSearchCriteria(BusinessDataSearchCriteria searchCriteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchCriteria.getId() != null && searchCriteria.getId() > 0) {
                predicates.add(cb.equal(root.get("id"), searchCriteria.getId()));
            }
            if (!searchCriteria.getType().isEmpty()) {
                predicates.add(cb.equal(root.get("type"), searchCriteria.getType()));
            }
            if (!searchCriteria.getBusinessValue().isEmpty()) {
                predicates.add(cb.equal(root.get("business_value"), searchCriteria.getBusinessValue()));
            }
            if (searchCriteria.getCreatedAt() != null) {
                predicates.add(cb.equal(root.get("created_ad"), searchCriteria.getCreatedAt()));
            }
            if (searchCriteria.getUpdatedAt() != null) {
                predicates.add(cb.equal(root.get("updated_ad"), searchCriteria.getUpdatedAt()));
            }
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }
}

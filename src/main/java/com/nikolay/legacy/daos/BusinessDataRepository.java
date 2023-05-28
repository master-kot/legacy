package com.nikolay.legacy.daos;

import com.nikolay.legacy.entities.BusinessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDataRepository extends JpaRepository<BusinessData, Long>,
        JpaSpecificationExecutor<BusinessData> {
}

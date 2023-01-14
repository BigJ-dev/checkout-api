package com.host.checkout.repository;

import com.host.checkout.data.entity.PricingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    PricingRule findPricingRuleByCode(String code);
    boolean existsByCode(String code);
}

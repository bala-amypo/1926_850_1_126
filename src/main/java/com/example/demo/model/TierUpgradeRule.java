package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "tier_upgrade_rule")
public class TierUpgradeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromTier;

    @Column(nullable = false)
    private String toTier;

    private Double minSpend;
    private Integer minVisits;
    private Boolean active;

    public TierUpgradeRule() {}

    public void setId(Long id) { this.id = id; }
    public void setFromTier(String fromTier) { this.fromTier = fromTier; }
    public void setToTier(String toTier) { this.toTier = toTier; }
    public void setMinSpend(Double minSpend) { this.minSpend = minSpend; }
    public void setMinVisits(Integer minVisits) { this.minVisits = minVisits; }
    public void setActive(Boolean active) { this.active = active; }

    public Long getId() { return id; }
    public String getFromTier() { return fromTier; }
    public String getToTier() { return toTier; }
    public Double getMinSpend() { return minSpend; }
    public Integer getMinVisits() { return minVisits; }
    public Boolean getActive() { return active; }
}
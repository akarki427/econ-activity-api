package com.dxc.api.econ_activity_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nace_details")
public class Nace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long orderNumber;
    private Long level;
    private String code;
    private String parent;
    private String description;
    private String inclusion;
    private String additionalInclusion;
    private String rulings;
    private String exclusion;
    private String reference;

    public Nace(Long id, Long orderNumber, Long level, String code, String parent, String description, String inclusion, String additionalInclusion, String rulings, String exclusion, String reference) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.level = level;
        this.code = code;
        this.parent = parent;
        this.description = description;
        this.inclusion = inclusion;
        this.additionalInclusion = additionalInclusion;
        this.rulings = rulings;
        this.exclusion = exclusion;
        this.reference = reference;
    }

    public Nace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInclusion() {
        return inclusion;
    }

    public void setInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    public String getAdditionalInclusion() {
        return additionalInclusion;
    }

    public void setAdditionalInclusion(String additionalInclusion) {
        this.additionalInclusion = additionalInclusion;
    }

    public String getRulings() {
        return rulings;
    }

    public void setRulings(String rulings) {
        this.rulings = rulings;
    }

    public String getExclusion() {
        return exclusion;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

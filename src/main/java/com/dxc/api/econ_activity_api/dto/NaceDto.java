package com.dxc.api.econ_activity_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NaceDto {
        @JsonProperty("Order")
        @NotNull(message = "Order cannot be null")
        private Long orderNumber;
        @JsonProperty("Level")
        private Long level;
        @JsonProperty("Code")
        @NotBlank(message = "Code cannot be empty")
        private String code;
        @JsonProperty("Parent")
        private String parent;
        @JsonProperty("Description")
        @NotBlank(message = "Description cannot be blank")
        private String description;
        @JsonProperty("Item_Inclusion")
        private String inclusion;
        @JsonProperty("Additional_Item_Inclusion")
        private String additionalInclusion;
        @JsonProperty("Rulings")
        private String rulings;
        @JsonProperty("Item_Exclusions")
        private String exclusion;
        @JsonProperty("Reference_Rev4_ISIC")
        private String reference;

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

/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.model.type;

import java.math.BigDecimal;
import java.util.Collection;

import javax.annotation.Generated;

import com.ibm.watsonhealth.fhir.model.visitor.Visitor;

/**
 * <p>
 * A rational number with implicit precision
 * </p>
 */
@Generated("com.ibm.watsonhealth.fhir.tools.CodeGenerator")
public class Decimal extends Element {
    private final BigDecimal value;

    private Decimal(Builder builder) {
        super(builder);
        value = builder.value;
    }

    /**
     * <p>
     * The actual value
     * </p>
     * 
     * @return
     *     An immutable object of type {@link BigDecimal}.
     */
    public BigDecimal getValue() {
        return value;
    }

    public static Decimal of(BigDecimal value) {
        return Decimal.builder().value(value).build();
    }

    public static Decimal of(Number value) {
        return Decimal.builder().value(value.toString()).build();
    }

    public static Decimal of(java.lang.String value) {
        return Decimal.builder().value(value).build();
    }

    @Override
    public void accept(java.lang.String elementName, Visitor visitor) {
        if (visitor.preVisit(this)) {
            visitor.visitStart(elementName, this);
            if (visitor.visit(elementName, this)) {
                // visit children
                accept(id, "id", visitor);
                accept(extension, "extension", visitor, Extension.class);
                accept(value, "value", visitor);
            }
            visitor.visitEnd(elementName, this);
            visitor.postVisit(this);
        }
    }

    @Override
    public Builder toBuilder() {
        return new Builder().from(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Element.Builder {
        // optional
        private BigDecimal value;

        private Builder() {
            super();
        }

        /**
         * <p>
         * unique id for the element within a resource (for internal references)
         * </p>
         * 
         * @param id
         *     xml:id (or equivalent in JSON)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder id(java.lang.String id) {
            return (Builder) super.id(id);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * </p>
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * </p>
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * <p>
         * The actual value
         * </p>
         * 
         * @param value
         *     Primitive value for decimal
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder value(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder value(java.lang.String value) {
            this.value = new BigDecimal(value);
            return this;
        }

        @Override
        public Decimal build() {
            return new Decimal(this);
        }

        private Builder from(Decimal decimal) {
            id = decimal.id;
            extension.addAll(decimal.extension);
            value = decimal.value;
            return this;
        }
    }
}
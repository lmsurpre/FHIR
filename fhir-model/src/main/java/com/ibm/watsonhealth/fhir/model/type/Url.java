/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.model.type;

import java.util.Collection;
import java.util.regex.Pattern;

import javax.annotation.Generated;

import com.ibm.watsonhealth.fhir.model.util.ValidationSupport;

/**
 * <p>
 * A URI that is a literal reference
 * </p>
 */
@Generated("com.ibm.watsonhealth.fhir.tools.CodeGenerator")
public class Url extends Uri {
    private static final Pattern PATTERN = Pattern.compile("\\S*");

    private Url(Builder builder) {
        super(builder);
        ValidationSupport.checkMinLength(value);
        ValidationSupport.checkValue(value, PATTERN);
    }

    public static Url of(java.lang.String value) {
        return Url.builder().value(value).build();
    }

    public static Uri uri(java.lang.String value) {
        return Url.builder().value(value).build();
    }

    @Override
    public Builder toBuilder() {
        return new Builder().from(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Uri.Builder {
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
         * Primitive value for url
         * </p>
         * 
         * @param value
         *     Primitive value for url
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder value(java.lang.String value) {
            return (Builder) super.value(value);
        }

        @Override
        public Url build() {
            return new Url(this);
        }

        private Builder from(Url url) {
            id = url.id;
            extension.addAll(url.extension);
            value = url.value;
            return this;
        }
    }
}
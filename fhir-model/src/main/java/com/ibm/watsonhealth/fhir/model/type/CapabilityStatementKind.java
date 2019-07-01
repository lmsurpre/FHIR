/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.model.type;

import java.util.Collection;

public class CapabilityStatementKind extends Code {
    /**
     * Instance
     */
    public static final CapabilityStatementKind INSTANCE = CapabilityStatementKind.of(ValueSet.INSTANCE);

    /**
     * Capability
     */
    public static final CapabilityStatementKind CAPABILITY = CapabilityStatementKind.of(ValueSet.CAPABILITY);

    /**
     * Requirements
     */
    public static final CapabilityStatementKind REQUIREMENTS = CapabilityStatementKind.of(ValueSet.REQUIREMENTS);

    private CapabilityStatementKind(Builder builder) {
        super(builder);
    }

    public static CapabilityStatementKind of(java.lang.String value) {
        return CapabilityStatementKind.builder().value(value).build();
    }

    public static CapabilityStatementKind of(ValueSet value) {
        return CapabilityStatementKind.builder().value(value).build();
    }

    public static String string(java.lang.String value) {
        return CapabilityStatementKind.builder().value(value).build();
    }

    public static Code code(java.lang.String value) {
        return CapabilityStatementKind.builder().value(value).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.id = id;
        builder.extension.addAll(extension);
        builder.value = value;
        return builder;
    }

    public static class Builder extends Code.Builder {
        private Builder() {
            super();
        }

        @Override
        public Builder id(java.lang.String id) {
            return (Builder) super.id(id);
        }

        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        @Override
        public Builder value(java.lang.String value) {
            return (Builder) super.value(ValueSet.from(value).value());
        }

        public Builder value(ValueSet value) {
            return (Builder) super.value(value.value());
        }

        @Override
        public CapabilityStatementKind build() {
            return new CapabilityStatementKind(this);
        }
    }

    public enum ValueSet {
        /**
         * Instance
         */
        INSTANCE("instance"),

        /**
         * Capability
         */
        CAPABILITY("capability"),

        /**
         * Requirements
         */
        REQUIREMENTS("requirements");

        private final java.lang.String value;

        ValueSet(java.lang.String value) {
            this.value = value;
        }

        public java.lang.String value() {
            return value;
        }

        public static ValueSet from(java.lang.String value) {
            for (ValueSet c : ValueSet.values()) {
                if (c.value.equals(value)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }
}
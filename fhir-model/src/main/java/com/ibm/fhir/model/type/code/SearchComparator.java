/*
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.model.type.code;

import com.ibm.fhir.model.type.Code;
import com.ibm.fhir.model.type.Extension;
import com.ibm.fhir.model.type.String;

import java.util.Collection;
import java.util.Objects;

import javax.annotation.Generated;

@Generated("com.ibm.fhir.tools.CodeGenerator")
public class SearchComparator extends Code {
    /**
     * Equals
     */
    public static final SearchComparator EQ = SearchComparator.builder().value(ValueSet.EQ).build();

    /**
     * Not Equals
     */
    public static final SearchComparator NE = SearchComparator.builder().value(ValueSet.NE).build();

    /**
     * Greater Than
     */
    public static final SearchComparator GT = SearchComparator.builder().value(ValueSet.GT).build();

    /**
     * Less Than
     */
    public static final SearchComparator LT = SearchComparator.builder().value(ValueSet.LT).build();

    /**
     * Greater or Equals
     */
    public static final SearchComparator GE = SearchComparator.builder().value(ValueSet.GE).build();

    /**
     * Less of Equal
     */
    public static final SearchComparator LE = SearchComparator.builder().value(ValueSet.LE).build();

    /**
     * Starts After
     */
    public static final SearchComparator SA = SearchComparator.builder().value(ValueSet.SA).build();

    /**
     * Ends Before
     */
    public static final SearchComparator EB = SearchComparator.builder().value(ValueSet.EB).build();

    /**
     * Approximately
     */
    public static final SearchComparator AP = SearchComparator.builder().value(ValueSet.AP).build();

    private volatile int hashCode;

    private SearchComparator(Builder builder) {
        super(builder);
    }

    public static SearchComparator of(ValueSet value) {
        switch (value) {
        case EQ:
            return EQ;
        case NE:
            return NE;
        case GT:
            return GT;
        case LT:
            return LT;
        case GE:
            return GE;
        case LE:
            return LE;
        case SA:
            return SA;
        case EB:
            return EB;
        case AP:
            return AP;
        default:
            throw new IllegalStateException(value.name());
        }
    }

    public static SearchComparator of(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    public static String string(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    public static Code code(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SearchComparator other = (SearchComparator) obj;
        return Objects.equals(id, other.id) && Objects.equals(extension, other.extension) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(id, extension, value);
            hashCode = result;
        }
        return result;
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.id(id);
        builder.extension(extension);
        builder.value(value);
        return builder;
    }

    public static Builder builder() {
        return new Builder();
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
            return (value != null) ? (Builder) super.value(ValueSet.from(value).value()) : this;
        }

        public Builder value(ValueSet value) {
            return (value != null) ? (Builder) super.value(value.value()) : this;
        }

        @Override
        public SearchComparator build() {
            return new SearchComparator(this);
        }
    }

    public enum ValueSet {
        /**
         * Equals
         */
        EQ("eq"),

        /**
         * Not Equals
         */
        NE("ne"),

        /**
         * Greater Than
         */
        GT("gt"),

        /**
         * Less Than
         */
        LT("lt"),

        /**
         * Greater or Equals
         */
        GE("ge"),

        /**
         * Less of Equal
         */
        LE("le"),

        /**
         * Starts After
         */
        SA("sa"),

        /**
         * Ends Before
         */
        EB("eb"),

        /**
         * Approximately
         */
        AP("ap");

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

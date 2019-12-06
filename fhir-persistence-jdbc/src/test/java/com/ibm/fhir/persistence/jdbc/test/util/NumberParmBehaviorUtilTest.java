/*
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.persistence.jdbc.test.util;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibm.fhir.config.FHIRRequestContext;
import com.ibm.fhir.exception.FHIRException;
import com.ibm.fhir.model.resource.Basic;
import com.ibm.fhir.model.resource.MolecularSequence;
import com.ibm.fhir.persistence.exception.FHIRPersistenceException;
import com.ibm.fhir.persistence.jdbc.util.JDBCQueryBuilder;
import com.ibm.fhir.persistence.jdbc.util.type.NumberParmBehaviorUtil;
import com.ibm.fhir.search.SearchConstants;
import com.ibm.fhir.search.parameters.Parameter;
import com.ibm.fhir.search.parameters.ParameterValue;

public class NumberParmBehaviorUtilTest {
    private static final Logger log = java.util.logging.Logger.getLogger(NumberParmBehaviorUtilTest.class.getName());
    private static final Level LOG_LEVEL = Level.INFO;

    private ParameterValue generateParameterValue(String value, SearchConstants.Prefix prefix) {
        ParameterValue parameterValue = new ParameterValue();
        parameterValue.setPrefix(prefix);
        parameterValue.setValueNumber(new BigDecimal(value));
        return parameterValue;
    }

    private Parameter generateParameter(SearchConstants.Prefix prefix, SearchConstants.Modifier modifier,
            String code, String... values) {
        Parameter parameter = new Parameter(SearchConstants.Type.NUMBER, code, modifier, null);
        for (String value : values) {
            parameter.getValues().add(generateParameterValue(value, prefix));
        }
        return parameter;
    }

    private Parameter generateParameter(SearchConstants.Prefix prefix, SearchConstants.Modifier modifier,
            String value) {
        return generateParameter(prefix, modifier, "precision", new String[] { value });
    }

    private Parameter generateParameter(SearchConstants.Prefix prefix, SearchConstants.Modifier modifier,
            String... values) {
        return generateParameter(prefix, modifier, "precision", values);
    }

    private void runTest(Parameter queryParm, List<Object> expectedBindVariables, String expectedSql)
            throws FHIRPersistenceException {
        runTest(queryParm, expectedBindVariables, expectedSql, "Basic", Basic.class);
    }

    private void runLowerUpperTest(String value, String lower, String upper) {
        BigDecimal decimal = new BigDecimal(value);
        String actualLower = Double.toString(NumberParmBehaviorUtil.generateLowerBound(decimal).doubleValue());
        assertEquals(actualLower, lower);

        String actualUpper = Double.toString(NumberParmBehaviorUtil.generateUpperBound(decimal).doubleValue());
        assertEquals(actualUpper, upper);
    }

    public void runSignificantDigitsTest(String value, int significantDigits) {
        assertEquals(NumberParmBehaviorUtil.calculateSignificantFigures(new BigDecimal(value)), significantDigits);
    }

    private void runTest(Parameter queryParm, List<Object> expectedBindVariables, String expectedSql,
            String tableAlias, Class<?> resourceType)
            throws FHIRPersistenceException {
        StringBuilder actualWhereClauseSegment = new StringBuilder();
        List<Object> actualBindVariables = new ArrayList<>();

        JDBCQueryBuilder queryBuilder = new JDBCQueryBuilder(null, null);
        NumberParmBehaviorUtil.executeBehavior(actualWhereClauseSegment, queryParm, actualBindVariables, resourceType,
                tableAlias, queryBuilder);

        if (log.isLoggable(LOG_LEVEL)) {
            log.info("whereClauseSegment -> " + actualWhereClauseSegment.toString());
            log.info("bind variables -> " + actualBindVariables);
        }
        assertEquals(actualWhereClauseSegment.toString(), expectedSql);

        for (Object o : expectedBindVariables) {
            actualBindVariables.remove(o);
        }

        if (log.isLoggable(LOG_LEVEL)) {
            log.info("leftover - bind variables -> " + actualBindVariables);
        }
        assertEquals(actualBindVariables.size(), 0);

    }

    @BeforeClass
    public static void before() throws FHIRException {
        FHIRRequestContext.get().setTenantId("number");
    }

    @AfterClass
    public static void after() throws FHIRException {
        FHIRRequestContext.get().setTenantId("default");
    }

    @Test
    public void testPrecisionWithExact() throws FHIRPersistenceException {
        // The spec states: 
        // When a comparison prefix in the set lgt, lt, ge, le, sa & eb is provided, 
        // the implicit precision of the number is ignored, and they are treated as 
        // if they have arbitrarily high precision
        // However there is no 'lgt', means gt? (documented in https://jira.hl7.org/browse/FHIR-21216) 

        // gt - Greater Than
        Parameter queryParm = generateParameter(SearchConstants.Prefix.GT, null, "1e3");
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1E+3"));
        String expectedSql = " AND (Basic.NUMBER_VALUE > ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);

        // lt - Less Than
        queryParm             = generateParameter(SearchConstants.Prefix.LT, null, "1e3");
        expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1E+3"));
        expectedSql = " AND (Basic.NUMBER_VALUE < ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);

        // ge - Greater than Equal
        queryParm             = generateParameter(SearchConstants.Prefix.GE, null, "1e3");
        expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1E+3"));
        expectedSql = " AND (Basic.NUMBER_VALUE >= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);

        // le - Less than Equal
        queryParm             = generateParameter(SearchConstants.Prefix.LE, null, "1e3");
        expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1E+3"));
        expectedSql = " AND (Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);

        // sa - starts after
        queryParm             = generateParameter(SearchConstants.Prefix.SA, null, "1e3");
        expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1E+3"));
        expectedSql = " AND (Basic.NUMBER_VALUE > ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);

        // eb - Ends before
        queryParm             = generateParameter(SearchConstants.Prefix.EB, null, "1e3");
        expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1E+3"));
        expectedSql = " AND (Basic.NUMBER_VALUE < ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test(expectedExceptions = { FHIRPersistenceException.class })
    public void testPrecisionIntegerWithStartsAfter() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.SA, null, "window-end", new String[] { "1" });
        List<Object> expectedBindVariables = new ArrayList<>();
        String expectedSql = "THROWS EXCEPTION";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }

    @Test(expectedExceptions = { FHIRPersistenceException.class })
    public void testPrecisionIntegerWithEndsBefore() throws FHIRPersistenceException {
        // eb - ends before with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.SA, null, "window-end", new String[] { "1" });
        List<Object> expectedBindVariables = new ArrayList<>();
        String expectedSql = "THROWS EXCEPTION";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }

    @Test
    public void testPrecisionWithEqual() throws FHIRPersistenceException {
        // in this case, our code if missing Prefix, it injects EQ.
        // therefore this test case tests two conditions 
        // Condition:
        //  [parameter]=100
        //  [parameter]=eq100

        // expectedBindVariables are pivoted on 100
        // Precision: 3 
        // Implied Range: [99.5 ... 100.5)
        // Exclusive and Inclusive

        Parameter queryParm = generateParameter(SearchConstants.Prefix.EQ, null, "100");
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal(99.5));
        expectedBindVariables.add(new BigDecimal(100.5));
        String expectedSql = " AND (Basic.NUMBER_VALUE > ? AND Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test
    public void testPrecisionWithMultipleValuesForEqual() throws FHIRPersistenceException {
        // in this case, our code if missing Prefix, it injects EQ.
        // therefore this test case tests two conditions 
        // Condition:
        //  [parameter]=100,1.00
        //  [parameter]=eq100,1.00

        // expectedBindVariables are pivoted on 100 and 1.00
        // Precision: 3 and 5
        // Implied Range: [99.5 ... 100.5) [0.995 ... 1.005)
        // Exclusive and Inclusive

        Parameter queryParm = generateParameter(SearchConstants.Prefix.EQ, null, new String[] { "100", "1.00" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("99.5"));
        expectedBindVariables.add(new BigDecimal("100.5"));
        expectedBindVariables.add(new BigDecimal("0.995"));
        expectedBindVariables.add(new BigDecimal("1.005"));
        String expectedSql =
                " AND (Basic.NUMBER_VALUE > ? AND Basic.NUMBER_VALUE <= ?) OR (Basic.NUMBER_VALUE > ? AND Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test
    public void testPrecisionWithNotEqual() throws FHIRPersistenceException {
        // Condition:
        //  [parameter]=ne100

        // expectedBindVariables are pivoted on 100
        // Precision: 3 
        // Implied Range: <= 99.5 OR 100.5 >
        // Exclusive and Inclusive

        Parameter queryParm = generateParameter(SearchConstants.Prefix.NE, null, "100");
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal(99.5));
        expectedBindVariables.add(new BigDecimal(100.5));
        String expectedSql = " AND (Basic.NUMBER_VALUE <= ? OR Basic.NUMBER_VALUE > ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test
    public void testPrecisionWithApprox() throws FHIRPersistenceException {
        // Condition:
        //  [parameter]=ap100

        // expectedBindVariables are pivoted on 100
        // Precision is implied by the use of ap
        // Implied Range: (89.55 ... 110.55)

        Parameter queryParm = generateParameter(SearchConstants.Prefix.AP, null, "100");
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("89.55"));
        expectedBindVariables.add(new BigDecimal("110.55"));
        String expectedSql = " AND (Basic.NUMBER_VALUE >= ? AND Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }
    
    @Test
    public void testPrecisionWithApproxNumberOne() throws FHIRPersistenceException {
        // Condition:
        //  [parameter]=ap1

        // expectedBindVariables are pivoted on 100
        // Precision is implied by the use of ap
        // Implied Range: (.45 ... 1.65)

        Parameter queryParm = generateParameter(SearchConstants.Prefix.AP, null, "1");
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("0.45"));
        expectedBindVariables.add(new BigDecimal("1.65"));
        String expectedSql = " AND (Basic.NUMBER_VALUE >= ? AND Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test
    public void testPrecisionWithMultipleSameApprox() throws FHIRPersistenceException {
        // Condition:
        //  [parameter]=ap100,ap100

        // expectedBindVariables are pivoted on 100
        // Precision is implied by the use of ap
        // Implied Range: (89.55 ... 110.55)

        // It should de-dupe

        Parameter queryParm = generateParameter(SearchConstants.Prefix.AP, null, new String[] { "100", "100" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("89.55"));
        expectedBindVariables.add(new BigDecimal("110.55"));
        String expectedSql = " AND (Basic.NUMBER_VALUE >= ? AND Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test
    public void testPrecisionWithMultipleDifferentApprox() throws FHIRPersistenceException {
        // Condition:
        //  [parameter]=ap100,ap100.00

        // expectedBindVariables are pivoted on 100
        // Precision is implied by the use of ap
        // Implied Range: (90 ... 110)

        // It should NOT de-dupe

        Parameter queryParm = generateParameter(SearchConstants.Prefix.AP, null, new String[] { "100", "100.00" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("89.55"));
        expectedBindVariables.add(new BigDecimal("110.55"));
        expectedBindVariables.add(new BigDecimal("89.9955"));
        expectedBindVariables.add(new BigDecimal("110.0055"));
        String expectedSql =
                " AND (Basic.NUMBER_VALUE >= ? AND Basic.NUMBER_VALUE <= ?) OR (Basic.NUMBER_VALUE >= ? AND Basic.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql);
    }

    @Test
    public void testLowerAndUpperBounds() {
        // [parameter]=1.00
        // range [0.005 ... 1.005)
        runLowerUpperTest("1.00", "0.995", "1.005");

        // [parameter]=100.00
        // significant figures precision = 5
        // range [99.995 ... 100.005)
        runLowerUpperTest("100.00", "99.995", "100.005");

        // [parameter]=100
        // significant figures precision = 3 
        // range [99.5 ... 100.5)
        runLowerUpperTest("100", "99.5", "100.5");

        // [parameter]=1e2
        // Value: 1e2
        // significant figures precision = 1
        // Implied Range: [95 ... 105)
        runLowerUpperTest("1e2", "95.0", "105.0");

        // [parameter]=1e3
        // Value: 1e3
        // Implied Range: [999.5 ... 100.5)
        runLowerUpperTest("1e3", "999.5", "1000.5");

        // [parameter]=1.0e2
        // Value: 1.0e2
        // Implied Range: [50 ... 150)
        runLowerUpperTest("1.0e2", "50.0", "150.0");

        // [parameter]=1.00e2
        // Value: 1.00e2
        // Implied Range: [99.5 ... 100.5)
        runLowerUpperTest("1.00e2", "99.5", "100.5");

        // [parameter]=1.02e2
        // Value: 1.02e2
        // Implied Range: [105.0 ... 115.0)
        runLowerUpperTest("1.1e2", "60.0", "160.0");
    }

    @Test
    public void testSignificantFiguresPrecision() {
        runSignificantDigitsTest("100.00", 5);
        runSignificantDigitsTest("100", 3);

        // Based on examples from https://en.wikipedia.org/wiki/Significant_figures
        runSignificantDigitsTest("12.3450", 6);
        runSignificantDigitsTest("12.345", 5);
        runSignificantDigitsTest("12.34", 4);
        runSignificantDigitsTest("12.3", 3);
        runSignificantDigitsTest("12.", 2);

        // Different than the standard approach, we're going for 2. 
        runSignificantDigitsTest("10", 2);

        runSignificantDigitsTest("0.01234500", 7);
        runSignificantDigitsTest("0.0123450", 6);
        runSignificantDigitsTest("0.012345", 5);
        runSignificantDigitsTest("0.01234", 4);
        runSignificantDigitsTest("0.0123", 3);
        runSignificantDigitsTest("0.012", 2);
        runSignificantDigitsTest("0.01", 1);
        runSignificantDigitsTest("0.0", 1);
        runSignificantDigitsTest("0", 1);

        // Demonstrates significant digits as in the specification.
        runSignificantDigitsTest("1e1", 0);
        runSignificantDigitsTest("1e2", -1);
        runSignificantDigitsTest("1e3", -2);
        runSignificantDigitsTest("1e4", -3);
    }

    @Test(expectedExceptions = {})
    public void testPrecisionIntegerWithEQ() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.EQ, null, "window-end", new String[] { "1" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1"));
        String expectedSql = " AND (MolecularSequence.NUMBER_VALUE = ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }

    @Test(expectedExceptions = {})
    public void testPrecisionIntegerWithNE() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.NE, null, "window-end", new String[] { "1" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("1"));
        String expectedSql = " AND (MolecularSequence.NUMBER_VALUE <> ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }

    @Test(expectedExceptions = {})
    public void testPrecisionIntegerWithEQNotInt() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.EQ, null, "window-end", new String[] { "1.0" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("0.95"));
        expectedBindVariables.add(new BigDecimal("1.05"));
        String expectedSql = " AND (MolecularSequence.NUMBER_VALUE > ? AND MolecularSequence.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }

    @Test(expectedExceptions = {})
    public void testPrecisionIntegerWithNENotInt() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.NE, null, "window-end", new String[] { "1.0" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("0.95"));
        expectedBindVariables.add(new BigDecimal("1.05"));
        String expectedSql = " AND (MolecularSequence.NUMBER_VALUE <= ? OR MolecularSequence.NUMBER_VALUE > ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }
    
    @Test(expectedExceptions = {})
    public void testPrecisionIntegerWithEQNotIntExp() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.EQ, null, "window-end", new String[] { "1e2" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("95"));
        expectedBindVariables.add(new BigDecimal("105"));
        String expectedSql = " AND (MolecularSequence.NUMBER_VALUE > ? AND MolecularSequence.NUMBER_VALUE <= ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }

    @Test(expectedExceptions = {})
    public void testPrecisionIntegerWithNENotIntExp() throws FHIRPersistenceException {
        // sa - starts after with integer, and it's not supported
        Parameter queryParm = generateParameter(SearchConstants.Prefix.NE, null, "window-end", new String[] { "1e2" });
        List<Object> expectedBindVariables = new ArrayList<>();
        expectedBindVariables.add(new BigDecimal("95"));
        expectedBindVariables.add(new BigDecimal("105"));
        String expectedSql = " AND (MolecularSequence.NUMBER_VALUE <= ? OR MolecularSequence.NUMBER_VALUE > ?))";
        runTest(queryParm,
                expectedBindVariables,
                expectedSql, "MolecularSequence", MolecularSequence.class);
    }
}
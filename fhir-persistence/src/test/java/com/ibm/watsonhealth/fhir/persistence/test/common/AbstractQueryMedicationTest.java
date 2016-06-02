/**
 * (C) Copyright IBM Corp. 2016,2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.persistence.test.common;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.ibm.watsonhealth.fhir.model.Medication;
import com.ibm.watsonhealth.fhir.model.Resource;
import com.ibm.watsonhealth.fhir.search.Parameter;
import com.ibm.watsonhealth.fhir.search.util.SearchUtil;

/**
 *  This class contains a collection of tests that will be run against
 *  each of the various persistence layer implementations.
 *  There will be a subclass in each persistence project.
 */
public abstract class AbstractQueryMedicationTest extends AbstractPersistenceTest {
	
    /**
     * Tests the FHIRPersistenceCloudantImpl create API for a Medication.
     * 
     * @throws Exception
     */
    @Test(groups = { "persistence", "create", "medication" })
    public void testCreateMedication() throws Exception {
    	 
    	Medication medication = readResource(Medication.class, "medicationexample4.canonical.json");

    	persistence.create(medication);
        assertNotNull(medication);
        assertNotNull(medication.getId());
        assertNotNull(medication.getId().getValue());
        assertNotNull(medication.getMeta());
        assertNotNull(medication.getMeta().getVersionId().getValue());
        assertEquals("1", medication.getMeta().getVersionId().getValue());
    	 
    }    
	
	/**
	 * Tests a query with a resource type but without any query parameters. This should yield all the resources created so far.
	 * @throws Exception
	 */
	@Test(groups = { "persistence", "search", "medication" }, dependsOnMethods = { "testCreateMedication" })
	public void testMedicationQuery_001() throws Exception {
		
		List<Parameter> searchParms = new ArrayList<>();
				
		List<Resource> resources = persistence.search(Medication.class, searchParms);
		assertNotNull(resources);
		assertTrue(resources.size() != 0);
	}	
	
	/**
	 * Tests a query for a Medication with manufacturer = 'http://www.a-smeds.com/fhirresource/1' which should yield correct results
	 * @throws Exception
	 */
	@Test(groups = { "persistence", "search", "medication", "referenceParam" }, dependsOnMethods = { "testCreateMedication" })
	public void testMedicationQuery_002() throws Exception {
		
		String parmName = "manufacturer";
		String parmValue = "http://www.a-smeds.com/fhirresource/1";
		Class<? extends Resource> resourceType = Medication.class;
        Map<String, List<String>> queryParms = new HashMap<String, List<String>>();
		
		queryParms.put(parmName, Collections.singletonList(parmValue));
		List<Parameter> searchParms = SearchUtil.parseQueryParameters(resourceType, queryParms);
		List<Resource> resources = persistence.search(Medication.class, searchParms);
		assertNotNull(resources);
		assertTrue(resources.size() != 0);
		assertEquals(((Medication)resources.get(0)).getManufacturer().getReference().getValue(),"http://www.a-smeds.com/fhirresource/1");
	}
	
	/**
	 * Tests a query for a Medication with family name = 'Non-existent' which should yield no results
	 * @throws Exception
	 */
	@Test(groups = { "persistence", "search", "medication", "referenceParam" }, dependsOnMethods = { "testCreateMedication" })
	public void testMedicationQuery_003() throws Exception {
		
		String parmName = "manufacturer";
		String parmValue = "http://www.a-smeds.com/fhirresource1";
		Class<? extends Resource> resourceType = Medication.class;
        Map<String, List<String>> queryParms = new HashMap<String, List<String>>();
		
		queryParms.put(parmName, Collections.singletonList(parmValue));
		List<Parameter> searchParms = SearchUtil.parseQueryParameters(resourceType, queryParms);
		List<Resource> resources = persistence.search(Medication.class, searchParms);
		assertNotNull(resources);
		assertTrue(resources.size() == 0);
	}
	
	/**
	 * Tests a query for a Medication with content = 'MedicationExample14' which should yield correct results
	 * @throws Exception
	 */
	@Test(groups = { "persistence", "search", "medication", "referenceParam" }, dependsOnMethods = { "testCreateMedication" })
	public void testMedicationQuery_004() throws Exception {
		
		String parmName = "content";
		String parmValue = "MedicationExample14";
		Class<? extends Resource> resourceType = Medication.class;
        Map<String, List<String>> queryParms = new HashMap<String, List<String>>();
		
		queryParms.put(parmName, Collections.singletonList(parmValue));
		List<Parameter> searchParms = SearchUtil.parseQueryParameters(resourceType, queryParms);
		List<Resource> resources = persistence.search(Medication.class, searchParms);
		assertNotNull(resources);
		assertTrue(resources.size() != 0);
		assertEquals(((Medication)resources.get(0)).getPackage().getContent().get(0).getItem().getReference().getValue(),"MedicationExample14");
	}	
		
	/**
	 * Tests a query for a Medication with ingredient = 'Amoxicillin' which should yield correct results
	 * @throws Exception
	 */
	@Test(groups = { "persistence", "search", "medication", "referenceParam" }, dependsOnMethods = { "testCreateMedication" })
	public void testMedicationQuery_005() throws Exception {
		
		String parmName = "ingredient";
		String parmValue = "Amoxicillin";
		Class<? extends Resource> resourceType = Medication.class;
        Map<String, List<String>> queryParms = new HashMap<String, List<String>>();
		
		queryParms.put(parmName, Collections.singletonList(parmValue));
		List<Parameter> searchParms = SearchUtil.parseQueryParameters(resourceType, queryParms);
		List<Resource> resources = persistence.search(Medication.class, searchParms);
		assertNotNull(resources);
		assertTrue(resources.size() != 0);
		assertEquals(((Medication)resources.get(0)).getProduct().getIngredient().get(0).getItem().getReference().getValue(),"Amoxicillin");
	}
}
/**
 * 
 */
package com.github.podd.ontology.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.podd.ontologies.CropOntologyID;
import com.github.podd.ontologies.CropOntologyScraper;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 * 
 */
public class CropOntologyScraperTest
{
    
    private CropOntologyScraper testScraper;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        testScraper = new CropOntologyScraper();
    }
    
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        testScraper = null;
    }
    
    @Test
    public final void testGetAllOntologies() throws Exception
    {
        Map<String, Set<CropOntologyID>> allCropOntologies = testScraper.getAllCropOntologies();
        
        assertTrue(allCropOntologies.size() > 0);
    }
    
}

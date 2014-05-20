/**
 * 
 */
package com.github.podd.ontology.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Parameterized test to validate PODD ontologies.
 * 
 * @author kutila
 * 
 */
@RunWith(value = Parameterized.class)
public class MultiplePoddOntologyVersionTest extends AbstractPoddOntologyVersionTest
{
    public static final String VERSION_1_PATH = "/ontologies/version/1/";
    public static final String VERSION_2_PATH = "/ontologies/version/2/";
    public static final String VERSION_3_PATH = "/ontologies/version/3/";
    
    @Parameters(name = "{0}")
    public static Collection<Object[]> data()
    {
        final Object[][] data =
                new Object[][] {
                        // schema ontologies
                        // version 1
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "dcTerms.owl", "application/rdf+xml", 47 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "foaf.owl", "application/rdf+xml", 38 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "poddUser.owl", "application/rdf+xml", 188 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "poddBase.owl", "application/rdf+xml", 331 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "poddScience.owl", "application/rdf+xml",
                                1449 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "poddPlant.owl", "application/rdf+xml", 217 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "poddAnimal.owl", "application/rdf+xml", 139 },
                        { MultiplePoddOntologyVersionTest.VERSION_1_PATH + "poddDataRepository.owl",
                                "application/rdf+xml", 47 },
                        // version 2
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "dcTerms.owl", "application/rdf+xml", 47 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "foaf.owl", "application/rdf+xml", 38 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "poddUser.owl", "application/rdf+xml", 188 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "poddBase.owl", "application/rdf+xml", 330 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "misteaObject.owl", "application/rdf+xml",
                                164 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "misteaEvent.owl", "application/rdf+xml",
                                179 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "poddScience.owl", "application/rdf+xml",
                                1462 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "poddPlant.owl", "application/rdf+xml", 217 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "poddAnimal.owl", "application/rdf+xml", 139 },
                        { MultiplePoddOntologyVersionTest.VERSION_2_PATH + "poddDataRepository.owl",
                                "application/rdf+xml", 47 },
                        { "/ontologies/external/co715/crop-ontology-715.owl", "application/rdf+xml", 2173 },
                        // version 3
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "dcTerms.owl", "application/rdf+xml", 47 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "foaf.owl", "application/rdf+xml", 38 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "poddUser.owl", "application/rdf+xml", 188 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "poddBase.owl", "application/rdf+xml", 330 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "misteaObject.owl", "application/rdf+xml",
                                164 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "misteaEvent.owl", "application/rdf+xml",
                                179 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "poddScience.owl", "application/rdf+xml",
                                1479 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "poddPlant.owl", "application/rdf+xml", 219 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "poddAnimal.owl", "application/rdf+xml", 141 },
                        { MultiplePoddOntologyVersionTest.VERSION_3_PATH + "poddDataRepository.owl",
                                "application/rdf+xml", 52 }, };
        return Arrays.asList(data);
    }
    
    /**
     * Parameterized constructor
     * 
     * @param number
     */
    public MultiplePoddOntologyVersionTest(final String filename, final String mimeType, final int statementCount)
    {
        super(filename, mimeType, statementCount);
    }
}

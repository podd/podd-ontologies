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
public class PoddOntologyVersion3Test extends AbstractPoddOntologyVersionTest
{
    public static final String VERSION_3_PATH = "/ontologies/version/3/";
    
    @Parameters(name = "{0}")
    public static Collection<Object[]> data()
    {
        final Object[][] data =
                new Object[][] {
                        // schema ontologies
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "dcTerms.owl", "application/rdf+xml", 47 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "foaf.owl", "application/rdf+xml", 38 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "poddUser.owl", "application/rdf+xml", 188 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "poddBase.owl", "application/rdf+xml", 330 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "misteaObject.owl", "application/rdf+xml",
                                164 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "misteaEvent.owl", "application/rdf+xml",
                                179 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "poddScience.owl", "application/rdf+xml", 1462 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "poddPlant.owl", "application/rdf+xml", 219 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "poddAnimal.owl", "application/rdf+xml", 141 },
                        { PoddOntologyVersion3Test.VERSION_3_PATH + "poddDataRepository.owl", "application/rdf+xml", 50 },
                        { "/ontologies/external/co715/crop-ontology-715.owl", "application/rdf+xml", 2173 }, };
        return Arrays.asList(data);
    }
    
    /**
     * Parameterized constructor
     *
     * @param number
     */
    public PoddOntologyVersion3Test(final String filename, final String mimeType, final int statementCount)
    {
        super(filename, mimeType, statementCount);
    }
}

/**
 * 
 */
package com.github.podd.ontology.test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openrdf.OpenRDFException;
import org.openrdf.model.URI;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.impl.DatasetImpl;
import org.openrdf.repository.RepositoryConnection;
import org.semanticweb.owlapi.model.IRI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests retrieving PODD artifacts/objects via SPARQL for display in the HTML interface.
 * 
 * @author kutila
 *
 */
public class SparqlQueryTest extends AbstractOntologyTest
{
    protected Logger log = LoggerFactory.getLogger(this.getClass());
   
    
    /**
     * Test that all objects are linked to "PoddObject". 
     */
    @Ignore
    @Test
    public void testAllObjectsAreLinkedToPoddObject() throws Exception
    {
        Assert.fail("TODO");
    }
    
    /**
     * Test retrieving objects sorted by "weight" allocated in the schema ontologies. 
     */
    @Ignore
    @Test
    public void testRetrieveObjectsSortedByWeight() throws Exception
    {
        Assert.fail("TODO");
    }
    
    /**
     * Test the performance of above queries. 
     * Move this to a separate test class. 
     */
    @Ignore
    @Test
    public void testPerformance() throws Exception
    {
        Assert.fail("TODO");
    }
    
    
    /**
     * Copied from PoddSesameManagerImpl.java as a reference only.
     */
    @Test
    public void testOntologyImports() throws Exception
    {
//        final String testResourcePath = "/test/artifacts/basic-1.rdf";
        final String testResourcePath = "/ontologies/poddBase.owl";
        final URI contextUri = ValueFactoryImpl.getInstance().createURI("urn:graph:poddBase:1");
        
        this.loadRdfToRepository(this.testRepositoryConnection, testResourcePath, contextUri);
        
        Assert.assertEquals("Not the expected number of statements in Repository", 278,
                this.testRepositoryConnection.size(contextUri));
        
        Set<IRI> imports = this.getDirectImports(this.testRepositoryConnection, contextUri);
        Assert.assertEquals("Podd-Base should have 0 imports", 0, imports.size());
        
    }
  
    /**
     * Copied from PoddSesameManagerImpl.java as a reference only.
     */
    protected Set<IRI> getDirectImports(final RepositoryConnection repositoryConnection, final URI context)
            throws OpenRDFException
        {
            final String sparqlQuery = "SELECT ?x WHERE { ?y <" + OWL.IMPORTS.stringValue() + "> ?x ." + " }";
            this.log.info("Generated SPARQL {}", sparqlQuery);
            final TupleQuery query = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, sparqlQuery);
            
            final DatasetImpl dataset = new DatasetImpl();
            dataset.addDefaultGraph(context);
            dataset.addNamedGraph(context);
            query.setDataset(dataset);
            
            final Set<IRI> results = Collections.newSetFromMap(new ConcurrentHashMap<IRI, Boolean>());
            
            final TupleQueryResult queryResults = query.evaluate();
            while(queryResults.hasNext())
            {
                final BindingSet nextResult = queryResults.next();
                final String ontologyIRI = nextResult.getValue("x").stringValue();
                results.add(IRI.create(ontologyIRI));
                
            }
            return results;
        }
    
    
}

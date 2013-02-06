/**
 * 
 */
package com.github.podd.ontology.test;

import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openrdf.model.URI;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.rio.RDFFormat;
import org.semanticweb.owlapi.model.IRI;

import com.github.podd.utils.InferredOWLOntologyID;

/**
 * Test for SparqlQuerySpike.java
 * 
 * @author kutila
 * 
 */
public class SparqlQuerySpikeTest extends AbstractOntologyTest
{
    
    private SparqlQuerySpike testSpike;
    
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
     * Test retrieving objects
     */
    @Test
    public void testRetrieveObjectsUnSorted() throws Exception
    {
        final String testResourcePath = "/test/artifacts/basic-1.rdf";
        final InferredOWLOntologyID nextOntologyID = this.loadArtifact(testResourcePath, RDFFormat.RDFXML);
        final URI contextUri = nextOntologyID.getVersionIRI().toOpenRDFURI();
        
        RepositoryConnection conn = null;
        try
        {
            conn = this.getConnection();
            this.testSpike = new SparqlQuerySpike();
            
            // TODO -
            
        }
        finally
        {
            if(conn != null)
            {
                conn.rollback();
                conn.close();
            }
        }
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
     * Test the performance of above queries. Move this to a separate test class.
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
        final String testResourcePath = "/test/artifacts/basic-1.rdf";
        final InferredOWLOntologyID nextOntologyID = this.loadArtifact(testResourcePath, RDFFormat.RDFXML);
        final URI contextUri = nextOntologyID.getVersionIRI().toOpenRDFURI();
        
        RepositoryConnection conn = null;
        try
        {
            conn = this.getConnection();
            Assert.assertEquals("Not the expected number of statements in Repository", 33, conn.size(contextUri));
            
            this.testSpike = new SparqlQuerySpike();
            final Set<IRI> imports = this.testSpike.getDirectImports(conn, contextUri);
            Assert.assertEquals("Podd-Base should have 2 imports", 2, imports.size());
            
            // DEBUG - print repository contents to console
            // this.printContexts(conn);
            this.printContents(conn, this.artifactGraph);
        }
        finally
        {
            if(conn != null)
            {
                conn.rollback();
                conn.close();
            }
        }
    }
    
}

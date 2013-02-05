package com.github.podd.ontology.test;

import java.io.InputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.memory.MemoryStore;

/**
 * Abstract test class for testing PODD artifacts
 * 
 * @author kutila
 *
 */
public class AbstractOntologyTest
{
    
    protected Repository testRepository;

    protected RepositoryConnection testRepositoryConnection;

    /**
     * Load the statements found in the given RDF/XML resource to the specified context
     * using the given RepositoryConnection.
     * 
     * @param conn
     * @param resourcePath
     * @param context
     * @throws Exception
     */
    protected void loadRdfToRepository(RepositoryConnection conn, String resourcePath, URI context) throws Exception
    {
        final InputStream artifact1InputStreamAgain = this.getClass().getResourceAsStream(resourcePath);
        Assert.assertNotNull("Resource was null", artifact1InputStreamAgain);
        conn.add(artifact1InputStreamAgain, "", RDFFormat.RDFXML, context);
    }
    
    /**
     * Helper method prints the contents of the given context of the given Repository.
     */
    public static void printContents(Repository repository, final URI context) throws Exception
    {
        final RepositoryConnection conn = repository.getConnection();
        conn.begin();
        
        StringBuilder b = new StringBuilder();
        b.append("==================================================\r\n");
        b.append("Graph = " + context);
        b.append("\r\n\r\n");
        final org.openrdf.repository.RepositoryResult<Statement> repoResults =
                conn.getStatements(null, null, null, false, context);
        while(repoResults.hasNext())
        {
            final Statement stmt = repoResults.next();
            b.append("   {" + stmt.getSubject() + "}   <" + stmt.getPredicate() + ">  {" + stmt.getObject()
                    + "}\r\n");
        }
        b.append("==================================================\r\n");
        System.out.println(b.toString());

        conn.rollback();
        conn.close();
    }

    /**
     * Helper method prints contexts/graphs available in the given Repository.
     */
    public static void printContexts(Repository repository) throws Exception
    {
        final java.util.HashSet<String> contextSet = new java.util.HashSet<String>();
        
        final RepositoryConnection conn = repository.getConnection();
        conn.begin();
        final org.openrdf.repository.RepositoryResult<Statement> repoResults =
                conn.getStatements(null, null, null, true);
        while(repoResults.hasNext())
        {
            final Statement stmt = repoResults.next();
            contextSet.add(stmt.getContext().stringValue());
        }

        StringBuilder b = new StringBuilder();
        
        b.append("==================================================\r\n");
        b.append("Contexts in Repository:  \r\n");
        for(final String context : contextSet)
        {
            b.append(context);
            b.append("\r\n");
        }
        b.append("==================================================\r\n");
        System.out.println(b.toString());
        
        conn.rollback();
        conn.close();
    }

    @Before
    public void setUp() throws Exception
    {
        // create a memory Repository for tests
        this.testRepository = new SailRepository(new MemoryStore());
        this.testRepository.initialize();
        this.testRepositoryConnection = this.testRepository.getConnection();
        this.testRepositoryConnection.begin();
    }

    @After
    public void tearDown() throws Exception
    {
        this.testRepositoryConnection.rollback();
        this.testRepositoryConnection.close();
        
        this.testRepository.shutDown();
    }

}
package com.github.podd.ontology.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.openrdf.model.Model;
import org.openrdf.model.Namespace;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * This simple program extracts data from an HTML page, transforms it into RDF and writes to a file. <br>
 * Limitations:
 * <ul>
 * <li>Works only to create poddScience:Platform instances</li>
 * <li>Does not take parameters. Everything is defined inside the main() method</li>
 * <li>Expected table structure, platform type are fixed</li>
 * <li>May fail if the source page contains javascript</li>
 * </ul>
 * 
 * <br>
 * Input parameters:
 * <ul>
 * <li>Full path to source HTML file</li>
 * <li>id of HTML table to scrape</li>
 * <li>Full path to destination RDF file</li>
 * <li>RDF format to write output in</li>
 * </ul>
 * 
 * @author kutila
 * @since 2013/04/19
 */
public class Webscraper
{
    public final static String PODD_PLANT = "http://purl.org/podd/ns/poddPlant#";
    
    public final static String PODD_SCIENCE = "http://purl.org/podd/ns/poddScience#";
    
    public final static URI OWL_NAMED_INDIVIDUAL = ValueFactoryImpl.getInstance().createURI(OWL.NAMESPACE,
            "NamedIndividual");
    
    public final static URI PLATFORM_TYPE_HARDWARE = ValueFactoryImpl.getInstance().createURI(Webscraper.PODD_SCIENCE,
            "Hardware");
    
    public final static URI PLATFORM_TYPE_HARDWARESOFTWARE = ValueFactoryImpl.getInstance().createURI(
            Webscraper.PODD_SCIENCE, "HardwareSoftware");
    
    public final static URI PLATFORM_TYPE_SOFTWARE = ValueFactoryImpl.getInstance().createURI(Webscraper.PODD_SCIENCE,
            "Software");
    
    public final static URI PODD_SCIENCE_HAS_PLATFORM_TYPE = ValueFactoryImpl.getInstance().createURI(
            Webscraper.PODD_SCIENCE, "hasPlatformType");
    
    public final static URI PODD_SCIENCE_PLATFORM = ValueFactoryImpl.getInstance().createURI(Webscraper.PODD_SCIENCE,
            "Platform");
    
    /**
     * Main method
     */
    public static void main(final String[] args) throws Exception
    {
        final String source = "/home/user/Documents/PODD/platform-list.csv";
        // "file:///home/user/path/ktemp/page2.html";
        final String tableId = "table1";
        
        final String destinationFile = "/home/user/Documents/PODD/out.rdf";
        final RDFFormat format = RDFFormat.RDFXML;
        
        final List<List<String>> tableData = Webscraper.getTableDataFromCsv(source);
        // Webscraper.getTableDataFromPage(sourceHtmlPage, tableId);
        final Model model = Webscraper.constructRdfFromData(tableData);
        Webscraper.writeToFile(model, destinationFile, format);
    }
    
    /**
     * 
     * Construct the statements for a poddScience:Platform individual from the given data.
     * 
     * 
     * @param data
     * @return
     */
    public static Model constructRdfFromData(final List<List<String>> data)
    {
        final Model model = new LinkedHashModel();
        
        model.setNamespace("rdf", RDF.NAMESPACE);
        model.setNamespace("rdfs", RDFS.NAMESPACE);
        model.setNamespace("owl", OWL.NAMESPACE);
        model.setNamespace("poddScience", Webscraper.PODD_SCIENCE);
        model.setNamespace("poddPlant", Webscraper.PODD_PLANT);
        
        for(final List<String> rows : data)
        {
            final String label = rows.get(0);
            
            final URI platformUri =
                    ValueFactoryImpl.getInstance().createURI(Webscraper.PODD_PLANT,
                            label.replaceAll("\\W", "") + "-" + UUID.randomUUID());
            
            model.add(platformUri, RDF.TYPE, OWL.THING);
            model.add(platformUri, RDF.TYPE, Webscraper.OWL_NAMED_INDIVIDUAL);
            model.add(platformUri, RDF.TYPE, Webscraper.PODD_SCIENCE_PLATFORM);
            model.add(platformUri, Webscraper.PODD_SCIENCE_HAS_PLATFORM_TYPE, Webscraper.PLATFORM_TYPE_HARDWARE);
            
            if(label != null && label.trim().length() > 0)
            {
                model.add(platformUri, RDFS.LABEL, ValueFactoryImpl.getInstance().createLiteral(label));
            }
            
            final String description = rows.get(3);
            if(description != null)
            {
                model.add(platformUri, RDFS.COMMENT, ValueFactoryImpl.getInstance().createLiteral(description));
            }
        }
        
        return model;
    }
    
    /**
     * Reads through a given HTML page and extracts the contents of a specified HTML table into a
     * List.
     * 
     * @param pageName
     * @param tableId
     * @return
     */
    public static List<List<String>> getTableDataFromPage(final String pageName, final String tableId)
    {
        final List<List<String>> tableList = new ArrayList<List<String>>();
        final WebClient webClient = new WebClient();
        try
        {
            final HtmlPage page = webClient.getPage(pageName);
            
            final HtmlTable table = page.getHtmlElementById(tableId);
            for(final HtmlTableRow row : table.getRows())
            {
                final List<String> rowList = new ArrayList<String>();
                for(final HtmlTableCell cell : row.getCells())
                {
                    rowList.add(cell.asText());
                }
                tableList.add(rowList);
            }
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            webClient.closeAllWindows();
        }
        
        System.out.println(tableList.size() + " rows of table data extracted.");
        return tableList;
    }
    
    /**
     * Reads through a given CSV file and extracts content into a List.
     * 
     * This method cannot handle values which have escaped commas or line breaks within values.
     * 
     * @param fileName
     * @return
     */
    public static List<List<String>> getTableDataFromCsv(final String fileName)
    {
        final List<List<String>> tableList = new ArrayList<List<String>>();
        
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                System.out.println(line);
                final List<String> row = new ArrayList<String>();
                
                final String[] values = line.split(",");
                row.add(values[0]); // label
                row.add(values[1]); // platform, ignored
                row.add(""); // ignored
                row.add(values[2]); // description
                
                tableList.add(row);
                line = reader.readLine();
            }
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
        return tableList;
    }
    
    public static void printToConsole(final List<List<String>> data)
    {
        final StringBuilder b = new StringBuilder();
        for(final List<String> rows : data)
        {
            for(final String cell : rows)
            {
                b.append(cell);
                b.append(" : ");
            }
            b.append("\n");
        }
        System.out.println(b.toString());
    }
    
    /**
     * Dump the contents of the given Model to a file in the specified RDF format.
     * 
     * @param model
     * @param filename
     * @param format
     */
    public static void writeToFile(final Model model, final String filename, final RDFFormat format)
    {
        try
        {
            final FileOutputStream out = new FileOutputStream(filename);
            final RDFWriter writer = Rio.createWriter(format, out);
            writer.startRDF();
            
            // add namespaces to RDFWriter
            for(final Namespace nextPrefix : model.getNamespaces())
            {
                writer.handleNamespace(nextPrefix.getPrefix(), nextPrefix.getName());
            }
            
            // write RDF statements
            for(final Statement st : model)
            {
                writer.handleStatement(st);
            }
            writer.endRDF();
            
            System.out.println("Wrote '" + filename + "' in " + format.getName() + " format.");
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

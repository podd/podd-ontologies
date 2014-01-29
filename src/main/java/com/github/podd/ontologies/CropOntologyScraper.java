/**
 * 
 */
package com.github.podd.ontologies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClient;
import org.semanticweb.owlapi.model.OWLOntologyID;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 * 
 */
public class CropOntologyScraper
{
    /**
     * An HTTP Accept header that prefers JSON.
     */
    protected static final String JSON_ACCEPT_HEADER = "application/json;q=1.0, */*;q=0.1";
    
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final JsonFactory JSON_FACTORY = new JsonFactory(JSON_MAPPER);
    private static HttpClient httpClient;
    
    static
    {
        JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        JSON_FACTORY.disable(JsonFactory.Feature.INTERN_FIELD_NAMES);
        JSON_FACTORY.disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
    }
    
    /**
     * 
     */
    public CropOntologyScraper()
    {
        // TODO Auto-generated constructor stub
    }
    
    public Set<OWLOntologyID> getAllCropOntologies() throws IOException
    {
        URL getOntologiesUrl = new URL("http://www.cropontology.org/get-ontologies");
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(openStreamFromURL(getOntologiesUrl)));
                final JsonParser parser = JSON_FACTORY.createParser(in);)
        {
            Map<String, Object> nextMap = (Map<String, Object>)parser.readValueAs(Map.class);
            
            for(Entry<String, Object> nextEntry : nextMap.entrySet())
            {
                System.out.println(nextEntry.getKey());
                System.out.println(nextEntry.getValue());
            }
        }
        
        return null;
    }
    
    public static InputStream openStreamFromURL(java.net.URL url) throws IOException
    {
        final String protocol = url.getProtocol();
        if(!protocol.equalsIgnoreCase("http") && !protocol.equalsIgnoreCase("https"))
        {
            return url.openStream();
        }
        final HttpUriRequest request = new HttpGet(url.toExternalForm());
        request.addHeader("Accept", JSON_ACCEPT_HEADER);
        
        final HttpResponse response = getHttpClient().execute(request);
        final int status = response.getStatusLine().getStatusCode();
        if(status != 200 && status != 203)
        {
            throw new IOException("Can't retrieve " + url + ", status code: " + status);
        }
        return response.getEntity().getContent();
    }
    
    protected static HttpClient getHttpClient()
    {
        if(httpClient == null)
        {
            synchronized(CropOntologyScraper.class)
            {
                if(httpClient == null)
                {
                    final DefaultHttpClient client = new SystemDefaultHttpClient();
                    client.addRequestInterceptor(new RequestAcceptEncoding());
                    client.addResponseInterceptor(new ResponseContentEncoding());
                    final CacheConfig cacheConfig = new CacheConfig();
                    cacheConfig.setMaxObjectSize(1024 * 128);
                    cacheConfig.setMaxCacheEntries(1000);
                    httpClient = new CachingHttpClient(client, cacheConfig);
                }
            }
        }
        return httpClient;
    }
}

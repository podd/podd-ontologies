/**
 * 
 */
package com.github.podd.ontologies;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 * 
 */
public class CropOntologyID
{
    private String ontologyID;
    private String ontologyName;
    private String ontologySummary;
    private String username;
    private Integer userid;
    private Integer tot;
    private Integer oboTerms;
    
    @JsonProperty("ontology_id")
    public String getOntologyID()
    {
        return this.ontologyID;
    }
    
    @JsonProperty("ontology_name")
    public String getOntologyName()
    {
        return this.ontologyName;
    }
    
    @JsonProperty("ontology_summary")
    public String getOntologySummary()
    {
        return this.ontologySummary;
    }
    
    @JsonProperty("username")
    public String getUsername()
    {
        return this.username;
    }
    
    @JsonProperty("userid")
    public Integer getUserid()
    {
        return this.userid;
    }
    
    @JsonProperty("tot")
    public Integer getTot()
    {
        return this.tot;
    }
    
    @JsonProperty("oboTerms")
    public Integer getOboTerms()
    {
        return this.oboTerms;
    }
}

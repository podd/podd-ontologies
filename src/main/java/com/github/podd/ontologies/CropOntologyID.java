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
    private Integer oboTerms;
    private String ontologyID;
    private String ontologyName;
    private String ontologySummary;
    private Integer tot;
    private Integer userid;
    private String username;
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof CropOntologyID))
            return false;
        CropOntologyID other = (CropOntologyID)obj;
        if(oboTerms == null)
        {
            if(other.oboTerms != null)
                return false;
        }
        else if(!oboTerms.equals(other.oboTerms))
            return false;
        if(ontologyID == null)
        {
            if(other.ontologyID != null)
                return false;
        }
        else if(!ontologyID.equals(other.ontologyID))
            return false;
        if(ontologyName == null)
        {
            if(other.ontologyName != null)
                return false;
        }
        else if(!ontologyName.equals(other.ontologyName))
            return false;
        if(ontologySummary == null)
        {
            if(other.ontologySummary != null)
                return false;
        }
        else if(!ontologySummary.equals(other.ontologySummary))
            return false;
        if(tot == null)
        {
            if(other.tot != null)
                return false;
        }
        else if(!tot.equals(other.tot))
            return false;
        if(userid == null)
        {
            if(other.userid != null)
                return false;
        }
        else if(!userid.equals(other.userid))
            return false;
        if(username == null)
        {
            if(other.username != null)
                return false;
        }
        else if(!username.equals(other.username))
            return false;
        return true;
    }
    
    @JsonProperty("oboTerms")
    public Integer getOboTerms()
    {
        return this.oboTerms;
    }
    
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
    
    @JsonProperty("tot")
    public Integer getTot()
    {
        return this.tot;
    }
    
    @JsonProperty("userid")
    public Integer getUserid()
    {
        return this.userid;
    }
    
    @JsonProperty("username")
    public String getUsername()
    {
        return this.username;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((oboTerms == null) ? 0 : oboTerms.hashCode());
        result = prime * result + ((ontologyID == null) ? 0 : ontologyID.hashCode());
        result = prime * result + ((ontologyName == null) ? 0 : ontologyName.hashCode());
        result = prime * result + ((ontologySummary == null) ? 0 : ontologySummary.hashCode());
        result = prime * result + ((tot == null) ? 0 : tot.hashCode());
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "CropOntologyID [ontologyID=" + ontologyID + ", ontologyName=" + ontologyName + ", ontologySummary="
                + ontologySummary + ", username=" + username + ", userid=" + userid + ", tot=" + tot + ", oboTerms="
                + oboTerms + "]";
    }
    
}

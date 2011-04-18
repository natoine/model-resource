/*
 * Copyright 2010 Antoine Seilles (Natoine)
 *   This file is part of model-resource.

    model-resource is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    model-resource is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with model-resource.  If not, see <http://www.gnu.org/licenses/>.

 */
package fr.natoine.model_resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "RESOURCE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public class Resource implements Serializable, RDFexportable
{
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "RESOURCE_ID")
	private Long id;
	
	@Column(name = "LABEL" , nullable=false)
	private String label;
	
	@Column(name = "DATE_CREATION")
	private Date creation;
	
	@Column(name = "CONTEXT_CREATION")
	private String contextCreation;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = URI.class)
	@JoinColumn(name = "URI_ID" , nullable=false)
	private URI representsResource;
	
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = URI.class)
	@JoinTable(
			name="RESOURCE_TO_URI",
			joinColumns=@JoinColumn(name="RESOURCE_ID"),
	        inverseJoinColumns=@JoinColumn(name="URI_ID")
				)
	private Collection<URI> uris;
	
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = UriStatus.class)
	@JoinTable(
			name="RESOURCE_TO_URISTATUS",
			joinColumns=@JoinColumn(name="RESOURCE_ID"),
	        inverseJoinColumns=@JoinColumn(name="URISTATUS_ID")
				)
	private Collection<UriStatus> uris_status;
	/**
	 * Gets the URI used to represent the resource. 
	 * A resource always has a URI to represent itself. 
	 * Sometime, it uses the same URI to be accessible, sometime not.
	 * @return
	 */
	public URI getRepresentsResource() {
		return representsResource;
	}
	/**
	 * Sets the URI used to represent the resource.
	 * A resource always has a URI to represent itself. 
	 * Sometime, it uses the same URI to be accessible, sometime not.
	 * @param representsResource
	 */
	public void setRepresentsResource(URI representsResource) {
		this.representsResource = representsResource;
	}
	/**
	 * Gets the label of a Resource.
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * Sets the label of a Resource.
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * Gets the date of creation of a Resource.
	 * @return
	 */
	public Date getCreation() {
		return creation;
	}
	/**
	 * Sets the date of creation of a Resource.
	 * @param creation
	 */
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	/**
	 * Gets the context of creation of a Resource. aka a label to sign the application that has created the Resource.
	 * @return
	 */
	public String getContextCreation() {
		return contextCreation;
	}
	/**
	 * Sets the context of creation of a Resource. aka a label to sign the application that has created the Resource.
	 * @param context_creation
	 */
	public void setContextCreation(String context_creation) {
		this.contextCreation = context_creation;
	}
	/**
	 * Gets the collection of URI associated to the Resource.
	 * A resource may be associated to many uris with different semantics (access, represents, linksTo, refersTo, ...).
	 * See http://ontologydesignpatterns.org/ont/web/irw.owl
	 * @return
	 */
	public Collection<URI> getUris() {
		return uris;
	}
	/**
	 * Sets the collection of URI associated to the Resource.
	 * A resource may be associated to many uris with different semantics (access, represents, linksTo, refersTo, ...).
	 * See http://ontologydesignpatterns.org/ont/web/irw.owl
	 * @param uris
	 */
	public void setUris(Collection<URI> uris) {
		this.uris = uris;
	}
	/**
	 * Get the ID of the Resource.
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Gets the collection of Uri Status associated to the Resource.
	 * Each Status defines the semantic of the corresponding URI associated to the resource.
	 * the first status defines the semantic of the first associated URI and so on.
	 * @return
	 */
	public Collection<UriStatus> getUrisStatus() {
		return uris_status;
	}
	/**
	 * Sets the collection of Uri Status associated to the Resource.
	 * Each Status defines the semantic of the corresponding URI associated to the resource.
	 * the first status defines the semantic of the first associated URI and so on.
	 * @param uris_status
	 */
	public void setUrisStatus(Collection<UriStatus> uris_status) {
		this.uris_status = uris_status;
	}
	/**
	 * Gets a status giving the position of the status inside the collection of status. 
	 * @param _uri_position
	 * @return
	 */
	public UriStatus getUriStatus(int _uri_position)
	{
		return (UriStatus) this.uris_status.toArray()[_uri_position];
	}
	/**
	 * Gets a uri giving the position of the uri inside the collection of uri. 
	 * @param _uri_position
	 * @return
	 */
	public URI getUri(int _uri_position)
	{
		return (URI) this.uris.toArray()[_uri_position];
	}
	/**
	 * Gets a full html representation of the resource
	 * @return a String that is a html representation of the resource
	 */
	public String toHTMLMax()
	{
		String _html =
			"<div class=resource_header><span class=resource_type>" + getClass().getSimpleName() + "</span> : <a href=" + getRepresentsResource().getEffectiveURI() + "?id=" + getId() + ">" + getLabel() + "</a></div>"
			+ "<div class=creation>Créée le : " + getCreation() + " via : " + getContextCreation() + "</div>" ;
		return _html;
	}
	
	/**
	 * Gets a html representation of the resource
	 * @return a String that is a html representation of the resource
	 */
	public String toHTML()
	{
		String _html =
			"<div class=resource_header><span class=resource_type>" + getClass().getSimpleName() + "</span> : <a href=" + getRepresentsResource().getEffectiveURI() + "?id=" + getId() + ">" + getLabel() + "</a></div>";
		return _html;
	}

	public String getRDFLabel()
	{
		String _labelRDF = this.getLabel().replaceAll(" ", "");
		_labelRDF = _labelRDF.replaceAll("'", "");
		_labelRDF = _labelRDF.replaceAll("é", "e");
		_labelRDF = _labelRDF.replaceAll("è", "e");
		_labelRDF = _labelRDF.replaceAll("ê", "e");
		_labelRDF = _labelRDF.replaceAll("ë", "e");
		_labelRDF = _labelRDF.replaceAll("à", "a");
		_labelRDF = _labelRDF.substring(0,1).toUpperCase() + _labelRDF.substring(1 , _labelRDF.length());
		return _labelRDF ;
	}
	
	public String toRDF(String _url_rdf_resource , String _rdf_toinsert)
	{
		//String _rdf = "<rdfs:Resource rdf:about=\""+ representsResource.getEffectiveURI() +"?id=" + id +"\" >" ;
		String _rdf = "<irw:Resource rdf:about=\""+ _url_rdf_resource +"?id=" + id +"\" >" ;
		if(! _url_rdf_resource.equalsIgnoreCase(representsResource.getEffectiveURI()))
		{
			_rdf = _rdf.concat("<rdfs:seeAlso rdf:resource=\"" + representsResource.getEffectiveURI() + "\" />");
		}
		_rdf = _rdf.concat("<rdfs:label>"+ label +"</rdfs:label>");
		_rdf = _rdf.concat("<dcterms:created>" + creation + "</dcterms:created>");
		if(_rdf_toinsert != null && _rdf_toinsert.length() > 0) _rdf = _rdf.concat(_rdf_toinsert);
		_rdf = _rdf.concat("</irw:Resource>");
		//_rdf = _rdf.concat("</rdfs:Resource>");
		return _rdf;
	}
	
	public String toRDF(String _url_rdf_resource , String _url_rdf_agent, String _rdf_toinsert)
	{
		return toRDF(_url_rdf_resource , _rdf_toinsert);
	}
	
	public List<String> rdfHeader()
	{
		ArrayList<String> _nms = new ArrayList<String>();
		_nms.add("xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"");
		_nms.add("xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"");
		_nms.add("xmlns:dcterms=\"http://purl.org/dc/terms/\"");
		_nms.add("xmlns:irw=\"http://ontologydesignpatterns.org/ont/web/irw.owl\"");
		_nms.add("xmlns:annotea=\"http://www.w3.org/2000/10/annotation-ns\"");
		return _nms ;
	}
	
	public String toSeeAlso(String _url_rdf_resources)
	{
		String rdf = "";
		rdf = rdf.concat("<irw:Resource rdf:about=\"").concat(_url_rdf_resources).concat("?id=").concat(id.toString()).concat("\" >");
		if(! _url_rdf_resources.equalsIgnoreCase(representsResource.getEffectiveURI()))
		{
			rdf = rdf.concat("<rdfs:seeAlso rdf:resource=\"" + representsResource.getEffectiveURI() + "\" />");
		}
		rdf = rdf.concat("<rdfs:seeAlso rdf:resource=\"").concat(_url_rdf_resources).concat("?id=").concat(id.toString()).concat("\" />");
		rdf = rdf.concat("</irw:Resource>");
		return rdf ;
	}
}
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "URI")
@XmlRootElement
public class URI implements Serializable
{
	@Id @GeneratedValue
    @Column(name = "URI_ID")
	private Long id;
	
	@Column(name = "EFFECTIVEURI", unique=true, nullable=false)
	private String effectiveURI;
	
	/**
	 * Gets the effective URI http:// ...
	 * @return
	 */
	public String getEffectiveURI() {
		return effectiveURI;
	}
	/**
	 * Sets the effective URI http:// ...
	 * @param effectiveURI
	 */
	public void setEffectiveURI(String effectiveURI) {
		this.effectiveURI = effectiveURI;
	}
	/**
	 * Gets the id of an uri
	 * Ok, an URI should not need an ID cause an URI is an ID.
	 * But in mysql case using hibernate, it makes requests be faster
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}
}
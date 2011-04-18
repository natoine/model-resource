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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "URISTATUS")
@XmlRootElement
public class UriStatus implements Serializable
{
	@Id @GeneratedValue
    @Column(name = "URISTATUS_ID")
	private Long id;
	
	@Column(name = "LABEL" , unique=true, nullable=false)
	private String label;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = UriStatus.class)
	@JoinColumn(name = "FATHERURISTATUS_ID")
	private UriStatus father;
	
	/**
	 * Gets the father of a UriStatus.
	 * UriStatus are organized as a hierarchy.
	 * The father of a UriStatus is the direct more general Status above the current status.
	 * @return
	 */
	public UriStatus getFather() {
		return father;
	}
	/**
	 * Sets the father of a UriStatus.
	 * UriStatus are organized as a hierarchy.
	 * The father of a UriStatus is the direct more general Status above the current status.
	 * @param father
	 */
	public void setFather(UriStatus father) {
		this.father = father;
	}
	/**
	 * Gets the label of a status.
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * Sets the label of a status.
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * Gets the comment of a status.
	 * Comment is used to disambiguate the status.
	 * @return
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * Sets the comment of a status.
	 * Comment is used to disambiguate the status.
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * Gets the id of a UriStatus.
	 * @return
	 */
	public Long getId() {
		return id;
	}
}
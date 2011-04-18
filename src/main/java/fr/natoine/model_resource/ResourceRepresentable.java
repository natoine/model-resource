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
//To make an object representable by a resource
public interface ResourceRepresentable 
{
	/**
	 * Gets the resource used to represent the current object
	 * Gets the Resource WebRepresentation of an object.
	 * @return
	 */
	public Resource getRepresents();
	/**
	 * Sets the resource used to represent the current object
	 * Sets the Resource WebRepresentation of an object.
	 * @param represents
	 */
	public void setRepresents(Resource represents);
}

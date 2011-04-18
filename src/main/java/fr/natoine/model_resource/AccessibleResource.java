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
//To make a resource become an accessible resource
public interface AccessibleResource
{
	/**
	 * Gets the URI used to access an Accessible Resource
	 * @return
	 */
	public URI getAccess();
	/**
	 * Sets the URI used to access an Accessible Resource
	 * @param access
	 */
	public void setAccess(URI access);
}
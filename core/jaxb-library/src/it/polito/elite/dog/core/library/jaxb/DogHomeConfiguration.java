/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2014 Dario Bonino and Luigi De Russis
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.15 at 03:00:00 PM CEST 
//

package it.polito.elite.dog.core.library.jaxb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for dogHomeConfiguration complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dogHomeConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buildingEnvironment" type="{http://elite.polito.it/dogHomeConfiguration}buildingEnvironment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="controllables" type="{http://elite.polito.it/dogHomeConfiguration}controllables" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dogHomeConfiguration", propOrder = { "buildingEnvironment", "controllables" })
public class DogHomeConfiguration implements Cloneable
{
	
	protected List<BuildingEnvironment> buildingEnvironment;
	protected List<Controllables> controllables;
	
	/**
	 * Creates a new {@code DogHomeConfiguration} instance.
	 * 
	 */
	public DogHomeConfiguration()
	{
		// CC-XJC Version 2.0.1 Build 2012-03-02T12:09:12+0000
		super();
	}
	
	/**
	 * Creates a new {@code DogHomeConfiguration} instance by deeply copying a
	 * given {@code DogHomeConfiguration} instance.
	 * 
	 * 
	 * @param o
	 *            The instance to copy.
	 * @throws NullPointerException
	 *             if {@code o} is {@code null}.
	 */
	public DogHomeConfiguration(final DogHomeConfiguration o)
	{
		// CC-XJC Version 2.0.1 Build 2012-03-02T12:09:12+0000
		super();
		if (o == null)
		{
			throw new NullPointerException("Cannot create a copy of 'DogHomeConfiguration' from 'null'.");
		}
		// 'BuildingEnvironment' collection.
		if (o.buildingEnvironment != null)
		{
			copyBuildingEnvironment(o.getBuildingEnvironment(), this.getBuildingEnvironment());
		}
		// 'Controllables' collection.
		if (o.controllables != null)
		{
			copyControllables(o.getControllables(), this.getControllables());
		}
	}
	
	/**
	 * Gets the value of the buildingEnvironment property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the buildingEnvironment property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getBuildingEnvironment().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link BuildingEnvironment }
	 * 
	 * 
	 */
	public List<BuildingEnvironment> getBuildingEnvironment()
	{
		if (buildingEnvironment == null)
		{
			buildingEnvironment = new ArrayList<BuildingEnvironment>();
		}
		return this.buildingEnvironment;
	}
	
	/**
	 * Gets the value of the controllables property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the controllables property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getControllables().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Controllables }
	 * 
	 * 
	 */
	public List<Controllables> getControllables()
	{
		if (controllables == null)
		{
			controllables = new ArrayList<Controllables>();
		}
		return this.controllables;
	}
	
	/**
	 * Copies all values of property {@code BuildingEnvironment} deeply.
	 * 
	 * @param source
	 *            The source to copy from.
	 * @param target
	 *            The target to copy {@code source} to.
	 * @throws NullPointerException
	 *             if {@code target} is {@code null}.
	 */
	private static void copyBuildingEnvironment(final List<BuildingEnvironment> source,
			final List<BuildingEnvironment> target)
	{
		// CC-XJC Version 2.0.1 Build 2012-03-02T12:09:12+0000
		if ((source != null) && (!source.isEmpty()))
		{
			for (final Iterator<?> it = source.iterator(); it.hasNext();)
			{
				final Object next = it.next();
				if (next instanceof BuildingEnvironment)
				{
					// CClassInfo:
					// it.polito.elite.dog.core.library.jaxb.BuildingEnvironment
					target.add(((BuildingEnvironment) next).clone());
					continue;
				}
				// Please report this at
				// https://apps.sourceforge.net/mantisbt/ccxjc/
				throw new AssertionError(
						(("Unexpected instance '" + next) + "' for property 'BuildingEnvironment' of class 'it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration'."));
			}
		}
	}
	
	/**
	 * Copies all values of property {@code Controllables} deeply.
	 * 
	 * @param source
	 *            The source to copy from.
	 * @param target
	 *            The target to copy {@code source} to.
	 * @throws NullPointerException
	 *             if {@code target} is {@code null}.
	 */
	private static void copyControllables(final List<Controllables> source, final List<Controllables> target)
	{
		// CC-XJC Version 2.0.1 Build 2012-03-02T12:09:12+0000
		if ((source != null) && (!source.isEmpty()))
		{
			for (final Iterator<?> it = source.iterator(); it.hasNext();)
			{
				final Object next = it.next();
				if (next instanceof Controllables)
				{
					// CClassInfo:
					// it.polito.elite.dog.core.library.jaxb.Controllables
					target.add(((Controllables) next).clone());
					continue;
				}
				// Please report this at
				// https://apps.sourceforge.net/mantisbt/ccxjc/
				throw new AssertionError(
						(("Unexpected instance '" + next) + "' for property 'Controllables' of class 'it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration'."));
			}
		}
	}
	
	/**
	 * Creates and returns a deep copy of this object.
	 * 
	 * 
	 * @return A deep copy of this object.
	 */
	@Override
	public DogHomeConfiguration clone()
	{
		try
		{
			{
				// CC-XJC Version 2.0.1 Build 2012-03-02T12:09:12+0000
				final DogHomeConfiguration clone = ((DogHomeConfiguration) super.clone());
				// 'BuildingEnvironment' collection.
				if (this.buildingEnvironment != null)
				{
					clone.buildingEnvironment = null;
					copyBuildingEnvironment(this.getBuildingEnvironment(), clone.getBuildingEnvironment());
				}
				// 'Controllables' collection.
				if (this.controllables != null)
				{
					clone.controllables = null;
					copyControllables(this.getControllables(), clone.getControllables());
				}
				return clone;
			}
		}
		catch (CloneNotSupportedException e)
		{
			// Please report this at
			// https://apps.sourceforge.net/mantisbt/ccxjc/
			throw new AssertionError(e);
		}
	}
	
}

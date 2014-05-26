/*
 * Dog - Core
 *
 * Copyright (c) 2011-2014 Dario Bonino and Luigi De Russis
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
// Automatically generated by the DogOnt2Dog utility
//

package it.polito.elite.dog.core.library.model.state;


import it.polito.elite.dog.core.library.model.statevalue.ChannelStateValue;



/**
* TunerState - automatically generated by it.polito.elite.domotics.ontologies.dogont.utilities.DogOnt2Dog
*
* @author it.polito.elite.domotics.ontologies.dogont.utilities.DogOnt2Dog
*
*/
public class TunerState extends ContinuousState
{
	/**
	 * The unique class version for serialization
	 */

	private static final long serialVersionUID = 1L;



	/**
	 * Class constructor for states inheriting from ContinuousState.
	 */
	public TunerState(ChannelStateValue channelstatevalue)
	{
		//call the super class constructor
		super(channelstatevalue);
	}
	/**
	 * Creates a state object in the Dog2.0 old way (before May 2012).
	 * 
	 * @param valueOfTheCurrentStateValue
	 */
	@Deprecated
	public TunerState(Object valueOfTheCurrentStateValue)
	{
		this.valueOfTheCurrentStateValue = valueOfTheCurrentStateValue;

		if(!(valueOfTheCurrentStateValue instanceof String))
		{
			ChannelStateValue sValue = new ChannelStateValue();
			sValue.setValue(valueOfTheCurrentStateValue);
			this.currentStateValue[0]=sValue;

		}
	}

	/**
	 * Default constructor.
	 */
	public TunerState()
	{
		super();
	}
}

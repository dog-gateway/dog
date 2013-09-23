/*
 * Dog - Core
 * 
 * Copyright (c) 2011-2013 Dario Bonino and Luigi De Russis
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
//Automatically generated by it.polito.elite.domotics.ontologies.dogont.utilities.DogOnt2Dog
//

package it.polito.elite.dog.core.library.model.devicemodel;


 
 /**
* DogTvModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.devicecategory.Tv;
import it.polito.elite.dog.core.library.model.state.*;

import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
public class DogTv extends AbstractDevice implements Tv
{

	public DogTv(BundleContext context, DeviceDescriptor dogDeviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(dogDeviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public void setVolume(Integer volume)
	{
		if(this.driver!=null){
((Tv) this.driver).setVolume(volume);
		}
	}

	public void stepDown()
	{
		if(this.driver!=null){
((Tv) this.driver).stepDown();
		}
	}

	public void stepUp()
	{
		if(this.driver!=null){
((Tv) this.driver).stepUp();
		}
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null){
		return ((Tv) this.driver).getState();
		}
		 return null;
	}

	public void set(Object value)
	{
		if(this.driver!=null){
((Tv) this.driver).set(value);
		}
	}

	public void on()
	{
		if(this.driver!=null){
((Tv) this.driver).on();
		}
	}

	public void setChannel(Integer channel)
	{
		if(this.driver!=null){
((Tv) this.driver).setChannel(channel);
		}
	}

	public void down()
	{
		if(this.driver!=null){
((Tv) this.driver).down();
		}
	}

	public void up()
	{
		if(this.driver!=null){
((Tv) this.driver).up();
		}
	}

	public void standBy()
	{
		if(this.driver!=null){
((Tv) this.driver).standBy();
		}
	}

	public void off()
	{
		if(this.driver!=null){
((Tv) this.driver).off();
		}
	}



	/*Generated Notifications*/

	/**
	/* Implements the DeviceCategory interface requirement 
	**/
	@Override
	public void notifyStateChanged(State newState)
	{
		super.notifyStateChanged(newState);
	}
}

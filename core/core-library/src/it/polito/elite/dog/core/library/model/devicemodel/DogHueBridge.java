/**
 * 
 */
package it.polito.elite.dog.core.library.model.devicemodel;

/**
 * @author bonino
 *
 */

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.devicecategory.HueBridge;
import it.polito.elite.dog.core.library.model.state.*;

import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;

public class DogHueBridge extends AbstractDevice implements HueBridge
{

	public DogHueBridge(BundleContext context,
			DeviceDescriptor dogDeviceDescriptor)
	{
		super(context);

		this.setDeviceProperties(dogDeviceDescriptor);

		this.registerDevice(Device.class.getName());
	}

	public DeviceStatus getState()
	{
		if (this.driver != null)
		{
			return ((HueBridge) this.driver).getState();
		}
		return null;
	}

	public void link()
	{
		if (this.driver != null)
		{
			((HueBridge) this.driver).link();
		}
	}

	/* Generated Notifications */

	/**
	 * /* Implements the DeviceCategory interface requirement
	 **/
	@Override
	public void notifyStateChanged(State newState)
	{
		super.notifyStateChanged(newState);
	}

}

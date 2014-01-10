/**
 * 
 */
package it.polito.elite.dog.core.library.model.devicemodel;

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.ZigBeeGateway;
import it.polito.elite.dog.core.library.model.state.*;

import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;

import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;

/**
 * @author bonino
 * 
 */
public class DogZigBeeGateway extends AbstractDevice implements ZigBeeGateway
{

	public DogZigBeeGateway(BundleContext context,
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
			return ((ZigBeeGateway) this.driver).getState();
		}
		return null;
	}

	public void associate()
	{
		if (this.driver != null)
		{
			((ZigBeeGateway) this.driver).associate();
		}
	}

	public void disassociate()
	{
		if (this.driver != null)
		{
			((ZigBeeGateway) this.driver).disassociate();
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

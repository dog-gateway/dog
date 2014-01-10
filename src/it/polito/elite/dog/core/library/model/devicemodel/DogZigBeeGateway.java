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

	public void installAppliance(String applianceId)
	{
		if (this.driver != null)
		{
			((ZigBeeGateway) this.driver).installAppliance(applianceId);
		}
	}

	public void openNetwork()
	{
		if (this.driver != null)
		{
			((ZigBeeGateway) this.driver).openNetwork();
		}
	}

	public DeviceStatus getState()
	{
		if (this.driver != null)
		{
			return ((ZigBeeGateway) this.driver).getState();
		}
		return null;
	}

	public void deleteAppliance(String applianceId)
	{
		if (this.driver != null)
		{
			((ZigBeeGateway) this.driver).deleteAppliance(applianceId);
		}
	}

	public void closeNetwork()
	{
		if (this.driver != null)
		{
			((ZigBeeGateway) this.driver).closeNetwork();
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

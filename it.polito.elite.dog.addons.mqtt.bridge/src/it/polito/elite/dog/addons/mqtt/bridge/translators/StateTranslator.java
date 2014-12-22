/**
 * 
 */
package it.polito.elite.dog.addons.mqtt.bridge.translators;

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.util.LogHelper;

/**
 * @author bonino
 *
 */
public interface StateTranslator
{
	public byte[] translateState(DeviceStatus state);
	
	public byte[] translateState(DeviceStatus state, LogHelper logger);
}

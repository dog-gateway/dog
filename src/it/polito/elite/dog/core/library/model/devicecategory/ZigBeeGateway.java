/**
 * 
 */
package it.polito.elite.dog.core.library.model.devicecategory;

/**
 * @author bonino
 *
 */
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.state.*;

public interface ZigBeeGateway extends HomeGateway
{
	public static int MATCH_TYPE=100;
	public static int MATCH_SUB_TYPE=50;
	public static int MATCH_MANUFACTURER=0;

	public DeviceStatus getState();
	public void associate();
	public void disassociate();


	/*Generated Notifications*/

	/*Notification: StateChangeNotification*/
	public void notifyStateChanged(State newState);
}

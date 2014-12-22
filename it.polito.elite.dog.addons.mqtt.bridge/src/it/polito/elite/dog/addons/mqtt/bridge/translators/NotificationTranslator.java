/**
 * 
 */
package it.polito.elite.dog.addons.mqtt.bridge.translators;

import it.polito.elite.dog.core.library.model.notification.Notification;
import it.polito.elite.dog.core.library.util.LogHelper;

/**
 * @author bonino
 *
 */
public interface NotificationTranslator
{
	public byte[] translateNotification(Notification notification);

	byte[] translateNotification(Notification notification, LogHelper logger);
}

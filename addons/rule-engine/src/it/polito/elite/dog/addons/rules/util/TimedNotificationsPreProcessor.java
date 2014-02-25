/*
 * Dog - Addons
 * 
 * Copyright (c) 2011-2012 Dario Bonino and Emiliano Castellina
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
package it.polito.elite.dog.addons.rules.util;

import it.polito.elite.dog.addons.rules.schemalibrary.Day;
import it.polito.elite.dog.addons.rules.schemalibrary.EBlock;
import it.polito.elite.dog.addons.rules.schemalibrary.Instant;
import it.polito.elite.dog.addons.rules.schemalibrary.Lhs;
import it.polito.elite.dog.addons.rules.schemalibrary.NotificationType;
import it.polito.elite.dog.addons.rules.schemalibrary.Param;
import it.polito.elite.dog.addons.rules.schemalibrary.RecurringInstant;
import it.polito.elite.dog.addons.rules.schemalibrary.Rule;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.dog.core.library.model.notification.core.TimedTriggerNotification;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.XMLGregorianCalendar;

import org.osgi.service.log.LogService;

/**
 * 
 * @author Castellina Emiliano (skeleton)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
@SuppressWarnings("unused")
public class TimedNotificationsPreProcessor
{
	private LogHelper logger;
	
	public TimedNotificationsPreProcessor(LogHelper logger)
	{
		this.logger = logger;
	}
	
	//TODO Fix when a new scheduler will be developed...
	/**
	 * Pre-process the given rule list by substituting time-based events with
	 * timedOnNotifications scheduled by means of the DogScheduler
	 * 
	 * @param rules
	 *            a {@link RuleList} containing the rules to be pre-processed
	 */
	/*public Set<DogMessage> preProcess(RuleList rules)
	{
		// the set of all messages needed for scheduling the pre-processed event
		// notifications
		HashSet<DogMessage> messagesToSchedule = new HashSet<DogMessage>();
		
		// start the elaboration, first iterate over rules
		for (Rule cRule : rules.getRule())
		{
			// get the time based events, first work on the if part and then on
			// the orIf if they exist
			Lhs lhs = cRule.getIf();
			messagesToSchedule.addAll(this.preProcessLhs(lhs));
			
			// get all the or if clauses and extract the timed events, if
			// present
			List<Lhs> orIfs = cRule.getOrIf();
			
			for (Lhs orIf : orIfs)
			{
				messagesToSchedule.addAll(this.preProcessLhs(orIf));
			}
			
		}
		
		// return the messages that must be scheduled for timed events
		return messagesToSchedule;
	}
	
	private Set<DogMessage> preProcessLhs(Lhs lhs)
	{
		// the set of all messages needed for scheduling the pre-processed lhs
		// notifications
		HashSet<DogMessage> messagesToSchedule = new HashSet<DogMessage>();
		
		// get the time event
		EBlock eBlock = lhs.getEvent();
		RecurringInstant timeEvent = eBlock.getTimeInstant();
		
		// check if the event is a time instant or not, if not return an empty
		// set of messages
		if (timeEvent != null)
		{
			// there is some time-based event to handle
			
			// prepare the new e-block that will substitute the timed one
			EBlock pEBlock = new EBlock();
			// generate a unique UID for the notification
			UUID notificationUUID = UUID.randomUUID();
			
			// generate the notification element
			NotificationType notificationT = new NotificationType();
			notificationT.setClazz("TimedTriggerNotification");
			notificationT.setFromDevice("DogScheduler");
			notificationT.setName(notificationUUID.toString());
			
			// generate the triggerid param
			Param triggerID = new Param();
			triggerID.setName("triggerId");
			triggerID.setValue(notificationUUID.toString());
			
			// add the parameter
			List<Param> params = notificationT.getParam();
			params.add(triggerID);
			
			pEBlock.setNotification(notificationT);
			
			Instant instantAt = timeEvent.getInstant();
			
			// get the corresponding XMLGregorian time calendar
			XMLGregorianCalendar instantTime = instantAt.getTime();
			
			// create the cron schedule for the timed event (repetitive by
			// default...)
			StringBuffer cronSpec = new StringBuffer();
			cronSpec.append(instantTime.getSecond() + " " + instantTime.getMinute() + " " + instantTime.getHour()
					+ " ? * ");
			
			// repeat the message for all indicated weekdays
			Day days = timeEvent.getWeekdays();
			for (Integer cDay : days.getDay())
				// schedule the DAY....
				cronSpec.append(cDay + ",");
			
			// remove the last comma and add the end of the cron specification
			cronSpec.deleteCharAt(cronSpec.length() - 1);
			cronSpec.append(" *");
			
			// here we can create the new dog messages and we can modify the
			// rule list accordingly
			
			// dogmessages
			TimedTriggerNotification notification = new TimedTriggerNotification();
			
			// prepare the job to schedule
			DogScheduleJob jobToSchedule = new DogScheduleJob();
			
			// set the timing information
			jobToSchedule.setChrone(cronSpec.toString());
			
			// get the jobId to set it as notification id
			notification.setTriggerId(notificationUUID.toString());
			
			// create the dog notification message
			DogMessage notificationMessage = DogMessageFactory.createNotificationMessage(notification, null);
			
			// set the job content
			jobToSchedule.setContent(notificationMessage);
			
			// create the scheduling request message
			DogMessage triggerSchedulingMessage = DogMessageFactory.createScheduleMessage(
					MonitorActionTypes.SCHEDULE_CRON, jobToSchedule, null);
			
			// store the message in the set of messages to be scheduled
			messagesToSchedule.add(triggerSchedulingMessage);
			
			this.logger.log(LogService.LOG_DEBUG,
					"[TimedNotificationsPreProcessor]: Created scheduled notification at: " + cronSpec);
			
			// replace the e-block
			lhs.setEvent(pEBlock);
			
			// debug
			this.logger.log(LogService.LOG_DEBUG,
					"[TimedNotificationsPreProcessor]: replaced the timed eBlock with a triggeredNotification block with UID = "
							+ notificationUUID.toString());
		}
		
		// return the messages that must be scheduled for timed events
		return messagesToSchedule;
		
	}*/
}

/*
 * Dog 2.0 - Addons
 * 
 * Copyright [2011]
 * [Emiliano Castellina (emiliano.castellina@polito.it), Politecnico di Torino]
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino]
 * [Luigi De Russis (luigi.derussis@polito.it), Politecnico di Torino]
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.addons.rules.util;

import it.polito.elite.dog.addons.rules.schemalibrary.ABlock;
import it.polito.elite.dog.addons.rules.schemalibrary.CBlock;
import it.polito.elite.dog.addons.rules.schemalibrary.Command;
import it.polito.elite.dog.addons.rules.schemalibrary.Day;
import it.polito.elite.dog.addons.rules.schemalibrary.EBlock;
import it.polito.elite.dog.addons.rules.schemalibrary.Instant;
import it.polito.elite.dog.addons.rules.schemalibrary.Interval;
import it.polito.elite.dog.addons.rules.schemalibrary.Lhs;
import it.polito.elite.dog.addons.rules.schemalibrary.NotificationType;
import it.polito.elite.dog.addons.rules.schemalibrary.Param;
import it.polito.elite.dog.addons.rules.schemalibrary.RecurringInterval;
import it.polito.elite.dog.addons.rules.schemalibrary.Rule;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.dog.addons.rules.schemalibrary.StateType;

import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

public class Xml2DrlTranslator
{
	public Xml2DrlTranslator()
	{
		// empty constructor...no instance data at the moment
	}
	
	/**
	 * Translates a set of XML rules specified as a JAXB class tree into a
	 * proper DRL rule definition string
	 * 
	 * @param localRules
	 * @return a {@link String} containing the DRL representation of the given
	 *         rules
	 */
	public synchronized String xml2drl(RuleList localRules)
	{
		// a string buffer for composing the DRL rules
		StringBuffer drlRules = new StringBuffer();
		
		// add the needed imports
		drlRules.append("import it.polito.elite.dog.addons.rules.RuleEngine;\n");
		drlRules.append("import it.polito.elite.domotics.model.state.*;\n");
		drlRules.append("import it.polito.elite.domotics.model.notification.*;\n");
		drlRules.append("import it.polito.elite.domotics.dog2.doglibrary.corenotifications.*;\n");
		drlRules.append("import it.polito.elite.dog.addons.rules.util.TimeConversion;\n");
		
		// add a globall reference to the SynchroState service
		drlRules.append("global it.polito.elite.dog.addons.rules.RuleEngine dogRule;\n");
		drlRules.append("global it.polito.elite.dog.addons.rules.util.TimeConversion timeConverter;\n");
		
		// get the single rules and iterate over them calling the
		// xmlRule2drlRule method
		for (Rule currentRule : localRules.getRule())
		{
			drlRules.append(this.xmlRule2drlRule(currentRule));
			drlRules.append("\n\n");
		}
		
		// the string representation of the DRL rules generated from the XML
		// rules file
		return drlRules.toString();
	}
	
	public synchronized String xmlRule2drlRule(Rule singleRule)
	{
		// a string buffer for composing the DRL rule
		StringBuffer drlRule = new StringBuffer();
		
		// get the rule name
		drlRule.append("rule \"" + singleRule.getName() + "\"\n");
		drlRule.append("when\n");
		
		// handle the LHS here
		drlRule.append(this.lhs2drlLhs(singleRule));
		
		// insert the "then" DRL clause
		drlRule.append("then\n");
		
		// handle the RHS here
		drlRule.append(this.rhs2drlRhs(singleRule));
		
		// insert the rule closing DRL clause
		drlRule.append("end");
		
		// the string representation of the DRL rule generated from the given
		// XML rule element
		return drlRule.toString();
	}
	
	/**
	 * Given the XML class tree corresponding to a single rule handles the
	 * translation of the rule LHS into the Drools drl format
	 * 
	 * @param singleRule
	 * @return a {@link String} containing the rule LHS expressed in DRL
	 */
	private String lhs2drlLhs(Rule singleRule)
	{
		// the buffer for accumulating the rule translation
		StringBuffer drlLhs = new StringBuffer();
		
		// 1) check if there are one or more orIfs
		List<Lhs> orIfs = singleRule.getOrIf();
		if (orIfs.size() > 0)
		{
			// comment the C-Block
			drlLhs.append("# start IF + OR-IF\n");
			drlLhs.append("(or\n"); // an or operator must be prepended
		}
		
		// 2) handle the main if
		drlLhs.append(this.xmlIf2drlwhen(singleRule.getIf()));
		
		// 3) handle or-ifs
		for (Lhs orIf : orIfs)
		{
			drlLhs.append(this.xmlIf2drlwhen(orIf));
		}
		if (orIfs.size() > 0)
			drlLhs.append(")\n"); // append the ending parenthesis of the or
									// operator
			
		drlLhs.append("# end IF + OR-IF\n");
		
		// convert the buffer to a String
		return drlLhs.toString();
	}
	
	/**
	 * Converts an XML if or an XML or-if clause into the corresponding WHEN
	 * clause in DRL
	 * 
	 * @param ruleLhs
	 *            the If(Or-If) to convert
	 * @return a {@String} containing the DRL representation of the
	 *         given If (Or-If) clause
	 */
	private String xmlIf2drlwhen(Lhs ruleLhs)
	{
		StringBuffer drlIf = new StringBuffer();
		
		// 2.1) get the C-Block if exists
		List<CBlock> cBlocks = ruleLhs.getWhen();
		if (cBlocks.size() > 0)
		{
			// comment the C-Block
			drlIf.append("# start IF (OR-IF) - C-BLOCKs in AND with the associated E-BLOCK\n");
			drlIf.append("(and\n"); // and operator between the if and all the
									// when blocks
		}
		
		// 2.2) get the E-Block
		EBlock ruleEvent = ruleLhs.getEvent();
		
		// handle the event translation
		drlIf.append(this.event2drl(ruleEvent));
		
		// 2.3) handle C-Blocks
		if (cBlocks.size() > 0)
		{
			for (CBlock cBlock : cBlocks)
			{
				drlIf.append(this.cblock2drl(cBlock));
			}
			
			drlIf.append(")\n"); // append the ending parenthesis of the and
									// operator
		}
		
		drlIf.append("# end IF (OR-IF)\n");
		
		return drlIf.toString();
	}
	
	/**
	 * Translated an e-block specified in the XML rule base into the
	 * corresponding DRL condition
	 * 
	 * @param ruleEvent
	 * @return a {@link String} containing the DRL representing the e-block
	 */
	private Object event2drl(EBlock ruleEvent)
	{
		// the string buffer for holding the e-block translation
		StringBuffer drlEBlock = new StringBuffer();
		drlEBlock.append("# begin E-Block\n");
		// get the notification representation
		NotificationType notification = ruleEvent.getNotification();
		if (notification != null)
		{
			List<Param> notificationParams = notification.getParam();
			
			if (!notificationParams.isEmpty())
			{
				// handle StateChangeNotifications separately
				String notificationClass = notification.getClazz();
				
				// explicit and declaration
				// drlEBlock.append("(and \n");
				
				// sigh sigh only hard-coding can save us....
				if (notificationClass.equalsIgnoreCase("StateChangeNotification"))
				{
					Param notificationParam = notificationParams.get(0);
									
					// declare the state change notification to match. It
					// shall come from the given device and shall be
					// carrying the above specified state
					
					drlEBlock.append(notification.getClazz() + "(deviceUri == '"
							+ notification.getFromDevice() + "', newState.currentStateValue[0].value == '"+notificationParam.getValue()+"'");  
					
					if ((notificationParam.getType() != null) && (!notificationParam.getType().isEmpty()))
					{
						// add the constraint on the state type
						drlEBlock.append(", newState.stateName == '" + notificationParam.getType() + "'");
					}
					drlEBlock.append(")\n");
				}
				else
				{
					// only match the notification coming from the given device
					// and matching the additional parameters
					// specified through the XML params
					drlEBlock.append(notification.getClazz() + "( deviceUri == '" + notification.getFromDevice()
							+ "', ");
					
					// counter for avoiding to concatenate the comma sign after
					// the last element
					int i = 1;
					for (Param currentParam : notificationParams)
					{
						// append the condition corresponding to the given
						// parameter
						drlEBlock.append(currentParam.getName() + " == '" + currentParam.getValue() + "'");
						if (i < notificationParams.size())
							drlEBlock.append(", ");
						i++;
					}
					drlEBlock.append(")\n");
				}
				// end of the explicit and declaration
				// drlEBlock.append(")\n");
			}
			else
			{
				// the notification does not have any parameter, therefore it is
				// a discrete notification
				// only the notification class and the device sending the
				// notification must be matched
				drlEBlock.append(notification.getClazz() + "( deviceUri == '" + notification.getFromDevice() + "')\n");
				
			}
		}
		drlEBlock.append("# end E-Block\n");
		
		// convert the string buffer to a string
		return drlEBlock.toString();
	}
	
	/**
	 * Translated a c-block specified in the XML rule base into the
	 * corresponding DRL condition
	 * 
	 * @param cBlock
	 * @return a {@link String} containing the DRL representing the c-block
	 */
	private String cblock2drl(CBlock cBlock)
	{
		// the string buffer for holding the e-block translation
		StringBuffer drlCBlock = new StringBuffer();
		drlCBlock.append("# begin C-Block\n");
		// TODO: handle recurring time interval...at the moment we only provide
		// support to c-blocks containing states
		StateType stateConstraint = cBlock.getState();
		RecurringInterval recurringInterval = cBlock.getTimeInterval();
		if (stateConstraint != null)
		{
			// handle state constraints
			//TODO move to the new state structure...
			// discrete state
			if (stateConstraint.getName() != null)
			{	
				 drlCBlock .append("eval(dogRule.getCurrentSStateOf(\"" +
				 stateConstraint.getOfDevice() + "\",\"" +
				 stateConstraint.getClazz() + "\").equalsIgnoreCase(\"" +
				 stateConstraint.getName() + "\"))\n");
			}
			else
			// handle a condition on a continuous state value
			{
				// get the value2
				 drlCBlock.append("eval(dogRule.getCurrentDStateOf(\"" +
				 stateConstraint.getOfDevice() + "\",\"" +
				 stateConstraint.getClazz() + "\") " +
				 stateConstraint.getEvaluator() + " " +
				 stateConstraint.getValue() + ")\n");

			}
		}
		else if (recurringInterval != null)
		{
			// first get the interval definition
			Interval interval = recurringInterval.getInterval();
			
			// get the start time
			XMLGregorianCalendar startGTime = interval.getStartTime();
			
			// get the end time
			XMLGregorianCalendar endGTime = interval.getEndTime();
			
			if (endGTime == null)
			{
				// get the duration
				Duration intervalDuration = interval.getDuration();
				if (intervalDuration != null)
				{
					endGTime = (XMLGregorianCalendar) startGTime.clone();
					endGTime.add(intervalDuration);
				}
			}
			
			// now start and end time have been defined.... (start time are
			// between 00:00:00 and 24:00:00), get them in seconds
			TimeConversion converter = new TimeConversion();
			long startSTime = converter.hmsToS(startGTime.getHour(), startGTime.getMinute(), startGTime.getSecond());
			long endSTime = converter.hmsToS(endGTime.getHour(), endGTime.getMinute(), endGTime.getSecond());
			
			// handle weekdays
			Day weekdays = recurringInterval.getWeekdays();
			StringBuffer allDays = new StringBuffer();
			for (int cDay : weekdays.getDay())
			{
				allDays.append(cDay + ",");
			}
			
			// delete the last character and trim
			allDays.deleteCharAt(allDays.length() - 1);
			allDays.trimToSize();
			
			// write the DRL expression
			drlCBlock.append("eval(timeConverter.isCurrentTimeIn(" + startSTime + "L ," + endSTime + "L ,\""
					+ allDays.toString() + "\"))\n");
		}
		
		drlCBlock.append("# end C-Block\n");
		
		// convert the string buffer to a string
		return drlCBlock.toString();
	}
	
	private String rhs2drlRhs(Rule singleRule)
	{
		// the buffer for holding the DRL translation of the XML rule rhs
		StringBuffer drlRhs = new StringBuffer();
		
		// get the action blocks
		List<ABlock> aBlocks = singleRule.getThen().getAction();
		
		// convert a-blocks
		for (ABlock aBlock : aBlocks)
		{
			drlRhs.append("# start A-Block\n");
			
			drlRhs.append(this.aBlock2drl(aBlock));
			
			drlRhs.append("# end A-Block\n");
		}
		
		return drlRhs.toString();
	}
	
	private String aBlock2drl(ABlock aBlock)
	{
		// the string buffer for holding the ABlock DRL definition
		StringBuffer drlABlock = new StringBuffer();
		
		// check if timed
		Instant cmdInstant = aBlock.getInstant();
		Interval cmdInterval = aBlock.getInterval();
		
		// direct command
		List<Command> cmds = aBlock.getCommand();
		
		for (Command cmd : cmds)
		{
			if ((cmdInstant == null) && (cmdInterval == null))
			{
				
				// treat the current command
				drlABlock.append(this.cmd2drl(cmd));
				
			}
			else
			{
				// the opposite command shall be scheduled
				// this is not trivial, especially assuming that
				// the opposite command is the one who resets the device state
				// at the same value it had before the command was sent
				if (cmdInterval != null)
				{
					// schedule the opposite command
					drlABlock.append(this.scheduleDrlCmd(cmd, cmdInterval));
					
				}
				else if (cmdInstant != null)
				{
					// just schedule the command at the given time instant
					drlABlock.append(this.scheduleDrlCommand(cmd, cmdInstant));
				}
			}
		}
		return drlABlock.toString();
	}
	
	private Object scheduleDrlCommand(Command cmd, Instant cmdInstant)
	{
		// extract the start time and the call the common wrapper for scheduled
		// commands
		// time, according to the XSD rules can only span over one day
		// (hh:mm:ss)
		XMLGregorianCalendar instantTime = cmdInstant.getTime();
		
		// extract the after...if exists
		Duration afterTime = cmdInstant.getAfter();
		
		long instant = 0;
		long after = 0;
		
		// if time is not null... then handle the precise time activation,
		if (instantTime != null)
		{
			// handle the calendar....
			TimeConversion converter = new TimeConversion();
			instant = converter.hmsToS(instantTime.getHour(), instantTime.getMinute(), instantTime.getSecond());
		}
		else if (afterTime != null)
		{
			// handle the calendar
			Calendar cal = Calendar.getInstance();
			
			// evaluate the time interval after which the command must be sent
			after = afterTime.getTimeInMillis(cal) / 1000;
		}
		return this.scheduleDrlCmd(cmd, instant, Long.MIN_VALUE, after);
	}
	
	/**
	 * Translates a timed command, i.e., a command with an associated duration
	 * and (or) with a start time and an end time
	 * 
	 * @param cmd
	 *            The JAXB object representing the command
	 * @param cmdInterval
	 *            The JAXB Interval object describing the time window in which
	 *            the command must be executed or for which the command must
	 *            last
	 * @return a {@link String} containing the DRL clause representing the
	 *         command
	 */
	private String scheduleDrlCmd(Command cmd, Interval cmdInterval)
	{
		
		// handle the interval
		long startTime = 0;
		
		TimeConversion converter = new TimeConversion();
		
		if (cmdInterval.getStartTime() != null)
		{
			XMLGregorianCalendar start = cmdInterval.getStartTime();
			// handle the calendar....
			startTime = converter.hmsToS(start.getHour(), start.getMinute(), start.getSecond());
		}
		
		long endTime = 0;
		if (cmdInterval.getEndTime() != null)
		{
			XMLGregorianCalendar end = cmdInterval.getEndTime();
			endTime = converter.hmsToS(end.getHour(), end.getMinute(), end.getSecond());
		}
		else
		{
			long duration = converter.durationToS(cmdInterval.getDuration());
			endTime = startTime + duration;
		}
		
		return this.scheduleDrlCmd(cmd, startTime, endTime, 0);
		
	}
	
	// start and end time are expressed in seconds
	private String scheduleDrlCmd(Command cmd, long startTime, long endTime, long afterTime)
	{
		StringBuffer drlCmd = new StringBuffer();
		
		// schedule the opposite command
		drlCmd.append("dogRule.scheduleCommand(\"" + cmd.getToDevice() + "\",\"" + cmd.getName() + "\"");
		
		// check parameters
		List<Param> parameters = cmd.getParam();
		
		// if there are any parameters to the command, serialize them as an
		// array
		// TODO: generalize this to avoid wrong interpretation of commands due
		// to wrong parameter order
		if ((parameters != null) && (!parameters.isEmpty()))
		{
			StringBuffer params = new StringBuffer();
			boolean first = true;
			for (Param param : parameters)
			{
				if (!first)
					params.append(",");
				else
					first = false;
				
				params.append(param.getValue());
			}
			
			drlCmd.append(", new Object[] {" + params + "}");
		}
		else
			drlCmd.append(", null");
		
		if (startTime > 0)
			drlCmd.append(", new Long(" + startTime + "L)");
		else
			drlCmd.append(", null");
		if (endTime > 0)
			drlCmd.append(", new Long(" + endTime + "L)");
		else
			drlCmd.append(", null");
		if (afterTime > 0)
			drlCmd.append(", new Long(" + afterTime + "L)");
		else
			drlCmd.append(", null");
		drlCmd.append(");\n");
		
		return drlCmd.toString();
	}
	
	/**
	 * Translate an action block involving a direct command into a corresponding
	 * DRL clause
	 * 
	 * @param cmd
	 *            The JAXB class representing the command
	 * @return a {@link String} containing the DRL clause representing the
	 *         command
	 */
	private String cmd2drl(Command cmd)
	{
		StringBuffer drlCmd = new StringBuffer();
		
		// compose the drl action block
		drlCmd.append("dogRule.executeCommand(\"" + cmd.getToDevice() + "\",\"" + cmd.getName() + "\"");
		
		// check parameters
		List<Param> parameters = cmd.getParam();
		
		// if there are any parameters to the command, serialize them as an
		// array
		// TODO: generalize this to avoid wrong interpretation of commands due
		// to wrong parameter order
		if ((parameters != null) && (!parameters.isEmpty()))
		{
			StringBuffer params = new StringBuffer();
			boolean first = true;
			for (Param param : parameters)
			{
				if (!first)
					params.append(",");
				else
					first = false;
				
				params.append(param.getValue());
			}
			
			drlCmd.append(", new Object[] {" + params + "}");
		}
		else
			drlCmd.append(", null");
		
		drlCmd.append(");\n");
		
		return drlCmd.toString();
	}
}

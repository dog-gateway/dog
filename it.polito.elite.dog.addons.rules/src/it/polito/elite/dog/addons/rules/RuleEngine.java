/*
 * Dog - Addons
 * 
 * Copyright (c) 2011-2013 Dario Bonino, Luigi De Russis and Emiliano Castellina
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
package it.polito.elite.dog.addons.rules;

import it.polito.elite.dog.addons.rules.api.RuleEngineApi;
import it.polito.elite.dog.addons.rules.schemalibrary.ObjectFactory;
import it.polito.elite.dog.addons.rules.schemalibrary.Rule;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.dog.addons.rules.util.ThreadedRuleBundleInitializer;
import it.polito.elite.dog.addons.rules.util.TimeConversion;
//import it.polito.elite.dog.addons.rules.util.TimedNotificationsPreProcessor;
import it.polito.elite.dog.addons.rules.util.Xml2DrlTranslator;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.notification.Notification;
import it.polito.elite.dog.core.library.model.notification.StateChangeNotification;
import it.polito.elite.dog.core.library.model.notification.core.TimedTriggerNotification;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.util.Executor;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.measure.DecimalMeasure;
import javax.measure.unit.Unit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;
import org.osgi.service.monitor.MonitorAdmin;
import org.osgi.service.monitor.StatusVariable;

/**
 * At last Dog can do something smart!
 * 
 * @author Castellina Emiliano (skeleton)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (original
 *         version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class RuleEngine implements ManagedService, RuleEngineApi, EventHandler
{
	/** OSGi framework reference */
	private BundleContext context;
	
	/** Dog logger */
	protected LogHelper logger;
	
	/** the log id **/
	public static final String logId = "[RuleEngine]: ";
	
	/** Reference to the MonitorAdmin */
	private MonitorAdmin monitorAdmin;
	
	/** Reference to the registration of the DogRulesService */
	private ServiceRegistration<?> srDogRulesService;
	
	/** Reference to the registration of the EventHandler */
	private ServiceRegistration<?> srEventHandler;
	
	/** flag for detecting if the bundle is ready to start or not **/
	private boolean rulesAreReady;
	private boolean servicesHaveBeenMatched;
	
	/** executor service for handling events **/
	private ExecutorService executor;
	
	private JAXBContext jaxbContext;
	
	/**********************************************************************************
	 * Rules specific part
	 */
	
	// the position of the xml file used as local store for Dog rules
	private String localRuleBasePath;
	
	// the JAXB model of the local store file
	private RuleList localRuleBaseJAXB;
	
	// the rule bundle drools rule base
	private KnowledgeBase ruleBase;
	
	// the knowledge session to be used for firing rules
	private StatefulKnowledgeSession runtimeRuleSession;
	
	/**
	 * Class constructor
	 * 
	 * @param context
	 *            OSGi framework reference
	 */
	public RuleEngine()
	{
		// intentionally left empty
		// System.err.println("Created an instance of RuleEngine");
	}
	
	public void activate(BundleContext context)
	{
		
		// initialize the context
		this.context = context;
		
		// create the logger
		this.logger = new LogHelper(context);
		
		// initially the bundle is not ready to offer his services
		this.rulesAreReady = false;
		this.servicesHaveBeenMatched = false;
		
		this.executor = Executors.newSingleThreadExecutor();
		
		// prepare the drools rule base object
		this.ruleBase = KnowledgeBaseFactory.newKnowledgeBase();
		
		try
		{
			this.jaxbContext = JAXBContext.newInstance(RuleList.class.getPackage().getName());
		}
		catch (JAXBException e)
		{
			this.logger.log(LogService.LOG_ERROR, RuleEngine.logId + "Exception in creating the JAXB context", e);
		}
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + "Activated...");
		
		// check if it can start
		this.checkAndRegisterServices();
	}
	
	public void deactivate()
	{
		// unregister provided services
		this.unregisterServices();
		
		// TODO: complete deactivation tasks
	}
	
	// SETTERS AND GETTERS
	/**
	 * @return the rulesAreReady
	 */
	public synchronized boolean isRulesAreReady()
	{
		return rulesAreReady;
	}
	
	/**
	 * @param rulesAreReady
	 *            the rulesAreReady to set
	 */
	public synchronized void setRulesAreReady(boolean rulesAreReady)
	{
		this.rulesAreReady = rulesAreReady;
	}
	
	/**
	 * @return the servicesHaveBeenMatched
	 */
	public synchronized boolean isServicesHaveBeenMatched()
	{
		return servicesHaveBeenMatched;
	}
	
	/**
	 * @param servicesHaveBeenMatched
	 *            the servicesHaveBeenMatched to set
	 */
	public synchronized void setServicesHaveBeenMatched(boolean servicesHaveBeenMatched)
	{
		this.servicesHaveBeenMatched = servicesHaveBeenMatched;
	}
	
	/**
	 * @return the localRuleBasePath
	 */
	public synchronized String getLocalRuleBasePath()
	{
		return localRuleBasePath;
	}
	
	/**
	 * @param localRuleBasePath
	 *            the localRuleBasePath to set
	 */
	public synchronized void setLocalRuleBasePath(String localRuleBasePath)
	{
		this.localRuleBasePath = localRuleBasePath;
	}
	
	/**
	 * @return the localRuleBaseJAXB
	 */
	public synchronized RuleList getLocalRuleBaseJAXB()
	{
		return localRuleBaseJAXB;
	}
	
	/**
	 * @param localRuleBaseJAXB
	 *            the localRuleBaseJAXB to set
	 */
	public synchronized void setLocalRuleBaseJAXB(RuleList localRuleBaseJAXB)
	{
		this.localRuleBaseJAXB = localRuleBaseJAXB;
	}
	
	/**
	 * @return the ruleBase
	 */
	public KnowledgeBase getRuleBase()
	{
		return ruleBase;
	}
	
	/**
	 * @param ruleBase
	 *            the ruleBase to set
	 */
	public synchronized void setRuleBase(KnowledgeBase ruleBase)
	{
		this.ruleBase = ruleBase;
		this.initRuleEvaluationSession();
	}
	
	public synchronized void initRuleEvaluationSession()
	{
		// prepare the new knowledge session...
		this.runtimeRuleSession = this.ruleBase.newStatefulKnowledgeSession();
		
		// asserting this as global variable
		this.runtimeRuleSession.setGlobal("dogRule", this);
		
		// add a time converter object
		TimeConversion converter = new TimeConversion();
		this.runtimeRuleSession.setGlobal("timeConverter", converter);
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " runtime session created... "
				+ this.runtimeRuleSession);
	}
	
	/**
	 * @return the logger
	 */
	public LogHelper getLogger()
	{
		return logger;
	}
	
	/***
	 * Register the services exported by the bundle
	 */
	private void registerServices()
	{
		Hashtable<String, Object> p = new Hashtable<String, Object>();
		// Add this bundle as a listener of monitor, TimedTriggerNotification
		// and DogOntNotification events
		p.put(EventConstants.EVENT_TOPIC, new String[] { "org/osgi/service/monitor",
				TimedTriggerNotification.notificationTopic, "it/polito/elite/domotics/model/notification/*" });
		this.srEventHandler = this.context.registerService(EventHandler.class.getName(), this, p);
		this.srDogRulesService = this.context.registerService(RuleEngineApi.class.getName(), this, null);
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + "Registered services... now ready to execute rules!");
		
	}
	
	/**
	 * Unregister the services exported by the bundle
	 */
	public void unregisterServices()
	{
		if (this.srDogRulesService != null)
		{
			this.srDogRulesService.unregister();
			this.srDogRulesService = null;
		}
		if (this.srEventHandler != null)
		{
			this.srEventHandler.unregister();
			this.srEventHandler = null;
		}
		
	}
	
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		// check that given properties are not null
		if (properties != null)
		{
			String rulesFileName = (String) properties.get(DeviceCostants.RULES);
			if ((rulesFileName != null) && (!rulesFileName.isEmpty()))
			{
				this.logger.log(LogService.LOG_DEBUG,
						"[DogRulesBundle] Loaded pointer to rules file.... now initializing the rule base");
				this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle] Rules: " + rulesFileName);
				
				// try to locate it
				this.localRuleBasePath = System.getProperty("configFolder");
				if (this.localRuleBasePath != null)
					this.localRuleBasePath += "/" + rulesFileName;
				
				// log the local rule base location
				this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle] Full path to the local rule store: "
						+ this.localRuleBasePath);
				
				// create and run the initialization thread
				ThreadedRuleBundleInitializer initThread = new ThreadedRuleBundleInitializer(this,
						this.localRuleBasePath);
				initThread.start();
			}
		}
		
	}
	
	/**
	 * This method is called when the rule bundle initialization has been
	 * completed, only at that point the bundle can register the services it
	 * offers, in a nearly safe way. The only condition that might prevent the
	 * service registration is the missing match with needed dog services such
	 * as the DogSynchroState
	 */
	public synchronized void initEnded()
	{
		this.setRulesAreReady(true);
		// if (this.isServicesHaveBeenMatched())
		// this.registerServices();
	}
	
	/****************************************************************************************************************
	 * BUNDLE LOGIC *
	 ***************************************************************************************************************/
	
	@Override
	public synchronized void addRule(RuleList rules)
	{
		
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + "Adding new rules...");
		
		// clone the original rule list
		List<Rule> originalRules = new ArrayList<Rule>();
		try
		{
			// TODO Implement as clone() for better performance (look for a
			// plugin on the Internet... it exists!)
			JAXBElement<RuleList> contentObj = new JAXBElement<RuleList>(new QName(RuleList.class.getSimpleName()),
					RuleList.class, this.localRuleBaseJAXB);
			JAXBSource source = new JAXBSource(this.jaxbContext, contentObj);
			// marshall the JAXBSource to obtain a deep copy
			originalRules.addAll((jaxbContext.createUnmarshaller().unmarshal(source, RuleList.class).getValue())
					.getRule());
		}
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// filter out duplicate rules....
		this.localRuleBaseJAXB = this.mergeRules(rules);
		
		// preprocess timed e-blocks
		// TimedNotificationsPreProcessor proc = new
		// TimedNotificationsPreProcessor(this.logger);
		// TODO Fix when a new scheduler will be developed...
		// Set<DogMessage> timedEvents =
		// proc.preProcess(this.localRuleBaseJAXB);
		
		// translator from xml to drl
		Xml2DrlTranslator translator = new Xml2DrlTranslator();
		
		// translate rules
		String drlRules = translator.xml2drl(this.localRuleBaseJAXB);
		
		try
		{
			if (drlRules != null)
			{
				// debug
				this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId
						+ " Merged existing and new rules and translated them to DRL:\n" + drlRules);
				
				// here we parse the just created DRL specification and load it
				// into
				// the rules bundle knowledge base
				KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
				
				// the reader for accessing the dynamically created rules
				StringReader drlReader = new StringReader(drlRules);
				
				// the knowledge builder is now used to read the created DRL
				kBuilder.add(ResourceFactory.newReaderResource(drlReader), ResourceType.DRL);
				
				// create the knowledge base to which rules shall be added
				KnowledgeBase ruleBase = KnowledgeBaseFactory.newKnowledgeBase();
				
				// add the just read DRL rules
				ruleBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
				this.setRuleBase(ruleBase);
				
				// TODO Fix when a new scheduler will be developed...
				// schedule timed events if any available
				// this.scheduleTimedEvents(timedEvents);
				
				// debug
				this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId
						+ " new rules have been added to the current rule base");
				
				// save
				this.saveRules(this.localRuleBasePath);
				
				// debug
				this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId
						+ " new rules have been save to the rule repository");
			}
		}
		catch (Exception e)
		{
			// log the exception
			this.logger.log(LogService.LOG_ERROR, "Error adding the rule " + rules.getRule().get(0), e);
			
			// restore the original rule list
			this.localRuleBaseJAXB.getRule().clear();
			this.localRuleBaseJAXB.getRule().addAll(originalRules);
		}
		
	}
	
	/**
	 * Merges the given rule list with the current one and provides back the
	 * merged list. Rules with the same name are overwritten keeping the most
	 * recent (i.e., the given) ones.
	 * 
	 * @param rules
	 * @return
	 */
	private RuleList mergeRules(RuleList rules)
	{
		ObjectFactory factory = new ObjectFactory();
		RuleList newLocalRules = factory.createRuleList();
		List<Rule> localRules = this.localRuleBaseJAXB.getRule();
		List<Rule> newRules = rules.getRule();
		Map<String, Rule> mergedRules = new HashMap<String, Rule>();
		
		for (Rule newRule : newRules)
			// add the new rules
			mergedRules.put(newRule.getName(), newRule);
		
		// merge
		for (Rule localRule : localRules)
			// if not in the map, add it
			if (!mergedRules.containsKey(localRule.getName()))
				mergedRules.put(localRule.getName(), localRule);
		
		newLocalRules.getRule().addAll(mergedRules.values());
		return newLocalRules;
	}
	
	@Override
	public synchronized boolean removeRule(String ruleName)
	{
		boolean ruleExist = false;
		
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " received request to remove a rule:\n" + ruleName);
		
		// remove the rule from the knowledge base (i.e., from all the knowledge
		// packages
		Collection<KnowledgePackage> packages = this.ruleBase.getKnowledgePackages();
		for (KnowledgePackage cPackage : packages)
		{
			if (this.ruleBase.getRule(cPackage.getName(), ruleName) != null)
			{
				this.ruleBase.removeRule(cPackage.getName(), ruleName);
				ruleExist = true;
			}
		}
		
		if (ruleExist)
		{
			// remove the rule from the current rule base and save it
			List<Rule> localRules = this.localRuleBaseJAXB.getRule();
			boolean found = false;
			for (int i = 0; (i < localRules.size() && (!found)); i++)
			{
				if (localRules.get(i).getName().equalsIgnoreCase(ruleName))
				{
					localRules.remove(i);
					found = true;
				}
			}
			
			// save the new rule base
			this.saveRules(this.localRuleBasePath);
		}
		else
		{
			this.logger.log(LogService.LOG_WARNING, "Impossible to remove the rule \"" + ruleName
					+ "\" since it does not exists!");
		}
		
		return ruleExist;
		
	}
	
	@Override
	public synchronized void updateRule(String ruleId, RuleList xmlRule)
	{
		boolean removed = this.removeRule(ruleId);
		if (removed)
			this.addRule(xmlRule);
		else
			this.logger.log(LogService.LOG_WARNING, "Impossible to update the rule \"" + ruleId
					+ "\" since it does not exists!");
		
	}
	
	@Override
	public void loadRules(URI location)
	{
		
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " received request to load rules from:\n" + location);
		
		// check if the URI is local otherwise log the error and fail
		File checkFile = new File(location);
		this.loadRules(checkFile);
		
	}
	
	@Override
	public void loadRules(String location)
	{
		
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " received request to load rules from:\n" + location);
		
		// check if the URI is local otherwise log the error and fail
		File checkFile = new File(location);
		this.loadRules(checkFile);
	}
	
	private void loadRules(File fileToLoad)
	{
		if (fileToLoad.exists() && fileToLoad.canRead())
		{
			this.rulesAreReady = false;
			ThreadedRuleBundleInitializer initializer = new ThreadedRuleBundleInitializer(this,
					fileToLoad.getAbsolutePath());
			initializer.start();
		}
		else
		{
			this.logger
					.log(LogService.LOG_ERROR,
							RuleEngine.logId
									+ " the rule file to load cannot be read, is it on the local machine? URIs cannot point to on-line resources...");
		}
	}
	
	@Override
	public synchronized void saveRules(URI location)
	{
		
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " received request to save rules in:\n" + location);
		File checkFile = new File(location);
		this.saveRules(checkFile);
	}
	
	@Override
	public synchronized void saveRules(String location)
	{
		this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " received request to save rules in:\n" + location);
		File checkFile = new File(location);
		this.saveRules(checkFile);
	}
	
	private synchronized void saveRules(File fileToSave)
	{
		if (fileToSave.exists())
			// overwrite the file...
			fileToSave.delete();
		try
		{
			// create the new file
			fileToSave.createNewFile();
			
			// marshal rules on file...
			// create the JAXB context for parsing the local rule store (in xml)
			Marshaller m = this.jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
					"http://elite.polito.it/domotics/dog/rules/rule_definition rule_definition.xsd ");
			
			StringWriter sw = new StringWriter();
			
			m.marshal(this.localRuleBaseJAXB, sw);
			
			// save the file
			FileWriter outWriter = new FileWriter(fileToSave);
			outWriter.write(sw.toString());
			outWriter.flush();
			outWriter.close();
			
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, RuleEngine.logId + " the rule file cannot be saved where specified\n"
					+ e);
		}
	}
	
	/**
	 * Get the local rule base encoded as DRL
	 * 
	 * @return A {@link String} representation of the local rule base using the
	 *         DRL language (DSL)
	 */
	@Override
	public String getDRLRules()
	{
		String drlRules = "Rule base currently empty";
		
		if (this.localRuleBaseJAXB != null)
		{
			// translator from xml to drl
			Xml2DrlTranslator translator = new Xml2DrlTranslator();
			drlRules = translator.xml2drl(this.localRuleBaseJAXB);
		}
		
		return drlRules;
	}
	
	/**
	 * Get the local rule base encoded as a JAXB object
	 * 
	 * @return an the {@link JAXB} encoding the local rule base according to the
	 *         Dog rule schema.
	 */
	@Override
	public RuleList getRules()
	{
		return this.localRuleBaseJAXB;
	}
	
	@Override
	public void handleEvent(final Event event)
	{
		this.executor.execute(new Runnable() {
			
			@Override
			public void run()
			{
				// Handle events...
				String eventTopic = event.getTopic();
				
				// Debug
				logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " received event of topic: " + eventTopic);
				
				Notification receivedNotification = null;
				
				if (runtimeRuleSession != null)
				{
					// Received a generic MonitorAdmin event (No MonitoringJob,
					// so
					// mon.listener.id property is null)
					if (eventTopic != null && eventTopic.equals("org/osgi/service/monitor")
							&& event.getProperty("mon.listener.id") == null)
					{
						DeviceStatus currentDeviceStatus = null;
						try
						{
							// Try the deserialization of the DeviceStatus
							// (property
							// mon.statusvariable.value)
							currentDeviceStatus = DeviceStatus.deserializeFromString((String) event
									.getProperty("mon.statusvariable.value"));
						}
						
						catch (Exception e)
						{
							logger.log(LogService.LOG_ERROR, RuleEngine.logId + " device status deserialization error",
									e);
						}
						
						// If the deserialization works
						if (currentDeviceStatus != null)
						{
							Map<String, State> allStates = currentDeviceStatus.getStates();
							for (State cState : allStates.values())
							{
								// store the received notification
								receivedNotification = new StateChangeNotification(cState);
								receivedNotification.setDeviceUri(currentDeviceStatus.getDeviceURI());
								
								// debug
								logger.log(LogService.LOG_DEBUG, RuleEngine.logId
										+ " handling state change notification(s): " + receivedNotification);
								
								// insert the notification in the working memory
								FactHandle notificationHandle = runtimeRuleSession
										.insert((StateChangeNotification) receivedNotification);
								
								// fire rules
								runtimeRuleSession.fireAllRules();
								
								// retract the notification
								runtimeRuleSession.retract(notificationHandle);
								
							}
						}
						
					}
					// Received a notification from the devices or a
					// TimedTriggerNotification (DogScheduler)
					else if (eventTopic != null
							&& (eventTopic.startsWith("it/polito/elite/domotics/model/notification/") || eventTopic
									.equals(TimedTriggerNotification.notificationTopic)))
					{
						// debug
						logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " trigger event: " + event);
						
						// handle Notification
						Object eventContent = event.getProperty(EventConstants.EVENT);
						
						if (eventContent instanceof Notification)
						{
							// store the received notification
							receivedNotification = (Notification) eventContent;
							
							// debug
							logger.log(LogService.LOG_DEBUG, RuleEngine.logId + " handling notification: "
									+ receivedNotification);
							
							// insert the notification in the working memory
							FactHandle notificationHandle = runtimeRuleSession.insert(receivedNotification);
							
							// fire rules
							runtimeRuleSession.fireAllRules();
							
							// retract the notification
							runtimeRuleSession.retract(notificationHandle);
						}
					}
				}
				
			}
		});
	}
	
	/*********************************************************************************************************
	 * 
	 * Drools helper service for querying states and sending commands
	 * 
	 ********************************************************************************************************/
	
	public String getCurrentSStateOf(String deviceURI, String stateClass)
	{
		String stringCurrentState = "";
		if (this.getCurrentStateOf(deviceURI, stateClass) instanceof String)
			stringCurrentState = (String) this.getCurrentStateOf(deviceURI, stateClass);
		return stringCurrentState;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public double getCurrentDStateOf(String deviceURI, String stateClass)
	{
		DecimalMeasure<?> stateValue = ((DecimalMeasure<?>) this.getCurrentStateOf(deviceURI, stateClass));
		return stateValue.doubleValue((Unit) stateValue.getUnit());
	}
	
	private Object getCurrentStateOf(String deviceURI, String stateClass)
	{
		Object returningState = new Object();
		// Get the status variable with path "deviceURI/status"
		StatusVariable deviceStatusVariable = monitorAdmin.getStatusVariable(AbstractDevice.toMonitorableId(deviceURI) + "/status");
		DeviceStatus currentDeviceStatus;
		try
		{
			// Try the deserialization of the DeviceStatus
			currentDeviceStatus = DeviceStatus.deserializeFromString((String) deviceStatusVariable.getString());
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, RuleEngine.logId + " device status deserialization error "
					+ e.getClass().getSimpleName());
			return returningState;
		}
		
		// if the device is not attached, currentDeviceState is null and the
		// evaluation cannot be performed (it throws a NullPointerException)
		if (currentDeviceStatus != null)
		{
			// new state representation, in the simplest and stupid way
			// TODO: extend to support multiple stateValues for each State...
			returningState = currentDeviceStatus.getState(stateClass).getCurrentStateValue()[0].getValue();
		}
		return returningState;
		
	}
	
	public void executeCommand(String toDevice, String commandName, Object[] params)
	{
		Executor.getInstance().execute(context, toDevice, commandName, params);
	}
	
	// TODO Review!
	// times are given in seconds...
	public void scheduleCommand(String toDevice, String commandName, Object[] params, Long startTimeS, Long endTimeS,
			Long afterTimeS)
	{
		GregorianCalendar now = new GregorianCalendar();
		TimeConversion converter = new TimeConversion();
		Long nowL = converter.hmsToS(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
		
		// if endTimeM is not specified than set the endTime value to
		// Long.Max_Value to schedule the command...
		// the null value will then be checked to avoid scheduling of the
		// opposite command.
		Long endTime = (endTimeS != null) ? endTimeS : new Long(Long.MAX_VALUE);
		
		// if the command already expired, do not schedule it
		if (nowL < endTime)
		{
			logger.log(LogService.LOG_INFO, "Command Execution on " + toDevice + " -> " + commandName);
			
			// only after is specified
			if ((startTimeS == null) && (afterTimeS != null))
			{
				startTimeS = nowL + afterTimeS;
			}
			
			// if the command should already be executed, execute it immediately
			if (startTimeS < nowL)
			{
				// execute the command immediately
				Executor.getInstance().execute(context, toDevice, commandName, params);
			}
			
			// TODO Fix when a new scheduler will be developed...
			/*
			 * else { // the command shall be scheduled // create the job to
			 * schedule DogScheduleJob timedCmd = new DogScheduleJob();
			 * timedCmd.setTimes(1); timedCmd.setContent(cmdMsg);
			 * 
			 * // convert to h:m:s Calendar startTime =
			 * converter.dayTimeToCalendar(startTimeS);
			 * timedCmd.setStartTime(startTime);
			 * 
			 * // debug this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId +
			 * " scheduled command " + commandName + " at " +
			 * startTime.getTime());
			 * 
			 * // create the schedule request MessageScheduleRequest
			 * requestToSchedule = new
			 * MessageScheduleRequest(MonitorActionTypes.SCHEDULE);
			 * requestToSchedule.setContent(timedCmd);
			 * 
			 * // create the wrapping message DogMessage schMsg = new
			 * DogMessage(); schMsg.setType(MessageTypes.SCHEDULE_COMMAND);
			 * schMsg.setContent(requestToSchedule);
			 * 
			 * // send the scheduling request
			 * this.scheduler.scheduleMessage(schMsg); }
			 * 
			 * // here we need to check the endTime and to possibly schedule the
			 * // opposite command if (endTimeS != null) { // TODO: handle the
			 * scheduling of the opposite command... }
			 */
		}
	}
	
	// TODO Fix when a new scheduler will be developed...
	/*
	 * public synchronized void scheduleTimedEvents(Set<DogMessage> timedEvents)
	 * { // schedule received event messages, if not empty if ((timedEvents !=
	 * null) && (!timedEvents.isEmpty())) { for (DogMessage eventToSchedule :
	 * timedEvents) { this.scheduler.scheduleMessage(eventToSchedule);
	 * 
	 * // debug this.logger.log(LogService.LOG_DEBUG, RuleEngine.logId +
	 * " Scheduled timed event: " + eventToSchedule); }
	 * 
	 * }
	 * 
	 * }
	 */
	
	/***************************************************************************
	 * 
	 * Declarative services handling
	 * 
	 **************************************************************************/
	/**
	 * Handle registration of the needed monitor admin service
	 * 
	 * @param monitorAdmin
	 */
	public void addedMonitorAdmin(MonitorAdmin monitorAdmin)
	{
		// assign the new reference to the local object
		this.monitorAdmin = monitorAdmin;
		
		// check whether the bundle could register provided services
		this.checkAndRegisterServices();
	}
	
	/**
	 * Handle removal of the needed monitor admin service
	 * 
	 * @param monitorAdmin
	 */
	public void removedMonitorAdmin(MonitorAdmin monitorAdmin)
	{
		// unregister
		this.unregisterServices();
		
		// TODO remove/clean inner data structures
	}
	
	// TODO Fix when a new scheduler will be developed...
	/*
	 * public void addedScheduler(DogSchedulerInterface scheduler) { // store a
	 * reference to the dog scheduler for "scheduling" timed // activations and
	 * commands this.scheduler = scheduler;
	 * 
	 * // check whether the bundle could register provided services
	 * this.checkAndRegisterServices(); }
	 * 
	 * public void removedScheduler(DogSchedulerInterface scheduler) { //
	 * unregister this.unregisterServices();
	 * 
	 * // TODO remove/clean inner data structures }
	 */
	
	/**
	 * Check if all dependencies are satisfied and services can be published...
	 */
	private void checkAndRegisterServices()
	{
		if (this.monitorAdmin != null /* && this.scheduler != null */&& this.context != null)
		{ // all the needed services are up and running?
		
			// if the needed services have been matched and the rule base has
			// been correctly initialized than
			// register the services offered by the rule bundle otherwise just
			// flag the serviceMatch and defer
			// the service registration to the end of the initialization phase
			this.setServicesHaveBeenMatched(true);
			// if (this.isRulesAreReady())
			this.registerServices();
		}
	}
}

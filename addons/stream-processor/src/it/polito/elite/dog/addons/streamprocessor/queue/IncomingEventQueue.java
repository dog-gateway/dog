/*
 * Dog - Addons
 * 
 * Copyright (c) 2011-2014 Dario Bonino, Luigi De Russis
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
package it.polito.elite.dog.addons.streamprocessor.queue;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.StreamProcessor;
import it.polito.elite.stream.processing.events.GenericEvent;
import it.polito.elite.stream.processing.events.queue.EventQueue;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (original
 *         version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a> (minor
 *         editing)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class IncomingEventQueue extends EventQueue
{
	// A reference to the stream processor to which events must be delivered
	StreamProcessor sp;
	
	/**
	 * @param logger
	 */
	public IncomingEventQueue(LogHelper logger, StreamProcessor sp)
	{
		super(logger);
		this.sp = sp;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.stream.processing.osgi.service.queue.EventQueue#processEvent
	 * (it.polito.elite.stream.processing.events.GenericEvent)
	 */
	@Override
	protected void processEvent(GenericEvent event)
	{
		// pushes the event to the right spChains source, assuming that the
		// stream name assigned to the event is equal to the id of the source.
		this.sp.pushEventToSource(event, event.getStreamName());
	}
	
}

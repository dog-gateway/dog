/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Dario Bonino and Luigi De Russis
 * 
 * This software is based on a bundle of the Apache Felix project.
 * See the NOTICE file distributed with this work for additional information.
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
package it.polito.elite.dog.core.devicemanager;

import it.polito.elite.dog.core.devicemanager.util.DriverLoader;
import it.polito.elite.dog.core.devicemanager.util.Util;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.device.Driver;

/**
 * This class represents the attributes of a driver in OSGi.
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (successive
 *         modifications)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DriverAttributes
{

    private final Bundle bundle;

    private final ServiceReference<?> ref;

    private final Driver driver;

    private final boolean dynamic;

    public DriverAttributes( ServiceReference<?> ref, Driver driver )
    {
    	this.ref = ref;
    	this.driver = driver;
    	this.bundle = ref.getBundle();
    	this.dynamic = this.bundle.getLocation().startsWith( DriverLoader.DRIVER_LOCATION_PREFIX );
    }


    public ServiceReference<?> getReference()
    {
        return this.ref;
    }


    public String getDriverId()
    {
        return this.ref.getProperty( Constants.DRIVER_ID ).toString();
    }


    public int match( ServiceReference<?> ref ) throws Exception
    {
        return this.driver.match( ref );
    }


    /**
     * a driver bundle is idle if it isn't connected to a device bundle.
     * 
     * @return
     */
    private boolean isInUse()
    {
        ServiceReference<?>[] used = this.bundle.getServicesInUse();
        if ( used == null || used.length == 0 )
        {
            return false;
        }

        for ( ServiceReference<?> ref : used )
        {
            if ( Util.isDevice( ref ) )
            {
                return true;
            }
        }
        return false;
    }


    public String attach( ServiceReference<?> ref ) throws Exception
    {
        return this.driver.attach( ref );
    }


    public void tryUninstall() throws BundleException
    {
    	
        // only install if the driver has been loaded
        if ( !isInUse() && dynamic )
        {
        	this.bundle.uninstall();
        }
    }

}

/*                               
    _/_/_/                          _/_/                _/      
   _/    _/    _/_/      _/_/_/  _/    _/  _/_/_/    _/_/_/_/   
  _/    _/  _/    _/  _/    _/  _/    _/  _/    _/    _/        
 _/    _/  _/    _/  _/    _/  _/    _/  _/    _/    _/         
_/_/_/      _/_/      _/_/_/    _/_/    _/    _/      _/_/      
                         _/                                     
                    _/_/                                DogOnt + Dog -> Semantic House Model

WEBSITE: http://elite.polito.it/dog-tools-80

Copyright (c) [2009] 
[Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and limitations under the License. 

 */
package it.polito.elite.dog.core.housemodel.semantic;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The Activator class for the Semantic House Model bundle of Dog, it uses the
 * same "standard" structure of the SimpleHouseModel activator class
 * 
 * @author bonino
 * 
 */

public class SemanticHouseModelActivator implements BundleActivator
{
	
	// the Semantic House Model object instance....
	protected SemanticHouseModel sHouseModel;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception
	{
		// on start create a new semantic house model instance
		this.sHouseModel = new SemanticHouseModel(context);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception
	{
		// unregister the semantic house model services
		this.sHouseModel.unRegisterServices();
	}
	
}

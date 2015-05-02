package edu.virginia.uvacluster.internal;

import java.util.Properties;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {
	public static CyNetworkManager networkManager;
	public static CyNetworkFactory networkFactory;
	public static CyNetworkNaming networkNaming;
	public static CyNetworkViewFactory networkViewFactory;
	public static CyNetworkViewManager networkViewManager;
	
	@Override
	public void start(BundleContext context) throws Exception {
		networkFactory = getService(context, CyNetworkFactory.class);
		networkManager = getService(context, CyNetworkManager.class);
		networkNaming = getService(context, CyNetworkNaming.class);
		networkViewFactory = getService(context, CyNetworkViewFactory.class);
		networkViewManager = getService(context, CyNetworkViewManager.class);
		
		GenModelTaskFactory modelFactory = new GenModelTaskFactory();
		
		//Set service properties
		Properties genNetworkProperties = new Properties();
		genNetworkProperties.setProperty("preferredMenu", "Apps.Supervised Complex");
		genNetworkProperties.setProperty("title", "Generate Default Model");
		
		//register services
		registerService(context, modelFactory, NetworkTaskFactory.class,genNetworkProperties);
	}
}

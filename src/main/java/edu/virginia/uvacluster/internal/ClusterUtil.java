package edu.virginia.uvacluster.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.SavePolicy;

public class ClusterUtil {

	public static CyNetwork getDefaultModel(String weightFeatureName, SavePolicy policy) {
		CyNetwork modelNetwork = CyActivator.networkFactory.createNetwork(policy);
		CyNode root, size, node;
		double weightCutoffs[] = {1.0, 1.2,  1.5, 1.8, 2.2, 2.6, 3.0};
		String[] featureNames = {"Mean : Degree (4)",
		                         "Variance : Degree (4)",
		                         "Median : Degree (4)",
		                         "Max : Degree (4)",
		                         String.format("Mean : weight{%s} (4)",weightFeatureName),
		                         String.format("Variance : weight{%s} (4)",weightFeatureName),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[0]),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[1]),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[2]),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[3]),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[4]),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[5]),
		                         String.format("Density at cutoff %s (6)",weightCutoffs[6]),
		                         "Density (4)",
		                         "Mean : Degree Correlation (4)",
		                         "Variance : Degree Correlation (4)",
		                         "Max : Degree Correlation (4)",
		                         "Mean : Clustering Coefficient (3)",
		                         "Variance : Clustering Coefficient (3)",
		                         "Max : Clustering Coefficient (3)",
		                         "Mean : Topological Coefficient (3)",
		                         "Variance : Topological Coefficient (3)",
		                         "Max : Topological Coefficient (3)",
		                         "1st : Singular Value (4)",
		                         "2nd : Singular Value (2)",
		                         "3rd : Singular Value (2)"};
		
		modelNetwork.getDefaultEdgeTable().createColumn("Probability", Double.class, false);
		root = modelNetwork.addNode();
		modelNetwork.getRow(root).set("name", "Root");
		size = modelNetwork.addNode();
		modelNetwork.getRow(size).set("name", "Count : Node (5)");
		modelNetwork.addEdge(root, size, true);
			
		for (int i = 0; i < featureNames.length; i++) {
			node = modelNetwork.addNode();
			modelNetwork.getRow(node).set("name", featureNames[i]);
			modelNetwork.addEdge(root, node, true);
			modelNetwork.addEdge(size, node, true);
		}
		return modelNetwork;
	}
}

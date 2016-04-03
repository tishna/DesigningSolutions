package tushti.graphs;
import java.util.HashMap;
import java.util.Map;

/*
 * 
 *  Tushti, Algorithms 
 *  Bellman Ford
 *  
 * Time complexity - O(EV)
 * Space complexity - O(V)
 * 
 * Write program for Bellman Ford algorithm to find single source shortest path in directed graph.
 *  
 */

public class bellman_ford {

	public bellman_ford() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Since we cant literally take infinity; taking a high val
	 */
	 private static int INFINITY_DUMMY = 10000000;

	 /*
	  * In case there is negative weight cycle. Bellman ford doesn't work
	  * So creating a custom Excepton for same by extensing runtime exception
	  */
	    class NegativeCycleException extends RuntimeException {
	    }
	    
	    /* 
	     * Function name: getShortestPath
	     * Returns Map with key as vertex and value as Integer
	     * Parameters : Graph<Integer> graph, Vertex<Integer> sourceVertex
	     * 
	     */
	    public Map<Vertex<Integer>, Integer> getShortestPathInGraph(Graph<Integer> graph,
	            Vertex<Integer> sourceVertex) {
	    	/*
	    	 * distance HashMap
	    	 */
	        Map<Vertex<Integer>, Integer> hmDistance = new HashMap<>();
	        /*
	         * child parent vertex hashmap
	         */
	        Map<Vertex<Integer>, Vertex<Integer>> hmParent = new HashMap<>();

	        /*
	        set distance of every vertex to be infinity initially of 
	        graph passed in parameter
	        */
	        for(Vertex<Integer> v : graph.getAllVertex()) {
	            hmDistance.put(v, INFINITY_DUMMY);
	            /*
	             * Tracking parent and distance in respective hashmaps
	             */
	            hmParent.put(v, null);
	        }

	        //set distance of source vertex to be 0
	        hmDistance.put(sourceVertex, 0);

	        int V = graph.getAllVertex().size();

	        //Parse edges repeatedly V - 1 times
	        for (int i = 0; i < V - 1 ; i++) {
	        	
	        	/*
	        	 * Looping all edges for each vertex
	        	 */
	            for (Edge<Integer> edge : graph.getAllEdges()) {
	            	/*
	            	 * Every edge has two vertices
	            	 */
	                Vertex<Integer> u = edge.getVertex1();
	                Vertex<Integer> v = edge.getVertex2();
	                //relax the edge
	                //if we get better distance to v via u then use this distance
	                //and set u as parent of v.
	                if (hmDistance.get(u) + edge.getWeight() < hmDistance.get(v)) {
	                    hmDistance.put(v, hmDistance.get(u) + edge.getWeight());
	                    hmParent.put(v, u);
	                }
	            }
	        }

	        //relax all edges again. If we still get lesser distance it means
	        //there is negative weight cycle in the graph. Throw exception in that
	        //case
	        for (Edge<Integer> edge : graph.getAllEdges()) {
	            Vertex<Integer> u = edge.getVertex1();
	            Vertex<Integer> v = edge.getVertex2();
	            if (hmDistance.get(u) + edge.getWeight() < hmDistance.get(v)) {
	                throw new NegativeCycleException();
	            }
	        }
	        return hmDistance;
	    }

	    public static void main(String args[]){
	        
	        Graph<Integer> graph = new Graph<>(false);
	        /*
	         * Add edges in graph to get started
	         * 
	         */
	        graph.addEdgeInGraph(0, 3, 8);
	        graph.addEdgeInGraph(0, 1, 4);
	        graph.addEdgeInGraph(0, 2, 5);
	        graph.addEdgeInGraph(1, 2, -3);
	        graph.addEdgeInGraph(2, 4, 4);
	        graph.addEdgeInGraph(3, 4, 2);
	        graph.addEdgeInGraph(4, 3, 1);

	        bellman_ford shortestPath = new bellman_ford();
	        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();
	        Map<Vertex<Integer>,Integer> distance = shortestPath.getShortestPathInGraph(graph, startVertex);
	        System.out.println(distance);
	    }

}

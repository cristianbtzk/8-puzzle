package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
	public static boolean run() {
	    int[][] goal = { { 1, 2, 3 }, { 4, 0, 6 }, { 7, 8, 9 } };
	    System.out.println("Inicio");
		List<Node> visitedStates = new ArrayList<Node>();
	    Queue<Node> nextStates = new LinkedList<Node>();
	    
	    nextStates.add(Utils.generateInitialState());
	    int i = 0;
	    while(true) {
	    	try {
	    		Node nextState = nextStates.remove();
	    		System.out.println("Estado atual");
	    		Utils.showM(nextState.getM());
	    		i++;
	    		// System.out.println("Visitados");
	    		// for (Node node : visitedStates) {
					//Utils.showM(node.getM());
					//System.out.println();
				//}
	    		//System.out.println("Fim visitados");
	    		//if(i == 5) return true;
	    		System.out.println(i);
	    		//Utils.showM(nextState.getM());
	    		// Utils.showM(nextState.getM());
	    		if(Utils.isMatrixEqual(goal, nextState.getM())) {
	    			//System.out.println("AA");
	    			return true;
	    		}
	    			
	    		visitedStates.add(nextState);
				// System.out.println("Filhps");

	    		for (Node node : Utils.getChildNodes(nextState)) {
					if(!Utils.isNodeInArrayList(node, visitedStates)) {
						// System.out.println("Nao ta");
						nextStates.add(node);
					}
					// System.out.println();
					// Utils.showM(node.getM());
				}
	    		
			} catch (Exception e) {
				System.out.println("Erro");
				e.printStackTrace();
				return false;
			}
	    	
	    }
	}
}

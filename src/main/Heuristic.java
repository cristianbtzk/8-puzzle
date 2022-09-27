package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Heuristic {
	public static boolean run() {
	    int[][] goal = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	    System.out.println("Inicio");
		List<String> visitedStates = new ArrayList<String>();
		List<Node> nextStates = new LinkedList<Node>();
		int[][] ini = { { 5, 2, 3 }, { 4, 0, 6 }, { 1, 7, 8 } };
	    Node n = new Node(ini ,1, 1);
	    nextStates.add(n);
	    // nextStates.add(Utils.generateInitialState());
	    int i = 0;
	    while(true) {
	    	try {
	    		//for (Node ni : nextStates) {
				//	Utils.showM(ni.getM());
				//	System.out.println(ni.getPriority());
				//}
	    		Node nextState = nextStates.remove(0);
	    		
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
	    			//System.out.println("AA");S
	    			return true;
	    		}
	    			
	    		visitedStates.add(nextState.key());
				// System.out.println("Filhos");

	    		for (Node node : Utils.getChildNodesWithValue(goal, nextState)) {
					if(!visitedStates.contains(node.key())) {
						for(Iterator<Node> iter = nextStates.iterator(); iter.hasNext();) {
						    Node data = iter.next();
						    if (Utils.isMatrixEqual(node.getM(), data.getM())) {
						        iter.remove();
						        break;
						    }
						}
						nextStates.add(0, node);
						// adicionar novamente
						// System.out.println("Nao ta");
						
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

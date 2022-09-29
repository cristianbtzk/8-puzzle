package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
	private int[][] goal = { { 1, 2, 3 }, { 4, 0, 5 }, { 6, 7, 8 } };
	
	private int visitedNodesSize;
	private long timeSpent;
	private  int[][] ini = { { 1, 2, 3 }, { 4, 0, 5 }, { 8, 6, 7 } };

	public long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public int getVisitedNodesSize() {
		return visitedNodesSize;
	}

	public void setVisitedNodesSize(int visitedNodesSize) {
		this.visitedNodesSize = visitedNodesSize;
	}

	public int[][] getGoal() {
		return goal;
	}

	public void setGoal(int[][] goal) {
		this.goal = goal;
	}

	public boolean isSolvable () {
		int linearPuzzle[] = new int[9];
	    int k = 0;
	    for(int i=0; i<3; i++)
	        for(int j=0; j<3; j++)
	            linearPuzzle[k++] = ini[i][j];
	    
	    int inv_count = 0;
	    for (int i = 0; i < 9; i++)
	        for (int j = i + 1; j < 9; j++)
	            if (linearPuzzle[i] > 0 &&
	            		linearPuzzle[j] > 0 && linearPuzzle[i] > linearPuzzle[j])
	                inv_count++;
	    return inv_count % 2 ==0;
	}
	
	public Node run() {
	    long tempo = System.currentTimeMillis();

	    List<String> visitedStates = new ArrayList<String>();
	    Queue<Node> nextStates = new LinkedList<Node>();
	    Node n = new Node(ini ,1, 1);
	    nextStates.add(n);
	    // nextStates.add(Utils.generateInitialState());
	    while(true) {
	    	try {
	    		Node nextState = nextStates.remove();
	    		visitedStates.add(nextState.key());
	    		
	    		if(Utils.isMatrixEqual(goal, nextState.getM())) {
	    			setVisitedNodesSize(visitedStates.size());
	    			setTimeSpent(System.currentTimeMillis() - tempo);
	    			return nextState;
	    		}
	    			

	    		for (Node node : Utils.getChildNodes(nextState)) {
					if(!visitedStates.contains(node.key()) && 
							!Utils.isNodeInQueue(node, nextStates)) {
						nextStates.add(node);
					}
					
				}
	    		
			} catch (Exception e) {
				System.out.println("Erro");
				e.printStackTrace();
				return null;
			}
	    	
	    }
	}
}

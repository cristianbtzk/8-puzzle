package main;

public class Node implements Comparable<Node>{
	private int[][] m;
	private int row0;
	private int col0;
	private Node parent;
	private int priority;
	
	public Node() {}
	public Node(int m[][], int row0, int col0) {
		this.m = m;
		this.row0 = row0;
		this.col0 = col0;
	}
	
	public Node(int m[][], int row0, int col0, Node parent) {
		this.m = m;
		this.row0 = row0;
		this.col0 = col0;
		this.parent = parent;
	}
	
	public Node(int m[][], int row0, int col0, Node parent, int priority) {
		this.m = m;
		this.row0 = row0;
		this.col0 = col0;
		this.parent = parent;
		this.priority = priority;
	}
	
	@Override public int compareTo(Node n) {
		if (priority > n.getPriority()) return -1;
		if (priority == n.getPriority()) return 0;
		return 1;

	}
	
	public int calcValue(int[][] goal) {
		int count = 0;
		
		for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal.length; j++) {
				int currentNumber = m[i][j];
				for (int k = 0; k < goal.length; k++) {
					for (int l = 0; l < goal.length; l++) {
						if(goal[k][l] == currentNumber) {
							if(i != k || j!=l) count++;
							count += Math.abs(k - i);
							count += Math.abs(l - j);
							break;
						}
					}
				}
			}
		}
		
		setPriority(count);
		
		return count;
	}
	
	public String key() {
		String s = "";
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				s += Integer.toString(m[i][j]);
			}
		}
		return s;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int[][] getM() {
		return m;
	}
	public void setM(int[][] m) {
		this.m = m;
	}
	public int getRow0() {
		return row0;
	}
	public void setRow0(int row0) {
		this.row0 = row0;
	}
	public int getCol0() {
		return col0;
	}
	public void setCol0(int col0) {
		this.col0 = col0;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	
}

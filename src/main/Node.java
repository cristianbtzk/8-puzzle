package main;

public class Node {
	private int[][] m;
	private int row0;
	private int col0;
	private Node parent;
	
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

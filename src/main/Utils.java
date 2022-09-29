package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Utils {
	public static int[][] copyArray(int[][] a) {
		int[][] b = new int[3][3];
		for (int i = 0; i < 3; i++) {
			  b[i] = Arrays.copyOf(a[i], a[i].length);
		}
		return b;
	}
	
	public static List<Node> getChildNodes(Node node) {
		List<Node> children = new ArrayList<Node>();
		int[][] m = node.getM();
		int col0 = node.getCol0();
		int row0 = node.getRow0();
		// col0 col0 2
		if(col0 < 2) { //Posso mover a esquerda
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0][col0 + 1];
			newM[row0][col0 + 1] = 0;
			Node newNode = new Node(newM, row0, col0 + 1, node, "Esquerda");
			children.add(newNode);
		}
		
		if(col0 > 0) { //Posso mover a direita
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0][col0 - 1];
			newM[row0][col0 - 1] = 0;
			Node newNode = new Node(newM, row0, col0 - 1, node, "Direita");
			children.add(newNode);
		}
		
		if(row0 > 0) { //Posso mover abaixo
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0 - 1][col0];
			newM[row0 - 1][col0] = 0;
			Node newNode = new Node(newM, row0 - 1, col0, node, "Baixo");
			children.add(newNode);
		}
		
		if(row0 < 2) { //Posso mover acima
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0 + 1][col0];
			newM[row0 + 1][col0] = 0;
			Node newNode = new Node(newM, row0 + 1, col0, node, "Cima");
			children.add(newNode);
		}
		return children;
	}
	
	public static List<Node> getChildNodesWithValue(int[][] goal, Node node) {
		List<Node> children = new ArrayList<Node>();
		int[][] m = node.getM();
		int col0 = node.getCol0();
		int row0 = node.getRow0();
		// col0 col0 2
		if(col0 < 2) { //Posso mover a esquerda
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0][col0 + 1];
			newM[row0][col0 + 1] = 0;
			Node newNode = new Node(newM, row0, col0 + 1, node, "Esquerda");
			newNode.calcValue(goal);
			children.add(newNode);
		}
		
		if(col0 > 0) { //Posso mover a direita
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0][col0 - 1];
			newM[row0][col0 - 1] = 0;
			Node newNode = new Node(newM, row0, col0 - 1, node, "Direita");
			newNode.calcValue(goal);

			children.add(newNode);
		}
		
		if(row0 > 0) { //Posso mover abaixo
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0 - 1][col0];
			newM[row0 - 1][col0] = 0;
			Node newNode = new Node(newM, row0 - 1, col0, node, "Baixo");
			newNode.calcValue(goal);
			children.add(newNode);
		}
		
		if(row0 < 2) { //Posso mover acima
			int[][] newM = copyArray(m);
			newM[row0][col0] = newM[row0 + 1][col0];
			newM[row0 + 1][col0] = 0;
			Node newNode = new Node(newM, row0 + 1, col0, node, "Cima");
			newNode.calcValue(goal);
			children.add(newNode);
		}
		Collections.sort(children);
		return children;
	}
	
	public static boolean isNodeInArrayList(Node node, List<Node> l) {
		for(Node n: l) {
			if(isMatrixEqual(node.getM(), n.getM())) return true;
		}
		return false;
	}
	
	public static boolean isNodeInQueue(Node node, Queue<Node> l) {
		for(Node n: l) {
			if(isMatrixEqual(node.getM(), n.getM())) return true;
		}
		return false;
	}
	
	public static boolean isMatrixEqual(int[][] a, int[][] b ) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(a[i][j] != b[i][j]) return false;
			}
		}
		return true;
	}
	
	public static Node generateInitialState(){
		int s = 8;
	    ArrayList<Integer> vals = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4,
	        5, 6, 7, 8));

	    Random r = new Random();
	    int[][] start = new int[3][3];
	    int row0 = 0;
	    int col0 = 0;
	    
	    for (int i = 0; i < 3; i++) {
	      for (int j = 0; j < 3; j++) {
	        if (s > 0) {
	          int generated = r.nextInt(s);
	          int value = vals.get(generated);
	          if(value == 0) {
	        	  row0 = i;
	        	  col0 = j;
	          }
	          start[i][j] = value;
	          vals.remove(generated);
	        } else {
	          start[i][j] = vals.get(0);
	        }
	        s--;
	      }
	    }
	    
	    Node n = new Node(start, row0, col0);
	    
	    return n;
	}
	

	public static void showM(int[][] m) {
		for (int i = 0; i < 3; i++) {
		      for (int j = 0; j < 3; j++) {
		        System.out.print(Integer.toString(m[i][j]) + ' ');
		      }
		      System.out.println();
		    }
	}
}

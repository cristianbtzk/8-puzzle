package main;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
//		if(Bfs.run()) {
//			System.out.println("Sucesso");
//		}else {
//			System.out.println("Não achou");
//		}

		Heuristic heuristica = new Heuristic();
		if(heuristica.isSolvable()) {
			Node n = heuristica.run();
			if(n != null) {
				Deque<Node> nodes = new ArrayDeque<Node>();
				nodes.add(n);
				Node node = n.getParent();
				while(node != null) {
					nodes.add(node);
					node = node.getParent();
				}
				
				for (Node nodea : nodes) {
					Utils.showM(nodea.getM());
					System.out.println();
				}
				System.out.println("Sucesso");
			}else {
				System.out.println("Não achou");
			}
		} else {
			System.out.println("Ímpossível resolver.");
		}
	}
}

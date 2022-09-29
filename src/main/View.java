package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class View extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		getContentPane().setLayout(null);
		
		JButton btnBuscaHeurstica = new JButton("Busca Heurística");
		btnBuscaHeurstica.setBounds(63, 195, 117, 25);
		getContentPane().add(btnBuscaHeurstica);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuscaHeurstica_1 = new JButton("Busca Heurística");
		
		btnBuscaHeurstica_1.setBounds(17, 170, 171, 25);
		contentPane.add(btnBuscaHeurstica_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 49, 100, 100);
		contentPane.add(panel);
		
		panel.setLayout(new GridLayout(3, 3));
		
		JPanel panelResolucao = new JPanel();

		panelResolucao.setBounds(200, 49, 1000, 1000);
		contentPane.add(panelResolucao);
		
		JLabel lblTimeSpent = new JLabel("A");
		lblTimeSpent.setBounds(23, 244, 160, 15);
		lblTimeSpent.setVisible(false);
		
		
		contentPane.add(lblTimeSpent);
		
		JLabel lblVisitedStates = new JLabel("EstadosVisitados");
		lblVisitedStates.setVerticalAlignment(SwingConstants.TOP);
		lblVisitedStates.setBounds(23, 260, 207, 91);
		lblVisitedStates.setVisible(false);

		contentPane.add(lblVisitedStates);
		
		JLabel lblmpossvelResolver = new JLabel("Ímpossível Resolver");
		lblmpossvelResolver.setBounds(23, 244, 70, 15);
		lblmpossvelResolver.setVisible(false);

		contentPane.add(lblmpossvelResolver);
		
		JButton btnNewButton = new JButton("Busca Horizontal");
		
		btnNewButton.setBounds(17, 207, 171, 25);
		contentPane.add(btnNewButton);

		
		int count = 1;
		for (int i = 0; i < 9; i++) {
			JLabel field;
			if(i == 4) {
				 field = new JLabel("0");
			}else {
				field = new JLabel(String.valueOf(count));
				count++;
			}
			field.setHorizontalAlignment(JTextField.CENTER);
			field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panel.add(field);
		}
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Bfs bfs = new Bfs();
				if(bfs.isSolvable()) {
					Node n = bfs.run();
					System.out.println(n);
					if(n != null) {
						Stack<Node> nodes = new Stack<Node>();
						nodes.add(n);
						Node node = n.getParent();
						while(node != null) {
							nodes.push(node);
							node = node.getParent();
						}
						Node nodea = null;
						
						lblTimeSpent.setVisible(true);
						lblTimeSpent.setText(String.valueOf(bfs.getTimeSpent()) + "ms");
						
						lblVisitedStates.setVisible(true);
						lblVisitedStates.setText("Estados visitados: " + String.valueOf(bfs.getVisitedNodesSize()));
						
						while(!nodes.isEmpty()) {
							nodea = nodes.pop();
						
							Utils.showM(nodea.getM());
							System.out.println();
							JPanel p = new JPanel();
							p.setPreferredSize(new Dimension(100, 100));
	
							p.setLayout(new GridLayout(3, 3));
							panelResolucao.add(p);
	
							int[][] m = nodea.getM();
							JLabel field;
	
							for (int i = 0; i < 3; i++) {
								for (int j = 0; j < 3; j++) {
									field = new JLabel(String.valueOf(m[i][j]));
									field.setHorizontalAlignment(JTextField.CENTER);
									field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
									p.add(field);
								}	
							}							
						}
						
						getContentPane().revalidate();
	
						getContentPane().repaint();
						JOptionPane.showMessageDialog(null, "A achou");
					}else {
						System.out.println("ASnnnn");
	
						JOptionPane.showMessageDialog(null, "Não achou");
					}
				} else {
					lblmpossvelResolver.setVisible(true);
				}
			}
		});
		
		btnBuscaHeurstica_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Heuristic heuristica = new Heuristic();
				if(heuristica.isSolvable()) {
					Node n = heuristica.run();
					System.out.println(n);
					if(n != null) {
						Stack<Node> nodes = new Stack<Node>();
						nodes.add(n);
						Node node = n.getParent();
						while(node != null) {
							nodes.push(node);
							node = node.getParent();
						}
						Node nodea = null;
						
						lblTimeSpent.setVisible(true);
						lblTimeSpent.setText(String.valueOf(heuristica.getTimeSpent()) + "ms");
						
						lblVisitedStates.setVisible(true);
						lblVisitedStates.setText("Estados visitados: " + String.valueOf(heuristica.getVisitedNodesSize()));
						
						while(!nodes.isEmpty()) {
							nodea = nodes.pop();
						
							Utils.showM(nodea.getM());
							System.out.println();
							JPanel p = new JPanel();
							p.setPreferredSize(new Dimension(100, 100));
	
							p.setLayout(new GridLayout(3, 3));
							panelResolucao.add(p);
	
							int[][] m = nodea.getM();
							JLabel field;
	
							for (int i = 0; i < 3; i++) {
								for (int j = 0; j < 3; j++) {
									field = new JLabel(String.valueOf(m[i][j]));
									field.setHorizontalAlignment(JTextField.CENTER);
									field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
									p.add(field);
								}	
							}
							System.out.println("a");
							
						}
						
						getContentPane().revalidate();
	
						getContentPane().repaint();
						JOptionPane.showMessageDialog(null, "A achou");
					}else {
						System.out.println("ASnnnn");
	
						JOptionPane.showMessageDialog(null, "Não achou");
					}
				} else {
					lblmpossvelResolver.setVisible(true);
				}
			}
		});
		
	}
}

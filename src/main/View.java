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
import javax.swing.JScrollPane;
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
		
		
		
		JPanel panelResolucao = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelResolucao.setPreferredSize(new Dimension(1000, 10000));
		JScrollPane scrollPane = new JScrollPane(panelResolucao);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(200, 180, 1000, 1000);
		contentPane.add(scrollPane);
		
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
		
		JLabel lblObjetivo = new JLabel("Objetivo");
		lblObjetivo.setBounds(60, 24, 70, 25);
		contentPane.add(lblObjetivo);
		
		JPanel panelSteps = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSteps.setBounds(200, 50, 1000, 100);
		contentPane.add(panelSteps);
		
		int count = 1;
		for (int i = 0; i < 9; i++) {
			JLabel field;
			if(i == 4) {
				 field = new JLabel(" ");
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
				panelResolucao.removeAll();
				panelSteps.removeAll();

				Bfs bfs = new Bfs();
				if(bfs.isSolvable()) {
					Node n = bfs.run();
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
						lblTimeSpent.setText("Tempo: " + String.valueOf(bfs.getTimeSpent()) + "ms");
						
						lblVisitedStates.setVisible(true);
						lblVisitedStates.setText("Estados visitados: " + String.valueOf(bfs.getVisitedNodesSize()));
						
						while(!nodes.isEmpty()) {
							nodea = nodes.pop();
							
							if(nodea.getMovement() != null) {
								JLabel field = new JLabel(nodea.getMovement());
								panelSteps.add(field);
							}
						
							JPanel p = new JPanel();
							p.setPreferredSize(new Dimension(100, 100));
	
							p.setLayout(new GridLayout(3, 3));
							panelResolucao.add(p);
	
							int[][] m = nodea.getM();
							JLabel field;
	
							for (int i = 0; i < 3; i++) {
								for (int j = 0; j < 3; j++) {
									if(m[i][j] == 0)
										field = new JLabel(" ");
									else
										field = new JLabel(String.valueOf(m[i][j]));
									field.setHorizontalAlignment(JTextField.CENTER);
									field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
									p.add(field);
								}	
							}							
						}	
						
					}else {	
						JOptionPane.showMessageDialog(null, "Não achou");
					}
				} else {
					lblmpossvelResolver.setVisible(true);
				}
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		
		btnBuscaHeurstica_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelResolucao.removeAll();
				panelSteps.removeAll();

				Heuristic heuristica = new Heuristic();
				if(heuristica.isSolvable()) {
					Node n = heuristica.run();
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
						lblTimeSpent.setText("Tempo: " + String.valueOf(heuristica.getTimeSpent()) + "ms");
						
						lblVisitedStates.setVisible(true);
						lblVisitedStates.setText("Estados visitados: " + String.valueOf(heuristica.getVisitedNodesSize()));
						
						while(!nodes.isEmpty()) {
							nodea = nodes.pop();
							
							if(nodea.getMovement() != null) {
								JLabel field = new JLabel(nodea.getMovement());
								panelSteps.add(field);
							}
						
							JPanel p = new JPanel();
							p.setPreferredSize(new Dimension(100, 100));
	
							p.setLayout(new GridLayout(3, 3));
							panelResolucao.add(p);
	
							int[][] m = nodea.getM();
							JLabel field;
	
							for (int i = 0; i < 3; i++) {
								for (int j = 0; j < 3; j++) {
									if(m[i][j] == 0)
										field = new JLabel(" ");
									else
										field = new JLabel(String.valueOf(m[i][j]));
									field.setHorizontalAlignment(JTextField.CENTER);
									field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
									p.add(field);
								}	
							}
							
						}
					}else {
	
						JOptionPane.showMessageDialog(null, "Não achou");
					}
				} else {
					lblmpossvelResolver.setVisible(true);
				}
				getContentPane().revalidate();
				
				getContentPane().repaint();
			}
		});
		
	}
}

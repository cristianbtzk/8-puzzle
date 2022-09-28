package main;

import java.awt.BorderLayout;
import java.awt.Color;
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuscaHeurstica_1 = new JButton("Busca Heurística");
		
		btnBuscaHeurstica_1.setBounds(59, 283, 185, 25);
		contentPane.add(btnBuscaHeurstica_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 49, 200, 200);
		contentPane.add(panel);
		
		panel.setLayout(new GridLayout(3, 3));
		
		JPanel panel_1 = new JPanel(); 
		panel_1.setBounds(298, 29, 752, 971);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
		btnBuscaHeurstica_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Heuristic heuristica = new Heuristic();
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
					System.out.println("ASd");
					Node nodea = null;
					
					while(!nodes.isEmpty()) {
						nodea = nodes.pop();
					
						Utils.showM(nodea.getM());
						System.out.println();
						JPanel p = new JPanel();
						p.setBounds(44, 49, 300, 300);
						p.setLayout(new GridLayout(3, 3));
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
						
						panel_1.add(p);
					}
					getContentPane().revalidate();

					getContentPane().repaint();
					JOptionPane.showMessageDialog(null, "A achou");
				}else {
					System.out.println("ASnnnn");

					JOptionPane.showMessageDialog(null, "Não achou");
				}

			}
		});
		
	}
}

package html_validator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class screen extends JFrame {

	private JPanel contentPane;
	private JTextField path;
	private JTextField text;
	
	PilhaLista<String> pilha = new PilhaLista<String>();

	
	
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screen frame = new screen();
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
	public screen() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arquivo:");
		lblNewLabel.setBounds(10, 14, 48, 14);
		contentPane.add(lblNewLabel);
		
		path = new JTextField();
		path.setBounds(77, 11, 439, 20);
		contentPane.add(path);
		path.setColumns(10);
		
		JButton btnNewButton = new JButton("Analisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(path.getText()));
					String line= " ";
					String tag = " ";
					String inside_tag = " ";
					int idx = 0;
					while(line != null) {
						line= br.readLine();
						
						if(line.trim().length() == 0) {
							continue;
						}
						if(Character.toString(line.charAt(0)).equals(" ")) {
							line = line.trim();
						}
						for(int i =0; i < line.length(); i++) {
							if(line.charAt(i)=='<') {
								idx= i + 1;
								for (int j = idx; j < line.length(); j++) {
									if(line.charAt(j)=='>') {
										inside_tag = line.substring(line.indexOf('<'), (line.indexOf('>')+1));
										if (inside_tag.contains(" ")) {
											tag = line.substring(line.indexOf('<'), line.indexOf(" "))+">";
										}else {
											tag = line.substring(line.indexOf('<'), (line.indexOf(">")+1));
										}
										line = line.replace(inside_tag, "");
										
										pilha.push(tag);
										break;
									}
								}
							}
						}
								
					}
						
					
				} catch (FileNotFoundException e0) {
					text.setText("Erro ao ler arquivo");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException il) {
					text.setText(il.toString());
				} catch (NullPointerException e2) {
					System.out.println(pilha.toString());
					if(pilha.validate()) {
						text.setText("Arquivo válido");
						System.out.println(pilha.listaTagToString());
						TagTableModel tagModel = new TagTableModel(pilha.lista_tag);
						table.setModel(tagModel);
						pilha.liberar();
					}else {
						text.setText("Arquivo não é válido");
					}
				}
			}
		});
		btnNewButton.setBounds(568, 10, 98, 23);
		contentPane.add(btnNewButton);
		
		text = new JTextField();
		text.setBounds(49, 42, 588, 90);
		contentPane.add(text);
		text.setColumns(10);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null}
			},
			new String[] {"Tag", "Número de Ocorrências"}
		));
		table.setBounds(49, 170, 577, 222);
		contentPane.add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(609, 170, 17, 48);
		contentPane.add(scrollBar);
		


	}
}

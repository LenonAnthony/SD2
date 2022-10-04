package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Produtos.Produto;

import java.awt.SystemColor;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class PopUpBuscaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.a
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpBuscaProduto frame = new PopUpBuscaProduto();
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
	public PopUpBuscaProduto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PopUpBuscaProduto.class.getResource("/Icone/IconPope.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 145, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 145, 145));
		panel.setBounds(10, 11, 414, 167);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 83, 68, 27);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(66, 83, 348, 27);
		textField.setColumns(10);
		panel.add(textField);

		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean achou = false;
				ArrayList<Produto> arrays = new ArrayList<>();
				arrays.addAll(PopUpProduto.getCp().getRepositorioProdutos().getProdutos());

				int tamanho = PopUpProduto.getCp().getRepositorioProdutos().getProdutos().size();
				for (int i = 0; i < tamanho; i++) {
					if (textField.getText().equalsIgnoreCase(arrays.get(i).getNome())) {
						JOptionPane.showMessageDialog(null, "Produto Encontrado!.");
						achou = true;
					}
				}
				if (!achou) {
					JOptionPane.showMessageDialog(null, "Produto n�o Encontrado!.");
				}
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(221, 193, 171, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1.setBounds(40, 193, 171, 45);
		contentPane.add(btnNewButton_1);
	}

}

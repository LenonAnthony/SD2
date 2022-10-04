package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clientes.Cliente;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class PopUpBuscaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpBuscaCliente frame = new PopUpBuscaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.a
	 */
	public PopUpBuscaCliente() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PopUpBuscaCliente.class.getResource("/Icone/IconPope.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 145, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 145, 145));
		panel.setBounds(10, 11, 414, 186);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 45, 88, 24);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(80, 42, 334, 27);
		textField.setColumns(10);
		panel.add(textField);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(0, 116, 88, 24);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(80, 113, 334, 27);
		textField_1.setColumns(10);
		panel.add(textField_1);

		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean achou = false;
				ArrayList<Cliente> arrays = new ArrayList<>();
				arrays.addAll(PopUpCliente.getCc().getRepositorioClientes().getClientes());

				int tamanho = PopUpCliente.getCc().getRepositorioClientes().getClientes().size();
				for (int i = 0; i < tamanho; i++) {

					if (textField.getText().equalsIgnoreCase(arrays.get(i).getNome())
							&& textField_1.getText().equalsIgnoreCase(arrays.get(i).getCpf())) {

						JOptionPane.showMessageDialog(null, "Cliente Encontrado!.");
						achou = true;

					}
				}
				if (!achou) {
					JOptionPane.showMessageDialog(null, "Cliente n�o Encontrado!.");

				}
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(224, 204, 171, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1.setBounds(44, 204, 171, 45);
		contentPane.add(btnNewButton_1);
	}

}

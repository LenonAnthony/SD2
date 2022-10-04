package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clientes.CCInterface;
import Clientes.Cliente;
import Clientes.ControladorCliente;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class PopUpCliente extends JFrame {

	public static ControladorCliente cc;
	public static CCInterface cc1;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static CCInterface getCc1() {
		return cc1;
	}

	public static void setCc1(CCInterface cc1) {
		PopUpCliente.cc1 = cc1;
	}

	public static ControladorCliente getCc() {
		return cc;
	}

	public static void setCc(ControladorCliente cc) {
		PopUpCliente.cc = cc;
	}

	public static void inicializar() {
		try {
			cc = new ControladorCliente();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cc1 = (CCInterface) Naming.lookup("rmi://localhost:1101/CC");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Naming.rebind("rmi://localhost:1101/CC", cc);
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Launch the application.a
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpCliente frame = new PopUpCliente();
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
	public PopUpCliente() throws Exception {

		cc = new ControladorCliente();
		cc1 = (CCInterface) Naming.lookup("rmi://localhost:1101/CC");
		Naming.rebind("rmi://localhost:1101/CC", cc);

		setIconImage(Toolkit.getDefaultToolkit().getImage(PopUpCliente.class.getResource("/Icone/IconPope.png")));
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
		panel.setBounds(10, 11, 414, 186);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 31, 49, 27);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(85, 31, 329, 27);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(0, 90, 49, 27);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(85, 87, 329, 27);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(0, 143, 88, 27);
		panel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(85, 143, 329, 27);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
						|| textField_2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Favor, preencher tudo.");
				} else {
					Cliente c = new Cliente(textField.getText(), textField_1.getText(), textField_2.getText(), 0);
					try {
						cc.cadastrar(c);
						cc1.cadastrar(c);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
					System.out.println(cc);
					dispose();

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(231, 210, 171, 45);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1.setBounds(50, 208, 171, 45);
		contentPane.add(btnNewButton_1);
	}
}

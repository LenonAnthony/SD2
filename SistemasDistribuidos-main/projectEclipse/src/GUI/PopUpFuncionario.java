package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Funcionarios.CFInterface;
import Funcionarios.ControladorFuncionario;
import Funcionarios.Funcionario;

import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class PopUpFuncionario extends JFrame {

	public static ControladorFuncionario cf;
	private static CFInterface cf1;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public static void inicializar() {
		try {
			cf = new ControladorFuncionario();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cf1 = (CFInterface) Naming.lookup("rmi://localhost:1099/CF");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Naming.rebind("rmi://localhost:1099/CF", cf);
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ControladorFuncionario getCf() {
		return cf;
	}

	public static CFInterface getCf1() {
		return cf1;
	}

	public static void setCf1(CFInterface cf1) {
		PopUpFuncionario.cf1 = cf1;
	}

	public static void setCf(ControladorFuncionario cf) {
		PopUpFuncionario.cf = cf;
	}

	/**
	 * Launch the application.a
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					PopUpFuncionario frame = new PopUpFuncionario();
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
	public PopUpFuncionario() throws Exception {

		cf = new ControladorFuncionario();
		cf1 = (CFInterface) Naming.lookup("rmi://localhost:1099/CF");
		Naming.rebind("rmi://localhost:1099/CF", cf);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PopUpFuncionario.class.getResource("/Icone/IconPope.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 145, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
						|| textField_3.getText().isEmpty() || textField_4.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Favor, preencher tudo.");
				} else {
					Funcionario f = new Funcionario(textField.getText(), textField_1.getText(), textField_4.getText(),
							textField_2.getText(), textField_3.getText());

					try {
						if (cf1.existeLoginSenha(f)) {
							JOptionPane.showMessageDialog(null, "Funcionario ja existe.");
						} else {
							cf.cadastrar(f);
							cf1.cadastrar(f);
							System.out.println(cf.getRepositorioFuncionario().getFuncionarios());
							System.out.println(cf1.getRepositorioFuncionario().getFuncionarios());
							dispose();

						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(224, 205, 171, 45);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 145, 145));
		panel.setBounds(10, 11, 414, 183);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(1, 3, 58, 24);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(69, 0, 345, 27);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(5, 41, 54, 24);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(69, 38, 345, 27);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(1, 76, 58, 27);
		panel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(69, 76, 345, 27);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(1, 114, 58, 27);
		panel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(69, 114, 345, 27);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Tipo:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(1, 152, 58, 27);
		panel.add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setBounds(69, 152, 345, 27);
		textField_4.setToolTipText("Valores permitidos: \"Gerente\" ou \"Funcionario\"");
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1.setBounds(43, 205, 171, 45);
		contentPane.add(btnNewButton_1);
	}

}

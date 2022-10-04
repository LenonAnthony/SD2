package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Clientes.Cliente;
import Funcionarios.Funcionario;
import Produtos.Produto;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.awt.Toolkit;

public class TelaGerente extends JFrame {
//s
	int contador = 0;
	private JPanel contentPane;
	private JTable table;
	private static int linha;
	private static int linhaCliente = -1;
	private String campoDeTexto;
	private String campoDeTexto_1;
	private String campoDeTexto_2;
	private String campoDePreco;
	private DefaultTableModel dtm;
	JLabel lblNewLabel = new JLabel("Tela Operacional Do Gerente");
	JButton btnNewButton_1_1 = new JButton("Card\u00E1pio");
	JButton btnNewButton_1 = new JButton("Equipe");
	JButton btnNewButton_1_1_1 = new JButton("Clientes");

	public static int getLinha() {
		return linha;
	}

	public static void setLinha(int linha) {
		TelaGerente.linha = linha;
	}

	public static int getLinhaCliente() {
		return linhaCliente;
	}

	public static void setLinhaCliente(int linhaCliente) {
		TelaGerente.linhaCliente = linhaCliente;
	}

	public void atualizarJTableFuncionarios() {

		int tamanho;
		try {
			PopUpFuncionario.getCf().atualiza();
			tamanho = PopUpFuncionario.getCf1().getRepositorioFuncionario().getFuncionarios().size();
			if (tamanho == 0) {

			} else {
				ArrayList<Funcionario> arrays = new ArrayList<>();
				arrays.addAll(PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios());
				for (int i = 0; i < tamanho; i++) {
					Object[] objs = { arrays.get(i).getNome(), arrays.get(i).getCpf(), arrays.get(i).getTipo() };
					dtm.addRow(objs);
					table.setModel(dtm);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizarJTableClientes() {

		int tamanho;
		try {
			PopUpCliente.getCc().atualiza();;
			tamanho = PopUpCliente.getCc1().getRepositorioClientes().getClientes().size();
			if (tamanho == 0) {
			} else {
				ArrayList<Cliente> arrays = new ArrayList<>();
				arrays.addAll(PopUpCliente.getCc().getRepositorioClientes().getClientes());
				for (int i = 0; i < tamanho; i++) {
					Object[] objs = { arrays.get(i).getNome(), arrays.get(i).getCpf(), arrays.get(i).getEndereco(),
							arrays.get(i).getPontos() };
					dtm.addRow(objs);
					table.setModel(dtm);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarJTableProdutos() {
		int tamanho;
		try {
			PopUpProduto.getCp().atualiza();;
			tamanho = PopUpProduto.getCp1().getRepositorioProdutos().getProdutos().size();
			if (tamanho == 0) {
			} else {
				ArrayList<Produto> arrays = new ArrayList<>();
				arrays.addAll(PopUpProduto.getCp().getRepositorioProdutos().getProdutos());
				for (int i = 0; i < tamanho; i++) {
					Object[] objs = { arrays.get(i).getNome(), arrays.get(i).getPreco(), arrays.get(i).getDescricao() };
					dtm.addRow(objs);
					table.setModel(dtm);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Funcionario funcLogado() {
		Funcionario desgracado = new Funcionario();
		for (int i = 0; i < PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().size(); i++) {
			if (PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(i).getLogado()) {
				desgracado = PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(i);
				return desgracado;
			}
		}
		return null;
	}

	public void definirTela() {
		if (TelaDeLogin.getAux().equals("Funcionario")) {
			lblNewLabel.setText("Tela Operacional Do Funcion�rio");
			btnNewButton_1.setVisible(false); // botao Equipe
			btnNewButton_1_1.setVisible(false); // botao Cardapio

		}
	}

	/**
	 * Launch the application.a
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerente frame = new TelaGerente();
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

	public TelaGerente() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaGerente.class.getResource("/Icone/IconPope.png")));
		setResizable(false);
		setTitle("Tela Operacional - Pope's Dance");
		definirTela();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 145, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(255, 145, 145));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(89, 39, 39));
		// panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 11, 768, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		lblNewLabel.setForeground(Color.LIGHT_GRAY);

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(41, 11, 678, 43);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(168, 25, 25));
		// panel_1.setBackground(new Color(11, 122, 117));

		panel_1.setBounds(10, 87, 989, 583);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnNewButton_1.setBounds(10, 240, 161, 72);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador = 1;
				System.out.println(contador);
				String aux2[] = new String[] { "Funcionario", "CPF", "Tipo" };
				dtm = new DefaultTableModel(aux2, 0);
				table.setModel(dtm);
				linhaCliente = -1;
				atualizarJTableFuncionarios();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		btnNewButton_1_1_1.setBounds(10, 129, 161, 72);
		panel_1.add(btnNewButton_1_1_1);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador = 2;
				String aux1[] = new String[] { "Nome", "CPF", "Endereco", "Pontos" };
				dtm = new DefaultTableModel(aux1, 0);
				table.setModel(dtm);
				System.out.println(contador);
				atualizarJTableClientes();
				linhaCliente = -1;
				System.out.println(linhaCliente);
				System.out.println(funcLogado());
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel lblNewLabel_1 = new JLabel("Administrativo:");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 43, 161, 31);
		panel_1.add(lblNewLabel_1);

		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador = 3;
				String aux[] = new String[] { "Produto", "Valor", "Descricao" };
				dtm = new DefaultTableModel(aux, 0);
				table.setModel(dtm);
				System.out.println(contador);
				System.out.println(contador);
				atualizarJTableProdutos();
				linhaCliente = -1;

			}
		});
		btnNewButton_1_1.setBounds(10, 352, 161, 72);
		panel_1.add(btnNewButton_1_1);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(244, 219, 216));
		scrollPane.setBounds(181, 42, 798, 483);
		panel_1.add(scrollPane);
		scrollPane.setBackground(new Color(244, 219, 216));
		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contador == 1) {
					linha = table.getSelectedRow();
					campoDeTexto = dtm.getValueAt(linha, 0).toString();
					System.out.println(campoDeTexto);
					campoDeTexto_1 = dtm.getValueAt(linha, 1).toString();
					System.out.println(campoDeTexto_1);
					campoDeTexto_2 = dtm.getValueAt(linha, 2).toString();
					System.out.println(campoDeTexto_2);
					linhaCliente = -1;
				} else if (contador == 2) {
					linhaCliente = table.getSelectedRow();
					campoDeTexto = dtm.getValueAt(linhaCliente, 0).toString();
					System.out.println(campoDeTexto);
					campoDeTexto_1 = dtm.getValueAt(linhaCliente, 1).toString();
					System.out.println(campoDeTexto_1);
					campoDeTexto_2 = dtm.getValueAt(linhaCliente, 2).toString();
					System.out.println(campoDeTexto_2);

				} else if (contador == 3) {
					linha = table.getSelectedRow();
					campoDeTexto = dtm.getValueAt(linha, 0).toString();
					System.out.println(campoDeTexto);
					campoDePreco = dtm.getValueAt(linha, 1).toString();
					campoDeTexto_2 = dtm.getValueAt(linha, 2).toString();

				}
			}
		});
		scrollPane.setViewportView(table);

		JButton btnNewButton_2 = new JButton("Adicionar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador == 1) {
					PopUpFuncionario popf;
					try {
						popf = new PopUpFuncionario();
						popf.setLocationRelativeTo(null);
						popf.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (contador == 2) {
					PopUpCliente popc;
					try {
						popc = new PopUpCliente();
						popc.setLocationRelativeTo(null);
						popc.setVisible(true);

					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				} else if (contador == 3) {
					PopUpProduto popp;
					try {
						popp = new PopUpProduto();
						popp.setLocationRelativeTo(null);
						popp.setVisible(true);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

				}
			}
		});
		btnNewButton_2.setBounds(181, 536, 130, 36);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Remover");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador == 1) {
					try {
						for (int i = 0; i < PopUpFuncionario.getCf1().getRepositorioFuncionario().getFuncionarios()
								.size(); i++) {
							if (campoDeTexto
									.equals(PopUpFuncionario.getCf1().getRepositorioFuncionario().getFuncionarios()
											.get(i).getNome())
									&& campoDeTexto_1.equals(PopUpFuncionario.getCf1().getRepositorioFuncionario()
											.getFuncionarios().get(i).getCpf())) {
								PopUpFuncionario.getCf().remover(
										PopUpFuncionario.getCf1().getRepositorioFuncionario().getFuncionarios().get(i));
								PopUpFuncionario.getCf1().remover(
										PopUpFuncionario.getCf1().getRepositorioFuncionario().getFuncionarios().get(i));
								System.out.println(PopUpFuncionario.getCf());
							}
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (contador == 2) {
					try {
						for (int i = 0; i < PopUpCliente.getCc1().getRepositorioClientes().getClientes().size(); i++) {
							if (campoDeTexto.equals(
									PopUpCliente.getCc1().getRepositorioClientes().getClientes().get(i).getNome())
									&& campoDeTexto_1.equals(PopUpCliente.getCc1().getRepositorioClientes()
											.getClientes().get(i).getCpf())) {
								PopUpCliente.getCc()
										.remover(PopUpCliente.getCc1().getRepositorioClientes().getClientes().get(i));
								PopUpCliente.getCc1()
										.remover(PopUpCliente.getCc1().getRepositorioClientes().getClientes().get(i));
								System.out.println(PopUpCliente.getCc());
							}

						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (contador == 3) {
					try {
						for (int i = 0; i < PopUpProduto.getCp1().getRepositorioProdutos().getProdutos().size(); i++) {
							if (campoDeTexto.equals(
									PopUpProduto.getCp1().getRepositorioProdutos().getProdutos().get(i).getNome())) {

								PopUpProduto.getCp()
										.remover(PopUpProduto.getCp1().getRepositorioProdutos().getProdutos().get(i));
								PopUpProduto.getCp1()
										.remover(PopUpProduto.getCp1().getRepositorioProdutos().getProdutos().get(i));
								System.out.println(PopUpProduto.getCp());

							}

						}
					} catch (RemoteException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

				}
			}
		});
		JButton btnNewButton_2_2 = new JButton("Atualizar");
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_1.setBounds(400, 536, 130, 36);
		panel_1.add(btnNewButton_2_1);
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador == 1) {

					PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(linha)
							.setNome(campoDeTexto);
					PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(linha)
							.setCpf(campoDeTexto_1);
					PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(linha)
							.setTipo(campoDeTexto_2);

				}
				if (contador == 2) {
					PopUpCliente.getCc().getRepositorioClientes().getClientes().get(linhaCliente).setNome(campoDeTexto);
					PopUpCliente.getCc().getRepositorioClientes().getClientes().get(linhaCliente)
							.setCpf(campoDeTexto_1);
					PopUpCliente.getCc().getRepositorioClientes().getClientes().get(linhaCliente)
							.setEndereco(campoDeTexto_2);

				}
				if (contador == 3) {
					PopUpProduto.getCp().getRepositorioProdutos().getProdutos().get(linha)
							.setPreco(Double.parseDouble(campoDePreco));
					PopUpProduto.getCp().getRepositorioProdutos().getProdutos().get(linha).setNome(campoDeTexto);
					PopUpProduto.getCp().getRepositorioProdutos().getProdutos().get(linha).setDescricao(campoDeTexto_2);

				}
			}
		});
		btnNewButton_2_2.setBounds(631, 536, 130, 36);
		panel_1.add(btnNewButton_2_2);

		JButton btnNewButton_2_3 = new JButton("Buscar");
		btnNewButton_2_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador == 1) {
					PopUpBuscaFuncionario popbf = new PopUpBuscaFuncionario();
					popbf.setLocationRelativeTo(null);
					popbf.setVisible(true);

				} else if (contador == 2) {
					PopUpBuscaCliente popbc = new PopUpBuscaCliente();
					popbc.setLocationRelativeTo(null);
					popbc.setVisible(true);

				} else if (contador == 3) {
					PopUpBuscaProduto popbp = new PopUpBuscaProduto();
					popbp.setLocationRelativeTo(null);
					popbp.setVisible(true);

				}

			}
		});

		btnNewButton_2_3.setBounds(849, 536, 130, 36);
		panel_1.add(btnNewButton_2_3);

		JButton btnNewButton_3 = new JButton("Sair");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PopUpFuncionario.getCf().atualiza();
					for (int i = 0; i < PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios()
							.size(); i++) {
						if (PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(i)
								.getLogado() == true) {
							PopUpFuncionario.getCf().getRepositorioFuncionario().getFuncionarios().get(i)
									.setLogado(false);
							System.out.println(
									PopUpFuncionario.getCf1().getRepositorioFuncionario().getFuncionarios().get(i));
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TelaDeLogin telaLogin;
				try {
					telaLogin = new TelaDeLogin();
					telaLogin.setLocationRelativeTo(null);
					telaLogin.getFrame().setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_3.setBounds(10, 542, 76, 30);
		panel_1.add(btnNewButton_3);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.LIGHT_GRAY);
		panel_2.setBorder(UIManager.getBorder("PasswordField.border"));
		panel_2.setBackground(new Color(138, 34, 34));
		// panel_2.setBackground(SystemColor.info);
		panel_2.setBounds(788, 11, 466, 64);
		contentPane.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel_2 = new JLabel("Dados do Usu\u00E1rio:");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel(TelaGerente.funcLogado().getNome());
		lblNewLabel_5.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 1;
		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel("CPF:");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 2;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_6 = new JLabel(TelaGerente.funcLogado().getCpf());
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 2;
		panel_2.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JButton btnNewButton = new JButton("Nova Venda");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 38));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaNovaVenda tnv;
				try {
					tnv = new TelaNovaVenda();
					tnv.setLocationRelativeTo(null);
					tnv.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(1009, 86, 245, 501);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1_2 = new JButton("Vendas");
		btnNewButton_1_2.setBounds(1009, 598, 245, 72);
		contentPane.add(btnNewButton_1_2);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaControleVendas tcv = new TelaControleVendas();
				tcv.setLocationRelativeTo(null);
				tcv.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}

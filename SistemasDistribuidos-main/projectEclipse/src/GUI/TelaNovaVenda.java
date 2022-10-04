package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Basicos.Carrinho;
import Clientes.CCInterface;
import Clientes.Cliente;
import Clientes.ControladorCliente;
import Produtos.Produto;
import Vendas.CVInterface;
import Vendas.ControladorVenda;
import Vendas.Venda;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.awt.Toolkit;

public class TelaNovaVenda extends JFrame {

	private static ControladorVenda cv;
	public static CVInterface cv1;
	

	public static void inicializar() {
		try {
			cv = new ControladorVenda();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cv1 = (CVInterface) Naming.lookup("rmi://localhost:1102/CV");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Naming.rebind("rmi://localhost:1102/CV", cv);
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static ControladorVenda getCv() {
		return cv;
	}

	public void setCv(ControladorVenda cv) {
		TelaNovaVenda.cv = cv;
	}
	
	public static CVInterface getCv1() {
		return cv1;
	}

	public static void setCv1(CVInterface cv1) {
		TelaNovaVenda.cv1 = cv1;
	}

	DefaultTableModel dtm;
	DefaultTableModel dtm1;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereo;
	private JTable table_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel panel_1;
	private JButton btnNewButton_3;
	private JTable table;
	private JTextField txtValorFinal;
	private int linha;
	private int linhaCarrinho;
	private String campoDeTexto;
	private String campoDeTexto_1;
	private String campoDeTexto_2;
	private String campoDeTextoCarrinho;
	private String campoDeTexto_1Carrinho;
	private String campoDeTexto_2Carrinho;
	private ArrayList<Produto> produtosNoCarrinho = new ArrayList<>();
	private double valorCarrinho;
	private double valorFinal;
	private JButton btnNewButton_4;

	/**
	 * Launch the application.a
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovaVenda frame = new TelaNovaVenda();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarJTableProdutos() throws RemoteException {
		
		int tamanho = PopUpProduto.getCp1().getRepositorioProdutos().getProdutos().size();
		if (tamanho == 0) {

		} else {
			ArrayList<Produto> arrays = new ArrayList<>();
			arrays.addAll(PopUpProduto.getCp().getRepositorioProdutos().getProdutos());
			
			for (int i = 0; i < tamanho; i++) {
				Object[] objs = { arrays.get(i).getNome(), arrays.get(i).getPreco(), arrays.get(i).getQuantidade() };
				dtm.addRow(objs);
				table.setModel(dtm);
			}
		}
	}

// carrinho de mentira, � so o da Jtable
	public void adicionarAoCarrinho() {
		
		ArrayList<Produto> arrays = new ArrayList<>();
		arrays.addAll(PopUpProduto.getCp().getRepositorioProdutos().getProdutos());
		Object[] objs = { arrays.get(linha).getNome(), arrays.get(linha).getPreco(),
				arrays.get(linha).getQuantidade() };
		Produto pTest = new Produto(arrays.get(linha).getNome(), arrays.get(linha).getDescricao(),
				arrays.get(linha).getQuantidade(), arrays.get(linha).getPreco(), false);
		produtosNoCarrinho.add(pTest);
		dtm1.addRow(objs);
		table_1.setModel(dtm1);
	}

	public void removerDoCarrinho() {

		produtosNoCarrinho.remove(linhaCarrinho);
		dtm1.removeRow(linhaCarrinho);

	}

	public double atualizarValorFinal() throws RemoteException {
		
		int tamanho = produtosNoCarrinho.size();
		valorFinal = 0;
		for (int i = 0; i < tamanho; i++) {
			
			valorFinal = valorFinal
					+ (produtosNoCarrinho.get(i).getPreco() * produtosNoCarrinho.get(i).getQuantidade());
			int tamanhoClientes = PopUpCliente.getCc1().getRepositorioClientes().getClientes().size();
			for (int y = 0; y < tamanhoClientes; y++) {
				
				if (PopUpCliente.getCc1().getRepositorioClientes().getClientes().get(y).getPontos() >= 5) {
					
					valorFinal = valorFinal * 0.90;
					System.out.println(valorFinal);
				}
			}
		}

		txtValorFinal.setText(String.valueOf(valorFinal));
		return valorFinal;
	}

	/**
	 * Create the frame.
	 */
	public TelaNovaVenda() throws Exception {
		
		cv = new ControladorVenda();
		cv1 = (CVInterface) Naming.lookup("rmi://localhost:1102/CV");
		Naming.rebind("rmi://localhost:1102/CV", cv);
		
		setTitle("Nova Venda - Pope's Dance");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaNovaVenda.class.getResource("/Icone/IconPope.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 145, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(138, 34, 34));
		panel.setBounds(10, 11, 267, 144);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cliente:");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(105, 11, 92, 14);
		panel.add(lblNewLabel);

		txtNome = new JTextField();
		if (TelaGerente.getLinhaCliente() == -1) {
			
			txtNome.setText("");
		} else {
			
			txtNome.setText("" + PopUpCliente.getCc1().getRepositorioClientes().getClientes()
					.get(TelaGerente.getLinhaCliente()).getNome());
		}
		txtNome.setHorizontalAlignment(SwingConstants.LEFT);
		txtNome.setBounds(10, 27, 247, 25);
		panel.add(txtNome);
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setHorizontalAlignment(SwingConstants.LEFT);
		if (TelaGerente.getLinhaCliente() == -1) {
			
			txtCpf.setText("");
		} else {
			
			txtCpf.setText("" + PopUpCliente.getCc1().getRepositorioClientes().getClientes()
					.get(TelaGerente.getLinhaCliente()).getCpf());
		}

		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 63, 247, 25);
		panel.add(txtCpf);

		txtEndereo = new JTextField();
		if (TelaGerente.getLinhaCliente() == -1) {
			
			txtEndereo.setText("");
		} else {
			
			txtEndereo.setText("" + PopUpCliente.getCc1().getRepositorioClientes().getClientes()
					.get(TelaGerente.getLinhaCliente()).getEndereco());
		}

		txtEndereo.setColumns(10);
		txtEndereo.setBounds(10, 99, 247, 25);
		panel.add(txtEndereo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 11, 967, 414);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				linha = table.getSelectedRow();
				campoDeTexto = dtm.getValueAt(linha, 0).toString();
				System.out.println(campoDeTexto);
				campoDeTexto_1 = dtm.getValueAt(linha, 1).toString();
				System.out.println(campoDeTexto_1);
				campoDeTexto_2 = dtm.getValueAt(linha, 2).toString();
				System.out.println(campoDeTexto_2);
				System.out.println("Linha: " + linha);
			}
		});
		
		scrollPane.setViewportView(table);
		String aux1[] = new String[] { "Produto", "Preco", "Quantidade" };
		dtm = new DefaultTableModel(aux1, 0);
		table.setModel(dtm);
		mostrarJTableProdutos();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 436, 977, 233);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		dtm1 = new DefaultTableModel(aux1, 0);
		table_1.setModel(dtm1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				linhaCarrinho = table_1.getSelectedRow();
				campoDeTextoCarrinho = dtm1.getValueAt(linhaCarrinho, 0).toString();
				campoDeTexto_1Carrinho = dtm1.getValueAt(linhaCarrinho, 1).toString();
				campoDeTexto_2Carrinho = dtm1.getValueAt(linhaCarrinho, 2).toString();
				System.out.println("Quantidade:" + campoDeTexto_2Carrinho);
			}
		});
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				adicionarAoCarrinho();
				try {
					valorCarrinho = atualizarValorFinal();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println(valorCarrinho);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(10, 166, 267, 55);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Remover");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				removerDoCarrinho();
				try {
					atualizarValorFinal();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(10, 255, 267, 55);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Finalizar Compra");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (valorFinal == 0) {
					
					JOptionPane.showMessageDialog(null, "Carrinho Vazio!");
				} else {
					
					int res = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(getParent(), "Deseja finalizar a Venda?", "Venda",
							res);
					if (dialogResult == 0) {

						ArrayList<Cliente> arrays = new ArrayList<>();
						arrays.addAll(PopUpCliente.getCc().getRepositorioClientes().getClientes());
						int tamanho;
						try {
							
							tamanho = PopUpCliente.getCc1().getRepositorioClientes().getClientes().size();
							for (int i = 0; i < tamanho; i++) {

								if (txtNome.getText().equals(arrays.get(i).getNome())
										&& txtCpf.getText().equals(arrays.get(i).getCpf())) {

									JOptionPane.showMessageDialog(null, "Cliente Encontrado!.");
									PopUpCliente.getCc().getRepositorioClientes().getClientes().get(i).addPontos();
									System.out.println("Baila: " + PopUpCliente.getCc1().getRepositorioClientes()
											.getClientes().get(i).getPontos());
									Carrinho prov = new Carrinho(
											PopUpCliente.getCc1().getRepositorioClientes().getClientes().get(i),
											produtosNoCarrinho);
									if (PopUpCliente.getCc1().getRepositorioClientes().getClientes().get(i)
											.getPontos() > 5) {
										
										PopUpCliente.getCc().getRepositorioClientes().getClientes().get(i).setPontos(0);
										JOptionPane.showMessageDialog(null, "Cupom!");
									}
									
									prov.setValorTotal(valorFinal);
									System.out.println(prov.getValorTotal());
									Venda provisoria = new Venda(prov, TelaGerente.funcLogado(), LocalDateTime.now().toString(), true);
									cv.cadastrar(provisoria);
								}
							}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}				

						JOptionPane.showMessageDialog(null, "Venda Finalizada!");
						dispose();
						
						TelaGerente tg = new TelaGerente();
						tg.setLocationRelativeTo(null);
						tg.setVisible(true);
						System.out.println(cv);
					}
				}
			}

		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2.setToolTipText("Finaliza a compra e fecha a aba");
		btnNewButton_2.setBounds(10, 538, 267, 91);
		contentPane.add(btnNewButton_2);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(138, 34, 34));
		panel_1.setBounds(10, 409, 267, 118);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtValorFinal = new JTextField();
		txtValorFinal.setForeground(Color.BLACK);
		txtValorFinal.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtValorFinal.setText("Valor Final:");
		txtValorFinal.setHorizontalAlignment(SwingConstants.LEFT);
		txtValorFinal.setColumns(10);
		txtValorFinal.setBounds(10, 33, 247, 52);
		panel_1.add(txtValorFinal);
		btnNewButton_3 = new JButton("Atualizar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				produtosNoCarrinho.get(linhaCarrinho).setQuantidade(Integer.parseInt(campoDeTexto_2Carrinho));
				try {
					valorCarrinho = atualizarValorFinal();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_3.setBounds(10, 343, 267, 55);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Cancelar Compra");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				TelaGerente tg = new TelaGerente();
				tg.setLocationRelativeTo(null);
				tg.setVisible(true);
			}
		});
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_4.setBounds(10, 640, 267, 29);
		contentPane.add(btnNewButton_4);

	}
}

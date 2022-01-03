/* Classe cliente instancia uma agenda e cria uma janela de visualização
 * exibindo os resultados de acordo com as operações realizadas no servidor
 */

package cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import servidor.Agenda;

@SuppressWarnings("serial")
public class AgendaClient extends JFrame implements ActionListener {

	private JLabel l_nome = new JLabel("NOME");
	private JLabel l_end = new JLabel("ENDEREÇO");
	private JLabel l_tel = new JLabel("TELEFONE");
	private JLabel l_email = new JLabel("E-MAIL");

	private JTextField t_nome = new JTextField(20);
	private JTextField t_end = new JTextField();
	private JTextField t_tel = new JTextField();
	private JTextField t_email = new JTextField();

	private JPanel p_superior = new JPanel();
	private JPanel p_inferior = new JPanel();
	private GridBagLayout l_superior = new GridBagLayout();
	private GridBagConstraints c_superior = new GridBagConstraints();

	private JButton bt_salvar = new JButton("Salvar");
	private JButton bt_editar = new JButton("Editar");
	private JButton bt_incluir = new JButton("Incluir");
	private JButton bt_excluir = new JButton("Excluir");
	private JButton bt_voltar = new JButton("Voltar");
	private JButton bt_avancar = new JButton("Avançar");

	private GridBagLayout l_inferior = new GridBagLayout();
	private GridBagConstraints c_inferior = new GridBagConstraints();

	private Agenda agenda;
	private String[][] v_temp = new String[1][4];

	public AgendaClient() throws RemoteException {

		try {

			agenda = (Agenda) Naming
					.lookup("rmi://localhost:1099/AgendaService");

			agenda.iniciar();
			v_temp[0] = agenda.atualizar();
			mostrarTexto(v_temp);

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n" + ex
					+ "\nClique em OK para encerrar o Programa", "Aviso",
					JOptionPane.WARNING_MESSAGE);

			System.exit(0);

		}

		setLayout(new GridLayout(2, 1));
		setTitle("AGENDA RMI");
		setSize(400, 300);

		p_superior.setLayout(l_superior);

		// Label Nome
		JPanel jtemp = new JPanel();

		c_superior.gridy = 0;
		c_superior.gridx = 0;
		c_superior.gridwidth = 2;
		l_superior.setConstraints(jtemp, c_superior);
		p_superior.add(jtemp);

		c_superior.gridy = 1;
		c_superior.gridx = 0;
		c_superior.gridwidth = 1;
		c_superior.ipadx = 40;
		c_superior.ipady = 10;
		l_superior.setConstraints(l_nome, c_superior);
		p_superior.add(l_nome);

		// Label Endereço
		c_superior.gridy = 2;
		c_superior.gridx = 0;
		l_superior.setConstraints(l_end, c_superior);
		p_superior.add(l_end);

		// Label Telefone
		c_superior.gridy = 3;
		c_superior.gridx = 0;
		l_superior.setConstraints(l_tel, c_superior);
		p_superior.add(l_tel);

		// Label E-mail
		c_superior.gridy = 4;
		c_superior.gridx = 0;
		l_superior.setConstraints(l_email, c_superior);
		p_superior.add(l_email);

		c_superior.gridwidth = 2;
		c_superior.ipadx = 260;
		c_superior.ipady = 10;
		c_superior.gridy = 1;
		c_superior.gridx = 1;
		l_superior.setConstraints(t_nome, c_superior);
		p_superior.add(t_nome);

		c_superior.gridy = 2;
		c_superior.gridx = 1;
		l_superior.setConstraints(t_end, c_superior);
		p_superior.add(t_end);

		c_superior.gridy = 3;
		c_superior.gridx = 1;
		l_superior.setConstraints(t_tel, c_superior);
		p_superior.add(t_tel);

		c_superior.gridy = 4;
		c_superior.gridx = 1;

		l_superior.setConstraints(t_email, c_superior);
		p_superior.add(t_email);

		p_inferior.setLayout(l_inferior);
		jtemp = new JPanel();
		c_inferior.gridy = 0;
		c_inferior.gridx = 0;
		c_inferior.gridwidth = 3;
		l_inferior.setConstraints(jtemp, c_inferior);
		p_inferior.add(jtemp);

		c_inferior.insets = new Insets(5, 5, 0, 5);
		c_inferior.gridwidth = 1;

		// Botão Salvar
		c_inferior.gridy = 1;
		c_inferior.gridx = 0;
		l_inferior.setConstraints(bt_salvar, c_inferior);
		p_inferior.add(bt_salvar);

		// Botão Incluir
		c_inferior.gridy = 1;
		c_inferior.gridx = 1;
		bt_incluir.setBounds(new Rectangle(5, 5));
		l_inferior.setConstraints(bt_incluir, c_inferior);
		p_inferior.add(bt_incluir);

		// Botão Voltar
		c_inferior.gridy = 1;
		c_inferior.gridx = 2;
		l_inferior.setConstraints(bt_voltar, c_inferior);
		p_inferior.add(bt_voltar);

		// Botão Editar
		c_inferior.gridy = 2;
		c_inferior.gridx = 0;
		l_inferior.setConstraints(bt_editar, c_inferior);
		p_inferior.add(bt_editar);

		// Botão Excluir
		c_inferior.gridy = 2;
		c_inferior.gridx = 1;
		bt_incluir.setSize(100, 100);
		l_inferior.setConstraints(bt_excluir, c_inferior);
		p_inferior.add(bt_excluir);

		// Botão Avançar
		c_inferior.gridy = 2;
		c_inferior.gridx = 2;
		l_inferior.setConstraints(bt_avancar, c_inferior);
		p_inferior.add(bt_avancar);

		setEditavel(false);

		bt_salvar.addActionListener(this);
		bt_incluir.addActionListener(this);
		bt_voltar.addActionListener(this);
		bt_editar.addActionListener(this);
		bt_excluir.addActionListener(this);
		bt_avancar.addActionListener(this);

		getContentPane().add(p_superior);
		getContentPane().add(p_inferior);

		setResizable(false);
		setVisible(true);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void setEditavel(boolean valor) {

		t_nome.setEditable(valor);
		t_end.setEditable(valor);
		t_tel.setEditable(valor);
		t_email.setEditable(valor);

	}

	public void limpar() {

		t_nome.setText("");
		t_end.setText("");
		t_tel.setText("");
		t_email.setText("");

	}

	public void mostrarTexto(String[][] v_temp) {

		t_nome.setText(v_temp[0][0]);
		t_end.setText(v_temp[0][1]);
		t_tel.setText(v_temp[0][2]);
		t_email.setText(v_temp[0][3]);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_editar) {

			try {

				agenda.editar();
				setEditavel(true);

			} catch (Exception e1) {

				JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"
						+ e1 + "\nClique em OK para encerrar o Programa",
						"Aviso", JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}
		}

		if (e.getSource() == bt_salvar) {

			try {

				if (agenda.cheia())
					JOptionPane.showConfirmDialog(null, "Agenda Cheia",
							"ATENÇÃO", JOptionPane.PLAIN_MESSAGE);

				if (agenda.vazia() && !agenda.getIncluir())
					JOptionPane.showConfirmDialog(null, "Agenda Vazia",
							"ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
				else {

					agenda.salvar(t_nome.getText(), t_end.getText(),
							t_tel.getText(), t_email.getText());
					setEditavel(false);
					v_temp[0] = agenda.atualizar();
					mostrarTexto(v_temp);

				}
			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"
						+ e2 + "\nClique em OK para encerrar o Programa",
						"Aviso", JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}
		}

		if (e.getSource() == bt_incluir) {

			try {

				if (agenda.cheia())
					JOptionPane.showConfirmDialog(null, "Agenda Cheia",
							"ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
				else {

					limpar();
					agenda.incluir();
					setEditavel(true);

				}

			} catch (Exception e3) {

				JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"
						+ e3 + "\nClique em OK para encerrar o Programa",
						"Aviso", JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}
		}

		if (e.getSource() == bt_excluir) {

			try {

				if (agenda.vazia() && !agenda.getIncluir())
					JOptionPane.showConfirmDialog(null, "Agenda Vazia",
							"ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
				else {

					limpar();
					agenda.excluir();
					v_temp[0] = agenda.atualizar();
					mostrarTexto(v_temp);

				}

			} catch (Exception e4) {

				JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"
						+ e4 + "\nClique em OK para encerrar o Programa",
						"Aviso", JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}
		}

		if (e.getSource() == bt_voltar) {

			try {

				v_temp[0] = agenda.voltar();
				mostrarTexto(v_temp);

			} catch (Exception e5) {

				JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"
						+ e5 + "\nClique em OK para encerrar o Programa",
						"Aviso", JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}
		}

		if (e.getSource() == bt_avancar) {

			try {

				v_temp[0] = agenda.avancar();
				mostrarTexto(v_temp);

			} catch (Exception e6) {

				JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"
						+ e6 + "\nClique em OK para encerrar o Programa",
						"Aviso", JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}
		}
	}

	public static void main(String[] args) throws RemoteException {

		new AgendaClient();

	}

}

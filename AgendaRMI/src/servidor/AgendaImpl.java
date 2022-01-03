package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import servidor.Agenda;

@SuppressWarnings("serial")
public class AgendaImpl extends UnicastRemoteObject implements Agenda {

	private String[][] dados = new String[100][4];

	private int total_dados = 0;
	private int indice = 0;

	private boolean editado = false;
	private boolean agendaVazia = true;
	private boolean agendaCheia = false;
	private boolean incluir = false;

	private File arquivo;
	private BufferedReader ler;
	private File arquivob;
	private BufferedReader lerb;

	public AgendaImpl() throws IOException {

		super();

	}

	public void iniciar() throws RemoteException, IOException {

		carregaDados();

	}

	public void salvaArquivo() throws IOException {

		arquivo = new File("total.txt");

		arquivo.createNewFile();

		FileWriter arquivo2 = new FileWriter(arquivo);
		BufferedWriter gravar = new BufferedWriter(arquivo2);

		gravar.write(total_dados + "\n");

		gravar.close();
		arquivo2.close();

		arquivob = new File("dados.txt");

		arquivob.createNewFile();

		arquivo2 = new FileWriter(arquivob);
		gravar = new BufferedWriter(arquivo2);

		for (int i = 0; i < total_dados; i++) {

			gravar.write("Dados:\n");
			gravar.write(dados[i][0] + "\n");
			gravar.write(dados[i][1] + "\n");
			gravar.write(dados[i][2] + "\n");
			gravar.write(dados[i][3] + "\n\n");

		}

		gravar.close();
		arquivo2.close();

	}

	public void carregaDados() throws IOException {

		arquivo = new File("total.txt");

		if (arquivo.exists()) {

			System.out.println("Arquivo existe1: " + arquivo.exists());

			FileReader arquivo2 = new FileReader(arquivo);
			ler = new BufferedReader(arquivo2);
			StringBuffer bufSaida = new StringBuffer();

			String d;

			d = ler.readLine();
			bufSaida.append(d);

			System.out.println("Integer.parseInt(" + d + ") = "
					+ Integer.parseInt(d));

			total_dados = Integer.parseInt(bufSaida.toString());

			System.out.println("++total_dados++ = " + total_dados);
			ler.close();
			arquivo2.close();

		}

		arquivob = new File("dados.txt");

		if (arquivob.exists()) {

			System.out.println("Arquivo existe2: " + arquivob.exists());

			FileReader arquivo2 = new FileReader(arquivob);
			lerb = new BufferedReader(arquivo2);
			String linha;

			System.out.println("--total_dados-- = " + total_dados);
			for (int i = 0; i < total_dados; i++) {

				linha = lerb.readLine();

				System.out.print("aqui1");
				while (linha.equals("Dados:"))
					linha = lerb.readLine();
				System.out.print("aqui2");

				for (int j = 0; j < 4; j++) {
					System.out.print("aqui3");

					if (linha == null)
						break;

					else
						dados[i][j] = linha;

					System.out.println("dados[i" + i + "][" + j + "]: "
							+ dados[i][j]);

					linha = lerb.readLine();

				}

			}

			lerb.close();
			arquivo2.close();

		}

	}

	public void editar() throws RemoteException {

		editado = true;

	}

	public void salvar(String nome, String end, String tel, String email)
			throws RemoteException, IOException {

		if (total_dados >= 0 && total_dados < 100) {

			if (editado) {

				dados[indice][0] = nome;
				dados[indice][1] = end;
				dados[indice][2] = tel;
				dados[indice][3] = email;

				editado = false;
				ordenar();

			} else {

				dados[total_dados][0] = nome;
				dados[total_dados][1] = end;
				dados[total_dados][2] = tel;
				dados[total_dados][3] = email;

				total_dados++;
				ordenar();

				if (total_dados == 100)
					agendaCheia = true;

			}

			agendaVazia = false;
			salvaArquivo();

		}

	}

	public void incluir() throws RemoteException {

		incluir = true;

	}

	public void excluir() throws RemoteException, IOException {

		dados[indice][0] = "";
		dados[indice][1] = "";
		dados[indice][2] = "";
		dados[indice][3] = "";

		if (!agendaVazia) {
			for (int i = indice; i < total_dados - 1; i++) {

				dados[i][0] = dados[i + 1][0];
				dados[i][1] = dados[i + 1][1];
				dados[i][2] = dados[i + 1][2];
				dados[i][3] = dados[i + 1][3];

				dados[i + 1][0] = "";
				dados[i + 1][1] = "";
				dados[i + 1][2] = "";
				dados[i + 1][3] = "";

			}

			total_dados--;

			if (total_dados == 0) {

				incluir = false;
				agendaVazia = true;

			}

			salvaArquivo();

		}
	}

	public void ordenar() throws RemoteException {

		String nome_temporario;
		String end_temporario;
		String tel_temporario;
		String email_temporario;
		boolean troca = true;

		while (troca) {

			troca = false;

			for (int i = 0; i < total_dados - 1; i++) {

				if (dados[i][0].compareTo(dados[i + 1][0]) > 0) {

					nome_temporario = dados[i][0];
					end_temporario = dados[i][1];
					tel_temporario = dados[i][2];
					email_temporario = dados[i][3];

					dados[i][0] = dados[i + 1][0];
					dados[i][1] = dados[i + 1][1];
					dados[i][2] = dados[i + 1][2];
					dados[i][3] = dados[i + 1][3];

					dados[i + 1][0] = nome_temporario;
					dados[i + 1][1] = end_temporario;
					dados[i + 1][2] = tel_temporario;
					dados[i + 1][3] = email_temporario;

					troca = true;

				}

			}

		}

	}

	public String[] atualizar() throws RemoteException {

		indice = 0;
		return dados[0];

	}

	public String[] voltar() throws RemoteException {

		if (indice > 0) {

			indice--;

		}

		return dados[indice];

	}

	public String[] avancar() throws RemoteException {

		if (indice < 99) {

			indice++;

		}

		return dados[indice];

	}

	public boolean vazia() throws RemoteException {

		return agendaVazia;

	}

	public boolean cheia() throws RemoteException {

		return agendaCheia;

	}

	public boolean getIncluir() throws RemoteException {

		return incluir;

	}

}

/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */ 

package Simplifica;

import java.util.ArrayList;

public class EliminaVazio {
    private Listas texto;
    private ArrayList<String> vazio;
    private ArrayList<String> linhaTemporaria;

    public EliminaVazio (Listas texto, String nomeArquivo) {
        this.texto = texto;
        iniciaEliminacao(nomeArquivo);
    }

    public void iniciaEliminacao(String nomeArquivo){

        System.out.println("Eliminação de Produções Vazias");

		int c = 0;
		String nomeTemporario = "";
		while(nomeArquivo.charAt(c) != '.')
			nomeTemporario += nomeArquivo.charAt(c++);

		nomeTemporario += "_semProducoesVazias.txt";

		String simboloInicial = texto.getLinhas(2).get(0);   // pega o 1º elemento da 3ª linha;
															// armazena o símbolo inicial
															// linha 3, elemento 1 da lista


		//        1º: verificar quem produz símbolo vazio e guardá-los em uma lista;
		//        2º: verificar se o símbolo inicial produz um símbolo vazio, se produzir
		//                inicialProduz = true;
		//        3º: setar como verdadeiro a variável inicialDireito = true, caso alguma produção
		//                inclua o símbolo inicial do lado direito;
		//        4º: verificar se algum não-terminal produz um não-terminal dessa lista;


		boolean inicialNaDireita = false;
		boolean inicialProduz = false;
		boolean contemVazio = false;
		vazio = new ArrayList<String>();

		for(int i = 0; i < texto.getLinhas(1).size(); i++){
			if(texto.getLinhas(1).get(i).equals("&"))
			contemVazio = true;
		}

		for(int i = 3; i < texto.getLinha().size(); i++){
			if (texto.getLinhas(i).get(4).equals("&")) {
				vazio.add(texto.getLinhas(i).get(0));
			} else {
				if (texto.getLinhas(i).get(4).equals("&")
				&& texto.getLinhas(i).get(0).equals(simboloInicial)) {
					vazio.add(texto.getLinhas(i).get(0));
					inicialProduz = true;
				} else {
					if (contemVazio
					&& texto.getLinhas(i).get(4).contains(simboloInicial)) {
						inicialNaDireita = true;
					}
				}
			}
		}

		int tamanho = vazio.size();

		for(int i = 3; i < texto.getLinha().size(); i++) {
			for (int j = 0; j < tamanho; j++) {
				if (!texto.getLinhas(i).get(0).equals(vazio.get(j))) {
					if (texto.getLinhas(i).get(4).contains(vazio.get(j))) {
						vazio.add(texto.getLinhas(i).get(0));
					}
				}
			}
		}

		if (vazio.contains(simboloInicial))
			inicialProduz = true;

		//        5º: se o inicialNaDireita for verdadeiro e inicialProduz for verdadeiro
		//                é criado um novo símbolo <S'> que produzirá o símbolo inicial,
		//                e um símbolo vazio, e a produção vazia do símbolo inicial é
		//                eliminada;

		if (inicialNaDireita && inicialProduz) {
			linhaTemporaria = new ArrayList<String>();
			linhaTemporaria.add("<S'>");
			linhaTemporaria.add(" ");
			linhaTemporaria.add("::=");
			linhaTemporaria.add(" ");
			linhaTemporaria.add(simboloInicial);
			texto.getLinha().add(3, linhaTemporaria);
			linhaTemporaria = new ArrayList<String>();
			linhaTemporaria.add("<S'>");
			linhaTemporaria.add(" ");
			linhaTemporaria.add("::=");
			linhaTemporaria.add(" ");
			linhaTemporaria.add("&");
			texto.getLinha().add(4, linhaTemporaria);
			int i = 5;
			while (texto.getLinhas(i).get(0).equals(simboloInicial)) {
				if (texto.getLinhas(i).get(4).equals("&")) {
					texto.getLinha().remove(i);
				} else
					i++;
			}
			simboloInicial = "";
			simboloInicial = "<S'>";
			texto.getLinha().remove(2);
			linhaTemporaria = new ArrayList<String>();
			linhaTemporaria.add(simboloInicial);
			texto.getLinha().add(2, linhaTemporaria);
		}


		//        6º: localizar cada elemento da lista, um por um, com o seu respectivo
		//                não-terminal em cada produção; se essa produção possuir o terminal
		//                lido, insere um novo elemento nessa lista,
		//                na posição atual, e esse terminal é eliminado dessa produção;

		int m = 3;
		if (simboloInicial.equals("<S'>"))
			while(simboloInicial.equals(texto.getLinhas(m).get(0))) // "pula" as produções do símbolo inicial
				m++;

		tamanho = texto.getLinha().size();
		int tamanhoDaLista = vazio.size();
		String stringTemp;

		//       percorre a lista
		for (int i = m; i < tamanho; i++) {
			//            verifica cada elemento da lista
			for (int j = 0; j < tamanhoDaLista; j++) {
				//                se o 4º elemento contém o símbolo da lista
				if (texto.getLinhas(i).get(4).contains(vazio.get(j))) {
					linhaTemporaria = new ArrayList<String>();
					linhaTemporaria.add(texto.getLinhas(i).get(0));
					linhaTemporaria.add(texto.getLinhas(i).get(1));
					linhaTemporaria.add(texto.getLinhas(i).get(2));
					linhaTemporaria.add(texto.getLinhas(i).get(3));
					stringTemp = texto.getLinhas(i).get(4);
					stringTemp = stringTemp.replace(vazio.get(j), "");
					linhaTemporaria.add(stringTemp);
					stringTemp = "";
					texto.getLinha().add(i+1, linhaTemporaria);
				}
			}
			tamanho = texto.getLinha().size();
		}

		//        a seguir basta eliminar as produções repetidas e as produções que produzem
		//        símbolos vazios

		boolean simboloReferenciado = false;

		while (!vazio.isEmpty()) {
			eliminaSimbolosRepetidos(texto, inicialProduz);
			for (int anda = 3; anda < texto.getLinha().size(); anda++) {
				if (texto.getLinhas(anda).get(0).equals(vazio.get(0)))
				simboloReferenciado = true;
			}
			if (!simboloReferenciado)
				Eliminar.eliminaLinhas(vazio.get(0), texto);
			simboloReferenciado = false;
			vazio.remove(0);
		}

		for(int inn = 0; inn < texto.getLinha().size(); inn++){
			for(int jnn = 0; jnn < texto.getLinhas(inn).size(); jnn++)
				System.out.print(texto.getLinhas(inn).get(jnn));
			System.out.println();
		}

		nomeArquivo = "";
		nomeArquivo += nomeTemporario;
		CriarArquivos.criar(texto,nomeArquivo);
    }

    private void eliminaSimbolosRepetidos(Listas texto, boolean inicialProduz) {

		String simboloInicial = texto.getLinhas(2).get(0);

		int i = 3;

		int tamanho = texto.getLinha().size();
		boolean eliminou = false;
		int ultimaLinha = 0;

		for (int linha = i; linha < tamanho; linha++) {
			for (int j = linha+1; j < tamanho; j++) {
				//                   se a própria produção for um símbolo vazio ou não possui nada: elimina
				
				if ((inicialProduz && !texto.getLinhas(linha).get(0).equals(simboloInicial))
                                        && texto.getLinhas(linha).get(4).equals("&")
				|| texto.getLinhas(linha).get(4).equals("")) {
					texto.getLinha().remove(linha);
					eliminou = true;
					break;
				} else {
					//               se for uma produção repetida: elimina
					if (texto.getLinhas(linha).get(0).equals(texto.getLinhas(j).get(0))
					&& texto.getLinhas(linha).get(4).equals(texto.getLinhas(j).get(4))) {
						texto.getLinha().remove(j--);
						eliminou = true;
						System.out.println(eliminou);
					}
				}
				tamanho = texto.getLinha().size();
			}
			if (eliminou){
				linha--;
				eliminou = false;
			}
			ultimaLinha = linha;
			tamanho = texto.getLinha().size();
		}

		if (texto.getLinhas(ultimaLinha).get(4).equals("&")
		|| texto.getLinhas(ultimaLinha).get(4).equals("")) {
			texto.getLinha().remove(ultimaLinha);
			eliminou = true;
		}

		// elimina os terminais na 2ª linha que não são acessados
		tamanho = texto.getLinhas(1).size();
		boolean eliminaTerminal = true;

		for(int in = 0; in < tamanho; in++){
			for(int jn = 3; jn < texto.getLinha().size(); jn++){
				if(!texto.getLinhas(jn).get(4).contains(texto.getLinhas(1).get(in))
				&& eliminaTerminal
				&& !texto.getLinhas(1).get(in).equals(" "))
					eliminaTerminal = true;
				else
					eliminaTerminal = false;
			}

			if(eliminaTerminal){
				texto.getLinhas(1).remove(in);
				tamanho = texto.getLinhas(1).size();
				if(in < tamanho)
					texto.getLinhas(1).remove(in);
				in = -1;
			}

			tamanho = texto.getLinhas(1).size();
			eliminaTerminal = true;
		}

    }
}
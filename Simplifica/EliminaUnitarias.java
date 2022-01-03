/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */

package Simplifica;

import java.util.ArrayList;

public class EliminaUnitarias {
    private Listas texto;
    private ArrayList<String> linhaTemporaria;
    private ArrayList<String> unitarios;

    public EliminaUnitarias(Listas texto, String nomeArquivo) {
        this.texto = texto;
        iniciaEliminacao (nomeArquivo);
    }

    public void iniciaEliminacao (String nomeArquivo) {

        System.out.println("Eliminação de Produções Unitárias");

        unitarios = new ArrayList<String>();

        int c = 0;
        String nomeTemporario = "";
        
        while(nomeArquivo.charAt(c) != '.')
            nomeTemporario += nomeArquivo.charAt(c++);

        nomeTemporario += "_semProducoesUnitarias.txt";

        int linhas = texto.getLinha().size();

        //        1º: pegar a produção (4º elemento da lista) que contenha '<';
        //        2º: passar esse elemento para uma String temporária;
        //        3º: substituir cada caractere a partir de '<' por '' até '>';

        for (int i = 3; i < linhas; i++) {
            if (texto.getLinhas(i).get(4).contains("<")) {
                String stringTemporaria = "";
                String stringTemporaria2 = "";
                stringTemporaria = texto.getLinhas(i).get(4);
                int caractere = 0;
                caractere = stringTemporaria.indexOf("<");

                while (stringTemporaria.charAt(caractere) != '>')
                    stringTemporaria2 += stringTemporaria.charAt(caractere++);

                stringTemporaria2 += stringTemporaria.charAt(caractere);
                stringTemporaria = stringTemporaria.replace(stringTemporaria2, "");

                //        4º: verificar se a String é igual a "":
                //            se for verdadeiro, esse é um simbolo unitário, é adicionado em uma lista;
                if (stringTemporaria.equals(""))
                    unitarios.add(texto.getLinhas(i).get(4));

            }
        }

        linhas = texto.getLinha().size();
        int tamanhoLista = unitarios.size();

        for (int i = 3; i < linhas; i++) {
            for (int j = 0; j < tamanhoLista; j++) {
                if (texto.getLinhas(i).get(4).equals(unitarios.get(j))) { // se for uma produção unitária
                    int posicao = 3;
                    int linhaInserida = i;
                    while (!texto.getLinhas(posicao).get(0).equals(unitarios.get(j)))
                        posicao++;

                    while (texto.getLinhas(posicao).get(0).equals(unitarios.get(j))) { // copia as produções do símbolo unitário
                        linhaTemporaria = new ArrayList<String>();
                        linhaTemporaria.add(texto.getLinhas(linhaInserida).get(0));
                        linhaTemporaria.add(" ");
                        linhaTemporaria.add("::=");
                        linhaTemporaria.add(" ");
                        linhaTemporaria.add(texto.getLinhas(posicao++).get(4));
                        texto.getLinha().add(linhaInserida++, linhaTemporaria);
                        if (posicao < texto.getLinha().size() && posicao > linhaInserida){
                            posicao++;
                            if (posicao >= texto.getLinha().size())
                                break;
                        }
                    }
                    texto.getLinha().remove(linhaInserida);
                    i = 2;
                    break;
                }
                tamanhoLista = unitarios.size();
            }
            linhas = texto.getLinha().size();
        }

        int tamanho = texto.getLinha().size();
        int continua = 0;
        while (continua < tamanho) {
            eliminaSimbolosRepetidos(texto);
            continua++;
            tamanho = texto.getLinha().size();
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

    private void eliminaSimbolosRepetidos(Listas texto) {

		int i = 3;

		int tamanho = texto.getLinha().size();
		boolean eliminou = false;
		int ultimaLinha = 0;

		for (int linha = i; linha < tamanho; linha++) {
			for (int j = linha+1; j < tamanho; j++) {
					//               se for uma produção repetida: elimina
					if (texto.getLinhas(linha).get(0).equals(texto.getLinhas(j).get(0))
					&& texto.getLinhas(linha).get(4).equals(texto.getLinhas(j).get(4))) {
						texto.getLinha().remove(j--);
						eliminou = true;
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
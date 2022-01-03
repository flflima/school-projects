/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */
 
package Simplifica;

import java.util.ArrayList;

public class EliminaInacessiveis {
    private Listas texto;
    private ArrayList<String> inacessiveis;
    private ArrayList<String> acessiveis;

    public EliminaInacessiveis (Listas texto, String nomeArquivo) {
        this.texto = texto;
        iniciaEliminacao(nomeArquivo);
    }

    public void iniciaEliminacao(String nomeArquivo){

                System.out.println("Eliminação de Símbolos Inacessíveis");

        inacessiveis = new ArrayList<String>();
        acessiveis = new ArrayList<String>();

        int c = 0;
        String nomeTemporario = "";

        while(nomeArquivo.charAt(c) != '.') // cria o nome do novo arquivo;
            nomeTemporario += nomeArquivo.charAt(c++);

        nomeTemporario += "_semInacessiveis.txt";

        String simboloInicial = texto.getLinhas(2).get(0); // pega o 1º elemento da 3ª linha;
                                                            // armazena o símbolo inicial
                                                            // linha 3, elemento 1 da lista;

        acessiveis.add(simboloInicial);

        //        inicialmente todos os não-terminais e terminais são considerados inacessiveis
        for(int i = 0; i < texto.getLinhas(0).size() ; i++){ // não-terminais
            if(!texto.getLinhas(0).get(i).equals(" "))
            inacessiveis.add(texto.getLinhas(0).get(i));
        }


        for(int k = 0; k < inacessiveis.size(); k++){ // lê o 1ºelemento da lista de inacessiveis
            for(int l = 3; texto.getLinhas(l).get(0).equals(simboloInicial); l++){
                // verifica se o terminal é produzido por outros terminais que não sejam ele
                if(texto.getLinhas(l).get(4).contains(inacessiveis.get(k))
                && !texto.getLinhas(l).get(0).equals(inacessiveis.get(k))){
                    acessiveis.add(inacessiveis.remove(k));
                    k--;
                    break;
                }
                if(l+1 > texto.getLinha().size())
                    break;
            }
        }

        int m = 3;
        while(simboloInicial.equals(texto.getLinhas(m).get(0))) // "pula" as produções do símbolo inicial
            m++;

        //       verificar agora se alguma produção produz um símbolo que está na lista de
        //       símbolos inacessíveis;
        for(int k = 0; k < inacessiveis.size(); k++){ // lê o 1ºelemento da lista de inacessiveis
            for(int l = m; l < texto.getLinha().size() ; l++){
                // verifica se o terminal é produzido por outros terminais que não sejam ele
                if(texto.getLinhas(l).get(4).contains(inacessiveis.get(k))
                && !texto.getLinhas(l).get(0).equals(inacessiveis.get(k))){
                    acessiveis.add(inacessiveis.remove(k));
                    k--;
                    break;
                }
            }
        }

        while(!inacessiveis.isEmpty()){
            Eliminar.eliminaLinhas(inacessiveis.get(0), texto);
            inacessiveis.remove(0);
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

}
/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */
 
package Simplifica;

import java.util.ArrayList;

public class EliminaInuteis {
    private Listas texto;
    private ArrayList<String> uteis;
    private ArrayList<String> inuteis;

    public EliminaInuteis(Listas texto, String nomeArquivo) {
        this.texto = texto;
        iniciaEliminacao(nomeArquivo);
    }

    public void iniciaEliminacao(String nomeArquivo){

                System.out.println("Eliminação de Símbolos Inúteis");

        uteis = new ArrayList<String>();
        inuteis = new ArrayList<String>();

        int tamanho = texto.getLinha().size();
        int c = 0;
        String nomeTemporario = "";

        while(nomeArquivo.charAt(c) != '.') // cria o nome do novo arquivo;
        nomeTemporario += nomeArquivo.charAt(c++);

        nomeTemporario += "_semInuteis.txt";

        String simboloInicial = texto.getLinhas(2).get(0);   // pega o 1º elemento da 3ª linha;
                                                            // armazena o símbolo inicial
                                                            // linha 3, elemento 1 da lista
        uteis.add(simboloInicial);

        int i = 3; // percorre a partir da 4ª linha

        while(simboloInicial.equals(texto.getLinhas(i).get(0))) // "pula" as produções do símbolo inicial
            i++;

        int j;
        boolean temTerminal = false;
        String simboloSalvo = "";
        String simboloProduzido = "";

        j = i;
        while (j < tamanho){ // ler o símbolo produzido (posição 4)
            simboloSalvo = texto.getLinhas(j).get(0);
            int cont = j;

            do{
                simboloProduzido = texto.getLinhas(cont).get(4);
                for(int k = 0; k < texto.getLinhas(1).size(); k++){
                    if(!simboloProduzido.contains("<") || !simboloProduzido.contains(">"))
                        temTerminal = true;
                }

                cont++;
                if(cont >= tamanho)
                    break;
            }while(simboloSalvo.equals(texto.getLinhas(cont).get(0)));

            if(!temTerminal)
                inuteis.add(simboloSalvo);
            else
                uteis.add(simboloSalvo);

            j = cont;

            temTerminal = false;
            tamanho = texto.getLinha().size();
        }

        int tamanhoInuteis = inuteis.size();
        int tamanhoUteis = uteis.size();
        String inutil;
        int anda;

        for (int in = 0; in < tamanhoInuteis; in++) {
            inutil = inuteis.get(in);
            anda = 3;

            while(!texto.getLinhas(anda).get(0).equals(inutil))
                anda++;

            int kn = anda;

            for (int jn = 0; jn < tamanhoUteis; jn++){
                int valTemp = kn;

                while(texto.getLinhas(valTemp).get(0).equals(inutil)){ // verifica se o símbolo inútil
                                                                        // produz um terminal
                                                                        // indiretamente
                if(texto.getLinhas(valTemp).get(4).contains(uteis.get(jn))){
                    uteis.add(inutil);
                    inuteis.remove(inutil);
                    break;
                }
                if(valTemp+1 < texto.getLinha().size())
                    valTemp++;
                else
                    break;
                }
            }
            tamanhoInuteis = inuteis.size();
        }

        while(!inuteis.isEmpty()){
            Eliminar.eliminaLinhas(inuteis.get(0), texto);
            inuteis.remove(0);
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
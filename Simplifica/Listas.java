/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */
 
package Simplifica;

import java.util.ArrayList;

public class Listas{
    private ArrayList<String> simbolosNaLinha;
    private ArrayList<ArrayList<String>> linhasDaGramatica;

    public Listas(){
        linhasDaGramatica = new ArrayList<ArrayList<String>>();
    }

    public ArrayList<ArrayList<String>> getLinha() { // passa a lista inteira
        return linhasDaGramatica;
    }

    public ArrayList<String> getLinhas(int posicao) { // passa um elemento da lista
        return linhasDaGramatica.get(posicao);
    }
    
    public void novaLinha(){
         simbolosNaLinha = new ArrayList();
         linhasDaGramatica.add(simbolosNaLinha);
    }

    public void inserirSimbolo(String simbolo, boolean novaLinha){
        if(novaLinha){
            novaLinha();
//            inserirLinha(simbolosNaLinha);
        }
        simbolosNaLinha.add(simbolo);
    }
    
}
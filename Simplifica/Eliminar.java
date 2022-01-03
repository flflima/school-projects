/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */


package Simplifica;

public class Eliminar {
    public Eliminar() {}

     public static void eliminaLinhas(String simboloElimina, Listas texto){

        // elimina as referências na 1ª linha
        int tamanho = texto.getLinhas(0).size();
        int k = 0;
        String temp = "";
        while(k < tamanho){
            if(simboloElimina.equals(texto.getLinhas(0).get(k))){
                texto.getLinhas(0).remove(k);
                tamanho = texto.getLinhas(0).size();
                if(k<tamanho)
                texto.getLinhas(0).remove(k);
            }else
                k++;
            tamanho = texto.getLinhas(0).size();
        }

        // elimina as demais linhas das produções
        tamanho = texto.getLinha().size();
        k = 3;
        int i = 0;
        while(k < tamanho){
            temp = texto.getLinhas(k).get(4);
            if(temp.contains(simboloElimina) || simboloElimina.equals(texto.getLinhas(k).get(0))){
                while(!texto.getLinhas(k).isEmpty())
                texto.getLinhas(k).remove(i);
                texto.getLinha().remove(k);
            }else
                k++;
            tamanho = texto.getLinha().size();
            temp = "";
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

        for(int im = 0 ; im < texto.getLinha().size(); im++){
            if(texto.getLinhas(im).get(texto.getLinhas(im).size()-1).equals(" "))
                texto.getLinhas(im).remove(texto.getLinhas(im).size()-1);
        }
    }
}

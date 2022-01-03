/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */

package Simplifica;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class CriarArquivos {
    public CriarArquivos() {}

    public static void criar(Listas texto, String nomeArquivo){
        System.out.println("\nCriando "+nomeArquivo+" ...");
        
        System.out.println();
        try {
            FileOutputStream reader = new FileOutputStream(nomeArquivo);
            PrintStream saida = new PrintStream(reader);

                for(int i = 0; i < texto.getLinha().size(); i++){
                   for(int j = 0; j < texto.getLinhas(i).size(); j++)
                       saida.print(texto.getLinhas(i).get(j));
                   
                   if(i+1<texto.getLinha().size())
                   saida.println();
                }
            saida.close();
            reader.close();
        } catch (FileNotFoundException ex) {
           ex.getMessage();
        }
        catch (IOException ex) {
            ex.getMessage();
        }
    }
}

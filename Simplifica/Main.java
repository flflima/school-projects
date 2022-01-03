/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */

package Simplifica;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
         System.out.println("O arquivo onde a gramática está salva deve estar de acordo com a Backus-Naur Form (BNF)\ne ter o seguinte formato: ");
         System.out.println("Linha 1 - Conjunto de não-terminais;");
         System.out.println("Linha 2 - Conjunto de terminais;");
         System.out.println("Linha 3 - Símbolo inicial;");
         System.out.println("A partir da linha 4 - Uma produção por linha.");
         System.out.println("(O símbolo vazio é representado pelo caractere '&')\n\nExemplo: ");
         System.out.println("<A> <B> <C>");
         System.out.println("a b c &");
         System.out.println("<S>");
         System.out.println("<S> ::= <A>");
         System.out.println("<S> ::= <B>");
         System.out.println("<S> ::= <C>");
         System.out.println("<A> ::= aa<A>a");
         System.out.println("<A> ::= <B>");
         System.out.println("<A> ::= &");
         System.out.println("<B> ::= b<B>b");
         System.out.println("<B> ::= b");
         System.out.println("<B> ::= <C>");
         System.out.println("<C> ::= c<C>");
         System.out.println();
         LerArquivo a = new LerArquivo();
    }

}

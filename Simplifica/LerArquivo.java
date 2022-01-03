/**
 * Nomes: Felipe Lima Freire
 *        Leandro da Silva Mattos
 *        Paulo da Silva Santos
 *        Ciência da Computação - 6º Semestre
 */

package Simplifica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LerArquivo{
    private int simbolo;
    private int flag;
    private final int fimDeLinha = 13; // ASCII
    private final int newLine = 10; // ASCII
    private Listas texto;

    public LerArquivo(){
        
        texto = new Listas();
        String palavra;
        int caractere;

        boolean novaLinha;
        boolean linha1 = false;
        boolean linha2 = false;
        boolean linha3 = false;
        boolean linha4 = false;
             
        
        System.out.println("Informe o diretório do arquivo: ");
        System.out.println("Ex.: diretorio\\nome_arquivo.txt");
        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine();
        
        try {
            FileReader reader = new FileReader(nome);
            BufferedReader leitor = new BufferedReader(reader);

//////////////////////////////////////////////////////////////////////////////////

            // lê a 1ª Linha
            caractere = leitor.read();
            flag = 0;
            simbolo = 0;
            novaLinha = true;
            palavra = "";
            System.out.println();
            System.out.println("Não-Terminais: ");
            
            while (caractere != fimDeLinha) {

                palavra += ((char)caractere);

                if(caractere == ((char)'>')){
                    primeiraLinha(palavra);
                    texto.inserirSimbolo(palavra, novaLinha);
                    System.out.print(palavra);
                    palavra = "";
                    flag++;
                    novaLinha = false;
                }
                else{
                
                    if(caractere == ((char)' ')){
                        texto.inserirSimbolo(palavra, novaLinha);
                        System.out.print(palavra);
                        palavra = "";
                        flag++;
                        novaLinha = false;
                    }
                }
                caractere = leitor.read();
            }
            if(!palavra.equals(""))
                    primeiraLinha(palavra);
            
            if(simbolo == 0 && flag > 0)
                linha1 = true; // Linha 1 está OK


//////////////////////////////////////////////////////////////////////////////////

            // lê a 2ª Linha
            caractere = leitor.read();
            caractere = leitor.read();
            palavra = "";
            simbolo = 0;
            novaLinha = true;
            System.out.println();
            System.out.println("Terminais: ");
            while (caractere != fimDeLinha && caractere != this.newLine) {
                palavra += ((char)caractere);
                if (caractere == ((char)' ')) {
                    texto.inserirSimbolo(palavra, novaLinha);
                    System.out.print(palavra);
                    palavra = "";
                    novaLinha = false;
                } else {
                    segundaLinha(palavra);
                    texto.inserirSimbolo(palavra, novaLinha);
                    System.out.print(palavra);
                    palavra = "";
                    novaLinha = false;
                }
                caractere = leitor.read();
            }
            if(simbolo == 0)
                linha2 = true; // Linha 2 está OK


//////////////////////////////////////////////////////////////////////////////////

            // lê a 3ª Linha
            caractere = leitor.read();
            caractere = leitor.read();
            palavra = "";
            simbolo = 0;
            novaLinha = true;
            System.out.println();
            System.out.println("Símbolo Inicial: ");
            while (caractere != fimDeLinha && caractere != this.newLine) {

                palavra += ((char)caractere);

                if(caractere == (char)'>'){
                    System.out.print(palavra);
                    terceiraLinha(palavra);
                    texto.inserirSimbolo(palavra, novaLinha);
                    palavra = "";
                    novaLinha = false;
                }
                caractere = leitor.read();
            }
            if(!palavra.equals(""))
                    primeiraLinha(palavra);
            if(simbolo == 0)
                linha3 = true; // Linha 3 está OK


//////////////////////////////////////////////////////////////////////////////////

            //lê a 4ª Linha em diante
            caractere = leitor.read();
            caractere = leitor.read();

            simbolo = 0;
            novaLinha = true;
            palavra = "";
            System.out.println();
            System.out.println("Produções: ");
            while (caractere != -1){

                if (caractere == ((char)'=')) {
                    palavra += ((char)caractere);
                    quartaLinha(palavra);
                    texto.inserirSimbolo(palavra, novaLinha);
                    System.out.print(palavra);
                    palavra = "";
                    novaLinha = false;
                } else {
                    if(caractere == ((char)' ')) {
                        if(novaLinha){
                            texto.inserirSimbolo(palavra, novaLinha);
                            System.out.print(palavra);
                            novaLinha = false;
                            palavra = "";
                        }
                        palavra += ((char)caractere);
                        texto.inserirSimbolo(palavra, novaLinha);
                        System.out.print(palavra);
                        palavra = "";
                    } else {
                        if(caractere == fimDeLinha) {
                            texto.inserirSimbolo(palavra, novaLinha);
                            System.out.print(palavra);
                            System.out.println();
                            novaLinha = true;
                            palavra = "";
                        } else {
                            if(caractere == this.newLine)
                            caractere = 0;
                            else
                            palavra += ((char)caractere);
                        }
                    }
                }

             caractere = leitor.read();
             
            }

            texto.inserirSimbolo(palavra, novaLinha);
            System.out.println(palavra);

            if(simbolo == 0)
                linha4 = true; // Linha 4 está OK

//////////////////////////////////////////////////////////////////////////////////
            leitor.close();
            System.out.println();
            if(!linha1 || !linha2 || !linha3 || !linha4){
                if(!linha1) {
                    System.out.println( "Erro na linha 1");
                    return;
                }
                if(!linha2) {
                    System.out.println("Erro na linha 2");
                    return;
                }
                if(!linha3) {
                    System.out.println("Erro na linha 3");
                    return;
                }
                if(!linha4){
                    System.out.println( "Erro a partir da linha 4");
                    return;
                }

                System.out.println();
            }

            leitor.close();
            reader.close();
            
       EliminaVazio vazios = new EliminaVazio(texto, nome);
//       EliminaInuteis inuteis = new EliminaInuteis(texto, nome);
//       EliminaInacessiveis inacessiveis = new EliminaInacessiveis(texto, nome);

        int c = 0;
        String nomeTemporario ="";
        while(nome.charAt(c) != '.')
            nomeTemporario += nome.charAt(c++);
        
        nomeTemporario += "_Simplificada.txt";

        CriarArquivos.criar(texto, nomeTemporario);


        } catch (FileNotFoundException ex) {
             ex.getMessage();
        }catch (IOException ex) {
             ex.getMessage();
        }
    }

    public void primeiraLinha(String caracteres){ // checar se na 1ª Linha os não-terminais foram escitos corretamente
        if(caracteres.charAt(0) == '<' && caracteres.charAt(caracteres.length()-1) != '>'
                || caracteres.charAt(0) != '<' && caracteres.charAt(caracteres.length()-1) == '>'
                || caracteres.charAt(0) != '<' && caracteres.charAt(caracteres.length()-1) != '>')
                        simbolo = 1;
    }

    public void segundaLinha(String caracteres){ // checar se na 2ª Linha os não-terminais foram escritos corretamente
        if(caracteres.charAt(0) == '<' || caracteres.charAt(caracteres.length()-1) == '>')
                        simbolo = 1;
    }

    public void terceiraLinha(String caracteres){ // checar se na 3ª o símbolo inicial foi escrito corretamente
        if(caracteres.charAt(0) != '<' || caracteres.charAt(caracteres.length()-1) != '>' )
                        simbolo = 1;
    }

    public void quartaLinha(String caracteres){ // checar a partir da 4ª Linha os não-terminais foram escitos corretamente
        if(!caracteres.equals("::="))
            simbolo = 1;
    }

}
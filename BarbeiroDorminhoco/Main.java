/***************************************************************************
   UniSantos - Universidade Católica de Santos				   
   Nomes: Felipe Lima Freire						   
          Leandro da Silva Mattos                                         
   Curso: Ciência da Computação					   
   Disciplina: Sistemas Operacionais I                            	   
   									   
   Problema: Barbeiro Dorminhoco.                                         
 									   
 ***************************************************************************/

package BarbeiroDorminhoco;

public class Main extends Thread{
    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia();
        Thread b = new Thread(new Barbeiro(barbearia));
        Thread c = new Thread(new Cliente(barbearia));
        b.start();
        c.start();
    }
}
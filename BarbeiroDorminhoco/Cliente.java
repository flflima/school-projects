/***************************************************************************
   UniSantos - Universidade Católica de Santos				   
   Nomes: Felipe Lima Freire						   
          Leandro da Silva Mattos                                         
   Curso: Ciência da Computação					   
   Disciplina: Sistemas Operacionais I                            	   
   									   
   Problema: Barbeiro Dorminhoco.                                         
 									   
 ***************************************************************************/

package BarbeiroDorminhoco;
import java.util.Random;

public class Cliente implements Runnable{
    Barbearia b;
    
    public Cliente (Barbearia b){
        this.b = b;
    }

    public void run(){
	Random gerador = new Random();
        int i = 0;
    	while(true){
            try {
                    Thread.sleep(800);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            if (gerador.nextInt(100) > 50){
                b.cliente(i);
                i++;
            }
     	}
    }
}
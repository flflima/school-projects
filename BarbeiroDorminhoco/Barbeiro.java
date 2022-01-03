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

public class Barbeiro implements Runnable{
    Barbearia b;

    public Barbeiro(Barbearia b) {
        this.b = b;
    }

    public void run(){
	Random gerador = new Random();
    	while(true){
            try {
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            if (gerador.nextInt(100) > 75)
                b.barbeiro();
     	}
    }
}
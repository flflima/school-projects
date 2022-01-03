/***************************************************************************
   UniSantos - Universidade Católica de Santos				   
   Nomes: Felipe Lima Freire						   
          Leandro da Silva Mattos                                         
   Curso: Ciência da Computação					   
   Disciplina: Sistemas Operacionais I                            	   
   									   
   Problema: Barbeiro Dorminhoco.                                         
 									   
 ***************************************************************************/

package BarbeiroDorminhoco;

public class Barbearia{
    private static final int CADEIRAS = 5; // Total de cadeiras na barbearia;
    private int esperando; // Total de clientes esperando para serem atendidos;
    private boolean dormindo; // Flag que indica se o barbeiro está acordado ou dormindo;

    public Barbearia() {
        esperando = 0; // Inicialmente não há nenhum cliente esperando;
        dormindo = false; // O barbeiro não está dormindo inicialmente;
    }

    public synchronized void barbeiro(){
        while(esperando == 0){ // Enquanto a barbearia estiver vazia o barbeiro vai dormir;
            dormindo = true; // Agora o barbeiro está dormindo;
            System.out.println("O Barbeiro está dormindo!\n");
            try {
                wait();
            } catch (InterruptedException ex) {
                    ex.printStackTrace();
            }
        }
        if(dormindo)
               dormindo = false; // Indica agora que o barbeiro foi acordado;

        esperando--; // O cliente vai atender um cliente;

        System.out.println("O Barbeiro está atendendo um cliente.\n");
    }

    public synchronized void cliente(int i){
        if(esperando < CADEIRAS){
            System.out.println("O Cliente "+i+" chegou na barbearia.");
            esperando++; // Um novo cliente espera para ser atendido;

            if(dormindo) // Se o barbeiro estiver dormindo, acorda ele;
                        notify();

            System.out.println("O Cliente "+i+" está esperando.\n");

        }else // Se a barbearia estiver cheia, o cliente vai embora;
            
            System.out.println("O Cliente "+i+" vai embora!\nA Barbearia está cheia.\n");
    }
}
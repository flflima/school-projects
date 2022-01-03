/* UniSantos - Universidade Católica de Santos
 * Nomes: Felipe Lima Freire
 * 		  Leandro da Silva Mattos
 * 		  Paulo da Silva Santos
 * Curso: Ciência da Computação - 6º Semestre	
 * */

package emulapag;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{  	
		int op;
		char continua, compara;
		Scanner sc = new Scanner(System.in);

		do {
			do {
				System.out.println("Digite qual algoritmo deseja usar:");
				System.out.println();
				System.out.println("1 - FIFO");
				System.out.println();
				System.out.println("2 - Ã“TIMO");
				System.out.println();
				System.out.println("3 - LRU");
				System.out.println();
				System.out.print("OpÃ§Ã£o: ");
				op = sc.nextInt();
			} while (op < 1 || op > 4);

			continua = 0;
			compara = 0;

			if (op == 1) {
				Fifo f = new Fifo();
				System.out.print("Deseja fazer uma comparaÃ§Ã£o(S/N)?: ");
				
				try {
					compara = (char) System.in.read();
				} catch (IOException erro) {
					System.err.println(erro.getMessage());
				}

				if (compara == 's' || compara == 'S') {
					Fifo f2 = new Fifo(f);
					System.out.println();
					System.out.println("Resultado: ");
					System.out.println("Primeira Chamada");
					System.out.println("Numero de Quadros: " + f.getNumQuadros() + "\tNÃºmero de Falhas: " + f.getTotalFalhas());
					System.out.println();
					System.out.println("Segunda Chamada");
					System.out.println("Numero de Quadros: " + f2.getNumQuadros() + "\tNÃºmero de Falhas: " + f2.getTotalFalhas());
				}
			}

			if (op == 2) {
				Otimo o = new Otimo();
			}

			if (op == 3) {
				Lru l = new Lru();
			}

			while ( (char) System.in.read() != '\n' )

			System.out.println();
			System.out.print("Continua(S/N): ");

			try {
				continua = (char) System.in.read();
			} catch (IOException erro) {
				System.err.println(erro.getMessage());
			}
			
		} while (continua == 'S' || continua == 's');
    }
}

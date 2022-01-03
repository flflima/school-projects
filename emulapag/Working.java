/* UniSantos - Universidade Católica de Santos
 * Nomes: Felipe Lima Freire
 * 		  Leandro da Silva Mattos
 * 		  Paulo da Silva Santos
 * Curso: Ciência da Computação - 6º Semestre	
 * */

package emulapag;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Working {
	int i,num,totPag = 0,quadrosQ;
	ArrayList<Integer> processoP = new ArrayList<Integer>();
	ArrayList<Integer> memoriaVirtual = new ArrayList<Integer>();
	ArrayList<Integer> sequenciaReferencia = new ArrayList<Integer>();

	public Working() {
		do {
			System.out.println();
			System.out.print("Informe o numero de pÃ¡ginas: ");
			Scanner sc = new Scanner(System.in);
			totPag = sc.nextInt();
		} while (totPag < 10 || totPag > 30);

		for(i = 0; i < totPag; i++){
			processoP.add(i+1);
		}
		
		memoriaVirtual = processoP;
		setQuadros();
		
		sequenciaReferencia.add(7);
		sequenciaReferencia.add(0);
		sequenciaReferencia.add(1);
		sequenciaReferencia.add(2);
		sequenciaReferencia.add(0);
		sequenciaReferencia.add(3);
		sequenciaReferencia.add(0);
		sequenciaReferencia.add(4);
		sequenciaReferencia.add(2);
		sequenciaReferencia.add(3);
		sequenciaReferencia.add(0);
		sequenciaReferencia.add(3);
		sequenciaReferencia.add(2);
		sequenciaReferencia.add(1);
		sequenciaReferencia.add(2);
		sequenciaReferencia.add(0);
		sequenciaReferencia.add(1);
		sequenciaReferencia.add(7);
		sequenciaReferencia.add(0);
		sequenciaReferencia.add(1);

		System.out.println();
		System.out.println();
		System.out.println("PÃ¡ginas P:");

		Iterator it = processoP.iterator();
		Iterator it1 = memoriaVirtual.iterator();
		Iterator it2 = sequenciaReferencia.iterator();

		i = 0;

		while (it.hasNext()) {
			System.out.print("[" + i + " - " + it.next()+"]\t");
			i++;
		}

		System.out.println();
		System.out.println();
		System.out.println("MemÃ³ria Virtual:");

		i = 0;

		while (it1.hasNext()) {
			System.out.print("[" + i + " - " + it1.next()+"]\t");
			i++;
		}

		System.out.println();
		System.out.println();
		System.out.println("SequÃªncia de referÃªncia:");

		i = 0;

		while (it2.hasNext()) {
			System.out.print("" + it2.next()+"\t");
			i++;
		}

		System.out.println();
		System.out.println();
		System.out.println();
	}

	public Working(int compara) {
		setQuadros();
	}

	public void setQuadros(){
		do {
			System.out.print("Digite a quantidade de quadros Q: ");
			Scanner sc = new Scanner(System.in);
			quadrosQ = sc.nextInt();
		} while (quadrosQ < 3 || quadrosQ > 6);
	}

	public ArrayList getProcesso(){
		return processoP;
	}

	public ArrayList getMemoria(){
		return memoriaVirtual;
	}

	public ArrayList getSequencia(){
		return sequenciaReferencia;
	}

	public int getNumQuadros(){
		return quadrosQ;
	}
}

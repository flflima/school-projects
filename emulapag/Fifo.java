/* UniSantos - Universidade Católica de Santos
 * Nomes: Felipe Lima Freire
 * 		  Leandro da Silva Mattos
 * 		  Paulo da Silva Santos
 * Curso: Ciência da Computação - 6º Semestre	
 * */

package emulapag;
import java.util.ArrayList;

public class Fifo extends Working {
	private int falha;

	public Fifo() {
		AlgoritmoFifo(processoP);
	}

	public void AlgoritmoFifo(ArrayList processo){
		ArrayList<Integer> quadros = new ArrayList<Integer>();
		int j = 0, achou = 0, k;
		falha = 0;

		for (i = 0; i < quadrosQ; i++) {
			quadros.add(j, null);
		}

		for (i = 0; i < sequenciaReferencia.size(); i++) {
			if (j == quadrosQ) {
				j = 0;
			}
			for (k = 0; k < quadrosQ; k++) {
				if (quadros.get(k) == memoriaVirtual.get(sequenciaReferencia.get(i)))
					achou = 1;
			}
			if (achou == 0) {
				quadros.set(j, memoriaVirtual.get(sequenciaReferencia.get(i)));
				imprimeQuadro(quadros);
				falha++;
				j++;
			}
			achou = 0;
		}
		System.out.println("Total de falhas = " + falha);
		System.out.println();
	}

	public Fifo(Fifo f1){
		super(1);
		memoriaVirtual = f1.getMemoria();
		sequenciaReferencia = f1.getSequencia();
		AlgoritmoFifo(f1.getProcesso());
	}

	public int getTotalFalhas(){
		return falha;
	}

	private void imprimeQuadro(ArrayList quadros) {
		int ctrl;
		System.out.println();

		for (ctrl = 0; ctrl < quadrosQ; ctrl++) {
			System.out.println("Q[" + ctrl + "] = " + quadros.get(ctrl));
		}

		System.out.println();
	}
}

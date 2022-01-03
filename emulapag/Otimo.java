/* UniSantos - Universidade Cat�lica de Santos
 * Nomes: Felipe Lima Freire
 * 		  Leandro da Silva Mattos
 * 		  Paulo da Silva Santos
 * Curso: Ci�ncia da Computa��o - 6� Semestre	
 * */

package emulapag;
import java.util.*;

public class Otimo extends Working {
	int posicaoQuadro = 0;
	
	public Otimo() {
		ArrayList<Integer> quadros = new ArrayList<Integer>();
		int falha = 0, j = 0, achou = 0, k;

		for (i = 0; i < quadrosQ; i++) {
			quadros.add(j, null);
		}

		int cont = 0;

		for (i = 0; i < sequenciaReferencia.size(); i++) {
			for (k = 0; k < quadrosQ; k++) {
				if (quadros.get(k) == memoriaVirtual.get(sequenciaReferencia.get(i))) {
					achou = 1;
				}
			}

			if (achou == 0) {
				if(cont < quadros.size()){
					quadros.set(cont, memoriaVirtual.get(sequenciaReferencia.get(i)));
					cont++;
				}else
					quadros.set(descobrePosicao(i, quadros), memoriaVirtual.get(sequenciaReferencia.get(i)));
				imprimeQuadro(quadros);
				falha++;
			}
			achou = 0;
		}
		
		System.out.println("Total de falhas = " + falha);
	}

	private void imprimeQuadro(ArrayList quadros) {
		int ctrl;

		System.out.println();

		for (ctrl = 0; ctrl < quadrosQ; ctrl++) {
			System.out.println("Q[" + ctrl + "] = " + quadros.get(ctrl));
		}

		System.out.println();
	}

	private int descobrePosicao(int i, ArrayList quadros) {
		int l = i + 1, p, numeroDaPosicao, maiorPosicao = -1;
		int achou = 0, posicao = 0, foraSR = -1;

		for(p = 0; p < quadrosQ; p++){
			achou = 0;
			
			while (l < sequenciaReferencia.size()){
				if (quadros.get(p) == memoriaVirtual.get(sequenciaReferencia.get(l))) {
					achou = 1;
					posicao = l;
					l = sequenciaReferencia.size();
				}
				l++;
			}
			
			if(achou == 1){
				if(posicao > maiorPosicao)
				maiorPosicao = posicao;
			}else{
				foraSR = p;
				p = quadrosQ;
			}        
			
			l = i + 1;
		}

		if(foraSR > 0)
			return foraSR;

		if(maiorPosicao > 0){
			numeroDaPosicao = quadros.indexOf(memoriaVirtual.get(sequenciaReferencia.get(maiorPosicao)));
			return numeroDaPosicao;
		}

		return (posicaoQuadro++ % quadrosQ);
	}
}

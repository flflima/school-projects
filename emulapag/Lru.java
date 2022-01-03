/* UniSantos - Universidade Católica de Santos
 * Nomes: Felipe Lima Freire
 * 		  Leandro da Silva Mattos
 * 		  Paulo da Silva Santos
 * Curso: Ciência da Computação - 6º Semestre	
 * */

package emulapag;
import java.util.Stack;

public class Lru extends Working{
	Stack<Integer> pilhaQuadros = new Stack<Integer>();
	int total_falhas;
	int pilhaCheia;
	int posicao;

	public Lru(){
		total_falhas = 0;
		pilhaCheia = 0;
		boolean achou = false;
		
		for (i = 0; i < sequenciaReferencia.size(); i++) {
			if(pilhaCheia < quadrosQ){
				for (int k = 0; k < pilhaQuadros.size(); k++) {
					if (pilhaQuadros.get(k) == memoriaVirtual.get(sequenciaReferencia.get(i))) {
						achou = true;
						posicao = k;
						k = quadrosQ;
					}
				}
				if(achou)
					atualiza(pilhaQuadros, posicao);
				else{
					total_falhas++;
					pilhaCheia++;
					pilhaQuadros.push(memoriaVirtual.get(sequenciaReferencia.get(i)));
					imprimeQuadro(pilhaQuadros);
				}
				achou = false;
			}else{
				for (int k = 0; k < pilhaQuadros.size(); k++) {
					if (pilhaQuadros.get(k) == memoriaVirtual.get(sequenciaReferencia.get(i))) {
						achou = true;
						posicao = k;
						k = quadrosQ;
					}
				}
				if (!achou) {
					total_falhas++;
					substitui(pilhaQuadros, memoriaVirtual.get(sequenciaReferencia.get(i)));
					imprimeQuadro(pilhaQuadros);
				}else{		
					System.out.println();
					System.out.println("---------------");
					System.out.println("Uma atualização: ");
					atualiza(pilhaQuadros, posicao);
					imprimeQuadro(pilhaQuadros);
					System.out.print("---------------");
					System.out.println();
				}
				achou = false;
			}
		}
		System.out.println();
		System.out.println("Total de falhas = " + total_falhas);
	}

	public void substitui(Stack<Integer> quadros, int novaPagina){
		int j = (quadrosQ - 1);
		Integer pagina;
		Stack<Integer> novaPilha = new Stack<Integer>();
		
		while(j > 0){
			pagina = (Integer)(quadros.pop());
			novaPilha.push(pagina);
			j--;
		}
		
		quadros.pop();
		
		while(!novaPilha.isEmpty()){
			pagina = (Integer)novaPilha.pop();
			quadros.push (pagina);
		}
		
		quadros.push(novaPagina);
	}

	public void atualiza(Stack<Integer> quadros, int posicao){
		int j = (quadros.size() - 1);
		Integer pagina;
		Stack<Integer> novaPilha = new Stack<Integer>();
		
		while(j > posicao){
			pagina = (Integer)quadros.pop();
			novaPilha.push(pagina);
			j--;
		}
		
		Integer aux = (Integer) quadros.pop();
		
		while(!novaPilha.isEmpty()){
			pagina = novaPilha.pop();
			quadros.push(pagina);
		}
		
		quadros.push(aux);
	}

	private void imprimeQuadro(Stack<Integer> quadros) {
		int ctrl;
		System.out.println();

		for (ctrl = 0; ctrl < quadros.size(); ctrl++) {
			System.out.println("Q[" + ctrl + "] = " + quadros.get(ctrl));
		}

		System.out.println();
	}
}
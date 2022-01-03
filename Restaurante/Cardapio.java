package Restaurante;
import java.util.*;
import java.io.*;

public class Cardapio{
	Item[] itens;
	
	class ItemInvalidoException extends Exception{
		public String toString(){
			return "O item e invalido!";
		}
	}
	
	public Cardapio(String arquivo) throws FileNotFoundException, IOException{
		Reader file = new FileReader(arquivo);
		StreamTokenizer st = new StreamTokenizer(file);
		int numItens, numDetalhes;
		String tipo, nome, detalhes;
		float preco;
		Pizza p;
		Massa m;
		Bebida b;
		
		st.nextToken();
		numItens = (int) st.nval; // número de itens do cardápio
		itens = new Item[numItens];
		
		for(int numItem = 0; numItem < numItens; numItem++) {
			st.nextToken();
			tipo = st.sval; // pizza, massa ou bebida
			nome = "";
			while(st.nextToken() == StreamTokenizer.TT_WORD) {
			nome += st.sval+" ";
			}
			st.pushBack();
			st.nextToken();
			preco = (float) st.nval; // preço
			if(tipo.equals("pizza")) { // pizza
				st.nextToken();
				numDetalhes = (int) st.nval; // número de ingredientes
				p = new Pizza(nome,preco,numDetalhes);
				st.eolIsSignificant(true);
				st.nextToken(); // pula o EOL
				for(int i = 0; i < numDetalhes; i++) { // lê osingredientes
					detalhes= "";
					while(st.nextToken() == StreamTokenizer.TT_WORD) {
						if(st.ttype == StreamTokenizer.TT_EOL)
						break;
						detalhes += st.sval+" ";
					}
					p.addIngrediente(i,detalhes.substring(0,detalhes.length()-1));
				}
				st.eolIsSignificant(false);
				itens[numItem] = p;
			}
			else if(tipo.equals("massa")) { // massa
				st.nextToken();
				numDetalhes = (int) st.nval; // número de acompanhamentos
				m = new Massa(nome,preco,numDetalhes);
				st.eolIsSignificant(true);
				st.nextToken(); // pula o EOL
				for(int i = 0; i < numDetalhes; i++) { // lê os acompanhamentos
					detalhes= "";
					while(st.nextToken() == StreamTokenizer.TT_WORD) {
						if(st.ttype == StreamTokenizer.TT_EOL)
							break;
						detalhes += st.sval+" ";
					}
					m.addAcompanhamento(i,detalhes.substring(0,detalhes.length()-1));
				}
				st.eolIsSignificant(false);
				itens[numItem] = m;
			} else {
			// bebida
				b = new Bebida(nome,preco);
				itens[numItem] = b;
			}
		}
		file.close();
	}
	
	public void exibir(){
	System.out.println("Cardápio");
		for(int i = 0; i < itens.length; i++)
			System.out.println((i + 1) + " " + itens[i].getDescricao() + " R$ " + itens[i].getPreco());
	}
	
	public Item getItem(int index) throws ItemInvalidoException{
		if(index < 0 || index > itens.length || itens[index] == null)
			throw new ItemInvalidoException();
		else
			return itens[index];
	}
}
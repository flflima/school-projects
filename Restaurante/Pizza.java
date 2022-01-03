package Restaurante;
public class Pizza extends Prato{
	private String[] ingredientes;
	
	public Pizza(String nome, float preco, int numIngredientes){
		super.setNome(nome);
		super.setPreco(preco);
		ingredientes = new String[numIngredientes];
	}
	
	public String getDescricao(){
		return getNome(); 
	}
	
	public void addIngrediente(int index, String ingrediente){
		ingredientes[index] = ingrediente;
	}
}
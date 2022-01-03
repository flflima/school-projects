package Restaurante;
public class Massa extends Prato{
	private String[] acompanhamento;
	
	public Massa(String nome, float preco, int numAcompanhamentos){
		super.setNome(nome);
		super.setPreco(preco);
		acompanhamento = new String[numAcompanhamentos];		
	}
	
	public String getDescricao(){
		return getNome();
	}
	
	public void addAcompanhamento(int index, String acompanhamento){
		acompanhamento[index] = acompanhamento;
	}
}
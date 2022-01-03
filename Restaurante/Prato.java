package Restaurante;
public abstract class Prato implements Item{
	private String nome;
	private float preco;
	
	public String getNome(){
		return nome;
	}
	
	public float getPreco(){
		return preco;
	}
	
	public abstract String getDescricao();

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setPreco(float preco){
		this.preco = preco;
	}
}
package Restaurante;
public class Bebida implements Item{
	private String nome;
	private float preco;
	
	public Bebida(String nome, float preco){
		this.nome = nome;
		this.preco = preco;
	}
	
	public String getDescricao(){
		return nome;
	}
	
	public float getPreco(){
		return preco;
	}
}
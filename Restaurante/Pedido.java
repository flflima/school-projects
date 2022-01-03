package Restaurante;
public class Pedido{
	private Item item;
	private int quantidade;
	
	public Pedido(){}
	
	public Pedido(Item item, int quantidade){
		this.item = item;
		this.quantidade = quantidade;
	}	
	
	public Item getItem(){
		return item;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	public void setItem(Item item){
		this.item = item;
	}
	
	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}
}
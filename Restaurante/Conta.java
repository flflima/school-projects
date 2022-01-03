package Restaurante;
public class Conta{
	private int numMesa;
	private float valor;
	private float gorjeta;
	private Pedido[] pedidos;
	
	public Conta(int numMesa, Pedido[] pedidos){
		this.numMesa = numMesa;
		this.pedidos = pedidos;
	}
	
	public void calculaValor(){
		valor = 0f;
		for(int i = 0; i < pedidos.length; i++)
			valor += (float)((pedidos[i].getItem().getPreco()) * (pedidos[i].getQuantidade()));
		gorjeta = valor * 0.1f;
	}
	
	public void exibir(){
		System.out.println("Mesa número " + numMesa);
		System.out.println("Consumo: ");
		System.out.println("Descrição\tQuant.\tPreço\tTotal");
		for(int i = 0; i < pedidos.length; i++)
			System.out.println(pedidos[i].getItem().getDescricao()+"\t"+pedidos[i].getQuantidade()+
								"\t"+pedidos[i].getItem().getPreco()+"\t"
								+pedidos[i].getItem().getPreco() * pedidos[i].getQuantidade());
		System.out.println("Total......: R$ " + valor);
		System.out.println("Gorjeta....: R$ " + gorjeta);
		System.out.println("Valor Total: R$ " + (valor+gorjeta));
	}
}
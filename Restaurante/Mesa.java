package Restaurante;
import java.util.*;
public class Mesa{
	private static final int MAX_PEDIDOS = 100;
	private int numMesa;
	private int numPedidos;
	private Pedido[] pedidos;
	
	class ItemInvalidoException extends Exception{
		public String toString(){
			return "O item e invalido!";
		}
	}
	
	class NumeroMaximoDePedidosExcedidoException extends Exception{
		public String toString(){
			return "O número máximo de pedidos foi excedido!";
		}
	}

	public Mesa(int numMesa){
		this.numMesa = numMesa;
		numPedidos = 0;
		pedidos = new Pedido[MAX_PEDIDOS];
	}
	
	public void realizarPedido(Cardapio cardapio) throws NumeroMaximoDePedidosExcedidoException{
		Scanner sc = new Scanner(System.in);
		int op = 0;
		do{
			cardapio.exibir();
			System.out.println("0. Para finalizar o pedido");
			System.out.print("Selecione um item do cardápio: ");
			op = sc.nextInt();
		}while(op == 0);
		
		try{	
			cardapio.getItem(op).getDescricao();
		}
		catch(ItemInvalidoException ob){
			System.out.println(ob.getMessage());
		}
		
		System.out.print("Quantidade do Item: ");
		int quantidade = sc.nextInt();
		if(numPedidos > MAX_PEDIDOS)
			throw new NumeroMaximoDePedidosExcedidoException();
		else
			adicionarItemPedido(cardapio.getItem(op), quantidade);		
	}	
	
	public void adicionarItemPedido(Item item, int quantidade) throws NumeroMaximoDePedidosExcedidoException{
		if(numPedidos > MAX_PEDIDOS)
			throw new NumeroMaximoDePedidosExcedidoException();
		else
		pedidos[numPedidos++] = new Pedido();
	}
	
	public Conta solicitarConta(){
		Conta c = new Conta(numMesa, pedidos);
		c.calculaValor();
		return c;
	}
	
}
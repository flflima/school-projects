package Restaurante;
import java.util.Scanner;
import java.io.*;

public class Restaurante{
	private static final int NUM_MESAS_DEFAULT = 50;
	private static final String NOME_ARQUIVO_CARDAPIO = "cardapio.txt";
	private Mesa[] mesas;
	private Cardapio cardapio;
	
	class MesaOcupadaException extends Exception{
		public String toString(){
			return "A mesa esta ocupada!";
		}
	}
	
	public Restaurante() throws FileNotFoundException, IOException{
		mesas = new Mesa[NUM_MESAS_DEFAULT];
		cardapio = new Cardapio(NOME_ARQUIVO_CARDAPIO);
	}
	
	public Restaurante(int numMesas) throws FileNotFoundException, IOException{
		mesas = new Mesa[numMesas];	
		cardapio = new Cardapio(NOME_ARQUIVO_CARDAPIO);
	}
	
	public void alocarNovoCliente(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Numero da mesa: ");
		int numMesa = sc.nextInt();
		try{
			addMesa(numMesa);
		}
		catch(IndexOutOfBoundsException ob){
			System.out.println(ob.getMessage());
		}
		catch(MesaOcupadaException ob){			
			System.out.println(ob.getMessage());
		}
	}
	
	public void cadastrarNovoPedido(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Numero da mesa: ");
		int numMesa = sc.nextInt();
		try{
			addMesa(numMesa);
		}
		catch(IndexOutOfBoundsException ob){
			System.out.println(ob.getMessage());
		}
		catch(MesaOcupadaException ob){			
			System.out.println(ob.getMessage());
		}
		mesas[numMesa].realizarPedido(cardapio);
	}
	
	public void emitirConta(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Numero da mesa: ");
		int numMesa = sc.nextInt();
		if(mesas[numMesa] != null){
			mesas[numMesa].solicitarConta().exibir();
			mesas[numMesa] = null;
		}
	}	
	
	public void addMesa(int numMesa) throws IndexOutOfBoundsException, MesaOcupadaException{
		if(numMesa < 0 || numMesa > mesas.length){
			throw new IndexOutOfBoundsException("Valores Invalidos!");
		}
		if(mesas[numMesa] != null){
			throw new MesaOcupadaException();
		}
		
		mesas[numMesa] = new Mesa(numMesa);
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Restaurante r;
		int opcao;
		do{
			System.out.println("***** Opções *****");
			System.out.println("<1> Novo Cliente");
			System.out.println("<2> Novo Pedido");
			System.out.println("<3> Emitir Conta");
			System.out.print("<0> Sair\nOpção: ");
			opcao = sc.nextInt();

			switch(opcao){
				case 1:
						System.out.println("Informe o número da mesa ocupada pelo cliente: ");
						r = new Restaurante(sc.nextInt());
						break;
				case 2:
						r = new Restaurante();
						r.cadastrarNovoPedido();
						break;
				case 3:
						r = new Restaurante();
						r.emitirConta();
						break;
				default: 
						System.out.println("Opção inválida");
						break;
			}
		}while(opcao != 0);
	}
}
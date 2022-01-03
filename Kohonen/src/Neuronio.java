/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipe
 */
public class Neuronio {
    private int[] pesos;
    
    public Neuronio(){
        this.pesos = new int[29];
        inicializaPesos();
    }
    
    // retorna um array
    public int[] getPesos(){
        return pesos;
    }
    
    public void inicializaPesos(){
        for (int i = 0; i < 29; i++) {
            this.pesos[i] = (int)Math.round(Math.random());
        }
    }
    
    public void ajustarPesos(int[][] x, int posicao, double ni, double h){
        
        for (int i = 0; i < 29; i++) 
            this.pesos[i] += (int)(ni*h*(x[i][posicao] - this.pesos[i]));
        
        
    }
}

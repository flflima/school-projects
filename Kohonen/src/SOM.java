
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class SOM {

    private int[][] padrao = {
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0},
        {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    Neuronio[][] n = new Neuronio[10][10];
    private double sigma;
    private double ni;
    private double t1;
    private double t2;

    public SOM() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.n[i][j] = new Neuronio();
            }
        }
    }

    public void iniciaRede() {
        int it = 0;
        int numRand;
        int posicao = 0;
        int[] lista = new int[16];
        double dEuclidiana;
        double dLateral;
        double h;
        double menor;
        int[] vencedor = new int[2];
        this.sigma = 50;
        this.ni = 0.1;
        this.t2 = 1000;
        this.t1 = 1000 / Math.log(this.sigma);
        int i, j;

        while (it < 5000) {
            System.out.println(it);

            for (int l = 0; l < 16; l++) {
                lista[l] = l;
            }

            for (int l = 0; l < 16; l++) {
                menor = 99999;

                // seleciona um padrão                
                do {
                    numRand = (int) (Math.random() * 16);
                } while (lista[numRand] == -1);

                posicao = lista[numRand];

                // percorre todos os neurônios e calcula a distância euclidiana               
                for (i = 0; i < 10; i++) {
                    for (j = 0; j < 10; j++) {
                        dEuclidiana = dEuclidiana(this.n[i][j], this.padrao, posicao);
                        if (dEuclidiana < menor) {
                            menor = dEuclidiana;
                            vencedor[0] = i;
                            vencedor[1] = j;
                        }
                    }
                }

                i = vencedor[0];
                j = vencedor[1];

                // percorre todos os neurônios e verifica se o neurônio pertence
                // a área vizinha do neurônio vencedor
               /* 
                for (int x = i-(int)Math.round(sigma); x < i+(int)Math.round(sigma); x++) {
                    for (int y = j-(int)Math.round(sigma); y < j+(int)Math.round(sigma); y++) {
                        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
                            dLateral = dEuclidianaLateral(x, y, i, j);
                            h = funcaoVizinhanca(dLateral, this.sigma);
                            if (h > 0)
                                n[i][j].ajustarPesos(this.padrao, posicao, this.ni, h);
                        }
                    }
                }*/
                
                for (int x = 0; x < 10; x++) {
                    for (int y = 0; y < 10; y++) {
                        // calcula a distância entre o neuronio vencedor
                        // e qualquer neurônio da grade;
                        // se essa distância estiver dentro da área do neurônio
                        // vencedor (definida por sigma), significa que seus pesos devem ser
                        // ajustados.

                        dLateral = dLateral(x, y, i, j);

                        if (Math.sqrt(dLateral) < Math.pow(this.sigma, 2)) {

                            h = funcaoVizinhanca(dLateral, this.sigma);
                            
                            if (h > 0)
                                n[i][j].ajustarPesos(this.padrao, posicao, this.ni, h);

                        }
                    }
                }

                lista[numRand] = -1;
            }

//            if (it%500 == 0){
//                Scanner s = new Scanner(System.in);
//                s.nextInt();
//            }


            int temp;
if((it+1) % 500 == 0) {
            for (i = 0; i < 10; i++) {
                for (j = 0; j < 10; j++) {
                    temp = 0;
                    for (int pos = 0; pos < 16; pos++) {
                        if (n[i][j].getPesos()[pos] == 1) {
                            temp += n[i][j].getPesos()[pos];
                            posicao = pos;
                        }
                    }
                    if (temp == 1) {
                        switch (posicao) {
                            case 0:
                                System.out.print("Pombo");
                                break;
                            case 1:
                                System.out.print("Galinha");
                                break;
                            case 2:
                                System.out.print("Pato");
                                break;
                            case 3:
                                System.out.print("Ganso");
                                break;
                            case 4:
                                System.out.print("Coruja");
                                break;
                            case 5:
                                System.out.print("Falcão");
                                break;
                            case 6:
                                System.out.print("Águia");
                                break;
                            case 7:
                                System.out.print("Raposa");
                                break;
                            case 8:
                                System.out.print("Cão");
                                break;
                            case 9:
                                System.out.print("Lobo");
                                break;
                            case 10:
                                System.out.print("Gato");
                                break;
                            case 11:
                                System.out.print("Tigre");
                                break;
                            case 12:
                                System.out.print("Leão");
                                break;
                            case 13:
                                System.out.print("Cavalo");
                                break;
                            case 14:
                                System.out.print("Zebra");
                                break;
                            case 15:
                                System.out.print("Vaca");
                                break;
                        }
                    } else {
                        System.out.print("*");
                    }
                    System.out.print("\t");
                }
                System.out.println("");
            }}
            this.sigma *= Math.exp(it / this.t1);
            this.ni *= Math.exp(it / this.t2);
            it++;
            System.out.println("");
            System.out.println("");
        }
    }

    public double dEuclidiana(Neuronio n, int[][] padrao, int posicao) {
        double dEuclidiana = 0;

        // calcula a distância entre a entrada e o vetor de pesos
        for (int i = 0; i < 29; i++) {
            dEuclidiana += Math.pow((padrao[i][posicao] - n.getPesos()[i]), 2);
        }

        return Math.sqrt(dEuclidiana);
    }

    public double dLateral(int x, int y, int i, int j) {
        return Math.pow((x - i), 2) + Math.pow((y - j), 2);
    }

    public double funcaoVizinhanca(double d, double sigma) {
        return Math.exp((-1*d) / (2 * sigma * sigma));
    }
}

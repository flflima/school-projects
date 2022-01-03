/*
 * To change this template, choose Tools | templates
 * and open the template in the editor.
 */
package analisaCredito;

import java.text.DecimalFormat;

/**
 *
 * @author User
 */
public class Main {

    // Salário Mínimo hipotético
    private final double sMinimo = 545;
    // Base de Dados
    static double idade[][] = {
        {20, 30, 40, 50, 60},
        {1.0, 0.5, 0.0, 0.0, 0.0}, // idade pequena
        {0.0, 0.5, 1.0, 0.5, 0.0}, // idade média
        //        {0.316, 0.836, 1.0, 0.547, 0.0}, // idade mais ou menos média
        {0.0, 0.0, 0.0, 0.5, 1.0} // idade grande
    };
    static double salario[][] = {
        {1000, 2000, 3000, 4000, 5000},
        {1.0, 0.5, 0.0, 0.0, 0.0}, // salário pequeno
        {0.0, 0.5, 1.0, 0.5, 0.0}, // salário médio
        {0.0, 0.0, 0.0, 0.5, 1.0} // salário grande
    };
    static double tempo[][] = {
        {1, 2, 3, 4, 5},
        {1.0, 0.5, 0.0, 0.0, 0.0}, // tempo pequeno
        {0.0, 0.5, 1.0, 0.5, 0.0}, // tempo médio
        //        {0.0, 0.0, 0.91, 0.19, 0.0}, // tempo mais ou menos grande
        {0.0, 0.0, 0.0, 0.5, 1.0} // tempo grande
    };
    static double lcredito[][] = {
        {500, 1000, 1500, 2500, 3000},
        {1.0, 0.5, 0.0, 0.0, 0.0}, // limite pequeno
        {0.0, 0.5, 1.0, 0.5, 0.0}, // limite médio
        {0.0, 0.0, 0.0, 0.5, 1.0} // limite grande
    };
    //BASE DE REGRAS
    static int regras[][] = {
        {1, 1, 1, 1},
        {1, 1, 2, 1},
        {1, 1, 3, 2},
        //        {1, 1, 4, 1},
        {1, 2, 1, 1},
        {1, 2, 2, 2},
        {1, 2, 3, 3},
        //        {1, 2, 4, 2},
        {1, 3, 1, 2},
        {1, 3, 2, 3},
        {1, 3, 3, 3},
        //        {1, 3, 4, 2},
        {2, 1, 1, 1},
        {2, 1, 2, 2},
        {2, 1, 3, 2},
        //        {2, 1, 4, 2},
        {2, 2, 1, 1},
        {2, 2, 2, 2},
        {2, 2, 3, 3},
        //        {2, 2, 4, 2},
        {2, 3, 1, 2},
        {2, 3, 2, 2},
        {2, 3, 3, 3},
        //        {2, 3, 4, 3},
        {3, 1, 1, 1},
        {3, 1, 2, 1},
        {3, 1, 3, 2},
        //        {3, 1, 4, 2},
        {3, 2, 1, 2},
        {3, 2, 2, 2},
        {3, 2, 3, 2},
        //        {3, 2, 4, 3},
        {3, 3, 1, 2},
        {3, 3, 2, 3},
        {3, 3, 3, 3}, //        {3, 3, 4, 3},
    //        {4, 1, 1, 1},
    //        {4, 1, 2, 2},
    //        {4, 1, 3, 2},
    //        {4, 1, 4, 2},
    //        {4, 2, 1, 2},
    //        {4, 2, 2, 2},
    //        {4, 2, 3, 3},
    //        {4, 2, 4, 3},
    //        {4, 3, 1, 3},
    //        {4, 3, 2, 3},
    //        {4, 3, 3, 3},
    //        {4, 3, 4, 3}
    };
    static int vIdade = 0;
    static int vSalario = 0;
    static int vTempo = 0;
    private static int selRegras[];
    private static double def;
    private static double[] RegrasInferidas;

    public static void main(String[] args) {

        Janela j = new Janela();

    }

    public static void iniciar(String[] s) {

        int id = Integer.parseInt(s[0]);
        int sal = Integer.parseInt(s[1]);
        int t = Integer.parseInt(s[2]);

        selRegras = selecionarRegras(id, sal, t);

        System.out.println("Selecionadas as regras..: ");
//        for (int i = 0; i < selRegras.length; i++) {
//            System.out.print(selRegras[i] + "..: ");
//            System.out.println(regras[selRegras[i]][0] + ", " + regras[selRegras[i]][1] + ", "
//                    + regras[selRegras[i]][2]);
//        }

        RegrasInferidas = inferirRegras(id, sal, t, selRegras);

        System.out.println("Regras fuzzyficadas..: ");
        for (int i = 0; i < RegrasInferidas.length; i++) {
            System.out.print(RegrasInferidas[i] + ", ");
        }

////        double def = defuzificar(RegrasInferidas);
        def = defuzificar(RegrasInferidas);

        System.out.println("\nSaída Defuzzificada do Limite de Crédito..: " + def);

    }

    public static int[] selecionarRegras(int id, int sal, int t) {
        int x = 0;
        int r[] = new int[(3 * 27)];

        System.out.println("id: " + id);
        //procurar variável linguistica de idade
        for (int j = 0; j <= idade[0].length; j++) {
            if (idade[0][j] == id) {
                vIdade = j;
                break;
            }
        }

        System.out.println("sal: " + sal);
        //procurar variável linguistica de salario
        for (int j = 0; j <= salario[0].length; j++) {
            if (salario[0][j] == sal) {
                vSalario = j;
                break;
            }
        }
        System.out.println("t: " + t);
        //procurar variável linguistica de tempo
        for (int j = 0; j <= tempo[0].length; j++) {
            if (tempo[0][j] == t) {
                vTempo = j;
                break;
            }
        }

        //procura regras

        for (int i = 1; i < 4; i++) {
            if (idade[i][vIdade] > 0) {
                for (int z = 1; z < 4; z++) {
                    if (salario[z][vSalario] > 0) {
                        for (int w = 1; w < 4; w++) {
                            if (tempo[w][vTempo] > 0) {
                                for (int y = 0; y < 27; y++) {
                                    if (regras[y][0] == i
                                            && regras[y][1] == z
                                            && regras[y][2] == w) {
                                        r[x] = y;
                                        x++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int ret[] = new int[x];
        for (int i = 0; i < x; i++) {
            ret[i] = r[i];
        }

        //Retonr vetor com os índices das regras
        return ret;
    }

    public static double[] inferirRegras(int id, int sal, int t, int r[]) {
        double inf[] = new double[5];
        double valorID = 0;
        double valorS = 0;
        double valorT = 0;
        double reg[][] = new double[48][5];

        //Operação de Minimo entre entradas e Saídas das regras selecionadas
        for (int i = 0; i < r.length; i++) {
            valorID = idade[regras[r[i]][0]][vIdade];
            valorS = salario[regras[r[i]][1]][vSalario];
            valorT = tempo[regras[r[i]][2]][vTempo];
            double minID = 0;
            double minS = 0;
            double minT = 0;
            for (int j = 0; j < lcredito[regras[r[i]][3]].length; j++) {
                if (valorID < lcredito[regras[r[i]][3]][j]) {
                    minID = valorID;
                } else {
                    minID = lcredito[regras[r[i]][3]][j];
                }
                if (valorS < lcredito[regras[r[i]][3]][j]) {
                    minS = valorS;
                } else {
                    minS = lcredito[regras[r[i]][3]][j];
                }
                if (valorT < lcredito[regras[r[i]][3]][j]) {
                    minT = valorT;
                } else {
                    minT = lcredito[regras[r[i]][3]][j];
                }
                if (minID <= minS && minID <= minT) {
                    reg[i][j] = minID;
                } else {
                    if (minS <= minID && minS <= minT) {
                        reg[i][j] = minS;
                    } else {
                        reg[i][j] = minT;
                    }
                }
            }
        }

        //Operação de Max das Regras Fuzzyficadas
        double max = 0;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 48; i++) {
                if (reg[i][j] > max) {
                    max = reg[i][j];
                }
            }
            inf[j] = max;
            max = 0;
        }

        return inf;
    }

    public static double defuzificar(double rinf[]) {

        //Defuzificação pelo método de centro de área (centróide)
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < 5; i++) {
            sum1 += rinf[i] * lcredito[0][i];
            sum2 += rinf[i];
        }

        return (sum1 / sum2);

    }

    public static int[][] getRegras() {
        return regras;
    }

    public static String getRegrasSelecionadas() {
        String temp = " ";
//        int regras[][] = Main.getRegras();
//            int selecionadas[] = getRegrasSelecionadas();
        for (int i = 0; i < selRegras.length; i++) {
            temp = selRegras[i] + "...: " + regras[selRegras[i]][0] + ", " + regras[selRegras[i]][1] + ", "
                    + regras[selRegras[i]][2] + "\n";
            System.out.print(selRegras[i] + "..: ");
            System.out.println(regras[selRegras[i]][0] + ", " + regras[selRegras[i]][1] + ", "
                    + regras[selRegras[i]][2]);
        }
        return temp;
    }

    public static int[] getRSelecionadas() {
        return selRegras;
    }

    public static String getRInferidas() {
        String s = "";
        for (int i = 0; i < RegrasInferidas.length; i++) {
            if (i < RegrasInferidas.length - 1) {
                s += RegrasInferidas[i] + ", ";
            } else {
                s += RegrasInferidas[i];
            }

        }

        return s;
    }

    public static String getDef() {
//        String d = String.valueOf(def);

        DecimalFormat decimal = new DecimalFormat("#,###.00");
        return decimal.format(def);

    }
}

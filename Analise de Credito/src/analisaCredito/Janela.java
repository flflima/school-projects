/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisaCredito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class Janela extends JFrame implements ActionListener {

//    private JPanel panel;
    // valores de entrada por default
    private String[] entrada = {"20", "1000", "1"};
    JComboBox listaIdade;
    JPanel painelzao2;
    JComboBox listaSalario;
    JComboBox listaTempo;
    private JScrollPane p;
    private JTextArea texto;
    private JScrollPane scroll;
    private JPanel p_Esquerdo;
    private JPanel p_Center;
    private JPanel p_Direito;
    private String[] idade = {"20", "30", "40", "50", "60"};
    private JLabel lb_idade;
    private String[] salario = {"1000", "2000", "3000", "4000", "5000"};
    private JLabel lb_salario;
    private String[] tempo = {"1", "2", "3", "4", "5"};
    private JLabel lb_tempo;
    private JButton bt_ok;
    private JButton bt_base;

    Janela() {
        new BaseDados();
        setTitle("TESTE");
        setLayout(new GridLayout(1, 2));
        JPanel painelzao = new JPanel();
        painelzao.setLayout(new GridLayout(1, 1, 5, 5));

        p_Esquerdo = new JPanel();

        p_Esquerdo.setLayout(new GridLayout(5, 1));

        listaIdade = new JComboBox(idade);

        listaSalario = new JComboBox(salario);

        listaTempo = new JComboBox(tempo);


        JPanel ptemp = new JPanel();

        ptemp.setLayout(new GridLayout(1, 3, 10, 10));
        ptemp.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        ptemp.add(new JLabel("Idade até "));
        ptemp.add(listaIdade);
        lb_idade = new JLabel("anos");
//        lb_idade.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        ptemp.add(lb_idade);
//        lb_idade.setPreferredSize(new Dimension(1, 2));
        p_Esquerdo.add(ptemp);

        ptemp = new JPanel();
        ptemp.setLayout(new GridLayout(1, 3, 10, 10));
        ptemp.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        ptemp.add(new JLabel("Salário até R$"));
//        lb_salario = new JLabel("R$");
//        lb_salario.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
//        ptemp.add(lb_salario);
        ptemp.add(listaSalario);
        ptemp.add(new JPanel());
        p_Esquerdo.add(ptemp);

        ptemp = new JPanel();
        ptemp.setLayout(new GridLayout(1, 3, 10, 10));
        ptemp.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        ptemp.add(new JLabel("Tempo de Serviço até "));
        ptemp.add(listaTempo);
        lb_tempo = new JLabel("anos");
//        lb_tempo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        ptemp.add(lb_tempo);
        p_Esquerdo.add(ptemp);

        p_Esquerdo.add(new JPanel());

        bt_ok = new JButton("OK");
        ptemp = new JPanel();
        ptemp.setLayout(new GridLayout(1, 1));
        ptemp.setBorder(BorderFactory.createEmptyBorder(5, 70, 5, 70));
        ptemp.add(bt_ok);
//        ptemp.setSize(new Dimension(20, 10));
//        ptemp.setMaximumSize(new Dimension(1, 2));
        p_Esquerdo.add(ptemp);
//        p_Esquerdo.add(bt_ok);

        p_Esquerdo.setBorder(BorderFactory.createEmptyBorder(75, 10, 70, 0));
//        painelzao.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 10));
        painelzao.add(p_Esquerdo);
        getContentPane().add(painelzao);

//       painelzao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        p_Esquerdo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        getContentPane().add(p_Esquerdo);


        painelzao2 = new JPanel();
        painelzao2.setLayout(new GridLayout(2, 1, 10, 10));


        p_Direito = new JPanel(new GridLayout(3, 1, 0, 0));
        texto = new JTextArea();

        p_Direito.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scroll = new JScrollPane(texto);
        p_Direito.add(scroll);
        p_Direito.add(new JPanel());
        p_Direito.add(new JLabel());
        painelzao2.add(p_Direito);

        bt_base = new JButton("BASE DE DADOS");
        ptemp = new JPanel(new GridLayout(1, 1));

        ptemp.setBorder(BorderFactory.createEmptyBorder(100, 70, 50, 70));
        ptemp.add(bt_base);
        painelzao2.add(ptemp);
//        painelzao2.add(bt_base);
//        bt_base.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelzao2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

//        painelzao2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(painelzao2);

//        add(p_Esquerdo, BorderLayout.NORTH);
//        p_Center = new JPanel();
//        add(p_Center, BorderLayout.CENTER);
//        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);
//        add(p_Direito, BorderLayout.SOUTH);

//                invalidate();
//                validate();
//        this.getContentPane().add(panel);

        setSize(900, 450);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        pack();
        listaIdade.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String i = (String) listaIdade.getSelectedItem();
                entrada[0] = i;
                System.out.println("idade:" + entrada[0] + "!!!!");
            }
        });
        listaSalario.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String s = (String) listaSalario.getSelectedItem();
                entrada[1] = s;
                System.out.println("salário:" + (entrada[1]) + "!!!!");
            }
        });
        listaTempo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String t = (String) listaTempo.getSelectedItem();
                entrada[2] = t;
                System.out.println("tempo:" + entrada[2] + "!!!!");
            }
        });
        bt_ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

//                getContentPane().remove(painelzao2);
                painelzao2.remove(p_Direito);

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        painelzao2.revalidate();
                    }
                });

                texto = new JTextArea();
                texto.setEditable(false);

                String temp = "";
                System.out.println("Ok!!!!");
                Main.iniciar(entrada);
                int regras[][] = Main.getRegras();
                int selecionadas[] = Main.getRSelecionadas();

                for (int i = 0; i < selecionadas.length; i++) {
                    temp = temp + "\n" + selecionadas[i] + "...: " + regras[selecionadas[i]][0] + ", " + regras[selecionadas[i]][1] + ", " + regras[selecionadas[i]][2];
                }

                System.out.print("temp..:" + temp);
                temp = "Regras Selecionadas:\n" + temp + "\n";

                texto.setText(temp);



                scroll = new JScrollPane(texto);
                p_Direito = new JPanel(new GridLayout(3, 1, 0, 0));
                p_Direito.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                p_Direito.add(scroll);
                texto = new JTextArea();

                temp = "Regras fuzzyficadas..: ";
                temp += Main.getRInferidas();
                JLabel jtemp = new JLabel(temp);
                jtemp.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                p_Direito.add(jtemp);

                temp = "\tSaída Defuzzificada do Limite de Crédito..: R$ ";
                temp += Main.getDef();
                texto.setText(temp);
                jtemp = new JLabel(temp);
//                jtemp.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
//                JPanel jp = new JPanel(new GridLayout(1,1));
//                jp.add(jtemp);
//                jp.setBorder(BorderFactory.createEmptyBorder(0, 5, 15, 5));
//                jtemp.setBorder(BorderFactory.createEmptyBorder(0, 5, 15, 5));
//                p_Direito.add(jtemp);
                jtemp.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

                p_Direito.add(jtemp);

////////                bt_base = new JButton("BASE DE DADOS");
////////                p_Direito.add(bt_base);
////////                painelzao2 = new JPanel(new GridLayout(1, 1));
////////                painelzao2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
////////
                painelzao2.add(p_Direito, 0);
//                getContentPane().add(painelzao2);

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        validate();
//                        p_Direito.revalidate();
//                        painelzao2.revalidate();
                    }
                });
            }
        });
        bt_base.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Base de Dados");

            }
        });
//

    }

    public String[] getEntrada() {
        return entrada;
    }

    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

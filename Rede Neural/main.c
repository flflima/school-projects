#include<stdio.h>
#include<stdlib.h>
#include "funcoes.h"

int main(){
    int entradas[120][8], saida[8][8], i, j, t, redeTreinada, padrao;
    float pesos[120][8], erro[8][8], bias[8], erroTotal, y[8], alfa;
    
    carregaEntradas(entradas);
    carregaSaidas(saida);
    carregaPesos(pesos);
    carregaBias(bias);
    /*
    for (i = 0; i < 8; i++){
        for (j = 0; j < 120; j++)
            printf("%.1f\t",pesos[i][j]);
            printf("\n");}
            system("PAUSE");
        */
    alfa = 0.1;
    redeTreinada = 0;
    
    int op;	
    do {
            printf("\n\t=========================================================\n");       
                 
            printf("\t!\tEscolha uma opcao:\t\t\t\t!\n");         
                      
            printf("\t!\t\t\t\t\t\t\t!\n");        
            
            printf("\t!\t[1] Treinar a Rede\t\t\t\t!\n");   
                   
            printf("\t!\t\t\t\t\t\t\t!\n");        
            
            printf("\t!\t[2] Sujar os Bits de todos os padroes\t\t!\n"); 
                   
            printf("\t!\t\t\t\t\t\t\t!\n");        
            
            printf("\t!\t[0] Sair\t\t\t\t\t!\n");           
                   
          //  printf("\t!\t\t\t\t\t\t\t!\n");        
            
           // printf("\t!\t[] Testar um caracter\t\t\t\t!\n");      
            
            printf("\t!\t\t\t\t\t\t\t!\n");     
            
            printf("\t=========================================================\n"); 
            
            printf("\n\tOpcao: ");
            scanf("%d",&op); 
            
            printf("\t\t\t\t\t\t\t\n");
            printf("\n");             
            switch(op){     
                   case 0 : 
                        exit(0);
                        break;              
                   case 1:  
                        
                        treinaRede(&erroTotal, entradas, saida,pesos,bias,alfa);
                                           
                        if (!erroTotal){
                           printf("\n\tA Rede foi treinada com sucesso !!\n\n");
                           redeTreinada = 1;
                        }
                           break;
                           
                   case 2:
                        if (redeTreinada) {
                                
                                if (testarRede(entradas, saida,pesos,bias))
                                   printf ("A rede reconheceu todos os padroes de entrada\n");
                                else
                                   printf ("A rede nao reconheceu todos os padroes de entrada\n");
                                   
                        } else {
                               printf ("A rede ainda nao foi treinada\n\n");
                               continue;
                        }                
                          break;
            default:
                    printf("A opcao informada nao existe!\n");
                    break;
            }           
    }while(op > 0);
    
                  
    system("pause");
    return 0;
}

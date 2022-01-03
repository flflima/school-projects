#include<math.h>
#include<time.h>

void carregaEntradas(int padroes[][8]){
    FILE *cfPtr;
    int i,j;
    
    if ((cfPtr = fopen("entrada.txt","r")) == NULL) {
               printf("Arquivo nao encontrado\n");
               exit(0);
    }
    
    for (i=0; i<120;i++){
        for(j=0;j<8;j++){ 
           fscanf(cfPtr,"%d",&padroes[i][j]);
        }
    } 
}

void imprimeEntradas (int padroes[][8]) {   
     int i, j;        
    for (i=0; i<8;i++){
             for(j=0;j<120;j++){ 
                 if (j % 10 == 0){
                       printf("\n");
                 } 
                 if (padroes[j][i] > 0){
					   printf("@");
        			}else{
					   printf(" ");
                 }  
             }
             printf("\n");
    }
}

void imprimeCaracter (int padroes[][8] , int j) {
     int i;          
             for(i=0;i<120;i++){ 
                 if (i % 10 == 0){
                       printf("\n");
                 } 
                 if (padroes[i][j] > 0){
					   printf("@");
        			}else{
					   printf(" ");
                 }  
             }
             printf("\n");
}

void carregaSaidas(int padroes[][8]){
    FILE *cfPtr;
    int i,j;
    
    if ((cfPtr = fopen("saida.txt","r")) == NULL) {
               printf("Arquivo nao encontrado\n");
               exit(0);
    } 
    
    for (i = 0; i < 8; i++){
        for(j = 0;j < 8; j++){ 
           fscanf(cfPtr,"%d",&padroes[i][j]);
        }
    } 
}

void carregaPesos(float pesos[][8]){
     int i, j;
     
     for(i = 0; i < 120; i++) {
           for(j = 0; j < 8; j++)
                 pesos[i][j] = 0.0; 
     }  
}

void carregaBias(float bias[8]){
     int i;
     
     for(i = 0; i < 8; i++)
                 bias[i] = 0.0;   
}

float calculaFuncao(int entradas[120][8], float pesos[120][8], float bias[8], int i, int j){
      // i é a coluna da entrada
      // j é a coluna da matriz de pesos, onde cada j é um neurônio
      float acumulador = 0.0;
      // linhas
      int k;
      
      for (k = 0; k < 120; k++)
          acumulador += entradas[k][i] * pesos[k][j];    
      
      acumulador += bias[j];
      
      return acumulador;       
}

void calculaErro(float erro[][8], int saida[][8], int j, float y[8]){
     // calcula o erro de cada neuronio para a entrada j
     // j é a posição da entrada que eu estou trabalhando
     // i representa cada um dos meus neurônios
     
      int i;
      
      for (i = 0; i < 8; i++)
          erro[i][j] = saida[i][j] - y[i];
      
}

void calculaErroTotal(float *erroTotal, float erro[][8], int j){
     // j é o indice das entradas
     
      int i;
      
      for (i = 0; i < 8; i++)         
        *erroTotal += abs(erro[i][j]);
              
}

void atualizaPesos(float pesos[][8], float alfa, float erro[][8], int entradas[][8], int i, int j){     
     // nessa função i representa o neurônio que terá seus pesos alterados;
     // já j representa a coluna referente a entrada
     
      int k;
      
      for (k = 0; k < 120; k++)          {
        //  printf("peso[%d][%d] = %.1f \n",k,i,pesos[k][i]);
        //  printf("entradas[%d][%d] = %d\n",k,j,entradas[k][j]);  
        //  printf("erro[%d][%d] = %f\n",i,j,erro[i][j]);
        // printf("alfa = %f\n\n",alfa);
          pesos[k][i] += (alfa * erro[i][j] * entradas[k][j]);  
       //  printf("peso[%d][%d] = %f\n\n",k,i,pesos[k][i]);
        // system("pause");
          }
      
}

void corrigeBias(float bias[], float alfa, float erro[][8], int i){
     // i é o indice de entradas
     
      int j;
      
      for (j = 0; j < 8; j++)          
          bias[j] += (alfa * erro[j][i]);    
      
}

void treinaRede (float *erroTotal, int entradas[][8], int saida[][8], float pesos[][8], float bias[], float alfa){
	float y[8], erro[8][8];
	int i,j,t;
			t = 0; 
		do {
		// enquanto for encontrado um erro em algum neurônio

			*erroTotal = 0.0;
            printf("\nIteracao %d: \n", t);
            
			for ( i = 0; i < 8; i++) {
			// i é cada posição de entrada
			//percorrer cada entrada


				for ( j = 0; j < 8; j++) {
				// percorrer cada neurônio

					if (calculaFuncao(entradas, pesos, bias, i, j) > 0) {
						y[j] = 1;
					} else {
						y[j] = -1;
					}                              
				}              

				calculaErro(erro, saida, i, y);  

				// calcula o erro total
				calculaErroTotal(&(*erroTotal), erro, i);


				printf("Entrada %d:  \n",i);
				int m;

				for(m=0; m<8; m++)
					printf("erro[%d][%d] = %.1f\n",m,i,erro[m][i]);
				printf("\n\n");
				
				for(m=0; m<8; m++)
					printf("y[%d] = %.1f\n",m,y[m]);
				printf("\n\n");


				// se o erro total for maior que zero 
				// atualiza os neuronios com erro
				if (erroTotal > 0) {
					int k;

					for (k = 0; k < 8; k++) {
					// cada k é o neuronio com erro
						if (abs(erro[k][i]) > 0) 
							atualizaPesos(pesos, alfa, erro, entradas, k, i);
					}
				}
				corrigeBias(bias, alfa, erro, i);

				// system("pause");


				printf("\n");
			}
  /*  for (i = 0; i < 8; i++){
        
            printf("%f\n",bias[i]);
            printf("\n");}
            system("PAUSE");*/

			t++;                            
			printf("erroTotal = %.4f\n",*erroTotal);

		}  while (*erroTotal > 0);     
}

int testarRede (int entrada[120][8], int saida[8][8], float pesos[120][8], float bias[8]) {
	int totalBits = 12 * 10;
	int porcentagem;
	int i, j;
	float y[8], erro[8][8], erroTotal = 0.0;
	int novaEntrada[120][8];  
	int m, n;

	srand(time(NULL));
	printf ("Informe a porcentagem (%%) de erro desejada (0 - 100) para cada caracter: ");
	scanf("%d",&porcentagem);
	printf("Porcentagem = %d %%\n",porcentagem);
	totalBits *= porcentagem;
	totalBits /= 100;

	printf("Bits = %d \n",totalBits);

	//system("PAUSE");

	// sequência de bits que serão substituídos
	// em cada um dos oito padrões 
	int bits[totalBits][8];

	for (m = 0; m < 8; m++) {
		for (n = 0; n < 120; n++)
			novaEntrada[n][m] = entrada[n][m];
		for (n = 0; n < totalBits; n++)
            bits[n][m] = 0;	
   }

	for (m = 0; m < 8; m++) {
		for (n = 0; n < totalBits; n++) {
			int k, num, repetido = 0, posicao;                   
			num = rand()%120;
			for (k = 0; k <= n-1; k++) { 
			// se o número escolhido for igual a algum já escolhido
				if (bits[k][m] == num) {  
					repetido = 1;
					posicao = k;
					break;
				}                         
			}           
			if (repetido) {
				while (bits[posicao][m] == num)                 
					num = rand()%120; 
			}
			
			bits[n][m] = num; 
			printf("%6d",bits[n][m]);
		}
		printf("\n");
	}

	for (m = 0; m < 8; m++) {
		for (n = 0; n < totalBits; n++) {
			if (novaEntrada[bits[n][m]][m] == 1) 
				novaEntrada[bits[n][m]][m] = -1;                   
			else
				novaEntrada[bits[n][m]][m] = 1;              
		}
	}
/*
	 for (m=0; m<120;m++){
	for(n=0;n<8;n++){
	printf("%d\t",novaEntrada[m][n]);
	}
	printf("\n");
	}     
*/
	imprimeEntradas(novaEntrada);
	printf("\n");

	for ( i = 0; i < 8; i++) {
	// i é cada posição de entrada
	// percorrer cada entrada

		for ( j = 0; j < 8; j++) {
		// percorrer cada neurônio

			if (calculaFuncao(novaEntrada, pesos, bias, i, j) > 0) {
			   y[j] = 1;
			} else {
			  y[j] = -1;
			} 
		}
		calculaErro(erro, saida, i, y); 
		calculaErroTotal(&erroTotal, erro, i);
	}   
	
    float acumulador = 0;
    
	if (erroTotal > 0) {
    	for (j = 0; j < 8; j++) {
        	for (i = 0; i < 8; i++) {
        	    //printf("%.4f\t",erro[i][j]);
        	   acumulador += abs(erro[i][j]);
            }
        	//      printf("\nAcumulador %f\n",acumulador);
        	   if (acumulador > 0)
        	      printf("\nNao reconheceu o padrao %d\n",j);
            acumulador = 0;
        //	printf("\n");
    	}
    	printf("\n");
//    	printf("\n%f\n",erroTotal);
    	return 0;
	}
	else 
	     return 1;         
} 

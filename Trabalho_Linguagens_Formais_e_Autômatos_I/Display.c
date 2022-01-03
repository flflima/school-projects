/***********************************************************************************
 *																				   *
 *  UniSantos - Universidade Cat�lica de Santos									   *					
 *  Nomes: Felipe Lima Freire													   *
 *         Leandro da Silva Mattos												   *
 *         Paulo da Silva Santos											       *
 *  Curso: Ci�ncia da Computa��o												   *
 *  Professor: Jos� Fontebasso Neto 											   *
 *  Disciplina: Linguagens Formais e Aut�matos I 								   *
 *  																			   *
 *  ENUNCIADO:																	   *
 *            4. Implemente o aut�mato finito elaborado na quest�o 2 e minimizado  *
 *  na quest�o 3 em um programa em C ou Java, o qual deve rodar a partir da linha  *
 *  de comando, receber uma sequ�ncia via teclado e informar se a sequ�ncia 	   *
 *  fornecida �, ou n�o, correspondente a um d�gito octal.					       *	
 *																				   *
 ***********************************************************************************/

#include <stdio.h>
#define LEDS 7 // Total de leds que ser�o acesos na seguinte condi��o:
               // 0(l�gico) -> Led apagado;
               // 1(l�gico) -> Led aceso.
int main(){
    int sequencia[LEDS]; // vetor que cont�m a sequ�ncia de 0's e 1's;
    char continua = 'S'; // flag para continuar o la�o;
	int posicao; // a vari�vel "posicao" ir� representar cada LED, por exemplo:
                 // * posicao = 0 -> LED 'A', posicao = 1 -> LED 'B', e assim 
                 // diante;
	int sequencia_inicial; // vari�vel que armazena a sequ�ncia digitada via 
                           // teclado;
	int divisor = 1; // vari�vel que auxilia a atribuir a cada posi��o do vetor 
                     // um valor (0 ou 1), e seu valor � fixo durante a execu��o
                     // do programa;
    int novo_divisor; // var�vel que tem a mesma fun��o de "divisor", por�m seu 
                      // valor muda durante a execu��o do programa;	
	int i;
	
	for(i = 0; i < LEDS; i++) // "divisor" recebe o valor 10^(LEDS);
        divisor *= 10;    
        
	divisor /= 10;
	novo_divisor = divisor;
	
	while(continua == 'S' || continua == 's'){ // la�o que permanece verdadeiro 
	                                           // enquanto o usu�rio digitar 'S' ou 's';
	
	    printf("Informe uma sequencia de oito digitos('0' ou '1'): ");           
		scanf("%d",&sequencia_inicial);
		
		for(i = 0; i < LEDS; i++){ // atribui a cada posi��o do vetor "sequencia" 
                                   // um valor: '0' ou '1'; 
			sequencia[i] = sequencia_inicial/novo_divisor;
			sequencia_inicial %= novo_divisor;
			novo_divisor /= 10;
		}
		
		novo_divisor = divisor;
		posicao = 0;
		
		switch(sequencia[posicao]){ // A
			case 0:
				posicao++;
				puts("A recebeu '0' e vai para B;");
				switch(sequencia[posicao]){ // B
					case 0:
						puts("B recebeu '0':");
						puts("Erro.\n");
						break;
					case 1:
						posicao++;
						puts("B recebeu '1' e vai para D;");
						switch(sequencia[posicao]){ // D
							case 0:
								puts("D recebeu '0':");
								puts("Erro.\n");
								break;
							case 1:
								posicao++;
								puts("D recebeu '1' e vai para G;");
								switch(sequencia[posicao]){ // G
									case 0:
									    posicao++;
										puts("G recebeu '0' e vai para K;");
										switch(sequencia[posicao]){ // K
											case 0:
												posicao++;
												puts("K recebeu '0' e vai para P;");
												switch(sequencia[posicao]){ // P
													case 0:
														posicao++;
														puts("P recebeu '0' e vai para U;");
														switch(sequencia[posicao]){ // U
															case 0:
																posicao++;
																puts("U recebeu '0' e vai para X.");
																break;
															case 1:
																puts("U recebeu '1':");
																puts("Erro.\n");
																break;
														}
														break;
													case 1:
														posicao++;
														puts("P recebeu '1' e vai para V;");
														switch(sequencia[posicao]){ // V
															case 0:
																puts("V recebeu '0':");
																puts("Erro.\n");
																break;
															case 1:
																posicao++;
															    puts("V recebeu '1' e vai para X.");
																break;
														}
														break;
												}
												break;
											case 1:
												puts("K recebeu '1':");
												puts("Erro.\n");
												break;
										}
										break;
									case 1:
										puts("G recebeu '1':");
										puts("Erro.\n");
										break;
								}
								break;
						}
						break;
				}
				break;
			case 1:
				posicao++;
				puts("A recebeu '1' e vai para C;");
				switch(sequencia[posicao]){ // C
					case 0:
						posicao++;
						puts("C recebeu '0' e vai para E;");
						switch(sequencia[posicao]){ // E
							case 0:
								puts("E recebeu '0':");
								puts("Erro.\n");
								break;
							case 1:
								posicao++;
								puts("E recebeu '1' e vai para H;");
								switch(sequencia[posicao]){ // H
									case 0:
										puts("H recebeu '0':");
										puts("Erro.\n");
										break;
									case 1:
										posicao++;
										puts("H recebeu '1' e vai para L;");
										switch(sequencia[posicao]){ // L
											case 0:
												posicao++;
												puts("L recebeu '0' e vai para Q;");
												switch(sequencia[posicao]){ // Q
													case 0:
														puts("Q recebeu '0':");
														puts("Erro.\n");
														break;
													case 1:
														posicao++;
														puts("Q recebeu '1' e vai para V;");
														switch(sequencia[posicao]){ // V
															case 0:
																puts("V recebeu '0':");
																puts("Erro.\n");
																break;
															case 1:
																posicao++;
															    puts("V recebeu '1' e vai para X.");
																break;
														}
														break;
												}
												break;
											case 1:
												posicao++;
												puts("L recebeu '1' e vai para Q;");
												switch(sequencia[posicao]){ // Q
													case 0:
														puts("Q recebeu '0':");
														puts("Erro.\n");
														break;
													case 1:
														posicao++;
														puts("Q recebeu '1' e vai para V;");
														switch(sequencia[posicao]){ // V
															case 0:
																puts("V recebeu '0':");
																puts("Erro.\n");
																break;
															case 1:
																posicao++;
															    puts("V recebeu '1' e vai para X.");
																break;
														}
														break;
												}
												break;
										}
										break;
								}
								break;
						}
						break;
					case 1:
						posicao++;
						puts("C recebeu '1' e vai para F;");
						switch(sequencia[posicao]){ // F
							case 0:
								posicao++;
								puts("F recebeu '0' e vai para I;");
								switch(sequencia[posicao]){ // I
									case 0:
										puts("I recebeu '0':");
										puts("Erro.\n");									
										break;
									case 1:
										posicao++;
										puts("I recebeu '1' e vai para M;");
										switch(sequencia[posicao]){ // M
											case 0:
												puts("M recebeu '0':");
												puts("Erro.\n");	
												break;
											case 1:
												posicao++;
												puts("M recebeu '1' e vai para R;");
												switch(sequencia[posicao]){ // R
													case 0:
														posicao++;
														puts("R recebeu '0' e vai para V;");
														switch(sequencia[posicao]){ // V
															case 0:
																puts("V recebeu '0':");
																puts("Erro.\n");
																break;
															case 1:
																posicao++;
															    puts("V recebeu '1' e vai para X.");
																break;
														}
														break;
													case 1:
														puts("R recebeu '1':");
														puts("Erro.\n");	
														break;
												}
												break;
										}
										break;
								}
								break;
							case 1:
								posicao++;
								puts("F recebeu '1' e vai para J;");
								switch(sequencia[posicao]){ // J
									case 0:
										posicao++;
										puts("J recebeu '0' e vai para N;");
										switch(sequencia[posicao]){ // N
											case 0:
												posicao++;
												puts("N recebeu '0' e vai para S;");
												switch(sequencia[posicao]){// S
													case 0:
														posicao++;
														puts("S recebeu '0' e vai para U;");
														switch(sequencia[posicao]){// U
															case 0:
																posicao++;
																puts("U recebeu '0' e vai para X.");
																break;
															case 1:
																puts("U recebeu '1':");
																puts("Erro.\n");
																break;
														}
														break;
													case 1:
														puts("S recebeu '1':");
														puts("Erro.\n");
														break;
												}
												break;
											case 1:
												puts("N recebeu '0':");
												puts("Erro.\n");	
												break;
										}
										break;
									case 1:
										posicao++;
										puts("J recebeu '1' e vai para O;");
										switch(sequencia[posicao]){ // O
											case 0:
												posicao++;
												puts("O recebeu '0' e vai para R;");
												switch(sequencia[posicao]){ // R
													case 0:
														posicao++;
														puts("R recebeu '0' e vai para V;");
														switch(sequencia[posicao]){ // V
															case 0:
																puts("V recebeu '0':");
																puts("Erro.\n");
																break;
															case 1:
																posicao++;
															    puts("V recebeu '1' e vai para X.");
																break;
														}
														break;
													case 1:
														puts("R recebeu '1':");
														puts("Erro.\n");	
														break;
												}
												break;
											case 1:
												posicao++;
												puts("O recebeu '1' e vai para T;");
												switch(sequencia[posicao]){ // T
													case 0:
														puts("T recebeu '0':");
														puts("Erro.\n");	
														break;
													case 1:
														posicao++;
													    puts("T recebeu '1' e vai para U;");
														switch(sequencia[posicao]){ // U
															case 0:
																posicao++;
																puts("U recebeu '0' e vai para X.");
																break;
															case 1:
																puts("U recebeu '1':");
																puts("Erro.\n");
																break;
														}
														break;
												}
												break;
										}
										break;
								}
								break;
						}
						break;
				}
				break;
		}
		
		if(posicao == 7){ // se foram percorridas todas as posi��es do vetor, ent�o 
		                  // a sequ�ncia � valida;
		   puts("");               
           puts("A sequencia corresponde a um digito octal!\n");
        }
        else   
		   puts("A sequencia nao corresponde a um digito octal!\n");
           		   
		printf("Continua?(S/N) ");		
		scanf(" %c",&continua);		
		system("cls");
	}
	
    return 0;
}

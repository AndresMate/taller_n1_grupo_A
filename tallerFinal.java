package taller_n1_grupo_A;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class tallerFinal {
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int op=0;
		int input=0;
		String inputSt="";
		
		while(op!=7) { 
			op=0; // Control del menú
			System.out.println("------ Taller Colaborativo ------");
			System.out.println("1. Número Romano");
			System.out.println("2. Factores Primos");
			System.out.println("3. Borrar Espacios");
			System.out.println("4. Números Ególatras");
			System.out.println("5. Número Mágico");
			System.out.println("6. Fechas");
			System.out.println("7. Salir");
			try {
				op=sc.nextInt();
			}catch(InputMismatchException e) {
				sc.nextLine();
			}
			
			try {
				switch(op) {
				case 1:
					sc.nextLine();
					System.out.println("Ingrese un número romano (limite por simbología 3999)");
					inputSt=sc.nextLine();
					
					if(romanNumber(inputSt.toUpperCase())==0){
						System.out.println("null");
					}else {
						System.out.println("Su valor en decimal es: " + romanNumber(inputSt.toUpperCase()));
					}
					break;
				case 2:
					input=0; nfactores=0; output=""; factores=false;
					System.out.println("Ingrese el número a descomponer");
					input=sc.nextInt();
					if(input>=1) {
						desmontarFactor(input);
						System.out.println(getOutput());
					}else {
						System.out.println("-- Valor invalido! --");
					}
		
					break;
				case 3:
					sc.nextLine();
					System.out.println("Ingrese una cadena de texto");
					System.out.println(borrarEspacios(sc.nextLine()));
				    break;
				case 4:
					System.out.println("Ingrese un número");
					input=sc.nextInt();
					
					if(numeroEgolatra(input)) {
						System.out.println("Es un número ególatra");
					}else {
						System.out.println("No es un número ególatra");
					}
					break;
				case 5: 
					System.out.println("Ingrese un número");	
					input=sc.nextInt();
					
					if(numeroMagico(input)) {
						System.out.println("Es un número magico");
					}else {
						System.out.println("No es un número magico");
					}
					break;
				case 6:
					sc.nextLine();
					System.out.println("Ingrese una fecha válida (verifique en el calendario)");
					System.out.println(fechas(sc.nextLine()) );
					break;
				case 7:
					System.out.println("-- Fin Algoritmo --");
					break;
				default:
					System.out.println("-- Valor invalido! --");
					break;   	
				}
			
			}catch(InputMismatchException e) {
				System.out.println("-- Valor invalido! --");
				sc.nextLine(); 
			}
			System.out.println("\n");
		}
	}
    
    
	//---------------EJERCICIO N.1-----------------
    
	public static int DecimalValue(char cRoman) {
		switch (cRoman) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
        }  
    }
      
	public static int romanNumber(String input) {
		int decimal = 0;
        int anterior = 0;
        boolean control=false;
        int [] aux4 = {0,0,0,0};
        int [] aux5 = {0,0,0};
        int aux=1;
        int numResto=0;
        for(int i=input.length()-1; i>=0; i--) {  
        	int actual = DecimalValue(input.charAt(i));
            
            if(actual==0) {
              control=true;
            }else {
            	
            	if(actual<anterior) {
              
	              //Los símbolos de base 5 siempre suman, y no pueden estar a la izquierda de uno de mayor valor.
	              if(actual/5==1 || actual/5==10 || actual/5==100){
	                control=true;
	              }
	              //I sólo puede restar a V y a X. - X sólo resta a L y a C - C sólo resta a D y a M.
	              if(anterior!=actual*5 && anterior!=actual*10 && (actual==1 || actual==10 || actual==100) ){
	                control=true;
	              }
	              
	              //CONTROL LOGICA
	              if(anterior == decimal/2) {
	            	  control=true;
	              }
	              
	              //Si un símbolo de tipo 1 que aparece restando se repite solo si su repetición esté colocada a su derecha.
	              if(numResto == actual) {
	            	  control=true;
	              }
	              numResto = actual; 
	              //
	              
	              decimal -= actual;   		
	            }else{
	              
	              if(actual==anterior) {
	                //(I, X, C y M) pueden repetirse hasta 3 veces consecutivas como sumandos.    			
	                for(int j=0; j<aux4.length; j++) {
	                  if(actual == 1*aux) {
	                    aux4[j]+=1;
	                  }
	                  aux*=10;
	                }
	                aux=1;
	                
	                //CONTROL LOGICA
	                if(numResto == actual) {
	              	  control=true;
	                }
	                
	              }
	              //Los números con base 5 (V, L y D), no pueden repetirse  			
	              for(int j=0; j<aux5.length; j++) {
	                if(actual== 5*aux ) {
	                	aux5[j]+=1;
	                }
	                aux*=10;	
	              }
	              aux=1;
	              
	              //
	              decimal += actual;
	            }
	            //
	            anterior=actual;    	
            
            }
        
        }//salida for principal
        
        if(aux4[0]>=3 || aux4[1]>=3 || aux4[2]>=3 || aux4[3]>=3 || aux5[0]>=2 || aux5[1]>=2 || aux5[2]>=2) {
        	control=true;
        }
        if(control) {
          decimal=0;
        }
        //
    return decimal;
    }	

	
	
	//---------------EJERCICIO N.2-----------------
	static int nfactores= 0;
	static boolean factores= false;
	static String output="";		
	
	public static void desmontarFactor(int number) {
		int producto= 1;
	    int number1 = number;
	    int factorp= 2;
	    do {
	        int subfactor = number1 % factorp;
	        if (subfactor == 0) {
	            number1 /= factorp;
	            producto*= factorp;
	            nfactores++;
	        } else {
	            if (nfactores> 0) {
	            	ingFactor(factorp);
	            }
	            factorp = primo(factorp);
	        }
	    } while (producto != number);
	    ingFactor(factorp);
	}
			
	public static int primo(int number) {
	    do {
	        number++;
	    } while (!si_primo(number));
	    return number;
	}
			
	public static boolean si_primo(int number) {
	    if(number <= 0) {
	        return false;
	    }
	    int cant_divisores = 0;
	    boolean divisores = false;
	    int rango = (int) sqrt(number);
	    int i = 2;
	    while (i<=rango && !divisores) {
	        if(number % i == 0) {
	            cant_divisores++;
	            divisores = true;
	        }
	        i++;
	    }
	    if(cant_divisores>0 || number==1) {
	        return false;
	    }
	    return true;  
	}
			
	private static int sqrt(int number) {
	 return 0;
	}
			
	public static void ingFactor(int factorp) {
	    String text="";
	    if(factores) {
	    	text +=" x ";
	    } else {
	        factores= true;
	    }
	    if(nfactores== 1) {
	    	text += String.valueOf(factorp);
	      
	    }else {
	    	text += String.valueOf(String.valueOf(factorp)+ "^"+String.valueOf(nfactores));
	    }
	    nfactores= 0;
	    output+=text;
	}
	
	public static String getOutput() {
		return output;
	}
	
	

    
    //---------------EJERCICIO N.3-----------------	
	
    public static String borrarEspacios (String input){
    	String cadena = input.replaceAll("\\s+", " ");
		cadena = cadena.trim();	 
		return cadena;
    }
	
	
  //---------------EJERCICIO N.4-----------------
    
    private static boolean numeroEgolatra(int num) {
    	boolean egolatra=true;
    	int cont=0;
	    double p=0;
	    double r=0;
	    double ra=0;
	    double x=0;
	    x=num;
	    p=num;
	    
	    while(Math.floor(x)!=0){
	      x=x/10;
	      cont++;
	    }
	    while(Math.floor(num)!=0){
	      r=Math.floor(num)%10;
	      ra=ra+(Math.pow(Math.floor(r), cont));
	      num=num/10;
	    }
	    
	    if(p==ra){
	    	egolatra=true;
	    }else {
	    	egolatra=false;
	    }
	    return egolatra;  
    }    
    

    

  //---------------EJERCICIO N.5-----------------
    
    public static boolean numeroMagico(int number) {
		int number1=number;
		int cont=0;
		int inverted = 0;
		boolean condicion=true;
		
		while(number > 0) {
			inverted = inverted * 10 + number % 10;
			number /= 10;
			cont++;
		}
 
		int[] digits = new int[cont];
		for(int i = 0; i < cont ; i++) {
			digits[i] = inverted % 10;
			inverted /= 10;
		}
		
		Arrays.sort(digits);
		int descending = 0;
		for(int i = cont-1; i >= 0; i--) {
			descending = descending * 10 + digits[i];
		}
 
		int ascending = 0;
		for(int i = 0; i < cont; i++) {
			ascending = ascending * 10 + digits[i];
		}
 
		if(number1 == descending-ascending) {
			condicion=true;
		}else {
			condicion=false;
		}
		return condicion;
	}
 
    
    
  //---------------EJERCICIO N.6-----------------
    
    public static String fechas (String input) {
    	String text="";
    	String aux[] = new String[3];
    	aux[0]=""; aux[1]="";aux[2]="";  
    	int aux2[] = new int[3];
    	int cont=0;
    	boolean control=true;
    	String mes[]= {"enero","febrero","marzo","abril","mayo","junio","julio",
    			"agosto","septiembre","octubre","noviembre","diciembre"};
    	
    	//Control - Ingreso incorrecto del numero de '/' 
    	try {
	    	for(int i=0; i<input.length(); i++) {
	    		if(input.charAt(i)!='/') {
	    			aux[cont]+=input.charAt(i);
	    		}else {
	    			cont++;
	    		}	
	    	}
    	}catch(ArrayIndexOutOfBoundsException e) {
    		control=false;
    	}
    	
    	if(cont!=2) {
    		control=false;
    	}
    	
    	//Control - Ingreso incorrecto de un número para el valor de los dias-meses-años
    	try {
	    	for(int i=0; i<3; i++) {
	    		aux2[i]=Integer.parseInt(aux[i]);
	    		
	    		if(aux2[i]==0 || aux2[i]<0) {
	    			control=false;
	    		}
	    	}
    	} catch(NumberFormatException e) {
    		control=false;
	    }
    	
    	//Control -valor incorrecto meses 
    	if(aux2[1]<1 || aux2[1]>12) {
    		control=false;
    	}
    	
    	//Control -valor incorrecto dias
    	if(control) {
    		
    		//control para febrero - años bisiestos
    		if(aux2[1]==2) {
    			if(aux2[0]>29) {
    				control=false;
    			}
    			if(aux2[0]==29 && aux2[2]%4!=0) {
    				control=false;
    			}
    		}
    		
    		//control de meses de max. 30 dias
    		if((aux2[1]==4 || aux2[1]==6 || aux2[1]==9 || aux2[1]==11) && aux2[0]>30 ){
    			control=false;
    		}else {
    			//control de meses max. 31 dias
    			if(aux2[0]>31) {
    				control=false;
    			}
    		}
    	}
    	
    	if(control) {
    		text = aux2[0] +" de " + mes[aux2[1]-1] + " de "+ aux2[2];
    	}else {
    		text= null;
    	}
    	
		return text;
     }
     
  }
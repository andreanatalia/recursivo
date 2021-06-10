
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		while(true)
		{
			Scanner scanner = new Scanner(System.in);  
			Parser parser = new Parser();
	
			String cadena; 
	       
	        System.out.print("Introduzca una cadena: ");       
	        cadena = scanner.nextLine();
	        String nuevo = " " + cadena + " ";
	        parser.entrada = parser.quitarEspacios(nuevo).toCharArray();
	        parser.noTerminalE();
	        
	        if(parser.error.equals(""))
	        {
	        	System.out.println("Parsing success!");
	        }
	        else 
	        {
	        	System.out.println("Parsing failed");
	        }
		}
	}

}

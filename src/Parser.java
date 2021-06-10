
import java.lang.reflect.Array;

public class Parser {

	int cont = 0;
	char[] entrada;
	char caracter;
	String error = "";
	boolean parentesis = false;

	public void noTerminalE() {
		caracter = entrada[cont];
		if (validarStrings(caracter)) {
			error += "error at " + cont + " character "+ caracter + "\n";
			System.out.println(error);	

		} else {
			noTerminalT();
			noTerminalEPrima();
		}
	}
	
	public void noTerminalT() {
		noTerminalF();
		noTerminalTPrima();
	}

	public void noTerminalTPrima() {
		if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
			match(caracter);
			noTerminalF();
			noTerminalTPrima();
		} else if (caracter == '&'/* LAMBDA */) {
			match(caracter);
		}

	}

	public void noTerminalF() {
		if (caracter == '(') {
			match('(');
			noTerminalE();
			parentesis = true;
		} else if (caracter == ')' && parentesis) {
			match(')');
			parentesis = false;
		} else {
			noTerminalG();
		} 
		if(caracter == ')' && !parentesis) {
			error += "error at " + cont + " character "+ caracter + "\n";
			System.out.println(error);	
		}
		if(Character.isLetter(caracter)) {
			if(caracter != 'I' || caracter != '&' || caracter != '+' || caracter != '-' || caracter != '*' || caracter != '/') {
				error += "error at " + cont + " character "+ caracter + "\n";
				System.out.println(error);	
			}
		}

	}

	public void noTerminalG() {
		if (Character.isDigit(caracter)) {
			while (Character.isDigit(caracter)) {
				match(caracter);
			}
		} else if (caracter == 'I') {
			match('I');
		} else if (caracter == '&' ) {
			match('&');		
		} else if (!Character.isDigit(caracter) || caracter != 'I' ) {	
			match('@');	
		}

	}

	public void noTerminalEPrima() {
		if (caracter == '+' || caracter == '-') {
			match(caracter);
			noTerminalT();
			noTerminalEPrima();
		} else if (caracter == '&'/* LAMBDA */) {
			match(caracter);
		}
		
	}

	public void finalizar() {
		noTerminalF();
		while (caracter == '(' || Character.isDigit(caracter)) {
			noTerminalF();
		}

	}

	public void match(char t) {
		if (caracter == t) {
			cont++;
			while (entrada[cont] == '&') {
				cont++;
			}
			caracter = Array.getChar(entrada, cont);
		} else {
			error += "error at " + cont + " character "+ caracter + "\n";
			System.out.println(error);	
		}
	}

	public String quitarEspacios(String cadena) {
		verificarSubstring(cadena);
		return cadena.replaceAll(" ", "&");
	}

	public void verificarSubstring(String cadena) {
		if (cadena.contains("id")) {
			cadena.replaceAll("id", "I");
		}
	}

	public boolean validarStrings(char s) {
		int i = (int) s;
		if (i >= 97 && i <= 122)
			return true;
		else
			return false;
	}
}

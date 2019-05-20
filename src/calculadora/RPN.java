package calculadora;

/*
Objetivo: Obtener código refactorizado a partir de otro que no lo está.
Tarea: Para esta tarea, se refactorizará un programa mal escrito, sin cambiar la forma en que funciona el programa. Este programa, RPN.java es una calculadora de notación inversa polaca que utiliza una pila.
Reverse Polish notation (RPN) Notación Polaca inversa, por ejemplo
la expresión: 4 * 5 - 7 / 2 % 3 nos da 1,5 respetando la prioridad de
los operadores en notación RPN seria: 4 5 * 7 2 / - 3 % (pues no podemos poner los paréntesis para alterar la prioridad)
Se debe reorganizar este código usando al menos tres de las reglas
vistas en clase.
*/
class NodoPila {
	public NodoPila abajo;
	public double dato;
	
	public NodoPila(double dato, NodoPila abajo) {
		this.dato = dato;
		this.abajo = abajo;
	}
}

public class RPN {
	private String commando;
	private NodoPila arriba;
	
	public RPN(String commando) {
		arriba = null;
		this.commando = commando;
	}
	
	public void pushPila(double nuevo_dato) {
		NodoPila nuevo_nodo = new NodoPila(nuevo_dato, arriba);
		arriba = nuevo_nodo;
	}

	public double popPila() {
		double dato_arriba = arriba.dato;
		arriba = arriba.abajo;
		return dato_arriba;
	}

	public double resultado() {
		double a=0, b=0;
		char operacion;
		for (int i = 0; i < commando.length(); i++) {
			if (Character.isDigit(commando.charAt(i))) {									
				conversion_y_a_pila(i);															//Método creado al refactorizar.									
			}else {																												
				operacion = commando.charAt(i);														
				realizar_operacion(a,b,operacion);									//Método creado al refactorizar.
			}
		}
		double val = popPila();
		if (arriba != null) {
			throw new IllegalArgumentException();
		}
		return val;
	}
	
	void conversion_y_a_pila(int i) {																	
		double numero;
		int j;
		String temp = "";
		for (j = 0; (j < 100) && (Character.isDigit(commando.charAt(i)) || (commando.charAt(i) == '.')); j++, i++) {
			temp = temp + String.valueOf(commando.charAt(i));
		}
		numero = Double.parseDouble(temp);
		pushPila(numero);
}

	void realizar_operacion(double a, double b, char operacion) {
		if (operacion == '+')
			sumar(a, b);
		else if (operacion == '-')
			restar(a, b);
		else if (operacion == '*')
			multiplicar(a, b);
		else if (operacion == '/')
			dividir(a, b);
		else if (operacion == '^')
			potencia(a, b);
		else if (operacion == '%')
			resto(a, b);
		else if (operacion != ' ')
			throw new IllegalArgumentException();
	}
	
	//CONJUNTO DE OPERACIONES DISPONIBLES
		void sumar(double a, double b) {
			b = popPila();
			a = popPila();
			pushPila(a + b);
		}
		void restar(double a, double b) {
			b = popPila();
			a = popPila();
			pushPila(a - b);
		}
		void multiplicar(double a, double b) {
			b = popPila();
			a = popPila();
			pushPila(a * b);
		}
		void dividir(double a, double b) {
			b = popPila();
			a = popPila();
			pushPila(a / b);
		}
		void potencia(double a, double b) {
			b = popPila();
			a = popPila();
			pushPila(Math.pow(a, b));
		}
		void resto(double a, double b) {
			b = popPila();
			a = popPila();
			pushPila(a % b);
		}
}
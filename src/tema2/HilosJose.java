package tema2;

import java.util.Scanner;

/**
 * 
 * Clase para ejecutar hilos, el constructor recoge 2 parametros de tipo int, el
 * numero limite hasta el que quieres que se realicen los calculos(incluido). Y
 * el codigo, en el que se indica si se van a calcular impares "1", impares con
 * calculos extras "11", pares "2", pares con calculos extras "22".
 * 
 * 
 * @param numlimite
 * @param codigo
 * @author jose
 * @see Thread
 * 
 *
 *
 */
public class HilosJose extends Thread {

	private int numlimite;
	private int codigo;

	public HilosJose(int numlimite, int codigo) {
		this.numlimite = numlimite;
		this.codigo = codigo;

	}

	@Override

	/**
	 * Sobreescribimos run(). En funcion del codigo que se pasa por el constructor a
	 * la instancia de la clase hilos Ejecutara un tipo de conteo u otro.
	 */

	public void run() {
		int media = 0;
		int contador = 0;
		int sumador = 0;

		switch (codigo) {
		case 1:

			for (int i = 1; i <= numlimite; i++) {

				if (i % 2 != 0) {

					System.out.println("--Numero impar: " + i);

				}
			}

		case 2:
			for (int i = 1; i <= numlimite; i++) {

				if (i % 2 == 0) {
					System.out.println("--Numero par: " + i);
				}

			}

		case 11:
			for (int i = 1; i <= numlimite; i++) {
				if (i % 2 != 0) {
					sumador += i;
					contador += 1;
					media = sumador / contador;

					System.out.println("--Numero impar: " + i);

				}
			}
			System.out.println("Esto hace un total de: " + contador + " numeros impares");
			System.out.println("Y la suma de todos ellos es: " + sumador);
			System.out.println("Cuya media es " + media);
			break;

		case 22:

			for (int i = 1; i <= numlimite; i++) {
				if (i % 2 == 0) {
					sumador += i;
					contador += 1;
					media = sumador / contador;

					System.out.println("--Numero par: " + i);

				}
			}
			System.out.println("Esto hace un total de: " + contador + " numeros pares");
			System.out.println("Y la suma de todos ellos es: " + sumador);
			System.out.println("Cuya media es " + media);
			break;

		default:
			break;

		}

	}

	public static void main(String[] args) {

		// Solicitamos por consola la entrada del numero limite a calcular
		System.out.println("Elije el numero maximo hasta donde quieres calcular");
		Scanner entrada = new Scanner(System.in);
		int numlimite = entrada.nextInt();

		// Instanciamos la clase hilos con ejemplares que realizaran lo que queremos
		// En nuestro caso hemos incluido realizar las operaciones extras por lo que
		// ejemplares de clase creados utilizan el correspondiente codigo.

		HilosJose h1 = new HilosJose(numlimite, 11);
		// hilos h2 = new hilos(numlimite,1);
		// hilos h3 = new hilos(numlimite,2);
		HilosJose h4 = new HilosJose(numlimite, 22);

		// Iniciamos los hilos simultaneamente para ver como la salida se ejecuta
		// entremezclada porque los dos hilos trabajan en paralelo.

		h1.start();
		h4.start();
		
		System.out.println("El programa ha finalizado, gracias por su tiempo");
		entrada.close();

	}

}

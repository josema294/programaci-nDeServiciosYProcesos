package deposito_modeloPC;

import java.util.Scanner;

/**
 * 
 * Clase que ejecuta un hilo a la escucha de la entrada standar del usuario para poder realizar la accion de cerrar el programa si se introduce la combinacion adecuada de teclas.
 * @author jose
 *
 */
public class InputListener extends Thread {

	private boolean ejecucion = true;

	public boolean isEjecucion() {
		return ejecucion;
	}

	public InputListener() {}

	public void run() {


		while (ejecucion) {

			Scanner scan = new Scanner(System.in);

			String in = scan.next();

			if (in.equals("z") ) {
				ejecucion = false;
				scan.close();
				
				
				
			}


		}

	}

}



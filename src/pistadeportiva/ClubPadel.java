package pistadeportiva;

import java.util.concurrent.Semaphore;

/**
 * 
 * Clase ClubPadel representa clubes de padel, la clase regula el acceso a sus
 * pistas mediante un array de la clase semaforo Que concede sus permisos a los
 * cuartetos de jugadores que lo solicitan
 * 
 * @author jose
 *
 */

public class ClubPadel {

	private String nombre;
	private int numeroPistas;
	private int preciopista;
	private int recaudacion;
	private Semaphore semaforo[];

	/**
	 * Constructor de ClubPadel, se construye cada ejemplar indicando el nombre del
	 * club, el numero de pistas que tiene y el precio que cuestan las pistas.
	 * 
	 * @param nombre
	 * @param numeropPistas
	 * @param precioPista
	 */
	public ClubPadel(String nombre, int numeropPistas, int precioPista) {
		this.nombre = nombre;
		this.numeroPistas = numeropPistas;
		this.preciopista = precioPista;
		semaforo = new Semaphore[numeroPistas];

		// Array de semaforos, cada pista tendra en su indice un semaforo de 1 permisos
		// para cuartetos
		for (int i = 0; i < semaforo.length; i++) {

			semaforo[i] = new Semaphore(1);

		}

	}

	/**
	 * Getter del nombre del club
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;

	}

	/**
	 * Getter numero de pistas de lclub
	 * 
	 * @return numeroPistas
	 */
	public int getNumeroPistas() {
		return numeroPistas;
	}

	/**
	 * Getter del precio de la pista en el club
	 * 
	 * @return
	 */
	public int getPreciopista() {
		return preciopista;
	}

	/**
	 * Recibe una instancia de Cuarteto que es la encargada de realizar el pago
	 * mediante el metodo .pagoPista() y se incrementa el valor de la variable
	 * recaudacion del club
	 * 
	 * @param c
	 */
	public synchronized void cobroPista(Cuarteto c) {

		recaudacion += c.pagoPista(this);

	}

	/**
	 * Getter de la recaudacion que lleva acumulado el club de padel
	 * 
	 * @return
	 */
	public int getRecaudacion() {
		return recaudacion;
	}

	/**
	 * Metodo que se encarga de comprobar la disponibilidad de las pistas de padel y
	 * dar acceso a estas. Comprobacion inicial de numero de jugadores en el
	 * cuarteto sea el adecuado, si no indica y lanza excepcion para interrumpir el
	 * hilo.
	 * 
	 * Si Cuarteto == 4 itera un array de semaforos, cuando adquiere permiso se
	 * pistaAcceso se vuelve true, y con el metodo .pistaOcupada() guarda en el
	 * cuarteto el numero de pista que estan usando.
	 * 
	 * @param e
	 * @return pistaAcceso
	 * @throws InterruptedException
	 */

	public boolean accesoPista(Cuarteto e) throws InterruptedException {

		boolean pistaAcceso = false;

		if (!e.equipoCompleto()) {
			System.out.println("se neceista un cuarteto completo para jugar un partido");
			throw new InterruptedException();

		}

		for (int i = 0; i < semaforo.length; i++) {

			if (semaforo[i].tryAcquire(1)) {

				pistaAcceso = true;
				e.setPistaOcupada(i);
				return pistaAcceso;

			}

		}
		return pistaAcceso;			

	}

	/**
	 * 
	 * Libera el permiso otorgado por accesoPista, iterando el array de semaforos y
	 * cuando estemos en el correspondiente a la pista donde estaba el cuarteto
	 * getPistaocupada() indica fin de partido y libera el semaforo ocuipado por el
	 * hilo.
	 * 
	 * @param e
	 * @throws InterruptedException
	 */
	public void salirPista(Cuarteto e) throws InterruptedException {

		for (int i = 0; i < semaforo.length; i++) {

			if (i == e.getPistaOcupada()) {

				System.out.println(e.partidoTerminado());
				semaforo[i].release();
			}

		}

	}
}

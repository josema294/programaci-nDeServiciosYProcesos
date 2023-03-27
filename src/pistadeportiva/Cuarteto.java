package pistadeportiva;

import java.util.Random;

/**
 * 
 * Clase que representa un cuarteto de jugadores de padel
 * 
 * @author jose
 * 
 */
public class Cuarteto extends Thread {

	private int jugadores;
	private static int contadorId = 0;
	private int idcuarteto;
	private ClubPadel club;
	private int pistaOcupada;

	/**
	 * Constructor de la clase Cuarteto, toma un int con el numero de jugadores que
	 * formaran el cuarteto y alertara si este es diferente de 4, y por otro lado
	 * toma una instancia de la clase ClubPadel, en representacion del club de padel
	 * al que va ir el cuarteto a jugar. A cada instancia se le asigna un ID unico
	 * 
	 * @param jugadores
	 * @param club
	 */
	public Cuarteto(int jugadores, ClubPadel club) {

		this.jugadores = jugadores;
		this.idcuarteto = contadorId;
		contadorId++;
		this.club = club;

		if (!this.equipoCompleto()) {

			System.out.println("Este equipo no esta completo, no podras jugar un partido de padel");
		}
	}

	@Override
	public void run() {

		try {

			boolean game = true;

			while (game) {

				if (club.accesoPista(this)) {

					club.cobroPista(this);
					System.out.println(this.jugarPartido());
					club.salirPista(this);

					game = false;

				}
			}
		}

		catch (InterruptedException e) {
			// TODO: handle exception
		}

	}

	/**
	 * Getter del identificador del cuarteto
	 * 
	 * @return Idcuarteto
	 */
	public int getIdcuarteto() {
		return idcuarteto;
	}

	/**
	 * Comprobacion de que el equipo esta formado por 4 jugadores/
	 * 
	 * @return boolean
	 */
	public boolean equipoCompleto() {

		if (jugadores == 4) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Devuelve el entero que indica el numero de pista que esta ocupando este
	 * cuarteto.
	 * 
	 * @return int con el numero de pista
	 */
	public int getPistaOcupada() {
		return pistaOcupada;
	}

	/**
	 * Setter para guardar el valor de la pista que ocupa la instancia de Cuarteto
	 * 
	 * @param pistaOcupada - entero que indica el numero de la pista,
	 */
	public void setPistaOcupada(int pistaOcupada) {
		this.pistaOcupada = pistaOcupada;
	}

	/**
	 * Pago de la pista por el cuarteto, recibe como parametro el club donde se
	 * juega, del que se extrae el entero con el precio de la pista.
	 * 
	 * @param club
	 * @return precio - int que devuelve el valor por el pago de la pista
	 */
	public int pagoPista(ClubPadel club) {

		int precio = club.getPreciopista();
		System.out.println(
				String.format("El equipo de padel %s ha pagado %s por jugar al padel", this.idcuarteto, precio));

		return precio;

	}

	/**
	 * 
	 * Representacion del partido jugado por el cuarteto, genera un long aleatorio
	 * entre 0 y 5000 y duerme el hilo durante ese tiempo(duracion del partido),
	 *  
	 * @return marcharse - Devuelve una cadena de Strings indicando que el cuarteto
	 *         esta jugando su partido
	 * @throws InterruptedException
	 */
	public String jugarPartido() throws InterruptedException {

		Random rand = new Random();
		Thread.sleep(rand.nextLong(5000));
		String partido = String.format("Los 4 jugadores del cuarteto %s juegan su partido", this.getIdcuarteto());
		return partido;
	}

	/**
	 * Indica la finalizacion del partido,
	 * 
	 * @return marcharse - String indicando que el cuarteto deja el club de padel.
	 * @throws InterruptedException
	 */
	public String partidoTerminado() throws InterruptedException {

		String marcharse = String.format(
				"Los 4 jugadores del cuarteto %s terminan su partido, se toman una cerveza reconstituyente y se marchan",
				this.getIdcuarteto());

		return marcharse;
	}

}

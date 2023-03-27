package deposito_modeloPC;

/**
 * Clase que maneja la amyoria de la logica de la aplicacion, la clase deposito representa el deposito sobre el que se efectuan los llenados vaciados, y a su vez 
 * sirve de controlador del proceso.
 * 
 * @author jose
 *
 */
public class Deposito {

	private int capacidadMaxima;
	private double capacidad;
	private boolean cicloLlenado = true;
	//Para aumentar la velocidad podemos modificar el parametro sleeptime, sleeptime y el caudal de vaciado/llenado deben de ir acordes para mantener la coherencia 
	private int sleepTime = 1000;
	private int caudalEntrada =0;
	private int caudalSalida =0;
	private int numLlenados =0;
	
	
	
	/**
	 * Constructor de deposito, se indica la capacidad maxima de este, para este ejercicio dado que solo hay una capacidad posible he optado por ignorar el parametro
	 * y que el constructor siempre cree deposito de 1000l, pero se podria adaptar de forma sencilla a otras situaciones.
	 * @param capacidadMaxima
	 */
	public Deposito(int capacidadMaxima) {
		//this.capacidadMaxima = capacidadMaxima;
		this.capacidadMaxima=1000;
	}

	/**
	 * 
	 * @return int - Devuelve la capacidad maxima de la instancia de deposito
	 */
	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}
	
	/**
	 * @return double - Devuelve el caudal de entrada que se esta producindo en el deposito. Solo caudal de entrada.
	 */
	public double getCaudalEntrada() {
		
		return caudalEntrada*1000/sleepTime;
	}
	/**
	 * @return double - Devuelve el caudal de salida que se esta producindo en el deposito. Solo caudal de salida.
	 */
public double getCaudalSalida() {
		
		return caudalSalida*1000/sleepTime;
	}

/**
 * @return double - Devuelve el caudal neto que se esta producindo en el deposito. Entrada menos Salida.
 */
public double getCaudalNeto () {
	
	return caudalEntrada - caudalSalida;
}
	
	/**
	 * 
	 * @return String - Indica si el proceso se encuentra en un ciclo de llenado o de vaciado, mediante una cadena de caracteres.
	 */
	public String getCiclo () {
		
		if (cicloLlenado) return "Proceso de llenado";		
		else return "Proceso de vaciado";
		
	}	

	/**
	 * @return int - Devuelve entero indicando los ciclos de llenado que ha tenido el deposito
	 */
	public int getNumLlenados() {
		return numLlenados;
	}

	/**
	 * @return deouble - Devuelve la capacidad actual a la que se encuentra el deposito, lo lleno que esta. 
	 */
	public double getCapacidad() {
		return capacidad;
	}
	
	/**
	 * @param sleeptime - Fija el tiempo de dormido del hilo. Este parametro sirve para controlar cuan frecuentemente se actualiza todo el proceso.
	 * Un parametro de 1000 indicaria que cada 1000ms(1s) se realizan los calculos y la logica antes de suspenderse por esa cantidad.
	 * 
	 */
	public void setSleepTime (int sleeptime) {
		this.sleepTime = sleeptime;
		
	}
	
	

	/**
	 * Metodo sincronizado de llenado del deposito. Contiene la logica de llenado del deposito, recibe una cantidad de caudal por el hilo llenador que lo invoca.
	 * y segun el propiop estado del deposito actura en consecuencia.
	 * 
	 * @param caudal 
	 */
	public synchronized void llena(int caudal) {

		try {
			while (true) {

				if (cicloLlenado && this.getCapacidad() < 900) {

					capacidad += caudal;
					caudalEntrada = caudal;
					Thread.sleep(sleepTime);
				}
				
				else if (cicloLlenado && this.getCapacidad()>=900 && this.getCapacidad()<1000) {
					
					capacidad += caudal;
					caudalEntrada = caudal;
					Thread.sleep(sleepTime);
					notify();
					wait();
					
				}

				else if (cicloLlenado && this.getCapacidad() >= 1000) {
					
					caudalEntrada = 0;
					cicloLlenado = false;
					numLlenados = getNumLlenados() + 1;					
					notify();
					wait();
				}
				
				else if (!cicloLlenado && this.getCapacidad() >= -100 && this.getCapacidad() <=100) {
					
					capacidad += caudal/2;
					caudalEntrada = caudal/2;
					notify();
					wait();
					
				}
				
				

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Equivalente del metodo llena, en este caso apra proceso de vaciado
	 * 
	 * @param caudal
	 * @see llena
	 */
	public synchronized void vacia(int caudal) {

		try {
			while (true) {

				if (cicloLlenado && this.getCapacidad() > 900 && this.getCapacidad() <=1100) {

					capacidad -= caudal/2;
					caudalSalida = caudal/2;
					notify();
					wait();
				}
				
				else if (!cicloLlenado && this.getCapacidad() > 100 && this.getCapacidad() <= 1000) {
					
					capacidad -= caudal;
					caudalSalida = caudal;
					Thread.sleep(sleepTime);
					
				}

				else if (!cicloLlenado && this.getCapacidad() > 0 && this.getCapacidad() <=100) {

					capacidad -= caudal;
					caudalSalida = caudal;
					Thread.sleep(sleepTime);
					notify();
					wait();
				}
				else if (!cicloLlenado && this.getCapacidad() > -100 && this.getCapacidad()<=0) {
					
					caudalSalida = 0;
					cicloLlenado = true;
					notify();
					wait();
					
				}
				
				

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

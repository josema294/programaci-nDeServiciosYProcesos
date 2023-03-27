package deposito_modeloPC;

/**
 * @author jose
 * 
 * Clase pensada para instanciar un llenador del deposito principal, funcion ejecutar hilo independiente que llame a el metodo llena
 *
 */
public class Llenador extends Thread{
	
	private Deposito dep;
	
	/**
	 * @param trabajandoSobre - Se indica en el constructor la instancia del deposito sobre el que el hilo trabajara para que se efectue la comunicacion entre clases
	 */
	public Llenador(Deposito trabajandoSobre) {
		
		dep = trabajandoSobre;
	}
	
	
	
	public void run() {
		
		//Se debe indicar el caudal de llenado maximo del vaciador/llenador por la unidad de tiempo.
	
		dep.llena(10);
		
		
	}
	
	
	

}

package deposito_modeloPC;

/**
 * @author jose
 * 
 * Clase pensada para instanciar un vaciador del deposito principal, funcion ejecutar hilo independiente que llame a el metodo vacia
 *
 */
public class Vaciador extends Thread{
	
	
	private Deposito dep;
	
	/**
	 * @param trabajandoSobre - Se indica en el constructor la instancia del deposito sobre el que el hilo trabajara para que se efectue la comunicacion entre clases
	 */
	public Vaciador(Deposito trabajandoSobre) {
		
		dep = trabajandoSobre;
	}
	
	
	
	public void run() {
		
		dep.vacia(10);		
		
	}
	
	
	

}
